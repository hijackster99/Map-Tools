package com.hijackster99.core.render;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class LoadResources {
	
	private static List<BufferedImage> tiles;
	private static List<BufferedImage> maps;
	private static BufferedImage map;
	

	public static void loadTiles() {
		tiles = new ArrayList<BufferedImage>();
		File file = new File(System.getProperty("user.dir") + "/bin/com/hijackster99/resources/maps");
		for(String fileName : file.list()) {
			if(fileName.endsWith(".png") || fileName.endsWith(".jpeg")) {
				try {
					BufferedImage image = ImageIO.read(file.listFiles(new FilenameFilter() {

						@Override
						public boolean accept(File dir, String name) {
							return fileName.equals(name);
						}
						
					})[0]);
					if(image != null) {
						tiles.add(image);
					}
				} catch (IOException e) {
					e.printStackTrace();
					System.err.println("Error: Failed to read file: " + fileName);
				}
			}
		}
	}
	
	public static void loadMaps() {
		maps = new ArrayList<BufferedImage>();
		File file = new File(System.getProperty("user.dir") + "/bin/com/hijackster99/resources/maps");
		for(String fileName : file.list()) {
			if(fileName.endsWith(".png") || fileName.endsWith(".jpeg")) {
				try {
					BufferedImage image = ImageIO.read(file.listFiles(new FilenameFilter() {

						@Override
						public boolean accept(File dir, String name) {
							return fileName.equals(name);
						}
						
					})[0]);
					if(image != null) {
						maps.add(image);
					}
				} catch (IOException e) {
					e.printStackTrace();
					System.err.println("Error: Failed to read file: " + fileName);
				}
			}
		}
	}

	public static List<BufferedImage> getTiles() {
		return tiles;
	}

	public static List<BufferedImage> getMaps() {
		return maps;
	}

	public static BufferedImage getMap() {
		return map;
	}

	public static void setMap(BufferedImage map) {
		LoadResources.map = map;
	}
	
}
