package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.Touch;

public abstract class TrapCard extends Card {
	
	//コンストラクタ
	protected TrapCard(){};
	protected TrapCard(int left, int top, int width, int height, int id ,int need) {
		super(left, top, width, height, id ,need);
	
	}
	
	// 更新処理
	public boolean Update()
	{
		if(super.Update()==false)return false;
		
		return true;		
	}	
	
	//押された際に設置する操作を決める
	public abstract void SetTrap( int line , int x );
	//選択されている時の処理
	@Override
	public void SelectedUpdate() {

		Touch touch = Touch.getInstance();
		if( touch.IsTouch() )
		{
			//座標を取得する
			int y = (int) touch.getY();						
			int x = (int) touch.getX();						
			
			//列によって表示する。
			if( y > Const.LINE_1_Y && x > Const.LINE_LEFT_1_X && x < Const.LINE_RIGHT_1_X )
			{
				SetTrap( Const.LINE_1_Y , x );
			}
			else if( y > Const.LINE_2_Y && y < Const.LINE_1_Y && x > Const.LINE_LEFT_2_X && x < Const.LINE_RIGHT_2_X )
			{
				SetTrap( Const.LINE_2_Y , x );
			}
			else if( y > Const.LINE_3_Y && y < Const.LINE_2_Y && x > Const.LINE_LEFT_3_X && x < Const.LINE_RIGHT_3_X )
			{					
				SetTrap( Const.LINE_3_Y , x );
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
		
		
	}
}
