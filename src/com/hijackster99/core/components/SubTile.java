package com.hijackster99.core.components;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class SubTile extends Tile {

	public static Map<String, SubTile> subTiles = new HashMap<String, SubTile>();
	
	public SubTile(String name, BufferedImage image) {
		super(name, image);
		subTiles.put(name, this);
	}

}
