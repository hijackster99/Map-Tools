package com.hijackster99.core.components;

import java.awt.Point;
import java.awt.Shape;
import com.hijackster99.core.render.Screen;

public abstract class Button {

	private Shape shape;
	private Point location;
	
	public Button(Shape shape, Point location) {
		this.shape = shape;
		this.location = location;
	}
	
	public abstract void render(Screen mainScreen);
	
	public boolean isHovered(Point mouseLocation) {
		Point scaledPoint = new Point(mouseLocation.x - location.x, mouseLocation.y - location.y);
		System.out.println(scaledPoint);
		return getShape().contains(scaledPoint);
	}
	
	public abstract void click();

	public Shape getShape() {
		return shape;
	}
}
