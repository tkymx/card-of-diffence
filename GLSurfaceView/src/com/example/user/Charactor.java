package com.example.user;

import java.util.LinkedList;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.SpriteAnimation;
import com.example.glsurfaceview.Texture;
import com.example.glsurfaceview.Vector3;

public abstract class Charactor extends SpriteAnimation {

	//状態の管理
	enum Charactor_State{ WALK_STATE , ATTACK_STATE , ATTACK_AFTER_STATE };
	
	private int lineNum;
		
	//体力や攻撃力
	int value_hp;
	int value_maxhp;
	int value_attack;
	int value_moveSpeed;

	//状態の管理をする
	Charactor_State state;
	
	//攻撃対象(城＞敵の順番)
	Charactor attackTarget;
	Castle castleTarget;
	
	//行動アニメーション
	Texture walkTexture;
	Texture attackBeforeTexture;	
	Texture attackAfterTexture;	
	
	//セッターとゲッター
	public int getValue_hp() {return value_hp;}
	public void setValue_hp(int value_hp) {this.value_hp = value_hp;}
	public int getValue_maxhp() {return value_maxhp;}
	public void setValue_maxhp(int value_maxhp){this.value_maxhp = value_maxhp;}
	public int getValue_attack() {return value_attack;}
	public void setValue_attack(int value_attack) {this.value_attack = value_attack;}	
	public int getValue_moveSpeed() {return value_moveSpeed;}
	public void setValue_moveSpeed(int value_moveSpeed) {this.value_moveSpeed = value_moveSpeed;}

	//状態のセット
	public Charactor_State getState() {return state;}
	public void setState(Charactor_State state) 
	{
		this.state = state;
		
		//状態によってテクスチャを変える
		if( state == Charactor_State.WALK_STATE )
		{
			//テクスチャのセット
			SetTexture(walkTexture);			
		}
		else if( state == Charactor_State.ATTACK_STATE )
		{
			//攻撃をセット
			SetTexture(attackBeforeTexture);			
		}
		else if( state == Charactor_State.ATTACK_AFTER_STATE )
		{
			//攻撃後をセット
			SetTexture(attackAfterTexture);			
		}
		
	}		
	
	
	//コンストラクタ
	public Charactor( int hp , int attack , int speed , int walk_id , int attak_before_id , int attak_after_id )
	{
		//基本情報設定
		value_hp = value_maxhp = hp;
		value_attack = attack;
		value_moveSpeed = speed;
		
		//歩く状態
		state = Charactor_State.WALK_STATE;
		
		//攻撃対象
		attackTarget = null;
		castleTarget = null;
		
		//行動アニメーション
		walkTexture = new Texture(walk_id);
		attackBeforeTexture = new Texture(attak_before_id);
		attackAfterTexture = new Texture(attak_after_id);
		
	}
	
	// 初期化処理
	public void Init()
	{
	}	
	
