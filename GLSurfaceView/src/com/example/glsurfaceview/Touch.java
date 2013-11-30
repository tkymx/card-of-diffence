package com.example.glsurfaceview;

import android.view.MotionEvent;

public class Touch {
	
	private static Touch Instance = new Touch();
	private float x, y;
	private float oldX, oldY;
	private float moveX, moveY;
	private boolean isTouch, isRelease, isCancel, isMove;
	
	// インスタンス取得
	public static Touch getInstance()
	{
		return Instance;
	}
	
	// コンストラクタ
	public Touch()
	{
		x = 0;
		y = 0;
		oldX = 0;
		oldY = 0;
		moveX = 0;
		moveY = 0;
	}
	
	// X取得
	public float getX()
	{
		return x;
	}
	
	// Y取得
	public float getY()
	{
		return MainActivity.height-y;
	}
	
	// 移動量X取得
	public float getMoveX()
	{
		return moveX;
	}
	
	// 移動量Y取得
	public float getMoveY()
	{
		return moveY;
	}
	
	// タッチ取得
	public boolean IsTouch()
	{
		return isTouch;
	}
	
	// リリース取得
	public boolean IsRelase()
	{
		return isRelease;
	}
	
	// キャンセル取得
	public boolean IsCancel()
	{
		return isCancel;
	}
	
	// 移動取得
	public boolean IsMove()
	{
		return isMove;
	}
	
	//毎度の処理
	public void UpdateEnd()
	{
		isTouch = false;
		isRelease = false;
		isCancel = false;
		isMove = false;		
	}
	
	// 更新処理
	public void Update( MotionEvent event )
	{
		isTouch = false;
		isRelease = false;
		isCancel = false;
		isMove = false;
		
		// 前回の座標の格納
		oldX = x;
		oldY = y;
		
		// イベント把握
		 switch ( event.getAction() ) 
		 {
		    case MotionEvent.ACTION_DOWN:
		        x = event.getX();
		        y = event.getY();
		        
		        isTouch = true;
		    	
		        break;
		        
		    case MotionEvent.ACTION_UP:
		    	x = event.getX();
		        y = event.getY();
		        
		        isRelease = true;
		    	
		        break;
		        
		    case MotionEvent.ACTION_MOVE:
		    	x = event.getX();
		        y = event.getY();
		    	
		    	moveX = x - oldX;
		    	moveY = y - oldY;
		    	
		    	isMove = true;
		        
		        break;
		        
		    case MotionEvent.ACTION_CANCEL:
		    	isCancel = true;
		        
		        break;
		 }
	}
}
