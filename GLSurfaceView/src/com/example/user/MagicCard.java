package com.example.user;

public abstract class MagicCard extends Card {
		
	
	//�R���X�g���N�^
	protected MagicCard(){}
	protected MagicCard( int left, int top, int width, int height, int id,int need) {
		super(left, top, width, height, id,need);		
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
