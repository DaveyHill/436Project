package com.cs436.engine;

public class PhongShader extends Shader
{
	public static final PhongShader instance = new PhongShader();
	private static final int MAX_POINT_LIGHTS = 4;
	
	
	public static PhongShader getInstance()
	{
		return instance;
	}
	
	private static PointLight[] pointLights = new PointLight[] {};
	private static Vector3f ambientLight = new Vector3f(0.3f, 0.3f, 0.3f);
	private static DLight dLight = new DLight(new BaseLight(new Vector3f(1,1,1), 0), new Vector3f(0,0,0));
	
	private PhongShader()
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
		
		for(int i = 0; i < MAX_POINT_LIGHTS; i++ )
		{
			addUniform("pointLights[" + i + "].base.color");
			addUniform("pointLights[" + i + "].base.intensity");

			addUniform("pointLights[" + i + "].atten.constant");
			addUniform("pointLights[" + i + "].atten.linear");
			addUniform("pointLights[" + i + "].atten.exponent");

			addUniform("pointLights[" + i + "].position");
			addUniform("pointLights[" + i + "].range");
		}
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
		
		for(int i = 0; i < pointLights.length; i++ )
		{
			setUniform("pointLights[" + i + "]", pointLights[i]);
		}
		
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
	
	public void setUniform(String uniformName, PointLight pointLight)
	{
		setUniform(uniformName + ".base", pointLight.getBase());
		setUniformf(uniformName + ".atten.constant", pointLight.getAtten().getConstant());
		setUniformf(uniformName + ".atten.linear", pointLight.getAtten().getLinear());
		setUniformf(uniformName + ".atten.exponent", pointLight.getAtten().getExponent());
		setUniform(uniformName + ".position", pointLight.getPosition());
		setUniformf(uniformName + ".range", pointLight.getRange());
	}

	public static DLight getdLight() {
		return dLight;
	}

	public static void setdLight(DLight dLight) {
		PhongShader.dLight = dLight;
	}
	
	public static void setPointLight(PointLight[] pointLights)
	{
		if(pointLights.length > MAX_POINT_LIGHTS)
		{
			System.err.println("Error: you passed in too many point lights. Max is " + MAX_POINT_LIGHTS + " you passed in " + pointLights.length);
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		PhongShader.pointLights = pointLights;
	}
}
