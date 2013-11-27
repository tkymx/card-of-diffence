package com.example.user;

import java.util.LinkedList;
import java.util.Random;

import com.example.data.CardInformation;
import com.example.data.CharactorInfomation;
import com.example.data.DataBase;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;

public class EnemyAppear extends Sprite {
	
	long time;
	private int pline1, pline2, pline3;
	private int eline1, eline2, eline3;
	private int playerLineNum, enemyLineNum;
	private Random rand = new Random(); 
    private int ran, waitRan; 
    private String[] name;
    private Stage prevStage;
    private MonsterCard[] enemyCard;
    private int stageLevel;
	
	// ����������
	public void Init()
	{		
		time = 0;
		waitRan = 0;
		ran = rand.nextInt( 4 );
		prevStage = DataBase.getPresentStage();
		name = prevStage.getEnemyDeck();
		stageLevel = prevStage.getLevel();
		
		enemyCard = new MonsterCard[11];
		
		for( int i = 0; i < name.length; i++ )
		{
			enemyCard[i] = ( MonsterCard )CardInformation.GetCardFromName(name[i], 0, 0, 0, 0);
		}
	}
	
	// �X�V����
	public boolean UpdateDamy()
	{
		if( System.currentTimeMillis() - time >= 8000 + waitRan )
		{
			//�G�L������z�u
			enemyCard[0].SetCharactor( CreateEnemy( Const.LINE_1_Y , "enemy1" ) );
			CreateEnemy( Const.LINE_2_Y , "enemy1" );
			CreateEnemy( Const.LINE_3_Y , "enemy1" );					
			
			time = System.currentTimeMillis();
		}
		return true;		
	}
	
	// �X�V����
	public boolean Update()
	{	
		if( ( System.currentTimeMillis() - time ) >= ( 3500 + waitRan - ( 250 * stageLevel ) ) )
		{				
			waitRan = rand.nextInt(2000);
			waitRan -= 1000;
			
			// �v���C���[���ǂ̃��C���ɉ��̂��邩���ׂ�
			serchPlayerLine();
			
			// �G�l�~�[���ǂ̃��C���ɉ��̂��邩���ׂ�
			serchEnemyLine();
			
			// �v���C���[��萔�����Ȃ���
			if( enemyLineNum <= ( playerLineNum + stageLevel ) )
			{
				// �G�l�~�[�J�[�h�̖�����
				for( int i = 0; i < enemyCard.length; i++ )
				{
					// �g�p���Ă��Ȃ��Ƃ�
					if( enemyCard[i].isUse )
					{
						Enemy enemy = null;
						//int playerMaxLine = serchPlayerLineMaxNum();
						int compareLine = compareEnemyToPlayer();		// ��ԏo�Ă���G�l�~�[�̐������Ȃ����C��
						
						ran = rand.nextInt(11);							// �擾����J�[�h�̔ԍ��𗐐��Ŏ擾
						
						// ��ԃv���C���[�̑������C���ɏo��
						switch( compareLine )
						{
						case Const.LINE1:
							enemy = CreateEnemy( Const.LINE_1_Y , name[ran] );
							break;
						case Const.LINE2:
							enemy = CreateEnemy( Const.LINE_2_Y , name[ran] );
							break;
						case Const.LINE3:
							enemy = CreateEnemy( Const.LINE_3_Y , name[ran] );
							break;
						}
						
						// null�łȂ��Ƃ�
						if( enemy != null )
						{
							// �L�����N�^�[�̃Z�b�g
							enemyCard[i].SetCharactor(enemy);
						}
						
						break;
					}
				}
			}
			
			time = System.currentTimeMillis();
		}
		
		return true;
	}
	
	public static Enemy CreateEnemy( int liney , String playername )
	{
		//���O�ŉ摜�𔻒f����
		int id = R.drawable.walk_enemy;
		//�G���
		Enemy enemy = CharactorInfomation.GetEnemyFromName(playername);
		
		if( liney == Const.LINE_1_Y)
		{
			enemy.Init( Const.LINE_RIGHT_1_X  , Const.LINE_1_Y , Const.LINE_1_W , Const.LINE_1_W , 3 , 1 , 5 , id , Const.SpriteType.TYPE_ENEMY.getValue() );			
			enemy.setLineNum(Const.LINE1);
		}
		else if( liney == Const.LINE_2_Y)
		{
			enemy.Init( Const.LINE_RIGHT_2_X  , Const.LINE_2_Y , Const.LINE_2_W , Const.LINE_2_W , 3 , 1 , 5 , id , Const.SpriteType.TYPE_ENEMY.getValue() );			
			enemy.setLineNum(Const.LINE2);
		}
		else if( liney == Const.LINE_3_Y)
		{
			enemy.Init( Const.LINE_RIGHT_3_X  , Const.LINE_3_Y , Const.LINE_3_W , Const.LINE_3_W , 3 , 1 , 5 , id , Const.SpriteType.TYPE_ENEMY.getValue() );			
			enemy.setLineNum(Const.LINE3);
		}
		
		return enemy;
	}	
	
