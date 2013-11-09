package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.Touch;

public class MonsterCard extends Card {
	
	//自分が作成したキャラクタの情報
	Charactor charactor;
	
	//生成メソッド
	public static MonsterCard CreateMonsterCard( int left, int top, int width, int height, int id,int need )
	{
		MonsterCard mc = new MonsterCard(left, top, width, height, id,need);
		return mc;
	}
	
	//コンストラクタ
	protected MonsterCard(int left, int top, int width, int height, int id ,int need) {
		super(left, top, width, height, id ,need);
		
		//キャラクターを始め無しにする
		charactor = null;
	}
	

	@Override
	public boolean Update() {	
		if(super.Update()==false)return false;
		
		boolean alive = false;
		
		//使えたら
		if( charactor != null )
		{
			//キャラクタが死んだら使えるようにするう		
			if( charactor.isDead() )
			{
				//カードを使えるようにする
				permitUse();			
			}
		}
		else if( !isUse )
		{
			//カードを使えるようにする
			permitUse();			
		}
		
		
		return true;
	}
	
	@Override
	public void SelectedUpdate() {

		Touch touch = Touch.getInstance();
		if( touch.IsTouch() )
		{
			//座標を取得する
			int y = (int) touch.getY();
						
			//列によって表示する。
			if( y > Const.LINE_1_Y )
			{
				charactor = PlayerAppear.CreatePlayer( Const.LINE_1_Y , "" );					
			}
			else if( y > Const.LINE_2_Y )
			{
				charactor = PlayerAppear.CreatePlayer( Const.LINE_2_Y , "" );										
			}
			else if( y > Const.LINE_3_Y )
			{					
				charactor = PlayerAppear.CreatePlayer( Const.LINE_3_Y , "" );					
			}
			else
			{
				//別のとこらなら終了
				return;
			}
			
			//使用済みにする
			use();
		}
	}
	
	//許可する際にキャラクタをnullぬする
	@Override
	public void permitUse() {
		super.permitUse();
		
		//キャラクタをnullにする
		charactor = null;
		
	}
	
}
