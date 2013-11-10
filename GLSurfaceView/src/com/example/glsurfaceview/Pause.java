package com.example.glsurfaceview;

import com.example.glsurfaceview.Const.SpriteType;

public class Pause {
	
	private Button pauseButton;
	//private Button backToGame;
	private GoToTitleButton goToTitle;
	private Sprite baseImage;
	private boolean onPause;

	// �R���X�g���N�^
	public Pause()
	{
		int x = MainActivity.width / 3;
		int y = MainActivity.height / 3;

		// �J�ڃ{�^���̐���
		goToTitle = GoToTitleButton.Create(x, y, x, y, R.drawable.back_title);
		goToTitle.texture.SetColor(0, 0, 0, 0);
		
		x = MainActivity.width / 10;
		y = MainActivity.height / 10;
		
		// �|�[�Y�{�^���̐���
		pauseButton = Button.Create(x*9, y*2, x, y, R.drawable.pause_base);
		
		// ���n�̐���
		baseImage = Sprite.Create(0, 0, MainActivity.width, MainActivity.height, R.drawable.pause_base, SpriteType.TYPE_TEXT.getValue());
		baseImage.texture.SetColor(0, 0, 0, 0);
		
		onPause =  false;
	}
	
	// �X�V����
	public void Update()
	{
		// �{�^�����^�b�`����Ă���Ƃ�
		if( pauseButton.isTouch )
		{
			goToTitle.texture.SetColor(0, 0, 0, 1);
			baseImage.texture.SetColor(0.5f, 0.5f, 0.5f, 0.5f);
			
			onPause = true;
			
			//�������~�߂�
			OpenGLSurfaceView.GameStop();
			
		}
		// �b��I�ȏ���
		else if( onPause )
		{			
			goToTitle.onUpdate();
			
			if( Touch.getInstance().IsTouch() )
			{
				goToTitle.texture.SetColor(0, 0, 0, 0);
				baseImage.texture.SetColor(0.2f, 0.2f, 0.2f, 0);
				
				onPause = false;

				//�������J�n����
				OpenGLSurfaceView.GameStart();
			}
		}
	}
	
	// �|�[�Y�����ǂ����̎擾
	public boolean getOnPause()
	{
		return onPause;
	}
}
