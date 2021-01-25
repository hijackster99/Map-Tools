package com.hijackster99.core;

import java.util.HashMap;
import java.util.Map;

import com.hijackster99.core.gamestates.MainMenu;
import com.hijackster99.core.gamestates.MainState;
import com.hijackster99.core.gamestates.SettingsMenu;

public class StateManager {

	public static StateManager INSTANCE;
	
	private Map<String, GameState> states;
	private String currentState;
	private boolean stateChange;
	
	public StateManager(GameState... states) {
		this.states = new HashMap<String, GameState>();
		currentState = "default";
		stateChange = true;
		for(GameState state : states) {
			this.states.put(state.getName(), state);
		}
	}
	
	public GameState getCurrentState() {
		stateChange = false;
		return states.get(currentState);
	}
	
	public boolean loadState(String newState) {
		if(states.containsKey(newState)) {
			currentState = newState;
			stateChange = true;
			return true;
		}
		return false;
	}
	
	public boolean hasStateChanged() {
		return stateChange;
	}
	
	public static void create() {
		GameState menu = new MainMenu("default");
		GameState settings = new SettingsMenu("settings");
		GameState mainState = new MainState("game");
		INSTANCE = new StateManager(menu, settings, mainState);
	}
}
