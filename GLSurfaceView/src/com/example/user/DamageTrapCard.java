package com.example.user;

import com.example.data.ParameterCardInfomatoin;

public class DamageTrapCard extends TrapCard {

	int damage;
	
	//�쐬�p�̊֐�
	public static DamageTrapCard CreateDamageTrapCard( int left, int top, int width, int height, int id ,int need )
	{
		DamageTrapCard mc = new DamageTrapCard(left, top, width, height, id ,need);
		return mc;
	}
	
	
	//�R���X�g���N�^
	protected DamageTrapCard(int left, int top, int width, int height, int id ,int need) {
		super(left, top, width, height, id ,need);
		
		damage = 10;
		
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

		DamageTrap.CreateDamageTrap(line, x , damage);
		
	}
	
	/*
	 * 
	 * �Ƃ肠����card����create���Ă΂��Ď������g���쐬���邽�߂̉��f�[�^�݂����Ȃ���
	 * 
	 */
	protected DamageTrapCard(){/*�������Ȃ�*/}
	public static DamageTrapCard CreateDamageTrapCard(){ return new DamageTrapCard();}		
	@Override
	public Card Create(int left, int top, int width, int height, int id,String name, int need) {

		DamageTrapCard mc = new DamageTrapCard(left, top, width, height, id ,need);
		
		return mc;
	}


	@Override
	public String GetNameForList(String name) {
		return name;
	}


	@Override
	public String GetExplainForList(String name) {
		ParameterCardInfomatoin pci = ParameterCardInfomatoin.GetMagicCard(name);
		return pci.getExplation();
	}


	@Override
	public String GetParameter1ForList(String name) {
		ParameterCardInfomatoin pci = ParameterCardInfomatoin.GetMagicCard(name);
		
		//-1�Ȃ�S�̍U��
		if( pci.getParameter1() == -1 )
		{
			return "�S�̃g���b�v";
		}
		//����ȊO�Ȃ�P�̍U��
		else
		{
			return "�P�̃g���b�v";
		}
	}


	@Override
	public String GetParameter2ForList(String name) {
		ParameterCardInfomatoin pci = ParameterCardInfomatoin.GetMagicCard(name);
		return "�U����  = " + pci.getParameter2();
	}				
	
}
