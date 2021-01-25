package com.hijackster99.core;

import java.util.ArrayList;
import java.util.List;

import com.hijackster99.core.input.InputHandler;
import com.hijackster99.core.render.Screen;

public class MapTools {

	public static MapTools INSTANCE;
	
	private Screen mainScreen;
	private List<Screen> subScreens;
	private boolean running = false;
	private Settings settings;
	private int framerate;
	private final int tickrate = 60;
	
	public MapTools(Settings settings) {
		this.settings = settings;
		framerate = settings.getFramerate();
		subScreens = new ArrayList<Screen>();
		if(settings.isFullscreen()) mainScreen = new Screen("Map Tools");
		else mainScreen = new Screen("Map Tools", settings.getX(), settings.getY(), settings.getWidth(), settings.getHeight(), true);
	}
	
	public void run() {
		running = true;
		double frameTime = 1000000000.0d/framerate;
		double tickTime = 1000000000.0d/tickrate;
		double prevFrameTime = System.nanoTime();
		double prevTickTime = System.nanoTime();
		GameState currentState = StateManager.INSTANCE.getCurrentState();
		while(running) {
			if(!mainScreen.isOpen()) stop();
			double currentTime = System.nanoTime();
			if(prevFrameTime + frameTime < currentTime) {
				prevFrameTime = currentTime;
				currentState.render(mainScreen, subScreens);
				mainScreen.render();
				for(Screen s : subScreens) {
					s.render();
				}
			}
			if(prevTickTime + tickTime < currentTime) {
				prevTickTime = currentTime;
				currentState.run();
				currentState.handleInput(InputHandler.INSTANCE.getEvent());
				if(StateManager.INSTANCE.hasStateChanged()) {
					currentState = StateManager.INSTANCE.getCurrentState();
				}
			}
		}
	}
	
	public void addScreen(Screen screen) {
		subScreens.add(screen);
	}
	
	public void closeScreen(Screen screen) {
		if(subScreens.contains(screen)) {
			screen.close();
			subScreens.remove(screen);
		}
	}
	
	public void stop() {
		running = false;
		if(!settings.isFullscreen()) {
			settings.setX(mainScreen.getX());
			settings.setY(mainScreen.getY());
			settings.setWidth(mainScreen.getWidth());
			settings.setHeight(mainScreen.getHeight());
		}
		settings.saveSettings();
		mainScreen.close();
	}
}
