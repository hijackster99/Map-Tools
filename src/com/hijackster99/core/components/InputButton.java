package com.hijackster99.core.components;

import java.awt.Point;
import java.awt.Rectangle;

import com.hijackster99.core.input.InputHandler.InputEvent;

public abstract class InputButton extends Button {

	private boolean hasFocus;
	
	public InputButton(int x, int y, int width, int height, Point location) {
		super(new Rectangle(x, y, width, height), location);
		hasFocus = false;
	}
	
	public abstract void handleInput(InputEvent event);

	public boolean hasFocus() {
		return hasFocus;
	}

	public void setFocus(boolean hasFocus) {
		this.hasFocus = hasFocus;
	}

}
