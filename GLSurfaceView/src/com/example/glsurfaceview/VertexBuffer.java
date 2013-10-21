package com.example.glsurfaceview;

import java.nio.FloatBuffer;

public class VertexBuffer {

	private FloatBuffer vertexBuffer;
	private int vertexSize;
	
	public VertexBuffer( float[] vertex )
	{
		// 頂点バッファの生成
		vertexBuffer = Common.FloatToBuffer( vertex );
		
		// 頂点バッファのサイズ
		vertexSize = vertex.length / 3;
	}
    
    // 頂点バッファの取得
    public FloatBuffer GetVertexBuffer()
    {
    	return vertexBuffer;
    }
    
    // 頂点バッファのサイズの取得
    public int GetVertexBufferSize()
    {
    	return vertexSize;
    }
    
    // 頂点バッファの格納
    public void SetVertexBuffer( float[] vertex )
    {
    	// 頂点バッファの生成
		vertexBuffer = Common.FloatToBuffer( vertex );
    }
}