	// �v���C���[�̂��郉�C���𒲂ׂ�
	private void serchPlayerLine()
	{
		int playerSize = Const.SpriteType.TYPE_PLAYER.getValue();
		LinkedList<Sprite> list = Sprite.spriteList.get(playerSize);
		Player player;
		pline1 = pline2 = pline3 = 0;
		
		// �v���C���[�T�C�Y�����[�v
		for( int i = 0; i < Sprite.spriteList.get( playerSize ).size(); i++ )
		{
			player = ( Player )list.get(i);
			
			switch( player.getLineNum() )
			{
			case Const.LINE1:
				pline1++;
				break;
				
			case Const.LINE2:
				pline2++;
				break;
				
			case Const.LINE3:
				pline3++;
				break;
			}
			
			// ���C���ɂ���v���C���[�̐�
			playerLineNum = pline1 + pline2 + pline3;
		}
	}
	
	// �G�l�~�[�̂��郉�C���𒲂ׂ�
	private void serchEnemyLine()
	{
		int enemyVal = Const.SpriteType.TYPE_ENEMY.getValue();
		LinkedList<Sprite> list = Sprite.spriteList.get(enemyVal);
		Enemy enemy;
		eline1 = eline2 = eline3 = 0;
		
		// �G�l�~�[�T�C�Y�����[�v
		for( int i = 0; i < Sprite.spriteList.get( enemyVal ).size(); i++ )
		{
			enemy = ( Enemy )list.get(i);
			
			switch( enemy.getLineNum() )
			{
			case Const.LINE1:
				eline1++;
				break;
				
			case Const.LINE2:
				eline2++;
				break;
				
			case Const.LINE3:
				eline3++;
				break;
			}
			
			// ���C���ɂ���v���C���[�̐�
			enemyLineNum = eline1 + eline2 + eline3;
		}
	}

	// �v���C���[�̈�ԑ������C���𒲂ׂ�
	private int serchPlayerLineMaxNum()
	{
		// ���ׂē������̂Ƃ�
		if( pline1 == pline2 && pline1 == pline3 )
		{
			ran = rand.nextInt(3);
			
			switch( ran )
			{
			case 0:
				return Const.LINE1;
				
			case 1:
				return Const.LINE2;
				
			case 2:
				return Const.LINE3;
			
			default:
				return Const.LINE1;
			}
		}
		// 1��������
		else if( pline1 >= pline2 )
		{
			// 1��������
			if( pline1 > pline3 )
			{
				// 1��2��������
				if( pline1 == pline2 )
				{
					ran = rand.nextInt(2);
					
					switch(ran)
					{
					case 0:
						return Const.LINE1;
					
					case 1:
						return Const.LINE2;
						
					default:
						return Const.LINE2;
					}
				}
				
				return Const.LINE1;
			}
			else 
			{
				return Const.LINE3;
			}
		}
		else
		{
			// 2���傫���Ƃ�
			if( pline2 >= pline3 )
			{
				// 2��3��������
				if( pline2 == pline3 )
				{
					ran = rand.nextInt(2);
					
					switch(ran)
					{
					case 0:
						return Const.LINE2;
					
					case 1:
						return Const.LINE3;
						
					default:
						return Const.LINE3;
					}
				}
				
				return Const.LINE2;
			}
			else
			{
				return Const.LINE3;
			}
		}
	}
	
	// �G�l�~�[�ƃv���C���[�̃��C�����r���G�l�~�[�̏o�Ă���̂����Ȃ����C����Ԃ�
	private int compareEnemyToPlayer()
	{
		int line1, line2, line3;
		
		line1 = pline1 - eline1;
		line2 = pline2 - eline2;
		line3 = pline3 - eline3;
		
		// ���ׂĂ�line�������Ƃ�
		if( ( line1 == line2 ) && ( line1 == line3 ) )
		{
			ran = rand.nextInt(3) + 1;
			
			return ran;
		}
		// 1��2��������
		else if( line1 == line2 )
		{
			// 1���3���傫���Ƃ�
			if( line1 > line3 )
			{
				ran = rand.nextInt(2) + 1;
				
				return ran;
			}
			else
			{
				return Const.LINE3;
			}
		}
		// 1��3�������Ƃ�
		else if( line1 == line3 )
		{
			// 2���1���傫����
			if( line1 > line2 )
			{
				ran = rand.nextInt(2);
				
				switch( ran )
				{
				case 0:
					return Const.LINE1;
				default :
					return Const.LINE3;
				}
			}
			else
			{
				return Const.LINE2;
			}
		}
		// line1����ԑ傫���Ƃ�
		else if( ( line1 > line2 ) && ( line1 > line3 ) )
		{
			return Const.LINE1;
		}
		// 2���1���傫���Ƃ�
		else if( line1 > line2 )
		{
			return Const.LINE3;
		}
		// 3���1���傫����
		else if( line1 > line3 )
		{
			return Const.LINE2;
		}
		
		ran = rand.nextInt(3) + 1;
		
		return ran;
	}
}
