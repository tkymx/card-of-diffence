package com.example.glsurfaceview;

import com.example.data.DataBase;
import com.example.scene.GameScene;

public class Evolution {
	private int evolutionNum;
	private static final int evolutionMax = 5;
	private static final int evolutinScore = 5000;
	private Button evolutionButton;
	private float[] main, sub;
	private int colorTime;
	private boolean isTouch;
	
	public Evolution()
	{
		int w = MainActivity.width;
		int h = MainActivity.height;
		
		colorTime = 0;
		
		isTouch = false;
		
		main = new float[]{ 1.0f, 1.0f, 1.0f, 1.0f };
		sub = new float[]{ 1.0f, 1.0f, 0.0f, 1.0f };
		
		evolutionNum = 0;
		evolutionButton = Button.Create(0, h / 10, w / 10, h / 20, R.drawable.image4);
	}
	
	public void evolutionCard()
	{
		if( evolutionNum > 0 )
		{
			evolutionNum--;
		}
	}
	
	public void Update()
	{
		Score score = DataBase.getPresentScore();
		int scoreNum = score.getScore();
		
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
			if( !isTouch )
			{
				// 一定時間たったとき
				if( ( colorTime % 60 ) == 0 )
				{
					if( ( colorTime % 120 ) == 0 )
					{
						evolutionButton.texture.SetColor(sub[0], sub[1], sub[2], sub[3]);
					}
					else
					{
						evolutionButton.texture.SetColor(main[0], main[1], main[2], main[3]);
					}
				}
			}
			else
			{
				evolutionButton.texture.SetColor(sub[0], sub[1], sub[2], sub[3]);
			}
			
			// タッチされたとき
			if( evolutionButton.IsTouch() )
			{
				isTouch = isTouch^true;
			}
			
			colorTime++;
		}
		else
		{
			evolutionButton.texture.SetColor(main[0], main[1], main[2], main[3]);
			colorTime = 0;
		}
	}
}
