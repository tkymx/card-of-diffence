package com.example.glsurfaceview;

public class Const {
	
	public static final int SCENE_TITLE = 0;
	public static final int SCENE_GAME = 1;
	public static final int SCENE_RESULT = 2;
	
	public enum SpriteType
	{
		TYPE_OTHER( 0 ),
		TYPE_BG( 1 ), 
		TYPE_CASLE( 2 ), 
		TYPE_CARD( 3 ) ,
		TYPE_ENEMY( 4 ), 
		TYPE_PLAYER( 5 ), 
		TYPE_MAX( 6 );
		
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
