package com.cs436.engine.core;

public class Vector3f {

	private float x, y, z;
	
	public Vector3f(float x, float y, float z){
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}
	
	public float dot(Vector3f r){
		return x * r.getX() + y * r.getY() + z * r.getZ();
	}
	
	public Vector3f normalize(){
		float length = length();
		
		return new Vector3f( x/length, y/length, z/length);
	}
	
	public Vector3f rotate(float angle, Vector3f axis)
	{
		float sinHalfAngle = (float)Math.sin(Math.toRadians(angle/2));
		float cosHalfAngle = (float)Math.cos(Math.toRadians(angle/2));
		
		float rX = axis.getX() * sinHalfAngle;
		float rY = axis.getY() * sinHalfAngle;
		float rZ = axis.getZ() * sinHalfAngle;
		float rW = cosHalfAngle;
		
		Quaternion rotation = new Quaternion(rX,rY,rZ,rW);
		Quaternion conjugate = rotation.conjugate();
		
		Quaternion w = rotation.mul(this).mul(conjugate);
		
		x = w.getX();
		y = w.getY();
		z = w.getZ();
		
		return this;
	}
	
	public float length(){
		return (float)Math.sqrt(x*x + y*y + z*z);
	}
	
	public Vector2f rotate( float angle ){
		double rad = Math.toRadians(angle);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);
		
		return new Vector2f((float)(x * cos - y * sin),(float)(x * sin + y * cos));
	}
	
	public Vector3f add(Vector3f r){
		return new Vector3f(x + r.getX(), y + r.getY(), z + r.getZ());
	}
	
	public Vector3f add(float r){
		return new Vector3f(x + r, y + r, z + r);
	}
	
	public Vector3f sub(Vector3f r){
		return new Vector3f(x - r.getX(), y - r.getY(), z - r.getZ());
	}
	
	public Vector3f sub(float r){
		return new Vector3f(x - r, y - r, z - r);
	}
	
	public Vector3f mul(Vector3f r){
		return new Vector3f(x * r.getX(), y * r.getY(), z * r.getZ());
	}
	
	public Vector3f mul(float r){
		return new Vector3f(x * r, y * r, z * r);
	}
	
	public Vector3f div(Vector3f r){
		return new Vector3f(x / r.getX(), y / r.getY(), z / r.getZ());
	}
	
	public Vector3f div(float r){
		return new Vector3f(x / r, y / r, z / r);
	}
	
	public Vector3f cross(Vector3f r){
		float x_ =  y * r.getZ() - z * r.getY();
		float y_ =  z * r.getX() - x * r.getZ();
		float z_ =  x * r.getY() - y * r.getX();
		
		return new Vector3f(x_, y_, z_);
	}
	
	public Vector3f abs()
	{
		return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}
	
}