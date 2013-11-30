package com.example.user;

import com.example.data.ParameterCardInfomatoin;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Sprite;

public class DamageMagicCard extends MagicCard {

	int damage;
	
	//�쐬�p�̊֐�
	public static DamageMagicCard CreateDamageMagicCard( int left, int top, int width, int height, int id ,int need )
	{
		DamageMagicCard mc = new DamageMagicCard(left, top, width, height, id ,need);
		return mc;
	}	
	

	//�R���X�g���N�^
	protected DamageMagicCard(int left, int top, int width, int height, int id ,int need) {
		super(left, top, width, height, id ,need);
		
		//�U���͂��Z�b�g
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
	public void SetMagic() {
		
		//�����ꂽ���ɑS�̂Ƀ_���[�W��^����
		for( Sprite sp : Sprite.spriteList.get( Const.SpriteType.TYPE_ENEMY.getValue() ) )
		{
			if( sp instanceof Enemy )
			{
				//�G���
				Enemy enemy = (Enemy)sp;
				//�G�ɍU��
				enemy.Damage( damage );				
			}
		}

		//�U���G�t�F�N�g
		AnimationEffect.Create( 
				0, 
				0, 
				Const.rx(1), 
				Const.ry(1), 
				4, 1, 5, R.drawable.attack, Const.SpriteType.TYPE_EFFECT.getValue());				
				
		
				
	}
	
	/*
	 * 
	 * �Ƃ肠����card����create���Ă΂��Ď������g���쐬���邽�߂̉��f�[�^�݂����Ȃ���
	 * 
	 */
	protected DamageMagicCard(){/*�������Ȃ�*/}
	public static DamageMagicCard CreateDamageMagicCard(){ return new DamageMagicCard();}	
	@Override
	public Card Create(int left, int top, int width, int height, int id,String name, int need) {

		DamageMagicCard mc = new DamageMagicCard(left, top, width, height, id ,need);
		
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
			return "�S�̖��@";
		}
		//����ȊO�Ȃ�P�̍U��
		else
		{
			return "�P�̖��@";
		}
	}


	@Override
	public String GetParameter2ForList(String name) {
		ParameterCardInfomatoin pci = ParameterCardInfomatoin.GetMagicCard(name);
		return "�U����  = " + pci.getParameter2();
	}	

}
