package com.example.glsurfaceview;

import com.example.data.DataBase;
import com.example.user.Charactor;

public class Evolution {
	private int evolutionNum;
	private static final int evolutionMax = 5;
	private static final int evolutinScore = 500;
	private static final int colorChangeTime = 50;
	private Button evolutionButton;
	private int colorTime;
	private boolean isEvolution;
	private Score score;
	
	// コンストラクタ
	public Evolution()
	{
		int w = MainActivity.width;
		int h = MainActivity.height;
		
		colorTime = 0;
		
		isEvolution = false;
		
		evolutionNum = 0;
		evolutionButton = Button.Create(0, h / 6, w / 9, h / 15, R.drawable.evolutionbutton);
		evolutionButton.texture.SetColor(0.2f, 0.2f, 0.2f, 1.0f);
	}
	
	// 更新
	public void Update()
	{
		score = DataBase.getPresentScore();
		int scoreNum = score.getScore();
		scoreNum += 100;
		
		if( ( scoreNum % evolutinScore ) == 0 )
		{
			// 進化できる最大数でないとき
			if( evolutionNum <= evolutionMax )
			{
				evolutionNum++;
			}
		}
		
		// 進化できるとき
		if( evolutionNum > 0 )
		{
			// タッチされていないとき
			if( !isEvolution )
			{
				// 一定時間たったとき
				if( ( colorTime % colorChangeTime ) == 0 )
				{
					if( ( colorTime % ( colorChangeTime * 2 ) ) == 0 )
					{
						evolutionButton.texture.SetColor(1.0f, 1.0f, 0.0f, 1.0f);
					}
					else
					{
						evolutionButton.texture.SetColor(1.0f, 1.0f, 1.0f, 1.0f);
					}
				}
			}
			else
			{
				evolutionButton.texture.SetColor(1.0f, 1.0f, 0.0f, 1.0f);
			}
			
			// タッチされたとき
			if( evolutionButton.IsTouch() )
			{
				isEvolution = isEvolution^true;
			}
			
			colorTime++;
		}
		else
		{
			evolutionButton.texture.SetColor(0.2f, 0.2f, 0.2f, 1.0f);
			colorTime = 0;
		}
	}
	
	// 進化を設定
	public void SetEvolution( Charactor charactor )
	{
		//charactor.ChangePram(1.2f, 1.1f, 0);
		evolutionNum--;
	}
	
	// 進化できるかのフラグ
	public boolean IsEvolution()
	{
		return isEvolution;
	}
}
