package com.example.scene;

import javax.microedition.khronos.opengles.GL10;

import com.example.data.DataBase;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.MainActivity;
import com.example.glsurfaceview.OpenGLSurfaceView;
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
import com.example.user.Stage;
import com.example.user.StartNotify;

public class GameScene extends Scene {
	
	private Pause pause = null;
	
	private StartNotify notify = null;
	private Score score = null;
	
	// コンストラクタ
	public GameScene()
	{
	}

	@Override
	public void Init() {
		
		//ステージ情報の取得
		Stage stage = DataBase.getPresentStage();
		
		//ステージがなかったらやめる
		if( stage == null )return;
		
		//マップの表示
		Map map = new Map();
		map.Init(0, 0, MainActivity.width, MainActivity.height, stage.getStage_background_image_id() ,  Const.SpriteType.TYPE_BG.getValue() );
		
		//プレイヤーについての出現処理を行いたい
		PlayerAppear pa = new PlayerAppear();
		pa.appear( Const.SpriteType.TYPE_OTHER.getValue() );		

		//敵についての出現処理を行いたい
		EnemyAppear ea = new EnemyAppear();
		ea.appear( Const.SpriteType.TYPE_OTHER.getValue() );		
		
		
		//城の配置
		PlayerCastle pc = new PlayerCastle(10);
		pc.Init(-Const.rx(0.12), Const.ry(0.17), Const.rw(0.28), Const.rh(0.8), stage.getStage_player_castle_id() , Const.SpriteType.TYPE_CASLE.getValue());
		
		EnemyCastle ec = new EnemyCastle(10);
		ec.Init(Const.rx(0.84), Const.ry(0.175), Const.rw(0.265), Const.rh(0.8), stage.getStage_enemy_castle_id() , Const.SpriteType.TYPE_CASLE.getValue());

		// スコア生成
		score = Score.Create();
		
		//カードの配置
		Deck deck = new Deck();
		deck.appear( Const.SpriteType.TYPE_CARD.getValue() );
		
		//一時停止
		pause = new Pause();
		
		//ゲームの通知
		notify = StartNotify.Create();
		
		//ゲームを止める
		OpenGLSurfaceView.GameStop();
		
	}

	@Override
	public void Uninit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Update() {
		
		
		//多分スレット外でシーンの遷移が入った際に
		//pauseがインスタンス化される前に呼び出されているためだと思われる。		
		
		if(notify != null)
		{
			if(notify.Update()==false)
			{
				notify = null;
			}
		}
		else
		{
			super.Update();			
		}
		
		if(pause != null)
		{
			pause.Update();
			
		}
	}

	@Override
	public void Draw(GL10 gl) {
		
		super.Draw(gl);
		
	}

}
