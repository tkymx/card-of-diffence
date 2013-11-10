package com.example.scene;

import javax.microedition.khronos.opengles.GL10;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.MainActivity;
import com.example.glsurfaceview.Pause;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Scene;
import com.example.glsurfaceview.Score;
import com.example.user.Deck;
import com.example.user.EnemyAppear;
import com.example.user.EnemyCastle;
import com.example.user.Map;
import com.example.user.PlayerAppear;
import com.example.user.PlayerCastle;

public class GameScene extends Scene {
	
	private Pause pause;
	
	// コンストラクタ
	public GameScene()
	{
	}

	@Override
	public void Init() {
		// TODO Auto-generated method stub
		
		//マップの表示
		Map map = new Map();
		map.Init(0, 0, MainActivity.width, MainActivity.height, R.drawable.map ,  Const.SpriteType.TYPE_BG.getValue() );
		
		//プレイヤーについての出現処理を行いたい
		PlayerAppear pa = new PlayerAppear();
		pa.appear( Const.SpriteType.TYPE_OTHER.getValue() );		

		//敵についての出現処理を行いたい
		EnemyAppear ea = new EnemyAppear();
		ea.appear( Const.SpriteType.TYPE_OTHER.getValue() );		
		
		
		//城の配置
		PlayerCastle pc = new PlayerCastle(10);
		pc.Init(-Const.rx(0.12), Const.ry(0.17), Const.rw(0.28), Const.rh(0.8), R.drawable.player_castle, Const.SpriteType.TYPE_CASLE.getValue());
		
		EnemyCastle ec = new EnemyCastle(10);
		ec.Init(Const.rx(0.84), Const.ry(0.175), Const.rw(0.265), Const.rh(0.8), R.drawable.enemy_castle, Const.SpriteType.TYPE_CASLE.getValue());

		// スコア生成
		Score.getInstance().Init();
		
		//カードの配置
		Deck deck = new Deck();
		deck.appear( Const.SpriteType.TYPE_CARD.getValue() );
		
		pause = new Pause();
	}

	@Override
	public void Uninit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		pause.Update();
	}

	@Override
	public void onDraw(GL10 gl) {
		// TODO Auto-generated method stub
		
	}

}
