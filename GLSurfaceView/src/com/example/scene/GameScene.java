package com.example.scene;

import javax.microedition.khronos.opengles.GL10;

import android.provider.ContactsContract.Contacts.Data;

import com.example.data.DataBase;
import com.example.glsurfaceview.BGMSound;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.Evolution;
import com.example.glsurfaceview.MainActivity;
import com.example.glsurfaceview.OpenGLSurfaceView;
import com.example.glsurfaceview.Pause;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Scene;
import com.example.glsurfaceview.Score;
import com.example.glsurfaceview.BGMSound.SOUND;
import com.example.user.CharactorHPBarSort;
import com.example.user.Deck;
import com.example.user.EnemyAppear;
import com.example.user.EnemyCastle;
import com.example.user.Map;
import com.example.user.PlayerAppear;
import com.example.user.PlayerCastle;
import com.example.user.ResultNotify;
import com.example.user.Stage;
import com.example.user.StartNotify;

public class GameScene extends Scene {
	
	private Pause pause = null;
	
	private StartNotify notify = null;
	private ResultNotify rnotify = null;
	private Evolution evolution = null;
	
	// コンストラクタ
	public GameScene()
	{
	}

	@Override
	public void Init() {
		
		BGMSound.BGMChoose[SOUND.SOUND_GAME.getValue()].Play();
		
		//ステージ情報の取得
		Stage stage = DataBase.getPresentStage();
		
		//ステージがなかったらやめる
		if( stage == null )return;
		
		//マップの表示
		Map map = new Map();
		map.Init(0, 0, MainActivity.width, MainActivity.height, stage.getStage_background_image_id() ,  Const.SpriteType.TYPE_BG.getValue() );
	
		//HPゲージのソートを行う
		CharactorHPBarSort bar = new CharactorHPBarSort();
		bar.appear( Const.SpriteType.TYPE_OTHER.getValue() );
		
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
		DataBase.setPresentScore( Score.Create( Const.rx(0.4) , Const.ry(0.95) , Const.rw(0.2)/Const.MAX_SCORE_NUM , Const.rh(0.07) ) );
		
		//カードの配置
		Deck deck = new Deck();
		deck.appear( Const.SpriteType.TYPE_OTHER.getValue() );
		
		// 進化ボタン等の実装
		evolution = new Evolution();
		
		//一時停止
		pause = new Pause();
		
		//ゲームの通知
		notify = StartNotify.Create();
		
		//結果画面の表示について
		rnotify = ResultNotify.Create();
		//リザルト表示をオフにする
		DataBase.setResult(false);
		
		//ゲームを止める
		OpenGLSurfaceView.GameStop();
		
	}

	@Override
	public void Uninit() {
		// TODO Auto-generated method stub
		BGMSound.BGMChoose[SOUND.SOUND_GAME.getValue()].Stop();
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
			evolution.Update();
			super.Update();			
		}
		
		if(pause != null)
		{
			pause.Update();
			
		}
		
		//リザルト画面
		if( DataBase.isResult() )
		{
			rnotify.Update();
		}
	}

	@Override
	public void Draw(GL10 gl) {
		
		super.Draw(gl);
		
	}
}
