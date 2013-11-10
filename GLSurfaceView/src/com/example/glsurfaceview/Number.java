package com.example.glsurfaceview;

public class Number {
	
	private static Number instance = new Number();
	public Texture texture;
	public float[][] UV;
	public int id = R.drawable.number003;
	private float width = 0.1f;
	
	// �R���X�g���N�^
	private Number()
	{
		UV = new float[10][8];
		texture = new Texture( id );
		
		// 0�`9�܂ł̒l�𐶐�
		for( int i = 0; i < 10; i++ )
		{
			UV[i][0] = ( float )i * width;
			UV[i][1] = 0.0f;
			UV[i][2] = ( float )i * width;
			UV[i][3] = 1.0f;
			UV[i][4] = ( float )i * width + width;
			UV[i][5] = 0.0f;
			UV[i][6] = ( float )i * width + width;
			UV[i][7] = 1.0f;
		}
	}
	
	// �C���X�^���X�̎擾
	public static Number getInstance()
	{
		return instance;
	}

	// �w�肵�������̃e�N�X�`��UV�擾
	public float[] getNumber( int number )
	{
		if( ( number > 9 ) || ( number < 0 ) )
		{
			number = 0;
		}
		
		return UV[number];
	}
	
	public Texture getTexture()
	{
		return texture;
	}
	
	public int getTextureID()
	{
		return id;
	}
	
	public void Resume()
	{
		texture.Resume();
	}
}
