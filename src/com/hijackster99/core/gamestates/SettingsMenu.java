package com.hijackster99.core.gamestates;

import java.util.List;

import com.hijackster99.core.GameState;
import com.hijackster99.core.input.InputHandler.InputEvent;
import com.hijackster99.core.render.Screen;

public class SettingsMenu extends GameState {

	private String loadMenu;
	
	public SettingsMenu(String name, String loadMenu) {
		super(name);
		this.loadMenu = loadMenu;
	}

	@Override
	public void run() {

	}

	@Override
	public void render(Screen mainScreen, List<Screen> subScreen) {

	}

	@Override
	public void handleInput(InputEvent event) {

	}

}
