package com.example.user;

import com.example.data.ParameterCardInfomatoin;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;

public class DamageMagicCard extends MagicCard {

	int damage;
	
	//作成用の関数
	public static DamageMagicCard CreateDamageMagicCard( int left, int top, int width, int height, int id ,int need )
	{
		DamageMagicCard mc = new DamageMagicCard(left, top, width, height, id ,need);
		return mc;
	}	
	

	//コンストラクタ
	protected DamageMagicCard(int left, int top, int width, int height, int id ,int need) {
		super(left, top, width, height, id ,need);
		
		//攻撃力をセット
		damage = 10;
	}
	
	//更新処理
	@Override
	public boolean Update(){
		if(super.Update()==false)return false;
		
		//使用不可だったら
		if( !isUse )
		{			
			//次の許可をする
			permitUse();					
		}
		
		return true;
	}

	@Override
	public void SetMagic() {
		
		//押された時に全体にダメージを与える
		for( Sprite sp : Sprite.spriteList.get( Const.SpriteType.TYPE_ENEMY.getValue() ) )
		{
			if( sp instanceof Enemy )
			{
				//敵情報
				Enemy enemy = (Enemy)sp;
				//敵に攻撃
				enemy.Damage( damage );				
			}
		}

		//攻撃エフェクト
		AnimationEffect.Create( 
				0, 
				0, 
				Const.rx(1), 
				Const.ry(1), 
				4, 1, 5, R.drawable.attack, Const.SpriteType.TYPE_EFFECT.getValue());				
				
		
				
	}
	
	/*
	 * 
	 * とりあえずcardからcreateを呼ばせて自分自身を作成するための仮データみたいなもん
	 * 
	 */
	protected DamageMagicCard(){/*何もしない*/}
	public static DamageMagicCard CreateDamageMagicCard(){ return new DamageMagicCard();}	
	@Override
	public Card Create(int left, int top, int width, int height, int id,String name, int need) {

		DamageMagicCard mc = new DamageMagicCard(left, top, width, height, id ,need);
		
		return mc;
	}


	@Override
	public String GetNameForList(String name) {
		return name;
	}


	@Override
	public String GetExplainForList(String name) {
		ParameterCardInfomatoin pci = ParameterCardInfomatoin.GetMagicCard(name);
		return pci.getExplation();
	}


	@Override
	public String GetParameter1ForList(String name) {
		ParameterCardInfomatoin pci = ParameterCardInfomatoin.GetMagicCard(name);
		
		//-1なら全体攻撃
		if( pci.getParameter1() == -1 )
		{
			return "全体魔法";
		}
		//それ以外なら単体攻撃
		else
		{
			return "単体魔法";
		}
	}


	@Override
	public String GetParameter2ForList(String name) {
		ParameterCardInfomatoin pci = ParameterCardInfomatoin.GetMagicCard(name);
		return "攻撃力  = " + pci.getParameter2();
	}	

}
