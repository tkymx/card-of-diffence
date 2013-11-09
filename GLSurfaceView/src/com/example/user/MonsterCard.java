package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.Touch;

public class MonsterCard extends Card {
	
	//�������쐬�����L�����N�^�̏��
	Charactor charactor;
	
	//�������\�b�h
	public static MonsterCard CreateMonsterCard( int left, int top, int width, int height, int id,int need )
	{
		MonsterCard mc = new MonsterCard(left, top, width, height, id,need);
		return mc;
	}
	
	//�R���X�g���N�^
	protected MonsterCard(int left, int top, int width, int height, int id ,int need) {
		super(left, top, width, height, id ,need);
		
		//�L�����N�^�[���n�ߖ����ɂ���
		charactor = null;
	}
	

	@Override
	public boolean Update() {	
		if(super.Update()==false)return false;
		
		boolean alive = false;
		
		//�g������
		if( charactor != null )
		{
			//�L�����N�^�����񂾂�g����悤�ɂ��邤		
			if( charactor.isDead() )
			{
				//�J�[�h���g����悤�ɂ���
				permitUse();			
			}
		}
		else if( !isUse )
		{
			//�J�[�h���g����悤�ɂ���
			permitUse();			
		}
		
		
		return true;
	}
	
	@Override
	public void SelectedUpdate() {

		Touch touch = Touch.getInstance();
		if( touch.IsTouch() )
		{
			//���W���擾����
			int y = (int) touch.getY();
						
			//��ɂ���ĕ\������B
			if( y > Const.LINE_1_Y )
			{
				charactor = PlayerAppear.CreatePlayer( Const.LINE_1_Y , "" );					
			}
			else if( y > Const.LINE_2_Y )
			{
				charactor = PlayerAppear.CreatePlayer( Const.LINE_2_Y , "" );										
			}
			else if( y > Const.LINE_3_Y )
			{					
				charactor = PlayerAppear.CreatePlayer( Const.LINE_3_Y , "" );					
			}
			else
			{
				//�ʂ̂Ƃ���Ȃ�I��
				return;
			}
			
			//�g�p�ς݂ɂ���
			use();
		}
	}
	
	//������ۂɃL�����N�^��null�ʂ���
	@Override
	public void permitUse() {
		super.permitUse();
		
		//�L�����N�^��null�ɂ���
		charactor = null;
		
	}
	
}
