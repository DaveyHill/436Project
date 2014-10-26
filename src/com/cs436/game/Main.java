package com.cs436.game;

import com.cs436.engine.core.CoreEngine;

public class Main 
{

	public static void main( String[] args )
	{
		CoreEngine coreEngine = new CoreEngine(800, 600, 60, new TestGame());
		coreEngine.createWindow("3D game engine");
		coreEngine.start();
	}
}
