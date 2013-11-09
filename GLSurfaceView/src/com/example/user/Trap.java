package com.example.user;

import com.example.glsurfaceview.Sprite;

public abstract class Trap extends Sprite {

	@Override
	public boolean Update()
	{
		//XV‚µ‚Ä‚¢‚é’†‚Å“–‚½‚è”»’è‚ğs‚¤
		if(TrapUpdate()==false)return false;
		
		return true;		
	}
	
	public abstract boolean TrapUpdate();
}
