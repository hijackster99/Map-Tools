package com.hijackster99.core;

import com.hijackster99.core.input.InputHandler.InputEvent;
import com.hijackster99.core.render.Screen;

public abstract class GameState {

	private String name;

	protected GameState(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public abstract void run();
	
	public abstract void render(Screen mainScreen);
	
	public abstract void handleInput(InputEvent event);
	
}
