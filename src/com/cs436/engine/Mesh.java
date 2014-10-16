package com.cs436.engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Mesh 
{

	private int vbo;
	private int size;
	
	public Mesh()
	{
		vbo = glGenBuffers();
		size = 0;
	}
	
	public void addVertices(Vertex[] vertices)
	{
		size = vertices.length * Vertex.SIZE;  //Total size of data at vbo
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		
		glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW);
	}
	
	public void draw()
	{
		glEnableVertexAttribArray(0);
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0); //Array 0, size of element, type, normalize?, total size, ??
		
		glDrawArrays(GL_TRIANGLES, 0, size);
		
		glDisableVertexAttribArray(0);
	}
}
