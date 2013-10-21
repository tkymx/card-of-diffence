package com.example.glsurfaceview;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Common {
	
	public static FloatBuffer FloatToBuffer( float[] array )
	{
		FloatBuffer fb = ByteBuffer.allocateDirect( array.length * 4 ).order(
                ByteOrder.nativeOrder() ).asFloatBuffer();
    	
    	fb.put( array ).position(0);
    	
    	return fb;
	}
	
}
