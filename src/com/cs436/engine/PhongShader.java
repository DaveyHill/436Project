package com.cs436.engine;

public class PhongShader extends Shader
{
	public static final PhongShader instance = new PhongShader();
	
	public static PhongShader getInstance()
	{
		return instance;
	}
	
	private static Vector3f ambientLight = new Vector3f(0.3f, 0.3f, 0.3f);
	private static DLight dLight = new DLight(new BaseLight(new Vector3f(1,1,1), 0), new Vector3f(0,0,0));
	
	public PhongShader()
	{
		super();
		
		addVertexShader(ResourceLoader.loadShader("phongVertex.vs"));
		addFragmentShader(ResourceLoader.loadShader("phongFragment.fs"));
		compileShader();
		
		addUniform("transform");
		addUniform("transformProjected");
		addUniform("baseColor");
		addUniform("ambientLight");
		
		addUniform("dLight.base.color");
		addUniform("dLight.base.intensity");
		addUniform("dLight.direction");
		
		addUniform("specularIntensity");
		addUniform("specularExponent");
		addUniform("eyePos");
	}
	
	public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material)
	{
		if(material.getTexture() != null)
			material.getTexture().bind();
		else
			RenderUtil.unbindTextures();
		
		setUniform("transform", worldMatrix);
		setUniform("transformProjected", projectedMatrix);
		setUniform("baseColor", material.getColor());
		setUniform("ambientLight", ambientLight);
		setUniform("dLight", dLight);
		
		setUniformf("specularIntensity", material.getSpecularIntensity());
		setUniformf("specularExponent", material.getSpecularExponent());
		setUniform("eyePos", Transform.getCamera().getPos());
		
	}

	public static Vector3f getAmbientLight() {
		return ambientLight;
	}

	public static void setAmbientLight(Vector3f ambientLight) {
		PhongShader.ambientLight = ambientLight;
	}
	
	public void setUniform(String uniformName, BaseLight base)
	{
		setUniform(uniformName + ".color", base.getColor());
		setUniformf(uniformName + ".intensity", base.getIntensity());
	}
	
	public void setUniform(String uniformName, DLight dLight)
	{
		setUniform(uniformName + ".base", dLight.getBase());
		setUniform(uniformName + ".direction", dLight.getDirection());
	}

	public static DLight getdLight() {
		return dLight;
	}

	public static void setdLight(DLight dLight) {
		PhongShader.dLight = dLight;
	}
}
