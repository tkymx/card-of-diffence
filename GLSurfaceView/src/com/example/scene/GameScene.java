package com.example.scene;

import javax.microedition.khronos.opengles.GL10;

import com.example.data.DataBase;
import com.example.glsurfaceview.BGMSound;
import com.example.glsurfaceview.Button;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.Const.SpriteType;
import com.example.glsurfaceview.Evolution;
import com.example.glsurfaceview.MainActivity;
import com.example.glsurfaceview.OpenGLSurfaceView;
import com.example.glsurfaceview.Pause;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Scene;
import com.example.glsurfaceview.Score;
import com.example.glsurfaceview.BGMSound.SOUND;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Touch;
import com.example.glsurfaceview.Vector3;
import com.example.user.CharactorHPBarSort;
import com.example.user.Deck;
import com.example.user.Enemy;
import com.example.user.EnemyAppear;
import com.example.user.EnemyCastle;
import com.example.user.Map;
import com.example.user.Player;
import com.example.user.PlayerAppear;
import com.example.user.PlayerCastle;
import com.example.user.ResultNotify;
import com.example.user.Stage;
import com.example.user.StartNotify;

public class GameScene extends Scene {
	
	public class Tutorial {
		public boolean tuto1, tuto2, tuto3, tuto4;
		public boolean tutorial;
		private Sprite tutoImage;
		private Button skip;
		private int nowTuto;
		public boolean appearEnemy;
		public Enemy enemy = null;
		public Player player = null;
		public float x, y;
		
		// コンストラクタ
		public Tutorial()
		{
		}
		
		// 初期化
		public void Init()
		{
			int w = MainActivity.width;
			int h = MainActivity.height;
			
			tuto2 = tuto3 = tuto4 = false;
			tutorial = tuto1 = true;
			appearEnemy = false;
			nowTuto = 0;
			tutoImage = Sprite.Create(0, 0, w, h, R.drawable.asist1, SpriteType.TYPE_TUTORIAL.getValue());
			skip = Button.Create(w - w/3, h - h/4, w/5, h/5, R.drawable.skip);
			
			tutoImage.GetTexture().SetColor(0, 0, 0, 0);
			skip.GetTexture().SetColor(0, 0, 0, 0);
		}
		
		public void  Update()
		{
			if( skip.IsTouch() )
			{
				tutoImage.GetTexture().SetColor(0, 0, 0, 0);
				skip.GetTexture().SetColor(0, 0, 0, 0);
				
				tutorial = false;
				tuto1 = false;
				tuto2 = false;
				tuto3 = false;
				tuto4 = false;
			}
			
			if( tuto2 || tuto3 || tuto4 )
			{
				if( enemy != null )
				{
					enemy.setTrans(new Vector3( Const.LINE_RIGHT_2_X  , Const.LINE_2_Y , 0 ));
				}
			}
			if( tuto4 )
			{
				if( player != null )
				{
					player.setTrans(new Vector3(x, y, 0));
				}
			}
			
			if( tuto1 || tuto4 )
			{
				if( Touch.getInstance().IsTouch() )
				{
					changeTutorial();
				}
			}
		}
		
		// 画像の変更
		public void changeImage( int id )
		{
			tutoImage.GetTexture().SetID(id);
		}
		
		// チュートリアルの変更
		public void changeTutorial()
		{
			nowTuto++;
			
			switch( nowTuto )
			{
			case 1:
				tuto1 = true;
				
				tutoImage.GetTexture().SetColor(1, 1, 1, 1);
				skip.GetTexture().SetColor(1, 1, 1, 1);
				
				break;
			case 2:
				tuto1 = false;
				tuto2 = true;
				
				changeImage(R.drawable.asist2);
				
				break;
				
			case 3:
				tuto2 = false;
				tuto3 = true;
				
				changeImage(R.drawable.asist3);
				
				break;
				
			case 4:
				tuto3 = false;
				tuto4 = true;
				
				changeImage(R.drawable.asist4);
				
				break;
				
			case 5:
				tuto4 = false;
				tutorial = false;
				
				tutoImage.GetTexture().SetColor(0, 0, 0, 0);
				skip.GetTexture().SetColor(0, 0, 0, 0);
				
				break;
			}
		}
	}
	
	private Pause pause = null;
	
	private StartNotify notify = null;
	private ResultNotify rnotify = null;
	public static Evolution evolution = null;
	public static Tutorial tutorial = null;
	
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
		
		tutorial = new Tutorial();
		tutorial.Init();
		
		if( stage.getLevel() != 1 )
		{
			tutorial.tutorial = false;
			tutorial.tuto1 = false;
			tutorial.tuto2 = false;
			tutorial.tuto3 = false;
			tutorial.tuto4 = false;
		}
		
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
				if( tutorial.tutorial )tutorial.changeTutorial();
			}
		}
		else
		{
			if( tutorial.tutorial ) tutorial.Update();
			
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
