package com.hijackster99.core.gamestates;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import com.hijackster99.core.GameState;
import com.hijackster99.core.StateManager;
import com.hijackster99.core.components.Button;
import com.hijackster99.core.components.InputButton;
import com.hijackster99.core.input.InputHandler.InputEvent;
import com.hijackster99.core.render.Screen;

public class SettingsMenu extends GameState {

	private String loadMenu;
	private Button backButton;
	private InputButton[] inputs;
	
	public SettingsMenu(String name, String menu) {
		super(name);
		this.loadMenu = menu;
		backButton = new Button(new Rectangle(10, 10, 50, 50), new Point(0, 0)) {

			@Override
			public void render(Screen screen) {
				screen.setColor(Color.DARK_GRAY);
				screen.paintBrush(5, new Point(10, 10), new Point(50, 50));
				screen.paintBrush(5, new Point(10, 50), new Point(50, 10));
			}

			@Override
			public void click() {
				StateManager.INSTANCE.loadState(loadMenu);
			}
		};
		inputs = new InputButton[0];
	}

	@Override
	public void run() {

	}

	@Override
	public void render(Screen screen) {
		screen.setColor(Color.BLACK);
		screen.fillRect(0, 0, 2560, 1440);
		backButton.render(screen);
		for(Button butt : inputs) {
			butt.render(screen);
		}
	}

	@Override
	public void handleInput(InputEvent event) {
		
	}

}
