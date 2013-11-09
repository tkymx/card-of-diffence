package com.example.user;

import java.util.ArrayList;
import java.util.LinkedList;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.Sprite;


public abstract class MagicCard extends Card {
		
	
	//�R���X�g���N�^
	protected MagicCard( int left, int top, int width, int height, int id,int s_id, int u_id,int need) {
		super(left, top, width, height, id, s_id, u_id,need);		
	}
	
	//�I�����ꂽ���̓���
	public abstract void SetMagic();
	
	
	//�I������Ă��鎞�̍X�V
	@Override
	public void SelectedUpdate() {

		//�g�p����
		SetMagic();		
		//�g�p�ς݂ɂ���
		use();
		
	}

}
