package com.example.glsurfaceview;

public class Pause {
	
	private Button pauseButton;
	private boolean onPause;

	// コンストラクタ
	public Pause()
	{
		pauseButton = Button.Create(0, 0, 100, 100, R.drawable.image4);
		onPause =  false;
	}
	
	// 更新処理
	public void Update()
	{
		// ボタンをタッチされているとき
		if( pauseButton.isTouch )
		{
			onPause = true;
		}
		// 暫定的な処理
		else if( onPause )
		{
			if( Touch.getInstance().IsTouch() )
			{
				onPause = false;
			}
		}
	}
	
	// ポーズ中かどうかの取得
	public boolean getOnPause()
	{
		return onPause;
	}
}
