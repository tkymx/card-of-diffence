package com.example.user;

public abstract class MagicCard extends Card {
		
	
	//コンストラクタ
	protected MagicCard(){}
	protected MagicCard( int left, int top, int width, int height, int id,int need) {
		super(left, top, width, height, id,need);		
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
