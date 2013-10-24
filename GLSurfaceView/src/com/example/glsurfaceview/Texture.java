package com.example.glsurfaceview;

import java.nio.FloatBuffer;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class Texture {
	
	private FloatBuffer texUV;
	private Bitmap texture;
	private int texBuffer[] = new int[1];
	private static BitmapFactory.Options options = new BitmapFactory.Options();
	
	// �R���X�g���N�^
	public Texture( int id, float[] UV )
	{
		boolean haveTextue = TextureManager.haveTexture(id);
		
		// �e�N�X�`���������Ă��Ȃ��Ƃ�
		if( haveTextue == false )
		{			
			options.inScaled = false;
			
			// �摜�̐���
			texture = BitmapFactory.decodeResource( OpenGLSurfaceView.c.getResources(), id, options );
			
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
			1.0f, 1.0f,
			1.0f, 0.0f,
			0.0f, 1.0f,
			0.0f, 0.0f,
		};
		
		boolean haveTextue = TextureManager.haveTexture(id);
		
		// �e�N�X�`���������Ă��Ȃ��Ƃ�
		if( haveTextue == false )
		{
			options.inScaled = false;
			
			// �摜�̐���
			texture = BitmapFactory.decodeResource( OpenGLSurfaceView.c.getResources(), id, options );
			
			// �e�N�X�`����UV���W�̐���
			texUV = Common.FloatToBuffer( UV );
			
			// �e�N�X�`�������X�g�ɒǉ�
			TextureManager.AddTexture(id, this);
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
	
	public Texture( int width, int height, String text )
	{
		texture = Bitmap.createBitmap(256, 256, Config.ARGB_8888);
		
		Canvas canvas = new Canvas( texture );
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Style.FILL);
        canvas.drawColor(0);
        canvas.drawText(text, 0, 15, paint); 
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
