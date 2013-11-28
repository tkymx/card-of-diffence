package com.example.user;


import com.example.data.DataBase;
import com.example.glsurfaceview.Const;
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
		//�J�[�h�̃Z�b�g
		for( int i = 0 ; i < 11 ; i++ )
		{			
			//���W�̃Z�b�g
			int x1 = (i+1) * Const.card_width_offset + i * Const.card_width;
			int y1 = -Const.card_heught_offset;	
			
			//�f�b�L����J�[�h�̏���
			DataBase.GetDeckCardInformation(i).getCard(x1, y1, Const.card_width, Const.card_height);
		}
		
	}
	
	// �X�V����
	public boolean Update()
	{
				
		return true;		
	}
}
