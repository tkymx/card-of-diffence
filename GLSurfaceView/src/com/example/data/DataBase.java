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
 * 基本的なデータベースを作成できるような仕組み
 * 
 * 今のデータ
 * 
 * 	カードの全種類のデータ
 * 	主人公が保持しているカードのデータ	
 * 	主人公がデッキに入れているカードのデータ
 * 
 * 
 * @author ultra-tkymx
 *
 */

public class DataBase {

	//自分のカード
	private static ArrayList<String> myCards;
	//自分のデッキ
	private static String[] myDecks;
	
	//自分のステージの情報
	private static Stage presentStage;
	private static int 	 presentStageNum;
	
	//今のスコア
	private static Score presentScore;
	
	//勝ちかどうか
	private static boolean win;
	private static boolean result;

	//勝ちかどうかの処理/////////////////////////////////////////////////////////////////////////////
	
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
	
	//保持しているカードの処理/////////////////////////////////////////////////////////////////////////////

	//自分のカードの追加
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
	
	//デッキの処理///////////////////////////////////////////////////////////////////////////////////////
	
	//デッキからカード情報を取り出す
	public static CardInformation GetDeckCardInformation( int locationDeck )
	{
		return CardInformation.GetCardInformaionFromName( myDecks[locationDeck] );
	}	
	
	//保持しているカードとデッキの処理////////////////////////////////////////////////////////////////////////

	//自分のカードカードからデッキに加える
	public static void SwapMyDecksFromMyCards( int locationDeck , int locationCards )
	{
		//デッキから情報を取ってくる。
		String deck = myDecks[locationDeck];
		
		//保持しているカードから情報をとって来る
		String card = myCards.get(locationCards);
		DeleteMyCards(locationCards);
		
		//入れ替え
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
	
	//ステージの処理////////////////////////////////////////////////////////////////////////
	public static Stage getPresentStage() {
		return presentStage;
	}
	public static void setPresentStage(Stage presentStage) {
		DataBase.presentStage = presentStage;
	}	
	
	//クリアした時にステージ数を増やす
	public static int getPresentStageNum() {
		return presentStageNum;
	}
	public static void setPresentStageNum(int presentStageNum) {
		//次のステージ数が今のステージ数より大きければそのままセット
		if( DataBase.presentStageNum < presentStageNum )
		{
			DataBase.presentStageNum = presentStageNum;
		}
	}
	
	//スコア関係////////////////////////////////////////////////////////////////////////
	
	public static Score getPresentScore() {
		return presentScore;
	}
	public static void addScore( int s ) {
		presentScore.addScore(s);
	}
	public static void setPresentScore(Score presentScore) {
		DataBase.presentScore = presentScore;
	}	
	
	//基本的にな処理////////////////////////////////////////////////////////////////////////
	public static void Init()
	{
		//全てのカード
		CharactorInfomation.Init();
		//全てのカード
		CardInformation.Init();
		//パラメータカードの初期化
		ParameterCardInfomatoin.Init();
		//ステージの初期化
		StageInformaion.Init();
		
		//自分のカード
		myCards = new ArrayList<String>();
		//自分のデッキ
		myDecks = new String[11];	
		
		//はじめはステージ情報は無し
		presentStage = null;
		
		//はじめスコアはなし
		presentScore = null;
	}
	
	public static void LoadData()
	{
		//ここで、デッキの初期化処理などを行う
		
		///////////////////////////////////////
		//キャラクタの追加を行う
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
		//魔法、トラップの追加を行う
		///////////////////////////////////////		
		ParameterCardInfomatoin.AddParameterCard(
				ParameterCardInfomatoin.Create("magic", "DamageMagicCard1", -1, 10));
		ParameterCardInfomatoin.AddParameterCard(
				ParameterCardInfomatoin.Create("trap", "DamageTrapCard1", 0, 10));
	
		
		///////////////////////////////////////
		//デッキの種類の追加
		///////////////////////////////////////

		//作成用サンプル
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

		//敵用のカード
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("enemy1",mc,R.drawable.moster_card_1,1));
		
		
		///////////////////////////////////////
		//自分の保持しているカードの追加
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
		//一応、自分のカードから自分のデッキへの追加
		///////////////////////////////////////
		
		
		for(int i = 0;i< 11;i++)
		{
			//デッキに追加していくたびに保持カードが減っていくため０でいい
			SwapMyDecksFromMyCards(i, 0);
		}
		
		
		
		///////////////////////////////////////
		//ステージの追加
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
		//ステージははじめ１から始まる
		///////////////////////////////////////		
		presentStageNum = 1;
		
		
	}	
	
}
