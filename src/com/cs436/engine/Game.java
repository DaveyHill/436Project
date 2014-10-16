package com.cs436.engine;

import org.lwjgl.input.Keyboard;

public class Game 
{
	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	
	public Game()
	{
		mesh = new Mesh();
		shader = new Shader();
		transform = new Transform();
		
		
		Vertex[] data = new Vertex[] { new Vertex(new Vector3f(-1,-1,0)),
										new Vertex(new Vector3f(0,1,0)),
										new Vertex(new Vector3f(1,-1,0))
										};
		
		mesh.addVertices(data);
		
		shader.addVertexShader(ResourceLoader.loadShader("/basicVertex.vs"));
		shader.addFragmentShader(ResourceLoader.loadShader("/basicFragment.fs"));
		shader.compileShader();
		
		shader.addUniform("transform");
	}
	
	public void input()
	{
		//Test up key
		if(Input.GetKeyDown(Keyboard.KEY_UP))
			System.out.println("Pressed up.");
		if(Input.GetKeyUp(Keyboard.KEY_UP))
			System.out.println("Released up");
		
		//Test right mouse button
		if(Input.GetMouseDown(1))
			System.out.println("Pressed right click at " + Input.GetMousePosition().toString());
		if(Input.GetMouseUp(1))
			System.out.println("Released right click");
	}
	
	float temp = 0.0f;
	float tempAmount = 0.0f;
	
	public void update()
	{
		temp += Time.getDelta();
		
		tempAmount = (float)Math.sin(temp);
		
		transform.setScale(tempAmount, tempAmount, tempAmount);
		
		transform.setTranslation((float)Math.sin(temp),(float)Math.cos(temp), 0);
		
		transform.setRotation(0,0,(float)Math.sin(temp) * 180);
	}
	
	public void render()
	{
		shader.bind();
		shader.setUniform("transform", transform.getTransformation());
		mesh.draw();
	}
}
