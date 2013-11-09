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
	private float texColor[] = new float[4];
	private Bitmap texture;
	private int texBuffer[] = new int[1];
	private static BitmapFactory.Options options = new BitmapFactory.Options();
	private boolean isBind;
	
	// �R���X�g���N�^
	public Texture( int id, float[] UV )
	{
		boolean haveTextue = TextureManager.haveTexture(id);
		
		// �e�N�X�`���������Ă��Ȃ��Ƃ�
		if( haveTextue == false )
		{			
			options.inScaled = false;
			isBind = false;
			
			float color[] = { 1.0f, 1.0f, 1.0f, 1.0f };
			
			// �摜�̐���
			texture = BitmapFactory.decodeResource( OpenGLSurfaceView.c.getResources(), id, options );
			
			// �e�N�X�`����UV���W�̐���
			texUV = Common.FloatToBuffer( UV );
			
			// �F�̐ݒ�
			texColor = color;
			
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
			
			// �F�̐ݒ�
			texColor = t.GetColor();
		}
	}
	
	// �R���X�g���N�^
	public Texture( int id )
	{
		// �f�t�H���gUV
		float UV[] = {
			0.0f, 1.0f,
			0.0f, 0.0f,
			1.0f, 1.0f,
			1.0f, 0.0f,
		};
		
		float color[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		
		boolean haveTextue = TextureManager.haveTexture(id);
		
		// �e�N�X�`���������Ă��Ȃ��Ƃ�
		if( haveTextue == false )
		{
			options.inScaled = false;
			
			// �摜�̐���
			texture = BitmapFactory.decodeResource( OpenGLSurfaceView.c.getResources(), id, options );
			
			// �e�N�X�`����UV���W�̐���
			texUV = Common.FloatToBuffer( UV );
			
			// �F�̐ݒ�
			texColor = color;
			
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
			
			// �F�̐ݒ�
			texColor = t.GetColor();
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
        
        // �f�t�H���gUV
 		float UV[] = {
 			1.0f, 1.0f,
 			1.0f, 0.0f,
 			0.0f, 1.0f,
 			0.0f, 0.0f,
 		};
 		
 		float color[] = { 1.0f, 1.0f, 1.0f, 1.0f };
 		
 		// �e�N�X�`����UV���W�̐���
 		texUV = Common.FloatToBuffer( UV );
 		
 		// �F�̐ݒ�
 		texColor = color;
	}
	
	// UV���W�̃Z�b�g
	public void SetUV( float[] UV )
	{
		texUV = Common.FloatToBuffer(UV);
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

    // �o�C���h�󋵎擾
    public boolean GetBind()
    {
    	return isBind;
    }
    
    // �o�C���h��L���ɂ���
    public void SetBind()
    {
    	isBind = true;
    }
    
    // �F�̎擾
    public float[] GetColor()
    {
    	return texColor;
    }
    
    // �F�̐ݒ�
    public void SetColor( float color[] )
    {
    	texColor = color;
    }
    public void SetColor( float r,float g,float b,float a )
    {
    	texColor[0] = r;
    	texColor[1] = g;
    	texColor[2] = b;
    	texColor[3] = a;
    }
}
