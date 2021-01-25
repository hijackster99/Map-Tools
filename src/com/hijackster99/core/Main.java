package com.hijackster99.core;

import com.hijackster99.core.input.InputHandler;
import com.hijackster99.core.render.LoadResources;

public class Main {
	
	public static void main(String[] args) {
		LoadResources.loadMaps();
		LoadResources.loadTiles();
		StateManager.create();
		InputHandler.INSTANCE = new InputHandler();
		MapTools.INSTANCE = new MapTools(Settings.getSettings());
		MapTools.INSTANCE.run();
	}
	
}
