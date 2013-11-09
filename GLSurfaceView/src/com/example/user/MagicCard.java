package com.example.user;

import java.util.ArrayList;
import java.util.LinkedList;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.Sprite;


public abstract class MagicCard extends Card {
		
	
	//コンストラクタ
	protected MagicCard( int left, int top, int width, int height, int id,int s_id, int u_id,int need) {
		super(left, top, width, height, id, s_id, u_id,need);		
	}
	
	//選択された時の動作
	public abstract void SetMagic();
	
	
	//選択されている時の更新
	@Override
	public void SelectedUpdate() {

		//使用する
		SetMagic();		
		//使用済みにする
		use();
		
	}

}
