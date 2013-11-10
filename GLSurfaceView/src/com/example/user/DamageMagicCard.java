package com.example.user;

import com.example.glsurfaceview.Const;
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
			//ここで、数秒まつなどの設定が必要になる
			
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
