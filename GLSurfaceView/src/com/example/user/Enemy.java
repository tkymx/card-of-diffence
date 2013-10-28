package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Vector3;

/**
 * 
 * �������ɁAsprite�̃��\�b�h��p���āA�摜�̒ǉ����s��
 * 
 * 
 * @author ultra-tkymx
 *
 */

public class Enemy extends Sprite {

	// ����������
	public void Init()
	{
		
	}
	
	// �X�V����
	public boolean Update()
	{
		//�������ǂ���
		boolean moveflag = true;
		//�G��������~�܂�
		for( Sprite sp : Sprite.spriteList.get( Const.SpriteType.TYPE_PLAYER.getValue() ) )
		{
			if( sp instanceof Player )
			{
				Player player = (Player)sp;
				
				//�����s��������
				if( player.getTrans().getY() == this.getTrans().getY() )
				{			
					//�����𔻒f���Ď~�܂�B
					if( player.getTrans().getX() + player.GetWidth() > this.getTrans().getX() )
					{
						moveflag = false;
					}
				}			
			}
		}
		//�ړ�
		if(moveflag==true)move();
		
		return true;			
	}
	
	private void move()
	{
		if( getTrans().getY() == Const.LINE_1_Y )
		{
			this.Translate( new Vector3( -Const.LINE_1_SPEED ,0,0) );
			
			//���W�K��
			if( getTrans().getX() < Const.LINE_LEFT_1_X )
			{
				setTrans( new Vector3( Const.LINE_LEFT_1_X , Const.LINE_1_Y , 0 ) );
			}			
		}
		else if( getTrans().getY() == Const.LINE_2_Y )
		{
			this.Translate( new Vector3( -Const.LINE_2_SPEED ,0,0) );
			
			//���W�K��
			if( getTrans().getX() < Const.LINE_LEFT_2_X )
			{
				setTrans( new Vector3( Const.LINE_LEFT_2_X , Const.LINE_2_Y , 0 ) );
			}			
		}
		else if( getTrans().getY() == Const.LINE_3_Y )
		{
			this.Translate( new Vector3( -Const.LINE_3_SPEED ,0,0) );
			
			//���W�K��
			if( getTrans().getX() < Const.LINE_LEFT_3_X )
			{
				setTrans( new Vector3( Const.LINE_LEFT_3_X , Const.LINE_3_Y , 0 ) );
			}			
		}		
	}
}
