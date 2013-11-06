package com.example.user;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;

/**
 * 
 * ��{�I�ɂ̓J�[�h�̊Ǘ����s���Ă������A���܂́A�J�[�h�\����ʂ̗���\������
 * 
 * @author ultra-tkymx
 *
 */

public class Deck extends Sprite {
	
	// ����������
	public void Init()
	{		
		//�J�[�h��11�����Y����
		for(int i = 0;i<11;i++)
		{
			//���W�̃Z�b�g
			int x1 = (i+1) * Const.card_width_offset + i * Const.card_width;
			int y1 = -Const.card_heught_offset;			
			
			//�����X�^�[�J�[�h
			if( i >= 0 && i <= 8 )
			{
				Card card = MonsterCard.CreateMonsterCard(
						x1 , 
						y1 , 
						Const.card_width , 
						Const.card_height , 
						R.drawable.moster_card ,
						R.drawable.moster_card_selected ,
						R.drawable.moster_card_used );
			}
			//㩃J�[�h
			if( i == 9 )
			{
				//�J�[�h���̐���
				Card card = DamageTrapCard.CreateDamageTrapCard(
						x1 , 
						y1 , 
						Const.card_width , 
						Const.card_height , 
						R.drawable.trap_card ,
						R.drawable.trap_card_selected ,
						R.drawable.trap_card_used );
			}
			//���@�J�[�h
			if( i == 10 )
			{
				//�J�[�h���̐���
				Card card = DamageMagicCard.CreateDamageMagicCard(
						x1 , 
						y1 , 
						Const.card_width , 
						Const.card_height , 
						R.drawable.magic_card ,
						R.drawable.magic_card_selected ,
						R.drawable.magic_card_used );
			}
		}
	}
	
	// �X�V����
	public boolean Update()
	{
				
		return true;		
	}
}
