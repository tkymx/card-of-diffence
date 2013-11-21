package com.example.user;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

import com.example.glsurfaceview.Const.SpriteType;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Texture;
import com.example.glsurfaceview.Touch;

/**
 * 
 * ��{�I�̓J�[�h�̕`��ƃJ�[�h�̏�Ԃ̕ύX�A���z�I�͓����ɉ摜�������A
 * �X�V�̎��ɐ؂�ւ�����悤�ȏ������ł���Η��z�ł���B
 * 
 * texture�ɂ��̎��̃e�N�X�`��������U���Ă��邪
 * ��Ԃ�texture�̐ݒu����Ă���C���X�^���X�ɂ���ĕς��Ă���
 * 
 * @author ultra-tkymx
 *
 */

public abstract class Card extends Sprite {

	//�S�̓I�̑I��Ώ�(�I������Ă���J�[�h)
	static Vector<Card> SelectedCard = new Vector<Card>();
	
	//���т̐�
	int neednum;

	//���ԏ�� -1�Ŏg��Ȃ�
	long alive_time;
	//���悤�����ǂ���
	boolean isUse;
			
	public void startCan() {
		//���Ԃ�ݒ�ł���悤�ɂ���
		if( alive_time < 0 )
		{
			alive_time = System.currentTimeMillis();			
		}
	}
	public void setCan() {
		texture.SetColor( 1.0f,1.0f,1.0f,1.0f );
		isUse = true;
		
		//���Ԃ̏�����
		alive_time = -1;
	}
	public void setSelected() {
		texture.SetColor( 1.5f,1.2f,0.0f,0.5f );
	}
	public void setUsed() {
		texture.SetColor( 0.4f,0.4f,0.4f,1.0f );
		isUse = false;
	}	
	
	//�p���悩�炵�����Y�ł��Ȃ�
	protected Card(  )
	{		
	}
	protected Card( int left , int top, int width , int height , int id , int need )
	{
		//���悤���ɂ���
		isUse = true;
		//���Ԃ̏�����
		alive_time = -1;
		
		Init(left, top, width, height, id, SpriteType.TYPE_CARD.getValue());				
		//���т̐�
		neednum = need;
	}	
	
	//�I������Ă��鎞�̍X�V����
	public abstract void SelectedUpdate();		
	//���C�����ǂ���
	public boolean isMainSelected( Card card )
	{
		if( SelectedCard.indexOf(card) != -1 )
		{
			if( SelectedCard.get( 0 ) == card )
			return true;
		}
		return false;
	}
	//�T�u���ǂ���
	public boolean isSubSelected( Card card )
	{
		if( SelectedCard.indexOf(card) != -1 )
		{
			if( SelectedCard.get( 0 ) != card )
			return true;
		}
		return false;
	}
	//���̂��ǂ���	
	public boolean isElseSelected( Card card )
	{
		if( SelectedCard.indexOf(card) == -1 )return true;
		return false;
	}
	//�K�v���ɖ������Ă��邩
	public boolean isNeed()
	{
		if( SelectedCard.size() > 0 )
		{
			//���C���̕K�v��
			int need = SelectedCard.get(0).neednum;
			//�K�v�������Ă�����
			if( SelectedCard.size() == need )
			{
				return true;
			}			
		}
		return false;
	}
	//�K�v���̎擾
	public int getNeed()
	{
		if( SelectedCard.size() > 0 )
		{
			//���C���̕K�v��
			return SelectedCard.get(0).neednum;
		}
		return -1;
	}
	//�Z���N�g�̒ǉ�
	public void SelectedAdd( Card card )
	{
		card.setSelected();
		SelectedCard.add(card);
	}
	//�Z���N�g�̃N���A
	public void SelectedClear(  )
	{
		for( Card card : SelectedCard )
		{
			card.setCan();
		}
		SelectedCard.clear();
	}
	public void SelectedClear( Card card )
	{
		if( SelectedCard.indexOf(card) != -1 )
		{
			card.setCan();
		}
		SelectedCard.remove(this);
	}
	//�Z���N�g�̃N���A
	public void SelectedAllUsedClear(  )
	{
		for( Card card : SelectedCard )
		{
			card.setUsed();
		}
		SelectedCard.clear();
	}
	// �X�V����
	public boolean Update()
	{
		//�������I��ΏۂȂ�
		//�g�p��������
		if( isUse )
		{			
			//���������C���̎�
			if( isMainSelected(this) )
			{
				//�^�b�`������
				if( IsTouch() )
				{
					//���ׂĂ�����
					SelectedClear();
				}
				else
				{
					//���������Ă�����
					if( isNeed() )
					{
						//�������^�b�`����Ă��Ȃ�������X�V����
						SelectedUpdate();						
					}
				}
			}
			//�������T�u�̎�
			else if( isSubSelected(this) )
			{
				if( IsTouch() )
				{
					//����������
					SelectedClear(this);
				}
			}
			//���������̎�
			else if( isElseSelected(this))
			{
				if( IsTouch() )
				{
					//���������Ă�����
					if( isNeed() )
					{
						//���ׂĂ������Ď���������
						SelectedClear();
						//����������
						SelectedAdd(this);
					}
					//�K�v������������
					else if( SelectedCard.size() < getNeed() || getNeed() == -1 )
					{
						SelectedAdd(this);
					}
				}
				else
				{
					setCan();
				}
			}
		}
		else
		{
			//0����Ŏ��Ԕ��f�J�n
			if( alive_time > 0 )
			{
				//�g���Ȃ����Ɏ��Ԍo�߂Ŏg����悤�ɂ���
				if( System.currentTimeMillis() - alive_time > 1000 )
				{
					setCan();
				}
			}
		}
		
		return true;		
	}	

	//�J�[�h���g�p����
	public void use()
	{
		//���ׂĂ�����
		SelectedAllUsedClear();
		//�������g�p�ς݂ɂ���
		setUsed();
	}
	
	//�g�p��������
	public void permitUse()
	{
		startCan();
	}

	
	//���g�p�ł��邩���`�F�b�N����
	public boolean IsUse()
	{
		return isUse;
	}
	
	/*
	 * �J�[�h���I����ʂɕ\������Ă��鎞�̕\���ɂ��Ă̒��ۊ֐����쐬���Ă���
	 */
	public abstract String GetNameForList(String name);
	public abstract String GetExplainForList(String name);
	public abstract String GetParameter1ForList(String name);
	public abstract String GetParameter2ForList(String name);
	

	/*
	 * �J�[�h���쐬���ꂽ���̍쐬���ۊ֐�������Ă���
	 */
	public abstract Card Create( int left , int top, int width , int height , int id , String name , int need  );
	
	
}
