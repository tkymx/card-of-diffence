package com.example.user;

import com.example.glsurfaceview.Sprite;

public abstract class Trap extends Sprite {

	@Override
	public boolean Update()
	{
		//更新している中で当たり判定を行う
		if(TrapUpdate()==false)return false;
		
		return true;		
	}
	
	public abstract boolean TrapUpdate();
}
