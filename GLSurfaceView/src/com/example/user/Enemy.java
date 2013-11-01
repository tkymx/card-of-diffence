package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Vector3;

/**
 * 
 * 生成時に、spriteのメソッドを用いて、画像の追加を行う
 * 
 * 
 * @author ultra-tkymx
 *
 */

public class Enemy extends Charactor {
	

	public Enemy(int hp, int attack) {
		super(hp, attack);
		// TODO Auto-generated constructor stub
	}

	// 初期化処理
	public void Init()
	{
		
	}
	
	// 更新処理
	@Override	
	protected void move_state()
	{
		//歩くかどうか
		boolean moveflag = true;
		//敵がいたら止まる
		for( Sprite sp : Sprite.spriteList.get( Const.SpriteType.TYPE_PLAYER.getValue() ) )
		{
			if( sp instanceof Player )
			{
				Player player = (Player)sp;
				
				//同じ行だったら
				if( player.getTrans().getY() == this.getTrans().getY() )
				{			
					//距離を判断して止まる。
					if( player.getTrans().getX() + player.GetWidth() > this.getTrans().getX() )
					{
						//止まる
						moveflag = false;
						
						//攻撃状態にする
						StartAttack( player );
						setState( Charactor_State.ATTACK_STATE );
						
						//終了
						break;
					}
				}			
			}
		}
		//移動
		if(moveflag==true)move();
	}
		
	private void move()
	{
		if( getTrans().getY() == Const.LINE_1_Y )
		{
			this.Translate( new Vector3( -Const.LINE_1_SPEED ,0,0) );
			
			//座標規制
			if( getTrans().getX() < Const.LINE_LEFT_1_X )
			{
				setTrans( new Vector3( Const.LINE_LEFT_1_X , Const.LINE_1_Y , 0 ) );
			}			
		}
		else if( getTrans().getY() == Const.LINE_2_Y )
		{
			this.Translate( new Vector3( -Const.LINE_2_SPEED ,0,0) );
			
			//座標規制
			if( getTrans().getX() < Const.LINE_LEFT_2_X )
			{
				setTrans( new Vector3( Const.LINE_LEFT_2_X , Const.LINE_2_Y , 0 ) );
			}			
		}
		else if( getTrans().getY() == Const.LINE_3_Y )
		{
			this.Translate( new Vector3( -Const.LINE_3_SPEED ,0,0) );
			
			//座標規制
			if( getTrans().getX() < Const.LINE_LEFT_3_X )
			{
				setTrans( new Vector3( Const.LINE_LEFT_3_X , Const.LINE_3_Y , 0 ) );
			}			
		}		
	}
	
	@Override	
	protected void attack_state()
	{
		//攻撃する
		if( !UpdateAttack() )
		{
			//攻撃対象から外す
			setAttackTarget( null );			
			//歩行状態にする
			setState( Charactor_State.WALK_STATE );				
		}
	}
	
	@Override
	public void Damage( Charactor c )
	{
		super.Damage(c);

		//ダメージエフェクト
		AnimationEffect.Create( 
				this.getTrans().getX() , 
				this.getTrans().getY() + m_height/2  - Const.rx(0.05)/2, 
				Const.rx(0.05), 
				Const.rx(0.05), 
				3, 1, 10, R.drawable.image1, Const.SpriteType.TYPE_PLAYER.getValue());

	}	
	

}
