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

	private static List<Map> maps;
	private static Map map;
	

	public static void loadTiles() {
		File filePrimary = new File(System.getProperty("user.dir") + "/bin/com/hijackster99/resources/tiles/primary");
		File fileSecondary = new File(System.getProperty("user.dir") + "/bin/com/hijackster99/resources/tiles/secondary");
		File fileSub = new File(System.getProperty("user.dir") + "/bin/com/hijackster99/resources/tiles/subtiles");
		for(String fileName : filePrimary.list()) {
			if(fileName.endsWith(".png") || fileName.endsWith(".jpg")) {
				try {
					String name = fileName.substring(0, fileName.indexOf('.'));
					BufferedImage image = ImageIO.read(filePrimary.listFiles(new FilenameFilter() {

						@Override
						public boolean accept(File dir, String n) {
							return fileName.equals(n);
						}
						
					})[0]);
					if(image != null) {
						PrimaryTile tile = new PrimaryTile(name, image);
						PrimaryTile.primaryTiles.put(name, tile);
						File[] files = fileSecondary.listFiles(new FilenameFilter() {
							
							@Override
							public boolean accept(File dir, String n) {
								return n.startsWith(name);
							}
							
						});
						for(File f : files) {
							String fName = f.getName();
							if(fName.endsWith(".png") || fName.endsWith(".jpg")) {
								String n = fName.substring(0, fName.indexOf('.'));
								BufferedImage img = ImageIO.read(f);
								if(img != null) {
									tile.addSecondaryTile(new SecondaryTile(n, img));
								}
							}
						}
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
						public boolean accept(File dir, String n) {
							return fileName.equals(n);
						}
						
					})[0]);
					if(image != null) {
						SubTile.subTiles.put(name, new SubTile(name, image));
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
	
}
