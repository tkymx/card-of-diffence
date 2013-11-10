package com.example.user;

import com.example.glsurfaceview.Const;
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
			//�����ŁA���b�܂Ȃǂ̐ݒ肪�K�v�ɂȂ�
			
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
				
	}

}
