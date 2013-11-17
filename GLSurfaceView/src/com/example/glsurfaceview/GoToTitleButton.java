package com.example.glsurfaceview;

public class GoToTitleButton extends Button {

	// コンストラクタ
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
		
		// タッチされているとき
		if( getTouch() )
		{
			// タイトルへ飛ぶ
			SceneManager.ChangeScene( SceneManager.titleKey );
		}
	}
}
