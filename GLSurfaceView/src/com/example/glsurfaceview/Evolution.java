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
	private final int directionTime = 100;
	private final int directionMove = 50;
	private final float moveX = MainActivity.width / directionMove;
	private int directionNowTime;
	private int repeat;
	public static Sprite touchCursol;
	
	// コンストラクタ
	public Evolution()
	{
		int w = MainActivity.width;
		int h = MainActivity.height;
		
		colorTime = 0;
		directionNowTime = 0;
		repeat = 1;
		
		isEvolution = false;
		isDirection = false;
		
		evolutionNum = 0;
		evolutionButton = Button.Create(w/8, h/2 + h/3.8f, w / 9, h / 12, R.drawable.evolutionbutton);
		evolutionButton.texture.SetColor(0.2f, 0.2f, 0.2f, 1.0f);
		
		base = Sprite.Create(0, h/2, w, h/9, R.drawable.groundwork, SpriteType.TYPE_TEXT.getValue());
		base.texture.SetColor(0.2f, 0.2f, 0.2f, 0);
		
		direction = Sprite.Create(-150.0f, h/2, w/9, h/9, R.drawable.evolution, SpriteType.TYPE_TEXT.getValue());
		
		touchCursol = Sprite.Create(w/10, h/3, w/10, w/10, R.drawable.touchcursol, SpriteType.TYPE_TEXT.getValue());
		touchCursol.texture.SetColor(0, 0, 0, 0);
	}
	
	// 更新
	public void Update()
	{
		score = DataBase.getPresentScore();
		int scoreNum = score.getScore();		
		scoreNum += 100;
		
		touchCursol.texture.SetColor(0, 0, 0, 0);
		
		if( ( scoreNum % ( evolutinScore * repeat ) ) == 0 )
		{
			// 進化できる最大数でないとき
			if( evolutionNum <= evolutionMax )
			{
				evolutionNum++;
				repeat++;
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
			if( Sprite.spriteList.get(SpriteType.TYPE_PLAYER.getValue()).size() != 0 )
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
					evolutionButton.texture.SetColor(1.0f, 0.0f, 0.0f, 1.0f);
					touchCursol.texture.SetColor(1.0f, 1.0f, 1.0f, 1.0f);
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
				isEvolution = false;
				evolutionButton.texture.SetColor(0.2f, 0.2f, 0.2f, 1.0f);
				colorTime = 0;
			}
		}
		else
		{
			isEvolution = false;
			evolutionButton.texture.SetColor(0.2f, 0.2f, 0.2f, 1.0f);
			colorTime = 0;
		}
	}
	
	// 進化を設定
	public void SetEvolution( Charactor charactor )
	{
		charactor.ChangePram(1.5f, 1.5f, R.drawable.player1_w, R.drawable.player1_w, R.drawable.player1_w);
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
		if( ( directionNowTime < 30 ) || ( directionNowTime > 70 ) )
		{
			direction.Translate( new Vector3(moveX, 0, 0) );
		}
		
		directionNowTime++;
		
		if( directionNowTime > directionTime )
		{
			base.texture.SetColor(0.2f, 0.2f, 0.2f, 0);
			direction.setTrans(new Vector3( -150.0f, MainActivity.height/2.0f, 0.0f ));
			
			directionNowTime = 0;
			
			isDirection = false;
		}
	}
}
