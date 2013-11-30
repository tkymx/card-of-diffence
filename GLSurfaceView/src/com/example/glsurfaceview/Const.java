package com.example.glsurfaceview;

public class Const {
	
	//列の位置（相対値）
	
	// ラインの番号
	public static final int LINE1 = 1;
	public static final int LINE2 = 2;
	public static final int LINE3 = 3;
	
	//左側の発生する位置とその時の大きさ（右の終点）
	public static final int LINE_1_Y = ry(0.57);
	public static final int LINE_2_Y = ry(0.38);
	public static final int LINE_3_Y = ry(0.2);

	public static final int LINE_LEFT_1_X = rx(0.14);
	public static final int LINE_LEFT_2_X = rx(0.11);
	public static final int LINE_LEFT_3_X = rx(0.09);

	public static final int LINE_RIGHT_1_X = rx(0.78);
	public static final int LINE_RIGHT_2_X = rx(0.81);
	public static final int LINE_RIGHT_3_X = rx(0.83);	

	public static final float LINE_1_SPEED = (LINE_RIGHT_1_X-LINE_LEFT_1_X)*0.001f;
	public static final float LINE_2_SPEED = (LINE_RIGHT_2_X-LINE_LEFT_2_X)*0.001f;
	public static final float LINE_3_SPEED = (LINE_RIGHT_3_X-LINE_LEFT_3_X)*0.001f;	
	
	public static final int LINE_1_W = rw(0.08);
	public static final int LINE_2_W = rw(0.09);
	public static final int LINE_3_W = rw(0.1);
	
	//カードの座標情報
	public static int card_width_offset =   Const.rx(0.005);
	public static int card_heught_offset =  Const.ry(0.054);
	public static int card_width = 			Const.rx( 1.0/11.0 ) - card_width_offset;
	public static int card_height = 		Const.ry( 0.23 );
	
	//シーン情報の紹介
	public static final int SCENE_TITLE = 0;
	public static final int SCENE_GAME = 1;
	public static final int SCENE_RESULT = 2;
	
	//座標変換系のマクロ
	public static int rx( double r ){return (int) (MainActivity.width*r);}
	public static int ry( double r ){return (int) (MainActivity.height*r);}
	public static int rw( double r ){return (int) (MainActivity.width*r);}
	public static int rh( double r ){return (int) (MainActivity.height*r);}
	
	// スコアの最大数表示数
	public static final int MAX_SCORE_NUM = 8;
	
	public static final int THE_NUMBER_OF_DECK = 11;
	
	public enum SpriteType
	{
		TYPE_OTHER( 0 ),
		TYPE_BG( 1 ), 
		TYPE_CASLE( 2 ), 
		TYPE_CARD( 3 ) ,
		TYPE_TRAP( 4 ),
		TYPE_ENEMY( 5 ), 
		TYPE_PLAYER( 6 ), 
		TYPE_EFFECT( 7 ),
		TYPE_TEXT( 8 ),
		TYPE_BUTTON( 9 ),
		TYPE_TUTORIAL( 10 ),
		TYPE_MAX( 11 );
		
		private int value;
		
		private SpriteType( int n )
		{
			value = n;
		}
		
		public int getValue()
		{
			return value;
		}
	};

}
