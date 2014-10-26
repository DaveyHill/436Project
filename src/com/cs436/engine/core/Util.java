package com.cs436.engine.core;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;

import com.cs436.engine.rendering.Vertex;

public class Util 
{
	public static FloatBuffer createFloatBuffer(int size)
	{
		return BufferUtils.createFloatBuffer(size);
	}
	
	public static IntBuffer createIntBuffer(int size)
	{
		return BufferUtils.createIntBuffer(size);
	}
	
	public static FloatBuffer createFlippedBuffer(Vertex[] vertices)
	{
		FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.SIZE );

		for(int i = 0; i < vertices.length ; i++ )
		{
			buffer.put(vertices[i].getPos().getX());
			buffer.put(vertices[i].getPos().getY());
			buffer.put(vertices[i].getPos().getZ());
			buffer.put(vertices[i].getTexCoord().getX());
			buffer.put(vertices[i].getTexCoord().getY());
			buffer.put(vertices[i].getNormal().getX());
			buffer.put(vertices[i].getNormal().getY());
			buffer.put(vertices[i].getNormal().getZ());
			
		}
		
		buffer.flip();
		
		return buffer;
	}
	
	public static FloatBuffer createFlippedBuffer(Matrix4f value)
	{
		FloatBuffer buffer = createFloatBuffer(4 * 4);
		
		for(int i = 0; i < 4 ; i++ )
			for(int j = 0; j < 4 ; j++ )
					buffer.put(value.get(i, j));
		
		buffer.flip();
		
		return buffer;
	}

	public static IntBuffer createFlippedBuffer(int... values) 
	{
		IntBuffer buffer = createIntBuffer(values.length);
		
		buffer.put(values);
		buffer.flip();
		
		return buffer;
	}

	public static String[] removeEmptyStrings(String[] tokens) 
	{
		ArrayList<String> result = new ArrayList<String>();
		
		for(int i = 0; i < tokens.length ; i++ )
		{
			if(!tokens[i].equals(""))
			{
				result.add(tokens[i]);
			}
		}
		String[] res = new String[result.size()];
		
		result.toArray(res);
		
		return res;
	}

	public static int[] toIntArray(ArrayList<Integer> indices) 
	{
		int[] result = new int[indices.size()];
		for(int i = 0; i < indices.size() ; i++ )
		{
			result[i] = indices.get(i).intValue();
		}
		
		return result;
	}
}
