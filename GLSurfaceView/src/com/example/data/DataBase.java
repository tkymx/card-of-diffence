package com.example.data;

import java.util.ArrayList;

import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Score;
import com.example.user.DamageMagicCard;
import com.example.user.DamageTrapCard;
import com.example.user.MagicCard;
import com.example.user.MonsterCard;
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
	private static int 	 presentStageNum;
	
	//���̃X�R�A
	private static Score presentScore;
	
	//�������ǂ���
	private static boolean win;
	private static boolean result;

	//�������ǂ����̏���/////////////////////////////////////////////////////////////////////////////
	
	public static boolean isWin() {
		return win;
	}
	public static void setWin(boolean win) {
		DataBase.win = win;
	}		
	public static boolean isResult() {
		return result;
	}
	public static void setResult(boolean result) {
		DataBase.result = result;
	}	
	
	//�ێ����Ă���J�[�h�̏���/////////////////////////////////////////////////////////////////////////////

	//�����̃J�[�h�̒ǉ�
	public static void AddMyCards( String str )
	{
		myCards.add(str);
	}
	public static String GetMyCards( int location )
	{
		return myCards.get(location);
	}	
	public static ArrayList<String> GetMyCards()
	{
		return myCards;
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
		
		if( deck != null )myCards.add(deck);		
	}
	public static String GetMyDeck( int location )
	{
		return myDecks[location];
	}	
	public static String[] GetMyDeck()
	{
		return myDecks;
	}		
	
	//�X�e�[�W�̏���////////////////////////////////////////////////////////////////////////
	public static Stage getPresentStage() {
		return presentStage;
	}
	public static void setPresentStage(Stage presentStage) {
		DataBase.presentStage = presentStage;
	}	
	
	//�N���A�������ɃX�e�[�W���𑝂₷
	public static int getPresentStageNum() {
		return presentStageNum;
	}
	public static void setPresentStageNum(int presentStageNum) {
		//���̃X�e�[�W�������̃X�e�[�W�����傫����΂��̂܂܃Z�b�g
		if( DataBase.presentStageNum < presentStageNum )
		{
			DataBase.presentStageNum = presentStageNum;
		}
	}
	
	//�X�R�A�֌W////////////////////////////////////////////////////////////////////////
	
	public static Score getPresentScore() {
		return presentScore;
	}
	public static void addScore( int s ) {
		presentScore.addScore(s);
	}
	public static void setPresentScore(Score presentScore) {
		DataBase.presentScore = presentScore;
	}	
	
	//��{�I�ɂȏ���////////////////////////////////////////////////////////////////////////
	public static void Init()
	{
		//�S�ẴJ�[�h
		CharactorInfomation.Init();
		//�S�ẴJ�[�h
		CardInformation.Init();
		//�p�����[�^�J�[�h�̏�����
		ParameterCardInfomatoin.Init();
		//�X�e�[�W�̏�����
		StageInformaion.Init();
		
		//�����̃J�[�h
		myCards = new ArrayList<String>();
		//�����̃f�b�L
		myDecks = new String[11];	
		
		//�͂��߂̓X�e�[�W���͖���
		presentStage = null;
		
		//�͂��߃X�R�A�͂Ȃ�
		presentScore = null;
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
		//���@�A�g���b�v�̒ǉ����s��
		///////////////////////////////////////		
		ParameterCardInfomatoin.AddParameterCard(
				ParameterCardInfomatoin.Create("magic", "DamageMagicCard1", -1, 10));
		ParameterCardInfomatoin.AddParameterCard(
				ParameterCardInfomatoin.Create("trap", "DamageTrapCard1", 0, 10));
	
		
		///////////////////////////////////////
		//�f�b�L�̎�ނ̒ǉ�
		///////////////////////////////////////

		//�쐬�p�T���v��
		MonsterCard mc = MonsterCard.CreateMonsterCard();
		DamageMagicCard dmc = DamageMagicCard.CreateDamageMagicCard();
		DamageTrapCard dtc = DamageTrapCard.CreateDamageTrapCard();
		
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster1",mc,R.drawable.moster_card_1,2));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster2",mc,R.drawable.moster_card_2,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster3",mc,R.drawable.moster_card_3,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster4",mc,R.drawable.moster_card_4,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster5",mc,R.drawable.moster_card_5,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster6",mc,R.drawable.moster_card_6,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster7",mc,R.drawable.moster_card_7,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster8",mc,R.drawable.moster_card_8,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("monster9",mc,R.drawable.moster_card_9,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("trap",dtc,R.drawable.trap_card,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("magic",dmc,R.drawable.magic_card,1));		

		//�G�p�̃J�[�h
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("enemy1",mc,R.drawable.moster_card_1,1));
		
		
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
		
		
		
		///////////////////////////////////////
		//�X�e�[�W�̒ǉ�
		///////////////////////////////////////		
		String str[] = {"enemy1","enemy1","enemy1","enemy1","enemy1","enemy1","enemy1","enemy1","enemy1","enemy1","enemy1"};
		Stage stage[] = 
		{
			new Stage(R.drawable.game_start, R.drawable.map, R.drawable.enemy_castle , R.drawable.player_castle, 2, 1 , str),
			new Stage(R.drawable.image1, R.drawable.effect, R.drawable.enemy_castle, R.drawable.player_castle, 3, 2 , str),
			new Stage(R.drawable.image2, R.drawable.explain, R.drawable.enemy_castle, R.drawable.player_castle, 3, 3 , str)			
		};		
		for(int i = 0;i<stage.length;i++)
		{
			StageInformaion.AddStage(stage[i]);
		}
		
		///////////////////////////////////////
		//�X�e�[�W�͂͂��߂P����n�܂�
		///////////////////////////////////////		
		presentStageNum = 1;
		
		
	}	
	
}
