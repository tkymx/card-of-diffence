package com.example.glsurfaceview;

import com.example.glsurfaceview.Const.SpriteType;

public class Pause {
	
	private Button pauseButton;
	//private Button backToGame;
	private GoToTitleButton goToTitle;
	private Sprite baseImage;
	private boolean onPause;

	// コンストラクタ
	public Pause()
	{
		int x = MainActivity.width / 3;
		int y = MainActivity.height / 3;

		// 遷移ボタンの生成
		goToTitle = GoToTitleButton.Create(x, y, x, y, R.drawable.back_title);
		goToTitle.texture.SetColor(0, 0, 0, 0);
		
		x = MainActivity.width / 10;
		y = MainActivity.height / 10;
		
		// ポーズボタンの生成
		pauseButton = Button.Create(x*9, y*2, x, y, R.drawable.pause_base);
		
		// 下地の生成
		baseImage = Sprite.Create(0, 0, MainActivity.width, MainActivity.height, R.drawable.pause_base, SpriteType.TYPE_TEXT.getValue());
		baseImage.texture.SetColor(0, 0, 0, 0);
		
		onPause =  false;
	}
	
	// 更新処理
	public void Update()
	{
		// ボタンをタッチされているとき
		if( pauseButton.isTouch )
		{
			goToTitle.texture.SetColor(0, 0, 0, 1);
			baseImage.texture.SetColor(0.5f, 0.5f, 0.5f, 0.5f);
			
			onPause = true;
			
			//処理を止める
			OpenGLSurfaceView.GameStop();
			
		}
		// 暫定的な処理
		else if( onPause )
		{			
			goToTitle.onUpdate();
			
			if( Touch.getInstance().IsTouch() )
			{
				goToTitle.texture.SetColor(0, 0, 0, 0);
				baseImage.texture.SetColor(0.2f, 0.2f, 0.2f, 0);
				
				onPause = false;

				//処理を開始する
				OpenGLSurfaceView.GameStart();
			}
		}
	}
	
	// ポーズ中かどうかの取得
	public boolean getOnPause()
	{
		return onPause;
	}
}
