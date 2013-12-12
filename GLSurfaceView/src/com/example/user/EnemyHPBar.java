package com.example.user;

import com.example.data.CardInformation;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Vector3;
import com.example.glsurfaceview.Const.SpriteType;

public class EnemyHPBar extends Sprite {

	Charactor charactor;
	
	//HP�o�[�̃X�v���C�g
	Sprite bar;
	
	//�o�[�̒���
	int barLength;
	//�摜
	Sprite image;	

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
		Init(x,y,w,h, R.drawable.enemyhpbar , Const.SpriteType.TYPE_TEXT.getValue());
		//�o�[
		bar = Sprite.Create(x,y,w,h, R.drawable.enemyhpbar_in , Const.SpriteType.TYPE_TEXT.getValue());
		
		//�摜		
		CardInformation ci = CardInformation.GetCardInformaionFromName(chara.getName());
		image = Sprite.Create(Const.rx(1-0.005)-Const.card_width*0.5f, Const.ry(1), Const.card_width*0.5f, Const.card_height*0.5f/2, ci.getCard_id() , SpriteType.TYPE_TEXT.getValue());
		//�摜�̏��؂�
		float UV[] = {
				0.0f, 0.5f,
				0.0f, 0.0f,
				1.0f, 0.5f,
				1.0f, 0.0f,
			};
		image.GetTexture().SetUV(UV);		
		
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
			image.remove();
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
			image.remove();
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
		v = new Vector3( bar.getTrans() );
		v.setY(f);
		bar.setTrans(v);
		
		v = new Vector3(image.getTrans() );
		v.setY( f - Const.rh(0.01) );
		image.setTrans(v);
	}
		
	
}
