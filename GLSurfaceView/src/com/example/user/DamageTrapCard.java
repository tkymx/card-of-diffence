package com.example.user;

public class DamageTrapCard extends TrapCard {

	//�쐬�p�̊֐�
	public static DamageTrapCard CreateDamageTrapCard( int left, int top, int width, int height, int id,int s_id, int u_id,int need )
	{
		DamageTrapCard mc = new DamageTrapCard(left, top, width, height, id, s_id, u_id,need);
		return mc;
	}
	
	
	//�R���X�g���N�^
	protected DamageTrapCard(int left, int top, int width, int height, int id,int s_id, int u_id,int need) {
		super(left, top, width, height, id, s_id, u_id,need);
	}

	//�X�V����
	@Override
	public boolean Update(){
		if(super.Update()==false)return false;
		
		//�g�p�s��������
		if( GetTexture() == used )
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
