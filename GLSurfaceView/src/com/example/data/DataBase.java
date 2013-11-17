package com.example.data;

import java.util.ArrayList;

import com.example.data.CardInformation.Card_Kind;
import com.example.glsurfaceview.R;
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

	//保持しているカードの処理/////////////////////////////////////////////////////////////////////////////
	
	//自分のカードの追加
	public static void AddMyCards( String str )
	{
		myCards.add(str);
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
		myCards.add(deck);
	}
	
	//ステージの処理////////////////////////////////////////////////////////////////////////
	public static Stage getPresentStage() {
		return presentStage;
	}
	public static void setPresentStage(Stage presentStage) {
		DataBase.presentStage = presentStage;
	}	
	
	//基本的にな処理////////////////////////////////////////////////////////////////////////
	public static void Init()
	{
		//全てのカード
		CharactorInfomation.Init();
		//全てのカード
		CardInformation.Init();
		//自分のカード
		myCards = new ArrayList<String>();
		//自分のデッキ
		myDecks = new String[11];	
		
		//はじめはステージ情報は無し
		presentStage = null;
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
		//デッキの種類の追加
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
		
		///////////////////////////////////////
		//一応、自分のカードから自分のデッキへの追加
		///////////////////////////////////////
		
		for(int i = 0;i< 11;i++)
		{
			//デッキに追加していくたびに保持カードが減っていくため０でいい
			SwapMyDecksFromMyCards(i, 0);
		}
		
	}	
	
}
