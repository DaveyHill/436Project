package com.cs436.engine;

public class DLight 
{
	private BaseLight base;
	private Vector3f direction;
	
	public DLight( BaseLight base, Vector3f direction )
	{
		this.base = base;
		this.direction = direction.normalize();
	}

	public Vector3f getDirection() {
		return direction;
	}

	public void setDirection(Vector3f direction) {
		this.direction = direction.normalize();
	}

	public BaseLight getBase() {
		return base;
	}

	public void setBase(BaseLight base) {
		this.base = base;
	}
}
