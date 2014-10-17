package com.cs436.engine;

import org.lwjgl.input.Keyboard;

public class Game 
{
	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	private Camera camera;
	
	public Game()
	{
		mesh = ResourceLoader.loadMesh("untitled.obj"); //new Mesh();
		shader = new Shader();
		camera = new Camera();
		
		transform = new Transform();
		transform.setCamera(camera);
		transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000f );
		
		
//		Vertex[] vertices = new Vertex[] { new Vertex(new Vector3f(-1,-1,0)),
//										new Vertex(new Vector3f(0,1,0)),
//										new Vertex(new Vector3f(1,-1,0)),
//										new Vertex(new Vector3f(0,-1,1))};
//		
//		int[] indices = new int[] 	{0,1,3,
//									 3,1,2,
//									 2,1,0,
//									 0,2,3};
		
//		mesh.addVertices(vertices, indices);
		
		shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vs"));
		shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fs"));
		shader.compileShader();
		
		shader.addUniform("transform");
	}
	
	public void input()
	{
		camera.input();
		
//		//Test up key
//		if(Input.GetKeyDown(Keyboard.KEY_UP))
//			System.out.println("Pressed up.");
//		if(Input.GetKeyUp(Keyboard.KEY_UP))
//			System.out.println("Released up");
//		
//		//Test right mouse button
//		if(Input.GetMouseDown(1))
//			System.out.println("Pressed right click at " + Input.GetMousePosition().toString());
//		if(Input.GetMouseUp(1))
//			System.out.println("Released right click");
	}
	
	float temp = 0.0f;
	float tempAmount = 0.0f;
	
	public void update()
	{
		temp += Time.getDelta();
		
		tempAmount = (float)Math.sin(temp);
		
		//transform.setScale(0.7f * tempAmount, 0.7f * tempAmount, 0.7f * tempAmount);
		
		//transform.setTranslation((float)Math.sin(temp),(float)Math.cos(temp), 5);
		
		transform.setRotation((float)Math.abs(tempAmount) * 180,0,0);
	}
	
	public void render()
	{
		shader.bind();
		shader.setUniform("transform", transform.getProjectedTransformation());
		mesh.draw();
	}
}
