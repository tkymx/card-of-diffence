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
	
	//バーの大きさ
	public final static int bar_width = Const.rw(0.3);
	
	//背景の表示時間
	float time;
	
	//経験値表示の時間
	float explaintime;
	//スコアの時間
	int scoretime;
	
	//カードが表示されていると嬉しい
	Sprite[] cardGraph;
	Sprite[] cardExplainback;
	Sprite[] cardExplainBar;
	
	//スコア
	Score score;
	
	int[] explain;
	int[] addexplain;
	int[] explainMax;
	
	//追加されるスコア数
	int addscore;
	
	//始め
	boolean start;
	
	// 生成 これ推奨
	public static ResultNotify Create()
	{
		ResultNotify s = new ResultNotify();
		
		// 初期化
		s.Init( Const.rx(0.05), Const.ry(0.05) , Const.rx(0.9), Const.ry(0.9) , R.drawable.back_result ,Const.SpriteType.TYPE_BUTTON.getValue() );
		
		return s;
	}	
	
	private ResultNotify()
	{				
		start = true;			
		
		//全てに100たす
		addscore = 100;
	}
	
	public void Init()
	{
		//はじめは表示しない
		GetTexture().SetColor(1, 1, 1, 0.0f);		
	}	
	
	public boolean Update()
	{
		if(super.Update()==false)return false;
		
		//結果表示の時につける
		if( DataBase.isResult() )
		{
			//ゲームを止める
			OpenGLSurfaceView.GameStop();
			
			//スコアなどの結果を表示する
			if( time > 1 )
			{
				
				//初期化
				if( start )
				{
					//デッキのカードを全てしようかにする
					for( Sprite sp : spriteList.get(Const.SpriteType.TYPE_CARD.getValue()) )
					{
						if( sp instanceof Card )
						{
							((Card)sp).setCan();
						}
					}
					
					//コンストラクタでとりあえず経験値を挙げる予定
					
					int w = Const.card_width/2;
					int h = Const.card_height/2;
					
					//表示時間
					explaintime = 0;
					scoretime = 0;

					//カードの経験値上昇の画像
					cardGraph = new Sprite[11];
					cardExplainback = new Sprite[11];
					cardExplainBar = new Sprite[11];
					
					//オフセット
					int absoffset = Const.rh(0.03);
					int offset = Const.rh(0.01);
					
					//カードの座標
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

					//経験値情報
					Random r = new Random();
					explainMax = new int[11];
					addexplain = new int[11];
					explain = new int[11];
					
					for( int i = 0 ; i < 11; i ++ )
					{			
						//経験値情報
						explainMax[i] = 150;
						addexplain[i] = 0;						
						explain[i] = 0;						
						
						//カードの画像情報
						int id = CardInformation.GetCardInformaionFromName( DataBase.GetMyDeck(i) ).getCard_id();
						//結果の表示
						cardGraph[i] 		= Sprite.Create(x[i], y[i], w , h , id, Const.SpriteType.TYPE_BUTTON.getValue() );
						cardExplainback[i] 	= Sprite.Create(x[i] + w + Const.rw(0.01) , y[i] + h/2 - h/4 , bar_width , h/2 , R.drawable.expbar, Const.SpriteType.TYPE_BUTTON.getValue() );					
						cardExplainBar[i] 	= Sprite.Create(x[i] + w + Const.rw(0.01) , y[i] + h/2 - h/4 ,  bar_width * ( (float)(explain[i] + addexplain[i] )/(float)explainMax[i] ) , h/2 , R.drawable.expbar_in, Const.SpriteType.TYPE_BUTTON.getValue() );					
						cardExplainBar[i].GetTexture().SetColor(1, 1, 1, 0.7f);
					}
					
					//スコア
					score = Score.Create(Const.rx(0.67), Const.ry(0.89) , Const.rx(0.35)/Const.THE_NUMBER_OF_DECK , h , Const.SpriteType.TYPE_BUTTON.getValue() );
					addscore = DataBase.getPresentScore().getScore()/10;
					
					String text = "Score";
					float[] color = { 1.0f, 1.0f, 1.0f, 1.0f };
					Text.Create(Const.rx(0.67), Const.ry(0.89), 500, 100, text, color);
					
					start = false;
				}			
								
				//経験値の情報
				if( scoretime >= addscore )
				{								
					//経験値の情報
					if( explaintime > 1 )
					{				
						//一定位置で終了
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
						
						//自動サイズ調整
						for( int i = 0 ; i < 11 ; i++ )
						{
							addexplain[i] = (int) (addscore * explaintime);
							
							//上限突破はしない
							if( explain[i] + addexplain[i] >= explainMax[i] )
							{
								addexplain[i] = explainMax[i] - explain[i];
							}
							
							//バーのサイズ調整
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

					
					//スコアに追加
					score.addScore(10);
					scoretime++;						
					
					//一定位置で終了
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
