package com.example.glsurfaceview;

import java.nio.FloatBuffer;

public class VertexBuffer {

	private FloatBuffer vertexBuffer;
	private int vertexSize;
	
	public VertexBuffer( float[] vertex )
	{
		// ���_�o�b�t�@�̐���
		vertexBuffer = Common.FloatToBuffer( vertex );
		
		// ���_�o�b�t�@�̃T�C�Y
		vertexSize = vertex.length / 3;
	}
    
    // ���_�o�b�t�@�̎擾
    public FloatBuffer GetVertexBuffer()
    {
    	return vertexBuffer;
    }
    
    // ���_�o�b�t�@�̃T�C�Y�̎擾
    public int GetVertexBufferSize()
    {
    	return vertexSize;
    }
    
    // ���_�o�b�t�@�̊i�[
    public void SetVertexBuffer( float[] vertex )
    {
    	// ���_�o�b�t�@�̐���
		vertexBuffer = Common.FloatToBuffer( vertex );
    }
}
