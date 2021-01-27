package com.hijackster99.core.components;

import java.awt.image.BufferedImage;

public class Map {

	private String name;
	private BufferedImage image;
	
	public Map(String name, BufferedImage image) {
		this.name = name;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public BufferedImage getImage() {
		return image;
	}
	
}
