package com.example.user;

import com.example.data.CharactorInfomation;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.Touch;
import com.example.scene.GameScene;

public class MonsterCard extends Card {
	
	//自分のキャラクタの名前
	String name;
	
	//自分が作成したキャラクタの情報
	Charactor charactor;
		
	//生成メソッド
	public static MonsterCard CreateMonsterCard( int left, int top, int width, int height, int id,int need , String name )
	{
		MonsterCard mc = new MonsterCard(left, top, width, height, id,need , name);
		return mc;
	}

	//コンストラクタ
	protected MonsterCard(int left, int top, int width, int height, int id ,int need , String name) {
		super(left, top, width, height, id ,need );
		
		//キャラクターを始め無しにする
		charactor = null;
		
		//名前のセット
		this.name = name;
	}
	

	@Override
	public boolean Update() {	
		if(super.Update()==false)return false;
		
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
			int line = 0;
						
			//列によって表示する。
			if( y > Const.LINE_1_Y )
			{
				charactor = PlayerAppear.CreatePlayer( Const.LINE_1_Y , name );			
				line = Const.LINE_1_Y;
			}
			else if( y > Const.LINE_2_Y )
			{
				charactor = PlayerAppear.CreatePlayer( Const.LINE_2_Y , name );	
				line = Const.LINE_2_Y;
			}
			else if( y > Const.LINE_3_Y )
			{					
				charactor = PlayerAppear.CreatePlayer( Const.LINE_3_Y , name );		
				line = Const.LINE_3_Y;
			}
			else
			{
				//別のとこらなら終了
				return;
			}
			
			if( GameScene.tutorial.tuto3 )
			{
				if( line != 0 )
				{
					GameScene.tutorial.player = (Player) charactor;
					GameScene.tutorial.x = Const.LINE_LEFT_2_X;	
					GameScene.tutorial.y = line;
					GameScene.tutorial.changeTutorial();
				}
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

	/*
	 * 
	 * とりあえずcardからcreateを呼ばせて自分自身を作成するための仮データみたいなもん
	 * 
	 */
	protected MonsterCard()	{/*何もしない*/}
	public static MonsterCard CreateMonsterCard(){ return new MonsterCard();}	
	@Override
	public Card Create(int left, int top, int width, int height, int id,String name, int need) {

		MonsterCard mc = new MonsterCard(left, top, width, height, id,need , name);		
		
		return mc;
	}
	
	@Override
	public String GetNameForList(String name) {
		return name;
	}


	@Override
	public String GetExplainForList(String name) {
		CharactorInfomation ci = CharactorInfomation.GetCharactorInformation(name);		
		return ci.getExplation();
	}


	@Override
	public String GetParameter1ForList(String name) {
		CharactorInfomation ci = CharactorInfomation.GetCharactorInformation(name);		
		return "体力:" + ci.getHp() + "攻撃:" + ci.getAttack();
	}


	@Override
	public String GetParameter2ForList(String name) {
		CharactorInfomation ci = CharactorInfomation.GetCharactorInformation(name);		
		return "速度:" + ci.getSpeed();
	}		

	// キャラクターの設定
	public void SetCharactor( Charactor card )
	{
		charactor = card;
	}
	
}
