package com.example.user;

import com.example.data.ParameterCardInfomatoin;

public class DamageTrapCard extends TrapCard {

	int damage;
	
	//作成用の関数
	public static DamageTrapCard CreateDamageTrapCard( int left, int top, int width, int height, int id ,int need )
	{
		DamageTrapCard mc = new DamageTrapCard(left, top, width, height, id ,need);
		return mc;
	}
	
	
	//コンストラクタ
	protected DamageTrapCard(int left, int top, int width, int height, int id ,int need) {
		super(left, top, width, height, id ,need);
		
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
	//トラップが接きちされた時の処理
	public void SetTrap(int line , int x) {

		DamageTrap.CreateDamageTrap(line, x , damage);
		
	}
	
	/*
	 * 
	 * とりあえずcardからcreateを呼ばせて自分自身を作成するための仮データみたいなもん
	 * 
	 */
	protected DamageTrapCard(){/*何もしない*/}
	public static DamageTrapCard CreateDamageTrapCard(){ return new DamageTrapCard();}		
	@Override
	public Card Create(int left, int top, int width, int height, int id,String name, int need) {

		DamageTrapCard mc = new DamageTrapCard(left, top, width, height, id ,need);
		
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
			return "全体トラップ";
		}
		//それ以外なら単体攻撃
		else
		{
			return "単体トラップ";
		}
	}


	@Override
	public String GetParameter2ForList(String name) {
		ParameterCardInfomatoin pci = ParameterCardInfomatoin.GetMagicCard(name);
		return "攻撃力  = " + pci.getParameter2();
	}				
	
}
