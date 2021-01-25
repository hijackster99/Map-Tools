package com.hijackster99.core.render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.hijackster99.core.input.InputHandler;

public class Screen {
	
	private JFrame frame;
	private BufferedImage image;
	private Graphics2D graphics;
	private boolean open;

	public Screen(String title, int x, int y, int width, int height, boolean resizable) {
		frame = new JFrame(title) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 909459771540860477L;

			@Override
			public void paint(Graphics g) {
				if(g != null)
					g.drawImage(image, 8, 31, getWidth(), getHeight(), this);
			}
			
		};
		frame.setLayout(new GridLayout());
		frame.setPreferredSize(new Dimension(width, height));
		frame.setLocation(x, y);
		frame.setResizable(resizable);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addKeyListener(InputHandler.INSTANCE);
		frame.addMouseListener(InputHandler.INSTANCE);
		frame.addMouseMotionListener(InputHandler.INSTANCE);
		open = true;
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				frame.dispose();
				open = false;
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				
			}
			
		});
		image = new BufferedImage(2560, 1440, BufferedImage.TYPE_INT_ARGB);
		graphics = image.createGraphics();
	}
	
	public Screen(String title) {
		frame = new JFrame(title) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -7382370877337842467L;

			@Override
			public void paint(Graphics g) {
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
			
		};
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addKeyListener(InputHandler.INSTANCE);
		frame.addMouseListener(InputHandler.INSTANCE);
		frame.addMouseMotionListener(InputHandler.INSTANCE);
		open = true;
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				frame.dispose();
				open = false;
			}

			@Override
			public void windowClosing(WindowEvent e) {
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				
			}
			
		});
		image = new BufferedImage(2560, 1440, BufferedImage.TYPE_INT_ARGB);
	}
	
	public void render() {
		frame.paint(frame.getGraphics());
	}
	
	public void close() {
		if(open) {
			open = false;
			frame.dispose();
		}
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public int getX() {
		return frame.getX();
	}
	
	public int getY() {
		return frame.getY();
	}
	
	public int getWidth() {
		return frame.getWidth();
	}
	
	public int getHeight() {
		return frame.getHeight();
	}
	
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2) {
		return graphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, frame);
	}
	
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor) {
		return graphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, frame);
	}
	
	public boolean drawImage(Image img, int x, int y, int width, int height) {
		return graphics.drawImage(img, x, y, width, height, frame);
	}
	
	public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor) {
		return graphics.drawImage(img, x, y, width, height, bgcolor, frame);
	}
	
	public boolean drawImage(Image img, int x, int y) {
		return graphics.drawImage(img, x, y, frame);
	}
	
	public boolean drawImage(Image img, int x, int y, Color bgcolor) {
		return graphics.drawImage(img, x, y, bgcolor, frame);
	}
	
	public void fillRect(int x, int y, int width, int height) {
		graphics.fillRect(x, y, width, height);
	}
	
	public void fillArea(Point[] points) {
		int[] x = new int[points.length];
		int[] y = new int[points.length];
		for(int i = 0; i < points.length; i++) {
			x[i] = (int) points[i].getX();
			y[i] = (int) points[i].getY();
		}
		graphics.fill(new Polygon(x, y, points.length));
	}
	
	public void fillArea(Point[] points, int xOffset, int yOffset) {
		int[] x = new int[points.length];
		int[] y = new int[points.length];
		for(int i = 0; i < points.length; i++) {
			x[i] = (int) points[i].getX() + xOffset;
			y[i] = (int) points[i].getY() + yOffset;
		}
		graphics.fill(new Polygon(x, y, points.length));
	}
	
	public void fill(Shape shape) {
		graphics.fill(shape);
	}
	
	public void paintBrush(int width, Point p1, Point p2) {
		for(Point p : getAllPointsBetween(p1, p2)) {
			graphics.fillOval(p.x - width/2, p.y - width/2, width, width);
		}
	}
	
	private List<Point> getAllPointsBetween(Point p1, Point p2) {
		List<Point> points = new ArrayList<Point>();
		double slope = (p2.y - p1.y)/((double)(p2.x - p1.x));
		for(int i = p1.x; i <= p2.x; i++) {
			points.add(new Point(i, (int)(i * slope) + p1.y));
		}
		return points;
	}
	
	public void setColor(Color c) {
		graphics.setColor(c);
	}
	
}
