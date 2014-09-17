package com.cs436.engine;

import org.lwjgl.input.Keyboard;

public class Game {
	public Game(){
		
	}
	
	public void input(){
		//Test up key
		if(Input.getKeyDown(Keyboard.KEY_UP))
			System.out.println("Pressed up.");
		if(Input.getKeyUp(Keyboard.KEY_UP))
			System.out.println("Released up");
		
		//Test right mouse button
		if(Input.getMouseDown(1))
			System.out.println("Pressed right click at " + Input.getMousePosition().toString());
		if(Input.getMouseUp(1))
			System.out.println("Released right click");
	}
	
	public void update(){
		
	}
	
	public void render(){
		
	}
}
