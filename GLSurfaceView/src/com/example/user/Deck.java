package com.example.user;


import com.example.data.DataBase;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.Sprite;

/**
 * 
 * 基本的にはカードの管理を行っていくが、いまは、カード表示画面の裏を表示する
 * 
 * @author ultra-tkymx
 *
 */

public class Deck extends Sprite {
	
	// 初期化処理
	public void Init()
	{		
		//カードのセット
		for( int i = 0 ; i < 11 ; i++ )
		{			
			//座標のセット
			int x1 = (i+1) * Const.card_width_offset + i * Const.card_width;
			int y1 = -Const.card_heught_offset;	
			
			//デッキからカードの召喚
			DataBase.GetDeckCardInformation(i).getCard(x1, y1, Const.card_width, Const.card_height);
		}
		
	}
	
	// 更新処理
	public boolean Update()
	{
				
		return true;		
	}
}
