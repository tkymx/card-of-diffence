package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Vector3;
import com.example.scene.GameScene;

public class PlayerHPBar extends Sprite {

	Charactor charactor;
	
	//HPバーのスプライト
	Sprite bar;
	
	//バーの長さ
	int barLength;

	//作成
	public static PlayerHPBar createPlayerHPBar( Charactor  c)
	{
		PlayerHPBar p = new PlayerHPBar(c);
		return p;
	}
	
	//コンストラクタ
	private PlayerHPBar( Charactor chara ) {

		//背景
		Init(Const.rx(0.05), Const.ry(1), Const.rw(0.15), Const.rh(0.05), R.drawable.barbase , Const.SpriteType.TYPE_TEXT.getValue());
		//バー
		bar = Sprite.Create(Const.rx(0.05), Const.ry(1), Const.rw(0.15), Const.rh(0.05), R.drawable.barbase , Const.SpriteType.TYPE_TEXT.getValue());
	
		//色の指定
		this.GetTexture().SetColor(0.0f, 0.0f, 0.0f, 0.7f);
		bar.GetTexture().SetColor(1.0f, 0.7f, 0.0f, 1.0f);
		
		//長さを追加
		barLength = (int) bar.GetWidth();
		
		//キャラククター追加
		charactor = chara;
		
	}
	
	@Override
	public void Init() {
		super.Init();
		
	}
	
	@Override
	public boolean Update() {
		if(super.Update()==false)return false;
		
		//キャラクタがいなかったら終了
		if(charactor == null)
		{
			bar.remove();
			return false;
		}
		
		//キャラクタのHPに応じて大きさを変更
		bar.setM_width(barLength*( (float)charactor.getValue_hp() / (float)charactor.getValue_maxhp() ));
		bar.Translate(new Vector3());
		
		// HPバーがタッチされたとき
		if( IsTouch() )
		{
			// 進化できるとき
			if( GameScene.evolution.IsEvolution() && charactor.IsEvolution() )
			{
				// キャラクターの進化
				GameScene.evolution.SetEvolution(charactor);
				bar.GetTexture().SetColor(1.0f, 1.0f, 0, 1.0f);
			}
		}
		
		if( charactor.isDead() )
		{
			//スプライトの消去
			bar.remove();
			
			return false;
		}
		
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
