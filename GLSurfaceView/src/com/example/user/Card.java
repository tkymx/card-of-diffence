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
	
	//�I����
	Texture can;
	//�I����
	Texture selected;
	//�g���Ȃ���
	Texture used;	
		
	public Texture getCan() {
		return can;
	}
	public void setCan(Texture can) {
		this.can = can;
	}
	public Texture getSelected() {
		return selected;
	}
	public void setSelected(Texture selected) {
		this.selected = selected;
	}
	public Texture getUsed() {
		return used;
	}
	public void setUsed(Texture used) {
		this.used = used;
	}	
	
	//�p���悩�炵�����Y�ł��Ȃ�
	protected Card( int left , int top, int width , int height , int id , int s_id , int u_id , int need )
	{
		setCan( new Texture(id) );
		setSelected( new Texture(s_id) );
		setUsed( new Texture(u_id) );	
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
		card.SetTexture( selected );
		SelectedCard.add(card);
	}
	//�Z���N�g�̃N���A
	public void SelectedClear(  )
	{
		for( Card card : SelectedCard )
		{
			card.SetTexture( card.can );
		}
		SelectedCard.clear();
	}
	public void SelectedClear( Card card )
	{
		if( SelectedCard.indexOf(card) != -1 )
		{
			card.SetTexture( can );
		}
		SelectedCard.remove(this);
	}
	// �X�V����
	public boolean Update()
	{
		//�������I��ΏۂȂ�
		//�g�p��������
		if( GetTexture() != used )
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
					SetTexture(can);
				}
			}
		}
		
		return true;		
	}	

	//�J�[�h���g�p����
	public void use()
	{
		//���ׂĂ�����
		SelectedClear();
		//�������g�p�ς݂ɂ���
		SetTexture( used );
	}
	
	//�g�p��������
	public void permitUse()
	{
		SetTexture( can );
	}

	
	//���g�p�ł��邩���`�F�b�N����
	public boolean IsUse()
	{
		return GetTexture() != used;
	}
	
}
