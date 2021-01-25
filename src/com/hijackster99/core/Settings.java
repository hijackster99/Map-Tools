package com.hijackster99.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Settings {

	private int framerate;
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean fullscreen;
	
	public Settings() {
		framerate = 144;
		x = 100;
		y = 100;
		width = 1080;
		height = 720;
		fullscreen = false;
	}
	
	public static Settings getSettings() {
		File file = new File(System.getProperty("user.dir") + "/bin/com/hijackster99/" + "settings.txt");
		if(file.exists()) {
			Settings settings = loadSettings(file);
			return settings;
		}
		return new Settings();
	}
	
	private static Settings loadSettings(File file) {
		try {
			FileInputStream stream = new FileInputStream(file);
			byte[] b = new byte[stream.available()];
			stream.read(b);
			stream.close();
			String str = new String(b);
			return createSettings(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Settings();
	}
	
	private static Settings createSettings(String str) {
		Settings s = new Settings();
		String[] settings = str.split("\n");
		for(String string : settings) {
			System.out.println(string);
			if(string.startsWith("framerate")) s.framerate = Integer.valueOf(string.substring(string.indexOf(' ') + 1).trim());
			else if(string.startsWith("x")) s.x = Integer.valueOf(string.substring(string.indexOf(' ')).trim());
			else if(string.startsWith("y")) s.y = Integer.valueOf(string.substring(string.indexOf(' ') + 1).trim());
			else if(string.startsWith("width")) s.width = Integer.valueOf(string.substring(string.indexOf(' ') + 1).trim());
			else if(string.startsWith("height")) s.height = Integer.valueOf(string.substring(string.indexOf(' ') + 1).trim());
			else if(string.startsWith("fullscreen")) s.fullscreen = Boolean.valueOf(string.substring(string.indexOf(' ') + 1).trim());
			System.out.println("return");
		}
		return s;
	}
	
	public void saveSettings() {
		File file = new File(System.getProperty("user.dir") + "/bin/com/hijackster99/" + "settings.txt");
		try {
			FileOutputStream stream = new FileOutputStream(file);
			stream.write(getBytes());
			stream.flush();
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private byte[] getBytes() {
		String str = "framerate: " + framerate + "\nx: " + x + "\ny: " + y + "\nwidth: " + width + "\nheight: " + height + "\nfullscreen: " + fullscreen;
		return str.getBytes();
	}

	public int getFramerate() {
		return framerate;
	}

	public void setFramerate(int framerate) {
		this.framerate = framerate;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isFullscreen() {
		return fullscreen;
	}

	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}
	
}
