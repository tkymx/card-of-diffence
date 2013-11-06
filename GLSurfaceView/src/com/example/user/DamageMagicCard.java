package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.Sprite;

public class DamageMagicCard extends MagicCard {

	int damage;
	
	//作成用の関数
	public static DamageMagicCard CreateDamageMagicCard( int left, int top, int width, int height, int id,int s_id, int u_id )
	{
		DamageMagicCard mc = new DamageMagicCard(left, top, width, height, id, s_id, u_id);
		return mc;
	}	
	

	//コンストラクタ
	protected DamageMagicCard(int left, int top, int width, int height, int id,int s_id, int u_id) {
		super(left, top, width, height, id, s_id, u_id);
		
		//攻撃力をセット
		damage = 10;
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
				
	}

}
