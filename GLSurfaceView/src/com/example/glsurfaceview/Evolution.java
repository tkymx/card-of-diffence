package com.example.glsurfaceview;

import com.example.data.DataBase;
import com.example.glsurfaceview.Const.SpriteType;
import com.example.user.Charactor;

public class Evolution {
	private int evolutionNum;
	private static final int evolutionMax = 5;
	private static final int evolutinScore = 300;
	private static final int colorChangeTime = 50;
	private Button evolutionButton;
	private int colorTime;
	private boolean isEvolution;
	private Score score;
	private Sprite base, direction;
	private boolean isDirection;
	private final int directionTime = 120;
	private final int directionMove = 80;
	private final float moveX = MainActivity.width / directionMove;
	private int directionNowTime;
	
	// コンストラクタ
	public Evolution()
	{
		int w = MainActivity.width;
		int h = MainActivity.height;
		
		colorTime = 0;
		directionNowTime = 0;
		
		isEvolution = false;
		isDirection = false;
		
		evolutionNum = 0;
		evolutionButton = Button.Create(0, h / 6, w / 9, h / 15, R.drawable.evolutionbutton);
		evolutionButton.texture.SetColor(0.2f, 0.2f, 0.2f, 1.0f);
		
		base = Sprite.Create(0, h/2, w, h/9, R.drawable.groundwork, SpriteType.TYPE_TEXT.getValue());
		base.texture.SetColor(0.2f, 0.2f, 0.2f, 0);
		
		direction = Sprite.Create(-80, h/2, w/9, h/9, R.drawable.evolution, SpriteType.TYPE_TEXT.getValue());
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
		
		// 進化演出
		if( isDirection )
		{
			EvolutionDirection();
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
		//charactor.ChangePram(1.2f, 1.1f, R.drawable.image4);
		evolutionNum--;
		isDirection = true;
		
		base.texture.SetColor(0.2f, 0.2f, 0.2f, 0.6f);
	}
	
	// 進化できるかのフラグ
	public boolean IsEvolution()
	{
		return isEvolution;
	}
	
	// 進化演出
	private void EvolutionDirection()
	{
		if( ( directionNowTime < 40 ) || ( directionNowTime > 80 ) )
		{
			direction.Translate( new Vector3(moveX, 0, 0) );
		}
		
		directionNowTime++;
		
		if( directionNowTime > directionTime )
		{
			base.texture.SetColor(0.2f, 0.2f, 0.2f, 0);
			direction.setTrans(new Vector3( -80.0f, MainActivity.height/2.0f, 0.0f ));
			
			directionNowTime = 0;
			
			isDirection = false;
		}
	}
}
