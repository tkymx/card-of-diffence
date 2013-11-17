package com.example.data;

import java.util.ArrayList;

import com.example.data.CardInformation.Card_Kind;
import com.example.glsurfaceview.R;
import com.example.user.Stage;


/**
 * ��{�I�ȃf�[�^�x�[�X���쐬�ł���悤�Ȏd�g��
 * 
 * ���̃f�[�^
 * 
 * 	�J�[�h�̑S��ނ̃f�[�^
 * 	��l�����ێ����Ă���J�[�h�̃f�[�^	
 * 	��l�����f�b�L�ɓ���Ă���J�[�h�̃f�[�^
 * 
 * 
 * @author ultra-tkymx
 *
 */

public class DataBase {

	//�����̃J�[�h
	private static ArrayList<String> myCards;
	//�����̃f�b�L
	private static String[] myDecks;
	
	//�����̃X�e�[�W�̏��
	private static Stage presentStage;

	//�ێ����Ă���J�[�h�̏���/////////////////////////////////////////////////////////////////////////////
	
	//�����̃J�[�h�̒ǉ�
	public static void AddMyCards( String str )
	{
		myCards.add(str);
	}
	public static void DeleteMyCards( int location )
	{
		myCards.remove(location);
	}
	
	//�f�b�L�̏���///////////////////////////////////////////////////////////////////////////////////////
	
	//�f�b�L����J�[�h�������o��
	public static CardInformation GetDeckCardInformation( int locationDeck )
	{
		return CardInformation.GetCardInformaionFromName( myDecks[locationDeck] );
	}	
	
	//�ێ����Ă���J�[�h�ƃf�b�L�̏���////////////////////////////////////////////////////////////////////////

	//�����̃J�[�h�J�[�h����f�b�L�ɉ�����
	public static void SwapMyDecksFromMyCards( int locationDeck , int locationCards )
	{
		//�f�b�L�����������Ă���B
		String deck = myDecks[locationDeck];
		
		//�ێ����Ă���J�[�h��������Ƃ��ė���
		String card = myCards.get(locationCards);
		DeleteMyCards(locationCards);
		
		//����ւ�
		myDecks[ locationDeck ] = card;
		myCards.add(deck);
	}
	
	//�X�e�[�W�̏���////////////////////////////////////////////////////////////////////////
	public static Stage getPresentStage() {
		return presentStage;
	}
	public static void setPresentStage(Stage presentStage) {
		DataBase.presentStage = presentStage;
	}	
	
	//��{�I�ɂȏ���////////////////////////////////////////////////////////////////////////
	public static void Init()
	{
		//�S�ẴJ�[�h
		CharactorInfomation.Init();
		//�S�ẴJ�[�h
		CardInformation.Init();
		//�����̃J�[�h
		myCards = new ArrayList<String>();
		//�����̃f�b�L
		myDecks = new String[11];	
		
		//�͂��߂̓X�e�[�W���͖���
		presentStage = null;
	}
	
	public static void LoadData()
	{
		//�����ŁA�f�b�L�̏����������Ȃǂ��s��
		
		///////////////////////////////////////
		//�L�����N�^�̒ǉ����s��
		///////////////////////////////////////		
		CharactorInfomation.AddCharactor(
				CharactorInfomation.Create("monster1","monsterCard1",10,1,1, 2, R.drawable.walk, R.drawable.image1, R.drawable.image2 ) );
		CharactorInfomation.AddCharactor(
				CharactorInfomation.Create("monster2","monsterCard2",10,1,1, 2, R.drawable.walk, R.drawable.image1, R.drawable.image2 ) );
		CharactorInfomation.AddCharactor(
				CharactorInfomation.Create("monster3","monsterCard3",10,1,1, 2, R.drawable.walk, R.drawable.image1, R.drawable.image2 ) );
		CharactorInfomation.AddCharactor(
				CharactorInfomation.Create("monster4","monsterCard4",10,1,1, 2, R.drawable.walk, R.drawable.image1, R.drawable.image2 ) );
		CharactorInfomation.AddCharactor(
				CharactorInfomation.Create("monster5","monsterCard5",10,1,1, 2, R.drawable.walk, R.drawable.image1, R.drawable.image2 ) );
		CharactorInfomation.AddCharactor(
				CharactorInfomation.Create("monster6","monsterCard6",10,1,1, 2, R.drawable.walk, R.drawable.image1, R.drawable.image2 ) );
		CharactorInfomation.AddCharactor(
				CharactorInfomation.Create("monster7","monsterCard7",10,1,1, 2, R.drawable.walk, R.drawable.image1, R.drawable.image2 ) );
		CharactorInfomation.AddCharactor(
				CharactorInfomation.Create("monster8","monsterCard8",10,1,1, 2, R.drawable.walk, R.drawable.image1, R.drawable.image2 ) );
		CharactorInfomation.AddCharactor(
				CharactorInfomation.Create("monster9","monsterCard9",10,1,1, 2, R.drawable.walk, R.drawable.image1, R.drawable.image2 ) );
		
		CharactorInfomation.AddCharactor(
				CharactorInfomation.Create("enemy1","enemyCard1",10,1,1, 2, R.drawable.walk_enemy, R.drawable.image1, R.drawable.image2 ) );
		
		///////////////////////////////////////
		//�f�b�L�̎�ނ̒ǉ�
		///////////////////////////////////////
		
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster1",Card_Kind.Monster,R.drawable.moster_card_1,2));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster2",Card_Kind.Monster,R.drawable.moster_card_2,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster3",Card_Kind.Monster,R.drawable.moster_card_3,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster4",Card_Kind.Monster,R.drawable.moster_card_4,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster5",Card_Kind.Monster,R.drawable.moster_card_5,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster6",Card_Kind.Monster,R.drawable.moster_card_6,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster7",Card_Kind.Monster,R.drawable.moster_card_7,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster8",Card_Kind.Monster,R.drawable.moster_card_8,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster9",Card_Kind.Monster,R.drawable.moster_card_9,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("trap",Card_Kind.DamageTrap,R.drawable.trap_card,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("magic",Card_Kind.DmageMagic,R.drawable.magic_card,1));		
		
		///////////////////////////////////////
		//�����̕ێ����Ă���J�[�h�̒ǉ�
		///////////////////////////////////////

		AddMyCards("monster1");
		AddMyCards("monster2");
		AddMyCards("monster3");
		AddMyCards("monster4");
		AddMyCards("monster5");
		AddMyCards("monster6");
		AddMyCards("monster7");
		AddMyCards("monster8");
		AddMyCards("monster9");
		AddMyCards("trap");
		AddMyCards("magic");
		
		///////////////////////////////////////
		//�ꉞ�A�����̃J�[�h���玩���̃f�b�L�ւ̒ǉ�
		///////////////////////////////////////
		
		for(int i = 0;i< 11;i++)
		{
			//�f�b�L�ɒǉ����Ă������тɕێ��J�[�h�������Ă������߂O�ł���
			SwapMyDecksFromMyCards(i, 0);
		}
		
	}	
	
}
