package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Const.SpriteType;
import com.example.glsurfaceview.Sprite;

/**
 * 
 * �ڐG�����G�ɍU����^����_���[�W��ݒu����
 * 
 * @author ultra-tkymx
 *
 */

public class DamageTrap extends Trap {

	int damage;
	
	// ���� ���ꐄ��
	public static DamageTrap CreateDamageTrap( int line, int x )
	{		
		DamageTrap trap = new DamageTrap();
		
		//���C���̑I��
		int width = 0;
		int height = 0;
		if(line == Const.LINE_1_Y){width=Const.LINE_1_W;height=Const.LINE_1_W;}
		else if(line == Const.LINE_2_Y){width=Const.LINE_2_W;height=Const.LINE_2_W;}
		else if(line == Const.LINE_3_Y){width=Const.LINE_3_W;height=Const.LINE_3_W;}
		
		//������
		trap.Init(x, line, width, height, R.drawable.trap , SpriteType.TYPE_TRAP.getValue());

		return trap;
	}	
	
	//�R���X�g���N�^
	protected DamageTrap(){
		super();
		
		//�U���͂̃Z�b�g
		damage = 10;
	}
	
	@Override
	public boolean TrapUpdate() {
		
		//�G�����[�v���āA���̂Ȃ��ł������G�ɍU����^����
		//�����ꂽ���ɑS�̂Ƀ_���[�W��^����
		for( Sprite sp : Sprite.spriteList.get( Const.SpriteType.TYPE_ENEMY.getValue() ) )
		{
			if( sp instanceof Enemy )
			{
				//�G���
				Enemy enemy = (Enemy)sp;
				
				//�����ڐG���Ă�����
				if( enemy.Collission(this) )
				{
					//�G�ɍU��
					enemy.Damage( damage );	
					
					//�������������
					return false;
				}				
			}
		}	
		
		return true;
	}

}
