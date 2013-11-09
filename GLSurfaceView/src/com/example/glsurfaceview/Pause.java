package com.example.glsurfaceview;

public class Pause {
	
	private Button pauseButton;
	private boolean onPause;

	// �R���X�g���N�^
	public Pause()
	{
		pauseButton = Button.Create(0, 0, 100, 100, R.drawable.image4);
		onPause =  false;
	}
	
	// �X�V����
	public void Update()
	{
		// �{�^�����^�b�`����Ă���Ƃ�
		if( pauseButton.isTouch )
		{
			onPause = true;
		}
		// �b��I�ȏ���
		else if( onPause )
		{
			if( Touch.getInstance().IsTouch() )
			{
				onPause = false;
			}
		}
	}
	
	// �|�[�Y�����ǂ����̎擾
	public boolean getOnPause()
	{
		return onPause;
	}
}
