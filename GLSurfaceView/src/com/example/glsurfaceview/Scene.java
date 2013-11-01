//////////////////////////////////////////////////////////////////
//	こいつを継承して使う場合はgame.javaを参考に作ってください。
//////////////////////////////////////////////////////////////////
package com.example.glsurfaceview;

import java.util.LinkedList;

import javax.microedition.khronos.opengles.GL10;

public abstract class Scene {

	public abstract void Init();
	public abstract void Uninit();
	
	public abstract void onUpdate();
	public void Update()
	{
		for( int i = 0; i < Const.SpriteType.TYPE_MAX.getValue(); i++ )
		{
			LinkedList<Sprite> list = Sprite.spriteList.get(i);
			
			// リストに登録されているシーンの数分ループ
			for( int j = 0; j < list.size(); j++ )
			{
				// 使用中のとき
				if( list.get(j).GetUse() == true )
				{
					Sprite sp = list.get(j);
					
					//fasleが帰ってきたら消す
					if(sp.Update()==false)
					{
						sp.remove();
					}
				}
			}
		}		
		onUpdate();
	}
	public abstract void onDraw( GL10 gl );
	public void Draw( GL10 gl )
	{
		for( int i = 0; i < Const.SpriteType.TYPE_MAX.getValue(); i++ )
		{
			LinkedList<Sprite> list = Sprite.spriteList.get(i);
			
			// リストに登録されているシーンの数分ループ
			for( int j = 0; j < list.size(); j++ )
			{
				// 使用中のとき
				if( list.get(j).GetUse() == true )
				{
					Sprite sp = list.get(j);
					sp.DrawSprite(gl);
				}
			}
		}			
		onDraw(gl);
	}
}
