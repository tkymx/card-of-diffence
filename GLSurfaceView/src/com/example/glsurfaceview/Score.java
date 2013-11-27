package com.example.glsurfaceview;

public class Score {
	
	private int score, scoreFigure;
	private int scoreNum = Const.MAX_SCORE_NUM;
	private Sprite[] sprite = new Sprite[scoreNum]; 
	private int width = MainActivity.width / 20;
	private int height = MainActivity.height / 5;
	private int firstPosX = MainActivity.width - ( width * scoreNum );
	private int firstPosY = MainActivity.height;
	
	
	// 生成
	public static Score Create(int fx , int fy , int w, int h)
	{
		Score score = new Score(fx,fy,w,h,Const.SpriteType.TYPE_TEXT.getValue());
		
		score.Init();
			
		return score;
	}
	public static Score Create(int fx , int fy , int w, int h,int type)
	{
		Score score = new Score(fx,fy,w,h,type);
		
		score.Init();
			
		return score;
	}		
	
	// コンストラクタ
	private Score(int fx , int fy , int w , int h,int type)
	{
		Number n = Number.getInstance();
		score = 0;
		scoreFigure = 1;
		
		int posX;
		
		firstPosX = fx;
		firstPosY = fy;
		
		width = w;
		height = h;
		
		for( int i = 0; i < scoreNum; i++ )
		{
			posX =  firstPosX + ( width * i );
			
			sprite[i] = Sprite.Create(posX, firstPosY, width, - height, 
					n.getTextureID(), n.getNumber(0),type);
			
			sprite[i].texture.SetUV( n.getNumber(0) );
			
			scoreFigure *= 10;
		}
	}
	
	// 初期化
	public void Init()
	{
		Number n = Number.getInstance();
		score = 0;
		
		for( int i = 0; i < scoreNum; i++ )
		{
			sprite[i].texture.SetUV( n.getNumber(0) );
		}
	}

	
	// 更新
	public void Update()
	{
	}
	
	// スコアの追加
	public void addScore( int addValue )
	{
		score += addValue;
		
		int nWork = 0, nPow = 0;
		
		for( int i = 0; i < scoreNum; i++ )
		{
			nPow = ( int )Math.pow( 10, i );
			nWork = ( ( score / ( scoreFigure / nPow ) ) % 10 );
			
			// テクスチャUVの更新
			sprite[i].GetTexture().SetUV( Number.getInstance().getNumber( nWork ) );
		}
	}
	
	// スコアの取得
	public int getScore()
	{
		return score;
	}

}
