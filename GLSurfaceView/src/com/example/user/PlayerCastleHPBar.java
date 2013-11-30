package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Vector3;

public class PlayerCastleHPBar extends Sprite {

	Castle castle;
	
	//HP�o�[�̃X�v���C�g
	Sprite bar;
	
	//�o�[�̒���
	int barLength;

	//�쐬
	public static PlayerCastleHPBar createPlayerCastleHPBar( Castle  c)
	{
		PlayerCastleHPBar p = new PlayerCastleHPBar(c);
		return p;
	}
	
	//�R���X�g���N�^
	private PlayerCastleHPBar( Castle c){

		//�w�i
		Init(Const.rx(0.04), Const.ry(0.88), Const.rw(0.3), Const.rh(0.07), R.drawable.playerhpbar , Const.SpriteType.TYPE_TEXT.getValue());
		//�o�[
		bar = Sprite.Create(Const.rx(0.04), Const.ry(0.88), Const.rw(0.3), Const.rh(0.07), R.drawable.playerhpbar_in , Const.SpriteType.TYPE_TEXT.getValue());
	
		//�F�̎w��
		this.GetTexture().SetColor(1.0f, 1.0f, 1.0f, 0.7f);
		bar.GetTexture().SetColor(1.0f, 1.0f, 1.0f, 0.7f);
		
		//������ǉ�
		barLength = (int) bar.GetWidth();
		
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
		bar.Translate(new Vector3());

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
