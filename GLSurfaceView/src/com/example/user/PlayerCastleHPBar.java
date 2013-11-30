package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Vector3;

public class PlayerCastleHPBar extends Sprite {

	Castle castle;
	
	//HPバーのスプライト
	Sprite bar;
	
	//バーの長さ
	int barLength;

	//作成
	public static PlayerCastleHPBar createPlayerCastleHPBar( Castle  c)
	{
		PlayerCastleHPBar p = new PlayerCastleHPBar(c);
		return p;
	}
	
	//コンストラクタ
	private PlayerCastleHPBar( Castle c){

		//背景
		Init(Const.rx(0.04), Const.ry(0.88), Const.rw(0.3), Const.rh(0.07), R.drawable.playerhpbar , Const.SpriteType.TYPE_TEXT.getValue());
		//バー
		bar = Sprite.Create(Const.rx(0.04), Const.ry(0.88), Const.rw(0.3), Const.rh(0.07), R.drawable.playerhpbar_in , Const.SpriteType.TYPE_TEXT.getValue());
	
		//色の指定
		this.GetTexture().SetColor(1.0f, 1.0f, 1.0f, 0.7f);
		bar.GetTexture().SetColor(1.0f, 1.0f, 1.0f, 0.7f);
		
		//長さを追加
		barLength = (int) bar.GetWidth();
		
		//キャラククター追加
		castle = c;
		
	}
	
	@Override
	public void Init() {
		super.Init();
		
	}
	
	@Override
	public boolean Update() {
		if(super.Update()==false)return false;
			
		//城のHPに応じて大きさを変更
		bar.setM_width(barLength*( (float)castle.getHp() / (float)castle.getMaxhp() ));
		bar.Translate(new Vector3());

		return true;
	}
	
	public void SetPosotion( float f )
	{
		//背景を変える
		Vector3 v = getTrans();
		v.setY(f);
		setTrans( v );
		
		//バーを変える
		bar.setTrans(v);
	}
		
	
}
