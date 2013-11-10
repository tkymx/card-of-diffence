package co.example.data;

import java.util.ArrayList;

import com.example.glsurfaceview.R;

import co.example.data.CardInformation.Card_Kind;

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
	
	//基本的にな処理////////////////////////////////////////////////////////////////////////
	
	public static void Init()
	{
		//全てのカード
		CardInformation.Init();
		//自分のカード
		myCards = new ArrayList<String>();
		//自分のデッキ
		myDecks = new String[11];	
	}
	
	public static void LoadData()
	{
		//ここで、デッキの初期化処理などを行う
		
		//デッキへカードの種類の投稿を行う		
		
		//モンスター
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
		
		//自分のカードへ追加する
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
		
		//自分のカードからデッキに加える
		for(int i = 0;i< 11;i++)
		{
			//デッキに追加していくたびに保持カードが減っていくため０でいい
			SwapMyDecksFromMyCards(i, 0);
		}
		
	}	
	
}
