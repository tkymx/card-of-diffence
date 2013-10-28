package com.example.glsurfaceview;

public class Vector3 {
	
	float x, y, z;
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	
	public Vector3()
	{
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
	}
	
	public Vector3( float x, float y, float z )
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3( Vector3 vec )
	{
		x = vec.x;
		y = vec.y;
		z = vec.z;
	}
	
	public void Add( Vector3 vec )
	{
		x += vec.x;
		y += vec.y;
		z += vec.z;
	}
	
	public void Multiply( Vector3 vec )
	{
		x *= vec.x;
		y *= vec.y;
		z *= vec.z;
	}
	
	public void Divide( Vector3 vec )
	{
		float a, b, c;
		a = 1.0f / vec.x;
		b = 1.0f / vec.y;
		c = 1.0f / vec.z;
		
		x *= a;
		y *= b;
		z *= c;
	}
}
