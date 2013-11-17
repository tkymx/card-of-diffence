package com.example.glsurfaceview;

public class GoToTitleButton extends Button {

	// �R���X�g���N�^
	public GoToTitleButton()
	{
	}
	
	public static GoToTitleButton Create( float left, float top, float width, float height, int id )
	{
		GoToTitleButton b = new GoToTitleButton();
		
		b.Init(left, top, width, height, id);
		
		return b;
	}
	
	public void onUpdate()
	{
		super.Update();
		
		// �^�b�`����Ă���Ƃ�
		if( getTouch() )
		{
			// �^�C�g���֔��
			SceneManager.ChangeScene( SceneManager.titleKey );
		}
	}
}
