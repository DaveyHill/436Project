package com.cs436.engine;

public class Matrix4f {

	private float[][] m;
	
	public Matrix4f(){
		setM(new float[4][4]);
	}

	public float[][] getM() {
		return m;
	}

	public void setM(float[][] m) {
		this.m = m;
	}
	
	public float get(int x, int y){
		return m[x][y];
	}
	
	public void set(int x, int y, float value){
		m[x][y] = value;
	}
	
	public void intiIdentity(){
		for(int j = 0; j < 4; j++){
			for(int k = 0; k < 4; k++){
				if(j != k){
					m[j][k] = 0;
				} else {
					m[j][k] = 1;
				}
			}
		}
	}
	
	public Matrix4f mul(Matrix4f r){
		Matrix4f result = new Matrix4f();
		
		for(int j = 0; j < 4; j++){
			for(int k = 0; k < 4; k++){
				r.set(j, k, m[j][0] * r.get(0, k) +
						    m[j][1] * r.get(1, k) +
						    m[j][2] * r.get(2, k) +
						    m[j][4] * r.get(3, k));
			}
		}
		
		return result;
	}
}
