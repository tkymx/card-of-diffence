package co.example.data;

import java.util.HashMap;

import com.example.user.Card;
import com.example.user.DamageMagicCard;
import com.example.user.DamageTrapCard;
import com.example.user.MagicCard;
import com.example.user.MonsterCard;

/**
 * 
 * ��{�I�ȃJ�[�h�̏�Ԃ��킩��
 * 
 * �J�[�h�̏���ێ����Ă���A���O���w�肷�邱�ƂŃJ�[�h�����擾���邱�Ƃ��ł���
 * 
 * @author ultra-tkymx
 *
 */

public class CardInformation {

	enum Card_Kind{ Monster , Trap , Magic }
	
	//��{���////////////////////////////////////////////
	
	private String name;
	private String explain;
	private int need;
	private int card_id;
	private int attack;
	private int deffence;	
	private Card_Kind kind;
	
	public int getNeed() {
		return need;
	}
	public void setNeed(int need) {
		this.need = need;
	}	
	public int getCard_id() {
		return card_id;
	}
	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDeffence() {
		return deffence;
	}
	public void setDeffence(int deffence) {
		this.deffence = deffence;
	}
	public Card_Kind getKind() {
		return kind;
	}
	public void setKind(Card_Kind kind) {
		this.kind = kind;
	}
	public static HashMap<String, CardInformation> getCards() {
		return cards;
	}
	public static void setCards(HashMap<String, CardInformation> cards) {
		CardInformation.cards = cards;
	}
	
	//�ÓI�ȏ��////////////////////////////////////////////
	
	//�J�[�h�̏����Z�b�g���Ă���
	private static HashMap<String, CardInformation> cards;
	public static void SetCardInforation( CardInformation ci )
	{
		cards.put(ci.getName(), ci);
	}
	//���O����J�[�h�����擾
	public static CardInformation GetCardInformaionFromName( String str )
	{
		return cards.get(str);
	}	
	//���O����J�[�h�����擾
	public static Card GetCardFromName( String str , int l,int t,int w,int h  )
	{
		CardInformation ci = GetCardInformaionFromName(str);
		if(ci==null)return null;
		
		return ci.getCard(l, t, w, h);
	}
	public static CardInformation CreateCardInformation(  String name,String explain,int attack,int deffence,Card_Kind kind,int id,int need )
	{
		return new CardInformation( name , explain , attack , deffence , kind , id , need );
	}	
	//������
	public static void Init()
	{
		cards = new HashMap<String, CardInformation>();
	}
	
	//�R���X�g���N�^���////////////////////////////////////////////
	
	private CardInformation( String name,String explain,int attack,int deffence,Card_Kind kind,int id,int need  )
	{
		this.name = name;
		this.explain = explain;
		this.attack = attack;
		this.deffence = deffence;
		this.kind = kind;
		this.card_id = id;
		this.need = need;
	}
	
	//�J�[�h��񂩂�J�[�h���擾
	public Card getCard(int l, int t,int w,int h )
	{
		if( kind == Card_Kind.Monster )
		{
			return MonsterCard.CreateMonsterCard(l, t, w, h, card_id, need);
		}
		else if( kind == Card_Kind.Trap )
		{
			return DamageTrapCard.CreateDamageTrapCard(l, t, w, h, card_id, need);				
		}
		else if( kind == Card_Kind.Magic )
		{				
			return DamageMagicCard.CreateDamageMagicCard(l, t, w, h, card_id, need);
		}
		return null;		
	}
	
	
	
}
