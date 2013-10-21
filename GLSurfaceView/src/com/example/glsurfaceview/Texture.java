package com.example.glsurfaceview;

import java.nio.FloatBuffer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Texture {
	
	private FloatBuffer texUV;
	private Bitmap texture;
	private int texBuffer[] = new int[1];
	
	// �R���X�g���N�^
	public Texture( int id, float[] UV )
	{
		boolean haveTextue = TextureManager.haveTexture(id);
		
		// �e�N�X�`���������Ă��Ȃ��Ƃ�
		if( haveTextue == false )
		{
			// �摜�̐���
			texture = BitmapFactory.decodeResource( OpenGLSurfaceView.c.getResources(), id );
			
			// �e�N�X�`����UV���W�̐���
			texUV = Common.FloatToBuffer( UV );
		}
		else
		{
			// �e�N�X�`���̎擾
			Texture t = TextureManager.GetTexture(id);
			
			// �摜�̎擾
			texture = t.GetBitmap();
			
			// UV�̎擾
			texUV = t.GetUV();
		}
	}
	
	// �R���X�g���N�^
	public Texture( int id )
	{
		// �f�t�H���gUV
		float UV[] = {
			-1.0f, -1.0f,
			-1.0f, 0.0f,
			0.0f, -1.0f,
			0.0f, 0.0f,
		};
		
		boolean haveTextue = TextureManager.haveTexture(id);
		
		// �e�N�X�`���������Ă��Ȃ��Ƃ�
		if( haveTextue == false )
		{
			// �摜�̐���
			texture = BitmapFactory.decodeResource( OpenGLSurfaceView.c.getResources(), id );
			
			// �e�N�X�`����UV���W�̐���
			texUV = Common.FloatToBuffer( UV );
		}
		else
		{
			// �e�N�X�`���̎擾
			Texture t = TextureManager.GetTexture(id);
			
			// �摜�̎擾
			texture = t.GetBitmap();
			
			// UV�̎擾
			texUV = t.GetUV();
		}
	}
    
    // UV���W�̎擾
    public FloatBuffer GetUV()
    {
    	return texUV;
    }
    
    // �摜�̎擾
    public Bitmap GetBitmap()
    {
    	return texture;
    }
    
    // �e�N�X�`���z��̎擾
    public int[] GetTextureBuffer()
    {
    	return texBuffer;
    }

}
