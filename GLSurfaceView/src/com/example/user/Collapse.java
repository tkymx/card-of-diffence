package com.example.user;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.SpriteAnimation;
import com.example.glsurfaceview.OpenGLSurfaceView;
import com.example.glsurfaceview.Sprite;
import com.example.user.Charactor.*;


//城が崩壊したら崩壊のエフェクトを付ける

public class Collapse extends SpriteAnimation{
/*	
	//生成
	public static Collapse Create( float left, float top, float width, float height, int UVWidth, int UVHeight, int animeTime, int id, int SpriteType ){
		Collapse s = new Collapse();
		s.Init( left, top, width, height, UVWidth, UVHeight, animeTime, id, SpriteType );		
		return s;
	}
	//崩壊後
	public void Damage( Charactor c )
	{
		super.Damage(c);

		AnimationEffect.Create( 
				this.getTrans().getX() + m_width - Const.rx(0.1), 
				this.getTrans().getY() + m_height/2  - Const.rx(0.1)/2, 
				Const.rx(0.1), 
				Const.rx(0.1), 
				10, 1, 1, R.drawable.effect, Const.SpriteType.TYPE_EFFECT.getValue());

	}	
	//通常のダメージを受けた時
	@Override	
	public void Damage( int c )
	{
		super.Damage(c);

		//ダメージエフェクト
		AnimationEffect.Create( 
				this.getTrans().getX() + m_width/2  - Const.rx(0.1)/2, 
				this.getTrans().getY() + m_height/2  - Const.rx(0.1)/2, 
				Const.rx(0.1), 
				Const.rx(0.1), 
				10, 1, 1, R.drawable.effect, Const.SpriteType.TYPE_EFFECT.getValue());

	}
	
	@Override
	public boolean Update()
	{
		super.Update();
		
		//崩壊してたらエフェクト追加
		if( isEnd == true )
			
			return false;
		
		return true;
	}
*/	
}

