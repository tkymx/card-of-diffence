package com.example.data;

import java.util.ArrayList;

import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Score;
import com.example.user.DamageMagicCard;
import com.example.user.DamageTrapCard;
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
				CharactorInfomation.Create("レッドセイバー","伝説の戦士。赤き聖剣の名で魔王討伐に乗り出す。その後は聖剣の魔力に取り憑かれ魔王となる。",20,2,1, 2, R.drawable.player1_w, R.drawable.player1_w, R.drawable.player1_w ) );
		CharactorInfomation.AddCharactor(
				CharactorInfomation.Create("ジン","疾風の戦士。戦場をかける速さはあまりに速く、その速さは音速をも超えるという。",8,1,1, 6, R.drawable.player2_w, R.drawable.player2_w, R.drawable.player2_w  ) );
		CharactorInfomation.AddCharactor(
				CharactorInfomation.Create("ホックロック","天性の海賊。その昔伝説の海賊を倒した際の呪いから顔に大きな字を持つ。",20,1,1, 2, R.drawable.player3_w, R.drawable.player3_w, R.drawable.player3_w  ) );
		CharactorInfomation.AddCharactor(
				CharactorInfomation.Create("ズーカー","最高学府出身の才に秀でた少年。勉強次第でどんな力も扱うことができる。",15,2,1, 2, R.drawable.player4_w, R.drawable.player4_w, R.drawable.player4_w  ) );
		CharactorInfomation.AddCharactor(
				CharactorInfomation.Create("ロベルチュリス","英国出身の紳士、紳士故に戦場においても落ち着きを崩さない。",10,1,2, 2, R.drawable.player5_w, R.drawable.player5_w, R.drawable.player5_w  ) );
		CharactorInfomation.AddCharactor(
				CharactorInfomation.Create("ウェルダム","重量戦士、戦場において前線を崩さないことを主な任務と心得ている。",14,3,1, 1, R.drawable.player6_w, R.drawable.player6_w, R.drawable.player6_w  ) );
		CharactorInfomation.AddCharactor(
				CharactorInfomation.Create("ケイチャン","運動不足寝不足になるほど戦闘力が上昇する。",10,2,1, 2, R.drawable.player7_w, R.drawable.player7_w, R.drawable.player7_w  ) );
		CharactorInfomation.AddCharactor(
				CharactorInfomation.Create("セノー","古の騎士、古来からの経験を持って全てに当たる。",15,3,1, 3, R.drawable.player8_w, R.drawable.player8_w, R.drawable.player8_w  ) );
		CharactorInfomation.AddCharactor(
				CharactorInfomation.Create("ゼルス","やすらぎの天使、戦場における紅一点として安らぎを与える反面、接触したものはいない。",30,1,1, 1, R.drawable.player9_w, R.drawable.player9_w, R.drawable.player9_w  ) );
		
		CharactorInfomation.AddCharactor(
				CharactorInfomation.Create("enemy1","死者の魂が骸骨に乗り移った姿、魂のレベルで強さが変化する",10,1,1, 2, R.drawable.enemy1_w, R.drawable.enemy1_ab, R.drawable.enemy1_af ) );
		CharactorInfomation.AddCharactor(
				CharactorInfomation.Create("dora","洞窟の中でいつも卑屈に生きてきた飛行生物らしい",15,2,1, 2, R.drawable.dora, R.drawable.dora, R.drawable.dora ) );
		CharactorInfomation.AddCharactor(
				CharactorInfomation.Create("dragon","死をも食らうドラゴン。誰一人として死に際を見たものはいない",20,3,1, 3, R.drawable.dragon, R.drawable.dragon, R.drawable.dragon ) );
		

		///////////////////////////////////////
		//魔法、トラップの追加を行う
		///////////////////////////////////////		
		ParameterCardInfomatoin.AddParameterCard(
				ParameterCardInfomatoin.Create("快晴の雷撃", "いつ起こるかわからない、快晴と油断しているといきなりやってくる。", -1, 2));
		ParameterCardInfomatoin.AddParameterCard(
				ParameterCardInfomatoin.Create("運命の落とし穴", "落とし穴にかかったものは人生の落とし穴に遭遇するという。", 0, 5));
	
		
		///////////////////////////////////////
		//デッキの種類の追加
		///////////////////////////////////////

		//作成用サンプル
		MonsterCard mc = MonsterCard.CreateMonsterCard();
		DamageMagicCard dmc = DamageMagicCard.CreateDamageMagicCard();
		DamageTrapCard dtc = DamageTrapCard.CreateDamageTrapCard();
		
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("レッドセイバー",mc,R.drawable.moster_card_1,2));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("ジン",mc,R.drawable.moster_card_2,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("ホックロック",mc,R.drawable.moster_card_3,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("ズーカー",mc,R.drawable.moster_card_4,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("ロベルチュリス",mc,R.drawable.moster_card_5,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("ウェルダム",mc,R.drawable.moster_card_6,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("ケイチャン",mc,R.drawable.moster_card_7,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("セノー",mc,R.drawable.moster_card_8,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("ゼルス",mc,R.drawable.moster_card_9,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("運命の落とし穴",dtc,R.drawable.trap_card,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("快晴の雷撃",dmc,R.drawable.magic_card,1));		

		//敵用のカード(敵は基本的にカードとはならないので、カードの画像はダミー)
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("enemy1",mc,R.drawable.moster_card_1,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("dragon",mc,R.drawable.moster_card_1,1));
		CardInformation.SetCardInforation(
				CardInformation.CreateCardInformation("dora",mc,R.drawable.moster_card_1,1));
		
		
		///////////////////////////////////////
		//自分の保持しているカードの追加
		///////////////////////////////////////

		AddMyCards("レッドセイバー");
		AddMyCards("ジン");
		AddMyCards("ホックロック");
		AddMyCards("ズーカー");
		AddMyCards("ロベルチュリス");
		AddMyCards("ウェルダム");
		AddMyCards("ケイチャン");
		AddMyCards("セノー");
		AddMyCards("ゼルス");
		AddMyCards("快晴の雷撃");
		AddMyCards("運命の落とし穴");
		AddMyCards("レッドセイバー");
		AddMyCards("ジン");
		AddMyCards("ホックロック");
		AddMyCards("ズーカー");
		AddMyCards("ロベルチュリス");
		AddMyCards("ウェルダム");
		AddMyCards("ケイチャン");
		AddMyCards("セノー");
		AddMyCards("ゼルス");
		AddMyCards("快晴の雷撃");
		AddMyCards("運命の落とし穴");		
		
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
		String str1[] = {"enemy1","enemy1","enemy1","enemy1","enemy1","enemy1","dora","dora","dora","dora","dora"};
		String str2[] = {"enemy1","enemy1","enemy1","enemy1","dora","dora","dora","dora","dragon","dragon","dragon"};
		Stage stage[] = 
		{
			new Stage(R.drawable.stage1, R.drawable.map, R.drawable.enemy_castle , R.drawable.player_castle, 2, 1 , str),
			new Stage(R.drawable.stage2, R.drawable.map, R.drawable.enemy_castle, R.drawable.player_castle, 3, 2 , str1),
			new Stage(R.drawable.stage3, R.drawable.map, R.drawable.enemy_castle, R.drawable.player_castle, 3, 3 , str2)			
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
