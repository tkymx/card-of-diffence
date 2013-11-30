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
		
		// �R���X�g���N�^
		public Tutorial()
		{
		}
		
		// ������
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
		
		// �摜�̕ύX
		public void changeImage( int id )
		{
			tutoImage.GetTexture().SetID(id);
		}
		
		// �`���[�g���A���̕ύX
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
	
	// �R���X�g���N�^
	public GameScene()
	{
	}

	@Override
	public void Init() {
		
		BGMSound.BGMChoose[SOUND.SOUND_GAME.getValue()].Play();
		
		//�X�e�[�W���̎擾
		Stage stage = DataBase.getPresentStage();
		
		//�X�e�[�W���Ȃ��������߂�
		if( stage == null )return;
		
		//�}�b�v�̕\��
		Map map = new Map();
		map.Init(0, 0, MainActivity.width, MainActivity.height, stage.getStage_background_image_id() ,  Const.SpriteType.TYPE_BG.getValue() );
	
		//HP�Q�[�W�̃\�[�g���s��
		CharactorHPBarSort bar = new CharactorHPBarSort();
		bar.appear( Const.SpriteType.TYPE_OTHER.getValue() );
		
		//�v���C���[�ɂ��Ă̏o���������s������
		PlayerAppear pa = new PlayerAppear();
		pa.appear( Const.SpriteType.TYPE_OTHER.getValue() );		

		//�G�ɂ��Ă̏o���������s������
		EnemyAppear ea = new EnemyAppear();
		ea.appear( Const.SpriteType.TYPE_OTHER.getValue() );		
		
		
		//��̔z�u
		PlayerCastle pc = new PlayerCastle(10);
		pc.Init(-Const.rx(0.12), Const.ry(0.17), Const.rw(0.28), Const.rh(0.8), stage.getStage_player_castle_id() , Const.SpriteType.TYPE_CASLE.getValue());
		
		EnemyCastle ec = new EnemyCastle(10);
		ec.Init(Const.rx(0.84), Const.ry(0.175), Const.rw(0.265), Const.rh(0.8), stage.getStage_enemy_castle_id() , Const.SpriteType.TYPE_CASLE.getValue());

		// �X�R�A����
		DataBase.setPresentScore( Score.Create( Const.rx(0.4) , Const.ry(0.95) , Const.rw(0.2)/Const.MAX_SCORE_NUM , Const.rh(0.07) ) );
		
		//�J�[�h�̔z�u
		Deck deck = new Deck();
		deck.appear( Const.SpriteType.TYPE_OTHER.getValue() );
		
		// �i���{�^�����̎���
		evolution = new Evolution();
		
		//�ꎞ��~
		pause = new Pause();
		
		//�Q�[���̒ʒm
		notify = StartNotify.Create();
		
		//���ʉ�ʂ̕\���ɂ���
		rnotify = ResultNotify.Create();
		//���U���g�\�����I�t�ɂ���
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
		
		//�Q�[�����~�߂�
		OpenGLSurfaceView.GameStop();
		
	}

	@Override
	public void Uninit() {
		// TODO Auto-generated method stub
		BGMSound.BGMChoose[SOUND.SOUND_GAME.getValue()].Stop();
	}

	@Override
	public void Update() {
		
		
		//�����X���b�g�O�ŃV�[���̑J�ڂ��������ۂ�
		//pause���C���X�^���X�������O�ɌĂяo����Ă��邽�߂��Ǝv����B		
		
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
		
		//���U���g���
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
