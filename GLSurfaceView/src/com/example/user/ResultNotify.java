package com.example.user;


import java.util.Random;

import com.example.data.CardInformation;
import com.example.data.DataBase;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.OpenGLSurfaceView;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.SceneManager;
import com.example.glsurfaceview.Score;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Text;
import com.example.glsurfaceview.Touch;
import com.example.glsurfaceview.Vector3;

public class ResultNotify extends Sprite {
	
	//�o�[�̑傫��
	public final static int bar_width = Const.rw(0.3);
	
	//�w�i�̕\������
	float time;
	
	//�o���l�\���̎���
	float explaintime;
	//�X�R�A�̎���
	int scoretime;
	
	//�J�[�h���\������Ă���Ɗ�����
	Sprite[] cardGraph;
	Sprite[] cardExplainback;
	Sprite[] cardExplainBar;
	
	//�X�R�A
	Score score;
	
	int[] explain;
	int[] addexplain;
	int[] explainMax;
	
	//�ǉ������X�R�A��
	int addscore;
	
	//�n��
	boolean start;
	
	// ���� ���ꐄ��
	public static ResultNotify Create()
	{
		ResultNotify s = new ResultNotify();
		
		// ������
		s.Init( Const.rx(0.05), Const.ry(0.05) , Const.rx(0.9), Const.ry(0.9) , R.drawable.back_result ,Const.SpriteType.TYPE_BUTTON.getValue() );
		
		return s;
	}	
	
	private ResultNotify()
	{				
		start = true;			
		
		//�S�Ă�100����
		addscore = 100;
	}
	
	public void Init()
	{
		//�͂��߂͕\�����Ȃ�
		GetTexture().SetColor(1, 1, 1, 0.0f);		
	}	
	
