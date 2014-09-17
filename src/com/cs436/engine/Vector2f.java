package com.cs436.engine;

public class Vector2f {

	private float x;
	private float y;
	
	public Vector2f(float x, float y){
		this.setX(x);
		this.setY(y);
		
	}
	
	public String toString(){
		return "(" + x + " " + y + ")";
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
