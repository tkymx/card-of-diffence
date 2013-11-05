package com.example.glsurfaceview;

import com.example.glsurfaceview.Const.SpriteType;

public class Button extends Sprite{
	
	protected boolean isTouch, isRelease;
	
	public Button()
	{
	}
	
	public static Button Create( float left, float top, float width, float height, int id )
	{
		Button b = new Button();
		
		b.Init(left, top, width, height, id);
		
		return b;
	}
	
	public void Init( float left, float top, float width, float height, int id )
	{
		super.Init(left, top, width, height, id, SpriteType.TYPE_BUTTON.getValue());
	}

	public boolean Update()
	{
		// タッチされているとき
		if( Touch.getInstance().IsTouch() )
		{
			float x = Touch.getInstance().getX();
			float y = Touch.getInstance().getY();
			
			// 範囲に入っているとき
			if( ( x > trans.x ) && ( x < trans.x + m_width ) &&
				( y > trans.y ) && ( y < trans.y + m_height ) )
			{
				isTouch = true;
				
				if( Touch.getInstance().IsRelase() )
				{
					isRelease = true;
				}
			}
		}
		
		return true;
	}
	
	// タッチされているか
	public boolean getTouch()
	{
		return isTouch;
	}
	
	// はなされたか
	public boolean getRelease()
	{
		return isRelease;
	}
	
}
