package com.example.user;

import com.example.glsurfaceview.Sprite;

public abstract class Trap extends Sprite {

	@Override
	public boolean Update()
	{
		//�X�V���Ă��钆�œ����蔻����s��
		if(TrapUpdate()==false)return false;
		
		return true;		
	}
	
	public abstract boolean TrapUpdate();
}
