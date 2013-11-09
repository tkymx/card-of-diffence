package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;

/**
 * 
 * 基本的にはカードの管理を行っていくが、いまは、カード表示画面の裏を表示する
 * 
 * @author ultra-tkymx
 *
 */

public class Deck extends Sprite {
	
	private void inCard( int x,int y,int id )
	{
		Card card = MonsterCard.CreateMonsterCard(
				x , 
				y , 
				Const.card_width , 
				Const.card_height , 
				id ,
				R.drawable.moster_card_selected ,
				R.drawable.moster_card_used );		
	}
	
	// 初期化処理
	public void Init()
	{		
		int[] image = new int[9];
		image[0] = R.drawable.moster_card_1;
		image[1] = R.drawable.moster_card_2;
		image[2] = R.drawable.moster_card_3;
		image[3] = R.drawable.moster_card_4;
		image[4] = R.drawable.moster_card_5;
		image[5] = R.drawable.moster_card_6;
		image[6] = R.drawable.moster_card_7;
		image[7] = R.drawable.moster_card_8;
		image[8] = R.drawable.moster_card_9;
		
		//カードを11枚生産する
		for(int i = 0;i<11;i++)
		{
			//座標のセット
			int x1 = (i+1) * Const.card_width_offset + i * Const.card_width;
			int y1 = -Const.card_heught_offset;			
			
			//モンスターカード
			if( i >= 0 && i <= 8 )
			{
				inCard(x1, y1, image[i]);
			}
			//罠カード
			if( i == 9 )
			{
				//カード情報の生成
				Card card = DamageTrapCard.CreateDamageTrapCard(
						x1 , 
						y1 , 
						Const.card_width , 
						Const.card_height , 
						R.drawable.trap_card ,
						R.drawable.trap_card_selected ,
						R.drawable.trap_card_used );
			}
			//魔法カード
			if( i == 10 )
			{
				//カード情報の生成
				Card card = DamageMagicCard.CreateDamageMagicCard(
						x1 , 
						y1 , 
						Const.card_width , 
						Const.card_height , 
						R.drawable.magic_card ,
						R.drawable.magic_card_selected ,
						R.drawable.magic_card_used );
			}
		}
	}
	
	// 更新処理
	public boolean Update()
	{
				
		return true;		
	}
}
