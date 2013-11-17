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
	
	// �R���X�g���N�^
	public GameScene()
	{
	}

	@Override
	public void Init() {
		
		//�X�e�[�W���̎擾
		Stage stage = DataBase.getPresentStage();
		
		//�X�e�[�W���Ȃ��������߂�
		if( stage == null )return;
		
		//�}�b�v�̕\��
		Map map = new Map();
		map.Init(0, 0, MainActivity.width, MainActivity.height, stage.getStage_background_image_id() ,  Const.SpriteType.TYPE_BG.getValue() );
		
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
		score = Score.Create();
		
		//�J�[�h�̔z�u
		Deck deck = new Deck();
		deck.appear( Const.SpriteType.TYPE_CARD.getValue() );
		
		//�ꎞ��~
		pause = new Pause();
		
		//�Q�[���̒ʒm
		notify = StartNotify.Create();
		
		//�Q�[�����~�߂�
		OpenGLSurfaceView.GameStop();
		
	}

	@Override
	public void Uninit() {
		// TODO Auto-generated method stub

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
