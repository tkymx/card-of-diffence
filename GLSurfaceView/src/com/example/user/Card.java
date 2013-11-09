package com.example.user;

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
	static Card SelectedCard = null;
	
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
	protected Card( int left , int top, int width , int height , int id , int s_id , int u_id )
	{
		setCan( new Texture(id) );
		setSelected( new Texture(s_id) );
		setUsed( new Texture(u_id) );	
		Init(left, top, width, height, id, SpriteType.TYPE_CARD.getValue());		
	}	
	
	
	//�I������Ă��鎞�̍X�V����
	public abstract void SelectedUpdate();		
	// �X�V����
	public boolean Update()
	{
		//�������I��ΏۂȂ�
		//�g�p��������
		if( GetTexture() != used )
		{
			if( SelectedCard == this )
			{
				//�Z���N�g���̍X�V����
				SelectedUpdate();
				
				//�����ꂽ���A���Ƃɖ߂�
				if( IsTouch() )
				{
					//���悤���ɂ���
					SetTexture( can );					
					SelectedCard = null;
				}						
				
			}
			else
			//�������I��Ώۂ���Ȃ�������
			{
				//�����ꂽ���A�I���ɂ���
				if( IsTouch() )
				{
					Touch touch = Touch.getInstance();
					//�Z���N�g��Ԃɂ���B
					SetTexture( selected );					
					SelectedCard = this;
					
					IsTouch();
				}
				//���ʂɖ߂�
				else if( GetTexture() != can )
				{
					//���ʂ̃J�[�h�ɂ���
					SetTexture( can );
				}
			}
		}
		
		return true;		
	}	

	//�J�[�h���g�p����
	public void use()
	{
		SelectedCard = null;
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