	public boolean Update()
	{
		if(super.Update()==false)return false;
		
		//���ʕ\���̎��ɂ���
		if( DataBase.isResult() )
		{
			//�Q�[�����~�߂�
			OpenGLSurfaceView.GameStop();
			
			//�X�R�A�Ȃǂ̌��ʂ�\������
			if( time > 1 )
			{
				
				//������
				if( start )
				{
					//�f�b�L�̃J�[�h��S�Ă��悤���ɂ���
					for( Sprite sp : spriteList.get(Const.SpriteType.TYPE_CARD.getValue()) )
					{
						if( sp instanceof Card )
						{
							((Card)sp).setCan();
						}
					}
					
					//�R���X�g���N�^�łƂ肠�����o���l��������\��
					
					int w = Const.card_width/2;
					int h = Const.card_height/2;
					
					//�\������
					explaintime = 0;
					scoretime = 0;

					//�J�[�h�̌o���l�㏸�̉摜
					cardGraph = new Sprite[11];
					cardExplainback = new Sprite[11];
					cardExplainBar = new Sprite[11];
					
					//�I�t�Z�b�g
					int absoffset = Const.rh(0.03);
					int offset = Const.rh(0.01);
					
					//�J�[�h�̍��W
					float[] x = { 
							Const.rx(0.1) , Const.rx(0.1) , Const.rx(0.1) , Const.rx(0.1) , Const.rx(0.1) , Const.rx(0.1) ,
							Const.rx(0.5) , Const.rx(0.5) , Const.rx(0.5) , Const.rx(0.5) , Const.rx(0.5) 
							};
					float[] y = { 
							Const.ry(0.1) , Const.ry(0.1) + h , Const.ry(0.1) + h*2 , Const.ry(0.1) + h*3 , Const.ry(0.1) + h*4 , Const.ry(0.1) + h*5 ,
							Const.ry(0.1) , Const.ry(0.1) + h , Const.ry(0.1) + h*2 , Const.ry(0.1) + h*3 , Const.ry(0.1) + h*4 
							};
					for( int i = 0 ; i < 6 ; i++ )y[i] += absoffset + offset*i;
					for( int i = 0 ; i < 5 ; i++ )y[6+i] += absoffset + offset*i;

					//�o���l���
					Random r = new Random();
					explainMax = new int[11];
					addexplain = new int[11];
					explain = new int[11];
					
					for( int i = 0 ; i < 11; i ++ )
					{			
						//�o���l���
						explainMax[i] = 150;
						addexplain[i] = 0;						
						explain[i] = 0;						
						
						//�J�[�h�̉摜���
						int id = CardInformation.GetCardInformaionFromName( DataBase.GetMyDeck(i) ).getCard_id();
						//���ʂ̕\��
						cardGraph[i] 		= Sprite.Create(x[i], y[i], w , h , id, Const.SpriteType.TYPE_BUTTON.getValue() );
						cardExplainback[i] 	= Sprite.Create(x[i] + w + Const.rw(0.01) , y[i] + h/2 - h/4 , bar_width , h/2 , R.drawable.expbar, Const.SpriteType.TYPE_BUTTON.getValue() );					
						cardExplainBar[i] 	= Sprite.Create(x[i] + w + Const.rw(0.01) , y[i] + h/2 - h/4 ,  bar_width * ( (float)(explain[i] + addexplain[i] )/(float)explainMax[i] ) , h/2 , R.drawable.expbar_in, Const.SpriteType.TYPE_BUTTON.getValue() );					
						cardExplainBar[i].GetTexture().SetColor(1, 1, 1, 0.7f);
					}
					
					//�X�R�A
					score = Score.Create(Const.rx(0.67), Const.ry(0.89) , Const.rx(0.35)/Const.THE_NUMBER_OF_DECK , h , Const.SpriteType.TYPE_BUTTON.getValue() );
					addscore = DataBase.getPresentScore().getScore()/10;
					
					String text = "Score";
					float[] color = { 1.0f, 1.0f, 1.0f, 1.0f };
					Text.Create(Const.rx(0.67), Const.ry(0.89), 500, 100, text, color);
					
					start = false;
				}			
								
				//�o���l�̏��
				if( scoretime >= addscore )
				{								
					//�o���l�̏��
					if( explaintime > 1 )
					{				
						//���ʒu�ŏI��
						if( Touch.getInstance().IsTouch() )
						{
							OpenGLSurfaceView.GameStart();
							
							if( DataBase.isWin() )
							{
								SceneManager.ChangeScene( SceneManager.clearKey );
							}
							else
							{
								SceneManager.ChangeScene( SceneManager.gameoverKey );				
							}
							
							return false;
						}												
					}
					else
					{
					
						explaintime += 0.01;
						
						//�����T�C�Y����
						for( int i = 0 ; i < 11 ; i++ )
						{
							addexplain[i] = (int) (addscore * explaintime);
							
							//����˔j�͂��Ȃ�
							if( explain[i] + addexplain[i] >= explainMax[i] )
							{
								addexplain[i] = explainMax[i] - explain[i];
							}
							
							//�o�[�̃T�C�Y����
							cardExplainBar[i].setM_width( bar_width * ( (float)(explain[i] + addexplain[i] )/(float)explainMax[i] ) );
							cardExplainBar[i].Translate(new Vector3());
						}		
						
						if( Touch.getInstance().IsTouch() )
						{
							explaintime = 1;
						}							
					}					
				}
				else
				{					

					
					//�X�R�A�ɒǉ�
					score.addScore(10);
					scoretime++;						
					
					//���ʒu�ŏI��
					if( Touch.getInstance().IsTouch() )
					{
						score.addScore( addscore*10 - score.getScore() );
						scoretime = addscore;
					}							
					
				}			
			}
			else
			{
				time += 0.05;	
				
				if( Touch.getInstance().IsTouch() )
				{
					time = 1;
				}				
			}
			
			GetTexture().SetColor(1, 1, 1, time);			
		}
		else
		{
			GetTexture().SetColor(1, 1, 1, 0.0f);						
		}
		
		return true;
	}
	
}
