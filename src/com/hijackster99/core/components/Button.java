package com.hijackster99.core.components;

import java.awt.Point;
import java.awt.Polygon;
import java.util.List;

import com.hijackster99.core.render.Screen;

public abstract class Button {

	private Polygon shape;
	private Point mouseLocation;
	
	public Button(Polygon shape, Point location) {
		this.shape = shape;
		this.getShape().translate((int) location.getX(), (int) location.getY());
	}
	
	public abstract void render(Screen mainScreen, List<Screen> subScreens);
	
	public void update(Point mouseLocation) {
		this.mouseLocation = mouseLocation;
	}
	
	public boolean isHovered() {
		return getShape().contains(mouseLocation);
	}
	
	public abstract void click();

	public Polygon getShape() {
		return shape;
	}
}
