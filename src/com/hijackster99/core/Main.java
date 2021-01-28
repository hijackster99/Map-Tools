package com.hijackster99.core;

import java.io.PrintStream;

import com.hijackster99.core.input.InputHandler;
import com.hijackster99.core.render.LoadResources;

public class Main {
	
	public static void main(String[] args) {
		System.setOut(new PrintStream(System.out) {
			
			@Override
			public void println(String x) {
				StackTraceElement[] elements = Thread.currentThread().getStackTrace();
				StackTraceElement element = elements[Math.min(3, elements.length - 1)];
				String str = element.toString() + ": " + x;
				super.println(str);
			}
			
			@Override
			public void println(Object x) {
				println(x.toString());
			}
			
			@Override
			public void println(boolean x) {
				println(Boolean.toString(x));
			}

			@Override
			public void println(char x) {
				println(new char[] {x});
			}
			
			@Override
			public void println(char[] x) {
				println(new String(x));
			}
			
			@Override
			public void println(int x) {
				println(Integer.toString(x));
			}
			
			@Override
			public void println(double x) {
				println(Double.toString(x));
			}
			
			@Override
			public void println(float x) {
				println(Float.toString(x));
			}
			
			@Override
			public void println(long x) {
				println(Long.toString(x));
			}
			
		});
		System.setErr(new PrintStream(System.err) {
			
			@Override
			public void println(String x) {
				StackTraceElement[] elements = Thread.currentThread().getStackTrace();
				StackTraceElement element = elements[Math.min(3, elements.length - 1)];
				String str = element.toString() + ": " + x;
				super.println(str);
			}
			
			@Override
			public void println(Object x) {
				println(x.toString());
			}
			
			@Override
			public void println(boolean x) {
				println(Boolean.toString(x));
			}

			@Override
			public void println(char x) {
				println(new char[] {x});
			}
			
			@Override
			public void println(char[] x) {
				println(new String(x));
			}
			
			@Override
			public void println(int x) {
				println(Integer.toString(x));
			}
			
			@Override
			public void println(double x) {
				println(Double.toString(x));
			}
			
			@Override
			public void println(float x) {
				println(Float.toString(x));
			}
			
			@Override
			public void println(long x) {
				println(Long.toString(x));
			}
			
		});
		LoadResources.loadMaps();
		LoadResources.loadTiles();
		StateManager.create();
		InputHandler.INSTANCE = new InputHandler();
		MapTools.INSTANCE = new MapTools(Settings.getSettings());
		MapTools.INSTANCE.run();
	}
	
}
