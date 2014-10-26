package com.cs436.engine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

import com.cs436.engine.core.Vector3f;


public class RenderUtil 
{

	public static void clearScreen()
	{
		glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );
	}
	
	public static void setClearColor(Vector3f color)
	{
		glClearColor(color.getX(), color.getY(), color.getZ(), 1.0f);
	}
	
	public static void setTextures(boolean enabled)
	{
		if(enabled)
			glEnable(GL_TEXTURE_2D);
		else
			glDisable(GL_TEXTURE_2D);
	}
	
	public static void initGraphics()
	{
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		glFrontFace(GL_CW);
		glCullFace(GL_BACK);		// Don't draw back faces
		glEnable(GL_CULL_FACE);		// Hide faces of objects that aren't seen by the "camera"
		glEnable(GL_DEPTH_TEST);    //Important for drawing more than one thing (tells order which order things should be drawn in
		
		glEnable(GL_DEPTH_CLAMP);
		
		glEnable(GL_TEXTURE_2D);
		//glEnable(GL_FRAMEBUFFER_SRGB); //Free exponential correction (used in texturing)
	}
	
	public static String getOpenGLVersion(){
		return glGetString(GL_VERSION);
	}

	public static void unbindTextures() 
	{
		glBindTexture(GL_TEXTURE_2D, 0);
	}
}