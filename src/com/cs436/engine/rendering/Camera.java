package com.cs436.engine.rendering;

import com.cs436.engine.core.Input;
import com.cs436.engine.core.Time;
import com.cs436.engine.core.Vector2f;
import com.cs436.engine.core.Vector3f;

public class Camera 
{
	public static final Vector3f yAxis = new Vector3f(0,1,0); 			//World up
	
	private Vector3f pos;
	private Vector3f forward;
	private Vector3f up;
	
	public Camera()
	{
		this(new Vector3f(0,0,0), new Vector3f(0,0,1), new Vector3f(0,1,0));
	}
	
	public Camera(Vector3f pos, Vector3f forward, Vector3f up )
	{
		this.setPos(pos);
		this.setForward(forward);
		this.setUp(up);
		
		up.normalize();
		forward.normalize();
	}
	
	boolean mouseLocked = false;
	Vector2f centerPosition = new Vector2f(Window.getWidth()/2, Window.getHeight()/2);
	
	public void input()
	{
		float sensitivity = 0.5f;
		float movAmt = (float)(10 * Time.getDelta());
		//float rotAmt = (float)(100 * Time.getDelta());
		
		if(Input.getKey(Input.KEY_ESCAPE))
		{
			Input.SetCursor(true);
			mouseLocked = false;
		}
		
		if(Input.getMouseDown(0))
		{
			Input.SetMousePosition(centerPosition);
			Input.SetCursor(false);
			mouseLocked = true;
		}
		
		if(Input.getKey(Input.KEY_W))
			move(getForward(), movAmt);
		if(Input.getKey(Input.KEY_S))
			move(getForward(), -movAmt);
		if(Input.getKey(Input.KEY_A))
			move(getLeft(), movAmt);
		if(Input.getKey(Input.KEY_D))
			move(getRight(), movAmt);
		
//		if(Input.GetKey(Input.KEY_UP))
//			rotateX(-rotAmt);
//		if(Input.GetKey(Input.KEY_DOWN))
//			rotateX(rotAmt);
//		if(Input.GetKey(Input.KEY_LEFT))
//			rotateX(-rotAmt);
//		if(Input.GetKey(Input.KEY_RIGHT))
//			rotateX(rotAmt);
		
		if(mouseLocked)
		{
			Vector2f deltaPos = Input.getMousePosition().sub(centerPosition);
			
			boolean rotY = deltaPos.getX() != 0;
			boolean rotX = deltaPos.getY() != 0;
			
			if(rotY)
				rotateY(deltaPos.getX() * sensitivity);
			if(rotX)
				rotateX(deltaPos.getY() * sensitivity);
			
			if(rotY || rotX)
				Input.SetMousePosition(new Vector2f(Window.getWidth()/2, Window.getHeight()/2));
		}
	}
	
	public void move(Vector3f dir, float amt)
	{
		pos = pos.add(dir.mul(amt));
	}
	
	public void rotateY(float angle)
	{
		Vector3f Haxis = yAxis.cross(forward);
		Haxis.normalize();
		
		forward.rotate(angle, yAxis);
		forward.normalize();
		
		up = forward.cross(Haxis);
		up.normalize();
	}
	public void rotateX(float angle)		//Tilting up and down
	{
		Vector3f Haxis = yAxis.cross(forward);
		Haxis.normalize();
		
		forward.rotate(angle, Haxis);
		forward.normalize();
		
		up = forward.cross(Haxis);
		up.normalize();
	}
	
	public Vector3f getLeft()
	{
		Vector3f left = forward.cross(up);
		left.normalize();
		return left;
	}
	
	public Vector3f getRight()
	{
		Vector3f right = up.cross(forward);
		right.normalize();
		return right;
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public Vector3f getForward() {
		return forward;
	}

	public void setForward(Vector3f forward) {
		this.forward = forward;
	}

	public Vector3f getUp() {
		return up;
	}

	public void setUp(Vector3f up) {
		this.up = up;
	}
}
