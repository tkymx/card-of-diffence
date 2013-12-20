package com.example.user;

import com.example.data.CharactorInfomation;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.Touch;
import com.example.scene.GameScene;

public class MonsterCard extends Card {
	
	//�����̃L�����N�^�̖��O
	String name;
	
	//�������쐬�����L�����N�^�̏��
	Charactor charactor;
		
	//�������\�b�h
	public static MonsterCard CreateMonsterCard( int left, int top, int width, int height, int id,int need , String name )
	{
		MonsterCard mc = new MonsterCard(left, top, width, height, id,need , name);
		return mc;
	}

	//�R���X�g���N�^
	protected MonsterCard(int left, int top, int width, int height, int id ,int need , String name) {
		super(left, top, width, height, id ,need );
		
		//�L�����N�^�[���n�ߖ����ɂ���
		charactor = null;
		
		//���O�̃Z�b�g
		this.name = name;
	}
	

	@Override
	public boolean Update() {	
		if(super.Update()==false)return false;
		
		//�g������
		if( charactor != null )
		{
			//�L�����N�^�����񂾂�g����悤�ɂ��邤		
			if( charactor.isDead() )
			{
				//�J�[�h���g����悤�ɂ���
				permitUse();			
			}
		}
		else if( !isUse )
		{
			//�J�[�h���g����悤�ɂ���
			permitUse();			
		}
		
		
		return true;
	}
	
	@Override
	public void SelectedUpdate() {

		Touch touch = Touch.getInstance();
		if( touch.IsTouch() )
		{
			//���W���擾����
			int y = (int) touch.getY();
			int line = 0;
						
			//��ɂ���ĕ\������B
			if( y > Const.LINE_1_Y )
			{
				charactor = PlayerAppear.CreatePlayer( Const.LINE_1_Y , name );			
				line = Const.LINE_1_Y;
			}
			else if( y > Const.LINE_2_Y )
			{
				charactor = PlayerAppear.CreatePlayer( Const.LINE_2_Y , name );	
				line = Const.LINE_2_Y;
			}
			else if( y > Const.LINE_3_Y )
			{					
				charactor = PlayerAppear.CreatePlayer( Const.LINE_3_Y , name );		
				line = Const.LINE_3_Y;
			}
			else
			{
				//�ʂ̂Ƃ���Ȃ�I��
				return;
			}
			
			if( GameScene.tutorial.tuto3 )
			{
				if( line != 0 )
				{
					GameScene.tutorial.player = (Player) charactor;
					GameScene.tutorial.x = Const.LINE_LEFT_2_X;	
					GameScene.tutorial.y = line;
					GameScene.tutorial.changeTutorial();
				}
			}
			
			//�g�p�ς݂ɂ���
			use();
		}
	}
	
	//������ۂɃL�����N�^��null�ʂ���
	@Override
	public void permitUse() {
		super.permitUse();
		
		//�L�����N�^��null�ɂ���
		charactor = null;
		
	}

	/*
	 * 
	 * �Ƃ肠����card����create���Ă΂��Ď������g���쐬���邽�߂̉��f�[�^�݂����Ȃ���
	 * 
	 */
	protected MonsterCard()	{/*�������Ȃ�*/}
	public static MonsterCard CreateMonsterCard(){ return new MonsterCard();}	
	@Override
	public Card Create(int left, int top, int width, int height, int id,String name, int need) {

		MonsterCard mc = new MonsterCard(left, top, width, height, id,need , name);		
		
		return mc;
	}
	
	@Override
	public String GetNameForList(String name) {
		return name;
	}


	@Override
	public String GetExplainForList(String name) {
		CharactorInfomation ci = CharactorInfomation.GetCharactorInformation(name);		
		return ci.getExplation();
	}


	@Override
	public String GetParameter1ForList(String name) {
		CharactorInfomation ci = CharactorInfomation.GetCharactorInformation(name);		
		return "�̗�:" + ci.getHp() + "�U��:" + ci.getAttack();
	}


	@Override
	public String GetParameter2ForList(String name) {
		CharactorInfomation ci = CharactorInfomation.GetCharactorInformation(name);		
		return "���x:" + ci.getSpeed();
	}		

	// �L�����N�^�[�̐ݒ�
	public void SetCharactor( Charactor card )
	{
		charactor = card;
	}
	
}
