package com.hijackster99.core.render;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.hijackster99.core.components.Map;
import com.hijackster99.core.components.PrimaryTile;
import com.hijackster99.core.components.SecondaryTile;
import com.hijackster99.core.components.SubTile;

public class LoadResources {
	
	private static List<PrimaryTile> primaryTiles;
	private static List<SecondaryTile> secondaryTiles;
	private static List<SubTile> subTiles;
	private static List<Map> maps;
	private static Map map;
	

	public static void loadTiles() {
		primaryTiles = new ArrayList<PrimaryTile>();
		secondaryTiles = new ArrayList<SecondaryTile>();
		subTiles = new ArrayList<SubTile>();
		File filePrimary = new File(System.getProperty("user.dir") + "/bin/com/hijackster99/resources/tiles/primary");
		File fileSecondary = new File(System.getProperty("user.dir") + "/bin/com/hijackster99/resources/maps/secondary");
		File fileSub = new File(System.getProperty("user.dir") + "/bin/com/hijackster99/resources/maps/subtiles");
		for(String fileName : filePrimary.list()) {
			if(fileName.endsWith(".png") || fileName.endsWith(".jpg")) {
				try {
					String name = fileName.substring(0, fileName.indexOf('.'));
					BufferedImage image = ImageIO.read(filePrimary.listFiles(new FilenameFilter() {

						@Override
						public boolean accept(File dir, String name) {
							return fileName.equals(name);
						}
						
					})[0]);
					if(image != null) {
						primaryTiles.add(new PrimaryTile(name, image));
					}
				} catch (IOException e) {
					e.printStackTrace();
					System.err.println("Error: Failed to read file: " + fileName);
				}
			}
		}
		for(String fileName : fileSecondary.list()) {
			if(fileName.endsWith(".png") || fileName.endsWith(".jpg")) {
				try {
					String name = fileName.substring(0, fileName.indexOf('.'));
					BufferedImage image = ImageIO.read(fileSecondary.listFiles(new FilenameFilter() {
	
						@Override
						public boolean accept(File dir, String name) {
							return fileName.equals(name);
						}
						
					})[0]);
					if(image != null) {
						secondaryTiles.add(new SecondaryTile(name, image));
					}
				} catch (IOException e) {
					e.printStackTrace();
					System.err.println("Error: Failed to read file: " + fileName);
				}
			}
		}
		for(String fileName : fileSub.list()) {
			if(fileName.endsWith(".png") || fileName.endsWith(".jpg")) {
				try {
					String name = fileName.substring(0, fileName.indexOf('.'));
					BufferedImage image = ImageIO.read(fileSub.listFiles(new FilenameFilter() {
		
						@Override
						public boolean accept(File dir, String name) {
							return fileName.equals(name);
						}
						
					})[0]);
					if(image != null) {
						subTiles.add(new SubTile(name, image));
					}
				} catch (IOException e) {
					e.printStackTrace();
					System.err.println("Error: Failed to read file: " + fileName);
				}
			}
		}
	}
	
	public static void loadMaps() {
		maps = new ArrayList<Map>();
		File file = new File(System.getProperty("user.dir") + "/bin/com/hijackster99/resources/smallmaps");
		for(String fileName : file.list()) {
			if(fileName.endsWith(".png") || fileName.endsWith(".jpg")) {
				try {
					String name = fileName.substring(0, fileName.indexOf('.'));
					BufferedImage image = ImageIO.read(file.listFiles(new FilenameFilter() {

						@Override
						public boolean accept(File dir, String n) {
							return fileName.equals(n);
						}
						
					})[0]);
					if(image != null) {
						maps.add(new Map(name, image));
					}
				} catch (IOException e) {
					e.printStackTrace();
					System.err.println("Error: Failed to read file: " + fileName);
				}
			}
		}
	}
	
	private static Map loadMap(String name) {
		File file = new File(System.getProperty("user.dir") + "/bin/com/hijackster99/resources/maps");
		try {
			BufferedImage image = ImageIO.read(file.listFiles(new FilenameFilter() {

				@Override
				public boolean accept(File dir, String n) {
					return (n.endsWith(".png") || n.endsWith(".jpg")) && n.startsWith(name);
				}
				
			})[0]);
			return new Map(name, image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Map> getMaps() {
		return maps;
	}

	public static Map getMap() {
		return map;
	}

	public static void setMap(Map map) {
		LoadResources.map = loadMap(map.getName());
	}

	public static List<PrimaryTile> getPrimaryTiles() {
		return primaryTiles;
	}

	public static List<SecondaryTile> getSecondaryTiles() {
		return secondaryTiles;
	}

	public static List<SubTile> getSubTiles() {
		return subTiles;
	}
	
}
