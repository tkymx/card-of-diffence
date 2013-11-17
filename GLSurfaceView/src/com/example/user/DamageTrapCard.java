package com.example.user;

public class DamageTrapCard extends TrapCard {

	//�쐬�p�̊֐�
	public static DamageTrapCard CreateDamageTrapCard( int left, int top, int width, int height, int id ,int need )
	{
		DamageTrapCard mc = new DamageTrapCard(left, top, width, height, id ,need);
		return mc;
	}
	
	
	//�R���X�g���N�^
	protected DamageTrapCard(int left, int top, int width, int height, int id ,int need) {
		super(left, top, width, height, id ,need);
	}

	//�X�V����
	@Override
	public boolean Update(){
		if(super.Update()==false)return false;
		
		//�g�p�s��������
		if( !isUse )
		{
			//���̋�������
			permitUse();					
		}
		
		return true;
	}
	
	
	@Override
	//�g���b�v���ڂ������ꂽ���̏���
	public void SetTrap(int line , int x) {

		DamageTrap.CreateDamageTrap(line, x );
		
	}
	
}