package com.example.glsurfaceview;

import android.view.MotionEvent;

public class Touch {
	
	private static Touch Instance = new Touch();
	private float x, y;
	private float oldX, oldY;
	private float moveX, moveY;
	private boolean isTouch, isRelease, isCancel, isMove;
	
	// �C���X�^���X�擾
	public static Touch getInstance()
	{
		return Instance;
	}
	
	// �R���X�g���N�^
	public Touch()
	{
		x = 0;
		y = 0;
		oldX = 0;
		oldY = 0;
		moveX = 0;
		moveY = 0;
	}
	
	// X�擾
	public float getX()
	{
		return x;
	}
	
	// Y�擾
	public float getY()
	{
		return MainActivity.height-y;
	}
	
	// �ړ���X�擾
	public float getMoveX()
	{
		return moveX;
	}
	
	// �ړ���Y�擾
	public float getMoveY()
	{
		return moveY;
	}
	
	// �^�b�`�擾
	public boolean IsTouch()
	{
		return isTouch;
	}
	
	// �����[�X�擾
	public boolean IsRelase()
	{
		return isRelease;
	}
	
	// �L�����Z���擾
	public boolean IsCancel()
	{
		return isCancel;
	}
	
	// �ړ��擾
	public boolean IsMove()
	{
		return isMove;
	}
	
	//���x�̏���
	public void UpdateEnd()
	{
		isTouch = false;
		isRelease = false;
		isCancel = false;
		isMove = false;		
	}
	
	// �X�V����
	public void Update( MotionEvent event )
	{
		isTouch = false;
		isRelease = false;
		isCancel = false;
		isMove = false;
		
		// �O��̍��W�̊i�[
		oldX = x;
		oldY = y;
		
		// �C�x���g�c��
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
