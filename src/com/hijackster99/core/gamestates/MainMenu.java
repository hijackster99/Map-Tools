package com.hijackster99.core.gamestates;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.List;

import com.hijackster99.core.GameState;
import com.hijackster99.core.StateManager;
import com.hijackster99.core.components.Button;
import com.hijackster99.core.input.InputHandler.InputEvent;
import com.hijackster99.core.render.LoadResources;
import com.hijackster99.core.render.Screen;

public class MainMenu extends GameState {
	
	Button rightArrow;
	Button leftArrow;
	Button map;
	Button settings;
	private int currentMap;
	
	public MainMenu(String name) {
		super(name);
		currentMap = 0;
		rightArrow = new Button(new Polygon(new int[] {0, 50, 0}, new int[] {0, 100, 200}, 3), new Point(1460, 620)) {

			@Override
			public void render(Screen mainScreen, List<Screen> subScreens) {
				mainScreen.setColor(Color.LIGHT_GRAY);
				mainScreen.fill(getShape());
				mainScreen.setColor(Color.DARK_GRAY);
				mainScreen.fillArea(new Point[] {new Point(10, 40), new Point(40, 100), new Point(10, 160)}, 1460, 620);
			}

			@Override
			public void click() {
				currentMap++;
				if(currentMap >= LoadResources.getMaps().size()) currentMap = 0;
			}
			
		};
		leftArrow = new Button(new Polygon(new int[] {50, 0, 50}, new int[] {0, 100, 200}, 3), new Point(1050, 620)) {

			@Override
			public void render(Screen mainScreen, List<Screen> subScreens) {
				mainScreen.setColor(Color.LIGHT_GRAY);
				mainScreen.fill(getShape());
				mainScreen.setColor(Color.DARK_GRAY);
				mainScreen.fillArea(new Point[] {new Point(40, 40), new Point(10, 100), new Point(40, 160)}, 1050, 620);
			}

			@Override
			public void click() {
				currentMap--;
				if(currentMap < 0) currentMap = LoadResources.getMaps().size() - 1;
			}
			
		};
		map = new Button(new Polygon(new int[] {0, 300, 0, 300}, new int[] {0, 0, 300, 300}, 4), new Point(450, 620)) {

			@Override
			public void render(Screen mainScreen, List<Screen> subScreens) {
				BufferedImage image = LoadResources.getMaps().get(currentMap);
				mainScreen.drawImage(image, 1130, 570, 1430, 870, 0, 0, image.getWidth(), image.getHeight(), null);
			}

			@Override
			public void click() {
				LoadResources.setMap(LoadResources.getMaps().get(currentMap));
				StateManager.INSTANCE.loadState("game");
			}
			
		};
		settings = new Button(new Polygon(new int[] {0, 25, 0, 25}, new int[] {0, 0, 25, 25}, 4), new Point(10, 10)) {

			@Override
			public void render(Screen mainScreen, List<Screen> subScreens) {
				mainScreen.setColor(Color.DARK_GRAY);
				mainScreen.paintBrush(5, new Point(10, 10), new Point(35, 10));
				mainScreen.paintBrush(5, new Point(10, 20), new Point(35, 20));
				mainScreen.paintBrush(5, new Point(10, 30), new Point(35, 30));
			}

			@Override
			public void click() {
				
			}
			
		};
	}

	@Override
	public void run() {
		
	}

	@Override
	public void render(Screen mainScreen, List<Screen> subScreens) {
		mainScreen.setColor(Color.BLACK);
		mainScreen.fillRect(0, 0, 2560, 1440);
		leftArrow.render(mainScreen, subScreens);
		rightArrow.render(mainScreen, subScreens);
		map.render(mainScreen, subScreens);
		settings.render(mainScreen, subScreens);
	}

	@Override
	public void handleInput(InputEvent event) {
		
	}

}
