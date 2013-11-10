package co.example.data;

import java.util.ArrayList;

import com.example.glsurfaceview.R;

import co.example.data.CardInformation.Card_Kind;

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
	
	//��{�I�ɂȏ���////////////////////////////////////////////////////////////////////////
	
	public static void Init()
	{
		//�S�ẴJ�[�h
		CardInformation.Init();
		//�����̃J�[�h
		myCards = new ArrayList<String>();
		//�����̃f�b�L
		myDecks = new String[11];	
	}
	
	public static void LoadData()
	{
		//�����ŁA�f�b�L�̏����������Ȃǂ��s��
		
		//�f�b�L�փJ�[�h�̎�ނ̓��e���s��		
		
		//�����X�^�[
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster1","monsterCard1",10,10,Card_Kind.Monster,R.drawable.moster_card_1,2));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster2","monsterCard2",10,10,Card_Kind.Monster,R.drawable.moster_card_2,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster3","monsterCard3",10,10,Card_Kind.Monster,R.drawable.moster_card_3,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster4","monsterCard4",10,10,Card_Kind.Monster,R.drawable.moster_card_4,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster5","monsterCard5",10,10,Card_Kind.Monster,R.drawable.moster_card_5,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster6","monsterCard6",10,10,Card_Kind.Monster,R.drawable.moster_card_6,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster7","monsterCard7",10,10,Card_Kind.Monster,R.drawable.moster_card_7,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster8","monsterCard8",10,10,Card_Kind.Monster,R.drawable.moster_card_8,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster9","monsterCard9",10,10,Card_Kind.Monster,R.drawable.moster_card_9,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("trap","trapCard",10,-1,Card_Kind.Trap,R.drawable.trap_card,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("magic","magicCard",10,-1,Card_Kind.Magic,R.drawable.magic_card,1));		
		
		//�����̃J�[�h�֒ǉ�����
		AddMyCards("monster2");
		AddMyCards("monster2");
		AddMyCards("monster2");
		AddMyCards("monster2");
		AddMyCards("monster2");
		AddMyCards("monster2");
		AddMyCards("monster2");
		AddMyCards("monster2");
		AddMyCards("monster2");
		AddMyCards("trap");
		AddMyCards("magic");
		
		//�����̃J�[�h����f�b�L�ɉ�����
		for(int i = 0;i< 11;i++)
		{
			//�f�b�L�ɒǉ����Ă������тɕێ��J�[�h�������Ă������߂O�ł���
			SwapMyDecksFromMyCards(i, 0);
		}
		
	}	
	
}
