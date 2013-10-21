package com.example.glsurfaceview;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;

public class SurfaceRenderer implements Renderer {
	
	private Context c;
	private float rate;
	public static int widthRate, heightRate;
	
	// �R���X�g���N�^
	public SurfaceRenderer( Context context )
	{
		c = context;
		
		widthRate = MainActivity.width / 100;
		heightRate = MainActivity.height / 100;
	}

	// �`��
	@Override
	public void onDrawFrame(GL10 gl) 
	{    
		gl.glClearColor(0.0f, 1.0f, 0.0f, 0.1f);
		//�@�\����ʂƃf�v�X�o�b�t�@�̃N���A
		gl.glClear( GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT );
    	
        // �����ɃV�[���̕`����Ă�
        SceneManager.Draw(gl);
	}
	
	// �T�[�t�F�[�X�̃T�C�Y���ύX���ꂽ�Ƃ�
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) 
	{
		// ��ʔ䗦
		rate = ( float )( width / height );
		
		// �r���[�|�[�g�̐ݒ�
		gl.glViewport( 0, 0, width, height );
		
		// �e�N�X�`�����g�p����
		gl.glEnable(GL10.GL_TEXTURE_2D);
	}

	// �T�[�t�F�C�X����
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) 
	{		
		// ���_�z���L���ɂ���
    	gl.glEnableClientState( GL10.GL_VERTEX_ARRAY );
    	// �e�N�X�`���o�b�t�@�̗L����
        gl.glEnableClientState( GL10.GL_TEXTURE_COORD_ARRAY );
		
		// �e�N�X�`�����g�p����
		gl.glEnable(GL10.GL_TEXTURE_2D);
		
		gl.glActiveTexture(GL10.GL_TEXTURE0);
        
        // �g�k�̐ݒ�
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
        
        // �u�����h�̎w��
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	Context GetContext()
	{
		return c;
	}
	
	public float getRate()
	{
		return rate;
	}
}
