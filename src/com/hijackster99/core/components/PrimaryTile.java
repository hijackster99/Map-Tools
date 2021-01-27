package com.hijackster99.core.components;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrimaryTile extends Tile {

	public static Map<String, PrimaryTile> primaryTiles = new HashMap<String, PrimaryTile>();
	
	private List<SecondaryTile> secondaryTiles;
	
	public PrimaryTile(String name, BufferedImage image) {
		super(name, image);
		secondaryTiles = new ArrayList<SecondaryTile>();
		primaryTiles.put(name, this);
	}
	
	public void addSecondaryTile(SecondaryTile tile) {
		secondaryTiles.add(tile);
	}

}
