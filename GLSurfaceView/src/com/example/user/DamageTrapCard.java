package com.example.user;

public class DamageTrapCard extends TrapCard {

	//作成用の関数
	public static DamageTrapCard CreateDamageTrapCard( int left, int top, int width, int height, int id,int s_id, int u_id,int need )
	{
		DamageTrapCard mc = new DamageTrapCard(left, top, width, height, id, s_id, u_id,need);
		return mc;
	}
	
	
	//コンストラクタ
	protected DamageTrapCard(int left, int top, int width, int height, int id,int s_id, int u_id,int need) {
		super(left, top, width, height, id, s_id, u_id,need);
	}

	//更新処理
	@Override
	public boolean Update(){
		if(super.Update()==false)return false;
		
		//使用不可だったら
		if( GetTexture() == used )
		{
			//次の許可をする
			permitUse();					
		}
		
		return true;
	}
	
	
	@Override
	//トラップが接きちされた時の処理
	public void SetTrap(int line , int x) {

		DamageTrap.CreateDamageTrap(line, x );
		
	}
	
}