	// 更新処理
	protected abstract boolean IsTarget( Sprite sp );
	protected void move_state()
	{
		//歩くかどうか
		boolean moveflag = true;
		
		//リストの作成
		LinkedList<Sprite> list = new LinkedList<Sprite>();
		for( Sprite sp : Sprite.spriteList.get( Const.SpriteType.TYPE_ENEMY.getValue() ) )list.add(sp);
		for( Sprite sp : Sprite.spriteList.get( Const.SpriteType.TYPE_PLAYER.getValue() ) )list.add(sp);
		
		//敵がいたら止まる
		for( Sprite sp : list )
		{
			if( IsTarget(sp) )
			{
				
				//同じ行だったら
				if( sp.getTrans().getY() == this.getTrans().getY() )
				{			
					//距離を判断して止まる。
					if( sp.Collission(this))
					{
						//移動させない
						moveflag = false;
						
						//攻撃状態にする
						StartAttack( (Charactor)sp );
						
						//終了
						break;						
					}
				}			
			}
		}
		//移動
		if(moveflag==true)
		{
			//接触していなかったらターゲットをなしにする
			attackTarget = null;
			//移動へ
			move();					
		}
	}
	private void move()
	{
	
		//城にアタックするかどうかを決めたフラグ
		boolean castle_attack_flag = false;
		
		if( getTrans().getY() == Const.LINE_1_Y )
		{
			this.Translate( new Vector3( Const.LINE_1_SPEED*value_moveSpeed ,0,0) );
			
			//座標規制(城まで進んだ)
			if( getTrans().getX() < Const.LINE_LEFT_1_X )
			{
				setTrans( new Vector3( Const.LINE_LEFT_1_X , Const.LINE_1_Y , 0 ) );
				//城フラグを立てる
				castle_attack_flag = true;
			}			
			if( getTrans().getX() > Const.LINE_RIGHT_1_X )
			{
				setTrans( new Vector3( Const.LINE_RIGHT_1_X , Const.LINE_1_Y , 0 ) );
				//城フラグを立てる
				castle_attack_flag = true;
			}			
		}
		else if( getTrans().getY() == Const.LINE_2_Y )
		{
			this.Translate( new Vector3( Const.LINE_2_SPEED*value_moveSpeed ,0,0) );
			
			//座標規制(城まで進んだ)
			if( getTrans().getX() < Const.LINE_LEFT_2_X )
			{
				setTrans( new Vector3( Const.LINE_LEFT_2_X , Const.LINE_2_Y , 0 ) );
				//城フラグを立てる
				castle_attack_flag = true;
			}			
			if( getTrans().getX() > Const.LINE_RIGHT_2_X )
			{
				setTrans( new Vector3( Const.LINE_RIGHT_2_X , Const.LINE_2_Y , 0 ) );
				//城フラグを立てる
				castle_attack_flag = true;
			}			
	
		}
		else if( getTrans().getY() == Const.LINE_3_Y )
		{
			this.Translate( new Vector3( Const.LINE_3_SPEED*value_moveSpeed ,0,0) );
			
			//座標規制(城まで進んだ)
			if( getTrans().getX() < Const.LINE_LEFT_3_X )
			{
				setTrans( new Vector3( Const.LINE_LEFT_3_X , Const.LINE_3_Y , 0 ) );
				//城フラグを立てる
				castle_attack_flag = true;
			}			
			if( getTrans().getX() > Const.LINE_RIGHT_3_X )
			{
				setTrans( new Vector3( Const.LINE_RIGHT_3_X , Const.LINE_3_Y , 0 ) );
				//城フラグを立てる
				castle_attack_flag = true;
			}			
	
		}		
		
		//城だったら
		if( castle_attack_flag )
		{
			//城でループする
			for( Sprite sprite : Sprite.spriteList.get( Const.SpriteType.TYPE_CASLE.getValue() ) )
			{
				//敵の城だったら攻撃対象としてセット
				if( IsTarget(sprite) )
				{
					//城をセットして攻撃状態にする
					StartAttack( (Castle)sprite );
				}
			}
		}
		else
		{
			//一番橋じゃなかったら城を攻撃しない
			castleTarget = null;
		}
	}	
	protected void attack_state()
	{	
		//終わっていたら攻撃して次へ
		if( isEnd )
		{
			ActionAttack();
			setState( Charactor_State.ATTACK_AFTER_STATE );
		}
	}
	protected void attack_after_state()
	{
		//終わっていたら攻撃して次へ
		if( isEnd )
		{
			//歩きへ
			setState( Charactor_State.WALK_STATE );
			
			//対象の変更
			attackTarget = null;
			castleTarget = null;
		}
	}
	
	@Override
	public boolean Update()
	{
		if(!super.Update())return false;
		
		//死んだらタスクから消える
		if( isDead() )
			return false;	
		
		if( state == Charactor_State.WALK_STATE )
		{
			move_state();
		}
		else if( state == Charactor_State.ATTACK_STATE )
		{
			attack_state();
		}
		else if( state == Charactor_State.ATTACK_AFTER_STATE )
		{
			attack_after_state();
		}
		
		return true;
	}		
	
	//攻撃始め
	public void StartAttack( Charactor c )
	{
		attackTarget = c;
		setState(Charactor_State.ATTACK_STATE);
	}
	public void StartAttack( Castle c )
	{
		castleTarget = c;
		setState(Charactor_State.ATTACK_STATE);		
	}
	//攻撃実行
	public void ActionAttack()
	{
		//敵が優先順位が高い
		if( attackTarget != null )
		{
			attackTarget.Damage(this);			
		}
		else if( castleTarget != null )
		{
			castleTarget.Damage(this);
		}
	}
	
	public void Damage( Charactor c )
	{
		value_hp -= c.getValue_attack();	
	}
	public void Damage( int c )
	{
		value_hp -= c;	
	}
	

	//死んでいるかどうか
	boolean isDead()
	{
		return value_hp <= 0;
	}
	
	// ラインのセット
	public void setLineNum( int line )
	{
		lineNum = line;
	}

	// ラインの番号取得
	public int getLineNum()
	{
		return lineNum;
	}

}
