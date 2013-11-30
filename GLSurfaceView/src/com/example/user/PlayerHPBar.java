package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Vector3;
import com.example.scene.GameScene;

public class PlayerHPBar extends Sprite {

	Charactor charactor;
	
	//HP�o�[�̃X�v���C�g
	Sprite bar;
	
	//�o�[�̒���
	int barLength;

	//�쐬
	public static PlayerHPBar createPlayerHPBar( Charactor  c)
	{
		PlayerHPBar p = new PlayerHPBar(c);
		return p;
	}
	
	//�R���X�g���N�^
	private PlayerHPBar( Charactor chara ) {

		//�w�i
		Init(Const.rx(0.05), Const.ry(1), Const.rw(0.15), Const.rh(0.05), R.drawable.playerhpbar , Const.SpriteType.TYPE_TEXT.getValue());

		//�o�[
		bar = Sprite.Create(Const.rx(0.05), Const.ry(1), Const.rw(0.15), Const.rh(0.05), R.drawable.playerhpbar_in , Const.SpriteType.TYPE_TEXT.getValue());
	
		//�F��ς���
		this.GetTexture().SetColor(1, 1, 1, 0.7f);
		bar.GetTexture().SetColor(1, 1, 1, 0.7f);

		
		//������ǉ�
		barLength = (int) bar.GetWidth();
		
		//�L�����N�N�^�[�ǉ�
		charactor = chara;
		
	}
	
	@Override
	public void Init() {
		super.Init();
		
	}
	
	@Override
	public boolean Update() {
		if(super.Update()==false)return false;
		
		//�L�����N�^�����Ȃ�������I��
		if(charactor == null)
		{
			bar.remove();
			return false;
		}
		
		//�L�����N�^��HP�ɉ����đ傫����ύX
		bar.setM_width(barLength*( (float)charactor.getValue_hp() / (float)charactor.getValue_maxhp() ));
		bar.Translate(new Vector3());
		
		// HP�o�[���^�b�`���ꂽ�Ƃ�
		if( IsTouch() )
		{
			// �i���ł���Ƃ�
			if( GameScene.evolution.IsEvolution() && charactor.IsEvolution() )
			{
				// �L�����N�^�[�̐i��
				GameScene.evolution.SetEvolution(charactor);
				this.GetTexture().SetColor(1, 1, 0, 0.7f);
				bar.GetTexture().SetColor(1.0f, 1.0f, 0, 0.7f);
			}
		}
		
		if( charactor.isDead() )
		{
			//�X�v���C�g�̏���
			bar.remove();
			
			return false;
		}
		
		return true;
	}
	
	public void SetPosotion( float f )
	{
		//�w�i��ς���
		Vector3 v = getTrans();
		v.setY(f);
		setTrans( v );
		
		//�o�[��ς���
		bar.setTrans(v);
	}
	
}
