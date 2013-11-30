package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Vector3;

public class EnemyHPBar extends Sprite {

	Charactor charactor;
	
	//HP�o�[�̃X�v���C�g
	Sprite bar;
	
	//�o�[�̒���
	int barLength;

	//�쐬
	public static EnemyHPBar createEnemyHPBar( Charactor  c)
	{
		EnemyHPBar p = new EnemyHPBar(c);
		return p;
	}
	
	//�R���X�g���N�^
	private EnemyHPBar( Charactor chara ) {

		int w = Const.rw(0.15);
		int h = Const.rh(0.05);
		
		int x = Const.rx(1-0.05) - w ; 
		//�����Ȃ��Ƃ���
		int y = Const.ry(1); 
		
		//�w�i
		Init(x,y,w,h, R.drawable.barbase , Const.SpriteType.TYPE_TEXT.getValue());
		//�o�[
		bar = Sprite.Create(x,y,w,h, R.drawable.barbase , Const.SpriteType.TYPE_TEXT.getValue());
	
		//�F�̎w��
		this.GetTexture().SetColor(0.0f, 0.0f, 0.0f, 0.7f);
		bar.GetTexture().SetColor(1.0f, 0.7f, 0.0f, 1.0f);
		
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
		
		//HP��\������ʒu���C��
		Vector3 v = new Vector3( getTrans() );
		v.setX( Const.rx(1-0.05) - bar.GetWidth() );
		bar.setTrans(v);
		
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
