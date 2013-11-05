package com.example.glsurfaceview;

public class Score {
	
	private int score, scoreFigure;
	private int scoreNum = Const.MAX_SCORE_NUM;
	private Sprite[] sprite = new Sprite[scoreNum]; 
	private static Score instance = new Score();
	private int width = MainActivity.width / 20;
	private int height = MainActivity.height / 5;
	private int firstPosX = MainActivity.width - ( width * scoreNum );
	private int firstPosY = MainActivity.height;
	
	public Score()
	{
		Number n = Number.getInstance();
		score = 0;
		scoreFigure = 1;
		
		int posX;
		
		for( int i = 0; i < scoreNum; i++ )
		{
			posX =  firstPosX + ( width * i );
			
			sprite[i] = Sprite.Create(posX, firstPosY, width, - height, 
					n.getTextureID(), n.getNumber(0), Const.SpriteType.TYPE_TEXT.getValue());
			
			sprite[i].texture.SetUV( n.getNumber(0) );
			
			scoreFigure *= 10;
		}
	}
	
	public void Update()
	{
		addScore(1);
	}
	
	public static Score getInstance()
	{
		return instance;
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
