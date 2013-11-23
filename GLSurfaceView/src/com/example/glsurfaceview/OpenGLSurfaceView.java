package com.example.glsurfaceview;

import com.example.data.DataBase;
import android.content.Context;
import android.content.res.Resources.Theme;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class OpenGLSurfaceView extends GLSurfaceView{
	
	private SurfaceRenderer renderer;
	private SceneManager sceneManager;
	private Thread thread;
	public static Context c;

	//�X�g�b�v�t���O�̐ݒ�
	private static boolean stopflag;
	public static void GameStop(){stopflag = true;};
	public static void GameStart(){stopflag = false;};
	public static boolean IsGameStop(){return stopflag;};	
	
	private boolean isHalt = false;	

	public OpenGLSurfaceView(Context context) 
	{
		super(context);
		// TODO Auto-generated constructor stub
		
		c = context;
		
		// �����_���[�̐���
		renderer = new SurfaceRenderer( context );
		
		// �����_���[�̐ݒ�
		setRenderer( renderer );
		
		// BGM�̍쐬
		BGMSound.getInstance().loadBGM();
		
		//�f�[�^�x�[�X�̏����������Ȃǂ��s��
		DataBase.Init();
		DataBase.LoadData();		
		
		// �V�[���}�l�[�W���[�̐���
		sceneManager = SceneManager.getInstance();
		
		// �X���b�h��~�t���O
		isHalt = false;		
		
		thread = new Thread( new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				// �X�V�X���b�h	
				while( thread != null )
				{
					if( !isHalt )
					{
						Scene scene = sceneManager.GetScene();
						
						// NULL�̂Ƃ�
						if( scene!= null ) {		
							
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
		isHalt = false;
		//BGMSound.getInstance().replayAll();
	}
	  
	@Override
	public void onPause()
	{
		isHalt = true;
		thread.interrupt();
		//BGMSound.getInstance().stopAll();
	}	
}
