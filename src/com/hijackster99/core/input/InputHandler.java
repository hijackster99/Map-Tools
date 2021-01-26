package com.hijackster99.core.input;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener {
	
	public static InputHandler INSTANCE;
	
	private InputEvent event;
	
	public InputHandler() {
		event = new InputEvent();
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		this.event.mousePressed.put(event.getButton(), event);
		this.event.mouseReleased.put(event.getButton(), event);
	}

	@Override
	public void mouseEntered(MouseEvent event) {}

	@Override
	public void mouseExited(MouseEvent event) {}

	@Override
	public void mousePressed(MouseEvent event) {
		this.event.mousePressed.put(event.getButton(), event);
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		this.event.mouseReleased.put(event.getButton(), event);
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
		this.event.keysPressed.put(event.getKeyCode(), event);
	}

	@Override
	public void keyReleased(KeyEvent event) {
		this.event.keysReleased.put(event.getKeyCode(), event);
	}

	@Override
	public void keyTyped(KeyEvent event) {
		this.event.keysPressed.put(event.getKeyCode(), event);
		this.event.keysReleased.put(event.getKeyCode(), event);
	}
	
	public InputEvent getEvent() {
		InputEvent eventCopy = event.copy();
		event.reset();
		return eventCopy;
	}
	
	public static class InputEvent {
		
		private Map<Integer, KeyEvent> keysPressed;
		private Map<Integer, KeyEvent> keysReleased;
		private Map<Integer, MouseEvent> mousePressed;
		private Map<Integer, MouseEvent> mouseReleased;
		private Point mouseLocation;
		
		public InputEvent() {
			keysPressed = new HashMap<Integer, KeyEvent>();
			keysReleased = new HashMap<Integer, KeyEvent>();
			mousePressed = new HashMap<Integer, MouseEvent>();
			mouseReleased = new HashMap<Integer, MouseEvent>();
		}

		public Map<Integer, KeyEvent> getKeysPressed() {
			return keysPressed;
		}

		public Map<Integer, KeyEvent> getKeysReleased() {
			return keysReleased;
		}

		public Map<Integer, MouseEvent> getMousePressed() {
			return mousePressed;
		}

		public Map<Integer, MouseEvent> getMouseReleased() {
			return mouseReleased;
		}

		public Point getMouseLocation() {
			return mouseLocation;
		}
		
		public InputEvent copy() {
			InputEvent copy = new InputEvent();
			for(Entry<Integer, KeyEvent> event : keysPressed.entrySet()) {
				copy.keysPressed.put(event.getKey(), event.getValue());
			}
			for(Entry<Integer, KeyEvent> event : keysReleased.entrySet()) {
				copy.keysReleased.put(event.getKey(), event.getValue());
			}
			for(Entry<Integer, MouseEvent> event : mousePressed.entrySet()) {
				copy.mousePressed.put(event.getKey(), event.getValue());
			}
			for(Entry<Integer, MouseEvent> event : mouseReleased.entrySet()) {
				copy.mouseReleased.put(event.getKey(), event.getValue());
			}
			copy.mouseLocation = mouseLocation;
			return copy;
		}
		
		public int size() {
			return keysPressed.size() + keysReleased.size() + mousePressed.size() + mouseReleased.size();
		}
		
		public void reset() {
			keysPressed.clear();
			keysReleased.clear();
			mousePressed.clear();
			mouseReleased.clear();
		}
		
	}

}
