package com.hijackster99.core.input;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener {
	
	public static InputHandler INSTANCE;
	
	private InputEvent event;
	
	public InputHandler() {
		event = new InputEvent();
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		this.event.mousePressed.add(event);
		this.event.mouseReleased.add(event);
	}

	@Override
	public void mouseEntered(MouseEvent event) {}

	@Override
	public void mouseExited(MouseEvent event) {}

	@Override
	public void mousePressed(MouseEvent event) {
		this.event.mousePressed.add(event);
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		this.event.mouseReleased.add(event);
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		this.event.mouseLocation = event.getLocationOnScreen();
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		this.event.mouseLocation = event.getLocationOnScreen();
	}

	@Override
	public void keyPressed(KeyEvent event) {
		this.event.keysPressed.add(event);
	}

	@Override
	public void keyReleased(KeyEvent event) {
		this.event.keysReleased.add(event);
	}

	@Override
	public void keyTyped(KeyEvent event) {
		this.event.keysPressed.add(event);
		this.event.keysReleased.add(event);
	}
	
	public InputEvent getEvent() {
		InputEvent eventCopy = event.copy();
		event.reset();
		return eventCopy;
	}
	
	public static class InputEvent {
		
		private List<KeyEvent> keysPressed;
		private List<KeyEvent> keysReleased;
		private List<MouseEvent> mousePressed;
		private List<MouseEvent> mouseReleased;
		private Point mouseLocation;
		
		public InputEvent() {
			keysPressed = new ArrayList<KeyEvent>();
			keysReleased = new ArrayList<KeyEvent>();
			mousePressed = new ArrayList<MouseEvent>();
			mouseReleased = new ArrayList<MouseEvent>();
		}

		public List<KeyEvent> getKeysPressed() {
			return keysPressed;
		}

		public List<KeyEvent> getKeysReleased() {
			return keysReleased;
		}

		public List<MouseEvent> getMousePressed() {
			return mousePressed;
		}

		public List<MouseEvent> getMouseReleased() {
			return mouseReleased;
		}

		public Point getMouseLocation() {
			return mouseLocation;
		}
		
		public InputEvent copy() {
			InputEvent copy = new InputEvent();
			for(KeyEvent event : keysPressed) {
				copy.keysPressed.add(event);
			}
			for(KeyEvent event : keysReleased) {
				copy.keysReleased.add(event);
			}
			for(MouseEvent event : mousePressed) {
				copy.mousePressed.add(event);
			}
			for(MouseEvent event : mouseReleased) {
				copy.mouseReleased.add(event);
			}
			copy.mouseLocation = mouseLocation;
			return copy;
		}
		
		public void reset() {
			keysPressed.clear();
			keysReleased.clear();
			mousePressed.clear();
			mouseReleased.clear();
		}
		
	}

}
