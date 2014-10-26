package com.cs436.game;

import com.cs436.engine.core.Time;
import com.cs436.engine.core.Transform;
import com.cs436.engine.core.Vector2f;
import com.cs436.engine.core.Vector3f;
import com.cs436.engine.rendering.Attenuation;
import com.cs436.engine.rendering.BaseLight;
import com.cs436.engine.rendering.Camera;
import com.cs436.engine.rendering.DLight;
import com.cs436.engine.rendering.Material;
import com.cs436.engine.rendering.Mesh;
import com.cs436.engine.rendering.PhongShader;
import com.cs436.engine.rendering.PointLight;
import com.cs436.engine.rendering.RenderUtil;
import com.cs436.engine.rendering.Shader;
import com.cs436.engine.rendering.SpotLight;
import com.cs436.engine.rendering.Texture;
import com.cs436.engine.rendering.Vertex;
import com.cs436.engine.rendering.Window;

public class TestGame implements Game
{
	private Mesh mesh;
	private Shader shader;
	private Material material;
	private Transform transform;
	private Camera camera;
	
	PointLight pLight1 = new PointLight(new BaseLight(new Vector3f(1,0.5f,0), 0.8f), new Attenuation(0,0,1), new Vector3f(-2,0,5f), 6);
	PointLight pLight2= new PointLight(new BaseLight(new Vector3f(0,0.5f,1), 0.8f), new Attenuation(0,0,1), new Vector3f(2,0,7f), 6);
	
	SpotLight spotLight1 = new SpotLight( new PointLight(new BaseLight(new Vector3f(0,1f,1f), 0.8f), new Attenuation(0,0,0.1f), new Vector3f(-2,0,5f), 30), new Vector3f(1,1,1), 0.7f );
	
	
	public void init()
	{
		transform = new Transform();
		//ResourceLoader.loadMesh("box.obj"); //
		material = new Material( new Texture("test.png"), new Vector3f(1,1,1), 1, 8 );
		shader = PhongShader.getInstance();
		camera = new Camera();
		
		float fieldDepth = 10.0f;
		float fieldWidth = 10.0f;
		
		Vertex[] vertices = new Vertex[] { new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
										   new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
										   new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth ), new Vector2f(1.0f, 0.0f)),
										   new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};
		
		int indicies[] = { 0, 1, 2,
						   2, 1, 3};
		

//		Vertex[] vertices = new Vertex[] { new Vertex( new Vector3f(-1.0f, -1.0f, 0.5773f),	new Vector2f(0.0f, 0.0f)),
//							new Vertex( new Vector3f(0.0f, -1.0f, -1.15475f),		new Vector2f(0.5f, 0.0f)),
//							new Vertex( new Vector3f(1.0f, -1.0f, 0.5773f),		new Vector2f(1.0f, 0.0f)),
//							new Vertex( new Vector3f(0.0f, 1.0f, 0.0f),      new Vector2f(0.5f, 1.0f)) };
//		
//			int indices[] = { 0, 3, 1,
//							  1, 3, 2,
//							  2, 3, 0,
//							  1, 2, 0 };
//		
		mesh = new Mesh(vertices, indicies, true);
		

		Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000f );
		Transform.setCamera(camera);
		
		PhongShader.setAmbientLight(new Vector3f(0.3f, 0.3f, 0.3f));
		PhongShader.setdLight(new DLight(new BaseLight(new Vector3f(1,1,1), 0.3f), new Vector3f(1,1,1)));
		
		PhongShader.setPointLights(new PointLight[]{pLight1, pLight2});
		
		PhongShader.setSpotLights(new SpotLight[]{spotLight1});
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
	
	
	public void update()
	{
		temp += Time.getDelta();
		
		float sinTemp  = (float)Math.sin(temp);
		
		transform.setTranslation(0, -1, 5);
		
		//transform.setRotation(0, sinTemp *180, 0);
		
		pLight1.setPosition( new Vector3f(3,0,8.0f * (float)(Math.sin(temp) + 1.0/2.0) + 10));
		pLight2.setPosition( new Vector3f(7,0,8.0f * (float)(Math.sin(temp) + 1.0/2.0) + 10));
		
		//transform.setScale(0.7f * sinTemp, 0.7f * sinTemp, 0.7f * sinTemp);
		
		spotLight1.getPointLight().setPosition(camera.getPos());
		spotLight1.setDirection(camera.getForward());
	}
	
	public void render()
	{
		RenderUtil.setClearColor(Transform.getCamera().getPos().div(2048f).abs());
		shader.bind();
		shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
		mesh.draw();
	}
}