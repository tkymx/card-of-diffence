package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Vector3;

public class EnemyCastleHPBar extends Sprite {

	Castle castle;
	
	//HP�o�[�̃X�v���C�g
	Sprite bar;
	
	//�o�[�̒���
	int barLength;

	//�쐬
	public static EnemyCastleHPBar createEnemyCastleHPBar( Castle  c)
	{
		EnemyCastleHPBar p = new EnemyCastleHPBar(c);
		return p;
	}
	
	//�R���X�g���N�^
	private EnemyCastleHPBar( Castle c){

		//�w�i
		Init(Const.rx(1-0.05-0.3), Const.ry(0.88), Const.rw(0.3), Const.rh(0.07), R.drawable.enemyhpbar , Const.SpriteType.TYPE_TEXT.getValue());
		//�o�[
		bar = Sprite.Create(Const.rx(1-0.05-0.3), Const.ry(0.88), Const.rw(0.3), Const.rh(0.07), R.drawable.enemyhpbar_in , Const.SpriteType.TYPE_TEXT.getValue());
		
		//������ǉ�
		barLength = (int) bar.GetWidth();
		
		//�F��ς���
		this.GetTexture().SetColor(1, 1, 1, 0.7f);
		bar.GetTexture().SetColor(1, 1, 1, 0.7f);
		
		
		//�L�����N�N�^�[�ǉ�
		castle = c;
		
	}
	
	@Override
	public void Init() {
		super.Init();
		
	}
	
	@Override
	public boolean Update() {
		if(super.Update()==false)return false;
			
		//���HP�ɉ����đ傫����ύX
		bar.setM_width(barLength*( (float)castle.getHp() / (float)castle.getMaxhp() ));
		
		//HP��\������ʒu���C��
		Vector3 v = new Vector3( getTrans() );
		v.setX( Const.rx(1-0.05) - bar.GetWidth() );
		bar.setTrans(v);

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
