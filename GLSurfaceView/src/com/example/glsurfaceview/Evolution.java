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
	
	// �R���X�g���N�^
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
	
	// �X�V
	public void Update()
	{
		score = DataBase.getPresentScore();
		int scoreNum = score.getScore();		
		scoreNum += 100;
		
		touchCursol.texture.SetColor(0, 0, 0, 0);
		
		if( ( scoreNum % ( evolutinScore * repeat ) ) == 0 )
		{
			// �i���ł���ő吔�łȂ��Ƃ�
			if( evolutionNum <= evolutionMax )
			{
				evolutionNum++;
				repeat++;
			}
		}
		
		// �i�����o
		if( isDirection )
		{
			EvolutionDirection();
		}
		
		// �i���ł���Ƃ�
		if( evolutionNum > 0 )
		{
			if( Sprite.spriteList.get(SpriteType.TYPE_PLAYER.getValue()).size() != 0 )
			{
				// �^�b�`����Ă��Ȃ��Ƃ�
				if( !isEvolution )
				{
					// ��莞�Ԃ������Ƃ�
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
				
				// �^�b�`���ꂽ�Ƃ�
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
	
	// �i����ݒ�
	public void SetEvolution( Charactor charactor )
	{
		charactor.ChangePram(1.5f, 1.5f, R.drawable.player1_w, R.drawable.player1_w, R.drawable.player1_w);
		evolutionNum--;
		isDirection = true;
		
		base.texture.SetColor(0.2f, 0.2f, 0.2f, 0.6f);
	}
	
	// �i���ł��邩�̃t���O
	public boolean IsEvolution()
	{
		return isEvolution;
	}
	
	// �i�����o
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
