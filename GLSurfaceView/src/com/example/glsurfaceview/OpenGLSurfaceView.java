package com.example.glsurfaceview;

import co.example.data.DataBase;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class OpenGLSurfaceView extends GLSurfaceView{
	
	private SurfaceRenderer renderer;
	private SceneManager sceneManager;
	private Thread thread;
	public static Context c;

	private static boolean stopflag;
	public static void GameStop(){stopflag = true;};
	public static void GameStart(){stopflag = false;};

	public OpenGLSurfaceView(Context context) 
	{
		super(context);
		// TODO Auto-generated constructor stub
		
		c = context;
		
		// �����_���[�̐���
		renderer = new SurfaceRenderer( context );
		
		// �����_���[�̐ݒ�
		setRenderer( renderer );
		
		// �V�[���}�l�[�W���[�̐���
		sceneManager = SceneManager.getInstance();
		
		//�X�g�b�v�t���O
		GameStart();
		
		//�f�[�^�x�[�X�̏����������Ȃǂ��s��
		DataBase.Init();
		DataBase.LoadData();
		
		thread = new Thread( new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				// �X�V�X���b�h
				while( thread != null )
				{
					if( !stopflag )
					{
						Scene scene = sceneManager.GetScene();
						
						// NULL�̂Ƃ�
						if( scene!= null ) {
						
							// �X�V����
							scene.Update();											
	
							//���͂̏�����
							Touch touch = Touch.getInstance();
							touch.UpdateEnd();
						}
						
						// ��O����
						try
						{
							// 60FPS�ōX�V
							Thread.sleep( 1000 / 60 );
						}
						catch(Exception e)
						{
						}
					}
				}	
			}
		} );
		
		thread.start();
	}

	// �^�b�`�C�x���g�̏���
	@Override
	public boolean onTouchEvent( MotionEvent event )
	{		
		Touch touch = Touch.getInstance();
		
		touch.Update(event);
		
		return true;
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
	}
	  
	@Override
	public void onPause()
	{
		super.onPause();
	}
}
