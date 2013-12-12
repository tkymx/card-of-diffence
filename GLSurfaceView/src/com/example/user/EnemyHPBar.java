package com.example.user;

import com.example.data.CardInformation;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Vector3;
import com.example.glsurfaceview.Const.SpriteType;

public class EnemyHPBar extends Sprite {

	Charactor charactor;
	
	//HPバーのスプライト
	Sprite bar;
	
	//バーの長さ
	int barLength;
	//画像
	Sprite image;	

	//作成
	public static EnemyHPBar createEnemyHPBar( Charactor  c)
	{
		EnemyHPBar p = new EnemyHPBar(c);
		return p;
	}
	
	//コンストラクタ
	private EnemyHPBar( Charactor chara ) {

		int w = Const.rw(0.15);
		int h = Const.rh(0.05);
		
		int x = Const.rx(1-0.05) - w ; 
		//見えないところ
		int y = Const.ry(1); 
		
		//背景
		Init(x,y,w,h, R.drawable.enemyhpbar , Const.SpriteType.TYPE_TEXT.getValue());
		//バー
		bar = Sprite.Create(x,y,w,h, R.drawable.enemyhpbar_in , Const.SpriteType.TYPE_TEXT.getValue());
		
		//画像		
		CardInformation ci = CardInformation.GetCardInformaionFromName(chara.getName());
		image = Sprite.Create(Const.rx(1-0.005)-Const.card_width*0.5f, Const.ry(1), Const.card_width*0.5f, Const.card_height*0.5f/2, ci.getCard_id() , SpriteType.TYPE_TEXT.getValue());
		//画像の上を切る
		float UV[] = {
				0.0f, 0.5f,
				0.0f, 0.0f,
				1.0f, 0.5f,
				1.0f, 0.0f,
			};
		image.GetTexture().SetUV(UV);		
		
		//色を変える
		this.GetTexture().SetColor(1, 1, 1, 0.7f);
		bar.GetTexture().SetColor(1, 1, 1, 0.7f);
		
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
			image.remove();
			return false;
		}
		
		//キャラクタのHPに応じて大きさを変更
		bar.setM_width(barLength*( (float)charactor.getValue_hp() / (float)charactor.getValue_maxhp() ));
		
		//HPを表示する位置も修正
		Vector3 v = new Vector3( getTrans() );
		v.setX( Const.rx(1-0.05) - bar.GetWidth() );
		bar.setTrans(v);
		
		if( charactor.isDead() )
		{
			//スプライトの消去
			bar.remove();
			image.remove();
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
		v = new Vector3( bar.getTrans() );
		v.setY(f);
		bar.setTrans(v);
		
		v = new Vector3(image.getTrans() );
		v.setY( f - Const.rh(0.01) );
		image.setTrans(v);
	}
		
	
}
