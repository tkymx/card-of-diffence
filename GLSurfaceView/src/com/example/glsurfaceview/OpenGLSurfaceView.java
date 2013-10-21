package com.example.glsurfaceview;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class OpenGLSurfaceView extends GLSurfaceView{
	
	private SurfaceRenderer renderer;
	private SceneManager sceneManager;
	private Thread thread;
	public static Context c;

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
		sceneManager = new SceneManager();
		
		thread = new Thread( new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				// �X�V�X���b�h
				while( thread != null )
				{
					Scene scene = sceneManager.GetScene();
					
					// NULL�̂Ƃ�
					if( scene!= null ) {
						// �X�V����
						scene.Update();
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
}
