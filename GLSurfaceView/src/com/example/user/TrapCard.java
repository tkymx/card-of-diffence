package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.Touch;

public abstract class TrapCard extends Card {
	
	//�R���X�g���N�^
	protected TrapCard(){};
	protected TrapCard(int left, int top, int width, int height, int id ,int need) {
		super(left, top, width, height, id ,need);
	
	}
	
	// �X�V����
	public boolean Update()
	{
		if(super.Update()==false)return false;
		
		return true;		
	}	
	
	//�����ꂽ�ۂɐݒu���鑀������߂�
	public abstract void SetTrap( int line , int x );
	//�I������Ă��鎞�̏���
	@Override
	public void SelectedUpdate() {

		Touch touch = Touch.getInstance();
		if( touch.IsTouch() )
		{
			//���W���擾����
			int y = (int) touch.getY();						
			int x = (int) touch.getX();						
			
			//��ɂ���ĕ\������B
			if( y > Const.LINE_1_Y && x > Const.LINE_LEFT_1_X && x < Const.LINE_RIGHT_1_X )
			{
				SetTrap( Const.LINE_1_Y , x );
			}
			else if( y > Const.LINE_2_Y && y < Const.LINE_1_Y && x > Const.LINE_LEFT_2_X && x < Const.LINE_RIGHT_2_X )
			{
				SetTrap( Const.LINE_2_Y , x );
			}
			else if( y > Const.LINE_3_Y && y < Const.LINE_2_Y && x > Const.LINE_LEFT_3_X && x < Const.LINE_RIGHT_3_X )
			{					
				SetTrap( Const.LINE_3_Y , x );
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
		
		
	}
}
