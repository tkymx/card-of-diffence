package com.example.glsurfaceview;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



import com.example.*;
import com.example.data.CardInformation;
import com.example.data.DataBase;
import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.Text;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class DeckSelect extends Activity{
	
	Button okButton;		//�f�b�L�̗p����{�^��
	Button backButton;		//�߂�{�^��
	ListView lv;			//���X�g�r���[�@�E�̃X�N���[���ł�����
	TextView cardNameText,atackText,defenceText,explainText;	//���̏ڍ׉�ʂɎg���e�L�X�g�r���[�B
	ImageView card;			//���̏ڍ׉�ʂɎg���L������ImageView
	
	List<CardData> objects;	//CardData�N���X�̃��X�g �����CardInfomation�g���������ɕς���
	List<CardInformation> cardList;
	
	LinearLayout ll;		//�S�̂𕢂����C�A�E�g
	RelativeLayout rl;		//���̏ڍ׏��̕����̃��C�A�E�g
	
	RelativeLayout.LayoutParams okParam;//�p�����[�^
	RelativeLayout.LayoutParams backParam;
	RelativeLayout.LayoutParams atackTextParam;
	RelativeLayout.LayoutParams defenceTextParam;
	RelativeLayout.LayoutParams explainTextParam;
	RelativeLayout.LayoutParams cardNameTextParam;
	RelativeLayout.LayoutParams cardParam;
	 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//���낢�됶��
		ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		lv = new ListView(this);
		rl = new RelativeLayout(this);
		cardNameText = new TextView(this);
		card = new ImageView(this);
		atackText = new TextView(this);
		defenceText = new TextView(this);
		explainText = new TextView(this);
		okButton = new Button(this);
		backButton = new Button(this);
		
		/*����ʂ̏����Z�b�g*/
		cardNameText.setText("�e�B�K���b�N�X");
		atackText.setText("�U���́@53��");
		defenceText.setText("�h��́@1");
		explainText.setText("��΂�����͂͂͂͂͂�\n�͂͂͂͂͂͂͂͂͂͂�\n�͂͂͂͂͂͂͂͂͂͂͂͂͂�");
		okButton.setText("OK");
		backButton.setText("�߂�");
		//�Ƃ肠�����K���ȉ摜�ŏ����ݒ�
		Bitmap image2;
		image2 = BitmapFactory.decodeResource(getResources(), R.drawable.card);	
		card.setImageBitmap(image2);
		
		
		//�e�L�X�g�T�C�Y�ݒ�
		cardNameText.setTextSize(Const.ry(0.030));
		atackText.setTextSize(Const.ry(0.020));	
		defenceText.setTextSize(Const.ry(0.020));
		explainText.setTextSize(Const.ry(0.010));
		
		//�p�����[�^�����A�傫���̐ݒ�
		cardNameTextParam = new RelativeLayout.LayoutParams(Const.rx(0.3), Const.ry(0.1));
		cardParam = new RelativeLayout.LayoutParams(Const.rx(0.2), Const.ry(0.3));
		atackTextParam = new RelativeLayout.LayoutParams(Const.rx(0.15), Const.ry(0.1));
		defenceTextParam = new RelativeLayout.LayoutParams(Const.rx(0.15), Const.ry(0.1));
		explainTextParam = new RelativeLayout.LayoutParams(Const.rx(0.3), Const.ry(0.2));
		okParam = new RelativeLayout.LayoutParams(Const.rx(0.1), Const.ry(0.1));
		backParam = new RelativeLayout.LayoutParams(Const.rx(0.1), Const.ry(0.1));
		
		//�p�����[�^�ŏꏊ�̎w��
		cardNameTextParam.setMargins(Const.rx(0.05), Const.ry(0.05),0,0);
		cardParam.setMargins(Const.rx(0.1), Const.ry(0.2),0,0);
		atackTextParam.setMargins(Const.rx(0.05), Const.ry(0.50),0,0);
		defenceTextParam.setMargins(Const.rx(0.20), Const.ry(0.50),0,0);
		explainTextParam.setMargins(Const.rx(0.05), Const.ry(0.60),0,0);
		okParam.setMargins(Const.rx(0.05), Const.ry(0.8),0,0);
		backParam.setMargins(Const.rx(0.25), Const.ry(0.8),0,0);
		
	/*�V�����f�[�^�x�[�X�ō���Ă݂�*/
		//cardList = new ArrayList<CardInformation>();
		DataBase.LoadData();
		CardDataAdapter ad = new CardDataAdapter(this,0,DataBase.myCards);
		lv.setAdapter(ad);
	
	
	/*�Ƃ肠�����K���Ƀf�[�^�x�[�X����̔z��������*/
		/*String[] name = {"��","�o�X","��s�@","�^�N�V�[","���]��","���s","�N���E��","���R","�n��","����","�͒�",};
		Bitmap image1 = BitmapFactory.decodeResource(getResources(), R.drawable.atack);
		Bitmap[] bmp = {image1,image1,image1,image1,image1,image1,image1,image1,image1,image1,image1,};
		String[] atack = {"�U���� 1000","�U���� 2000","�U���� 3000","�U���� 1000","�U���� 2000","�U���� 3000","�U���� 1000","�U���� 2000","�U���� 3000","�U���� 1000","�U���� 1000"};
		String[] defence = {"�h��́@500","�h��́@600","�h��́@700","�h��́@500","�h��́@600","�h��́@700","�h��́@500","�h��́@600","�h��́@700","�h��́@500","�h��́@500"};
		String[] explain = {"������������������������������������������������������������","�o�X","��s�@","�^�N�V�[","���]��","���s","�N���E��","���R","�n��","����","�͒�",};			
		
		
		objects = new ArrayList<CardData>();
		for(int i=0;i<name.length;i++){
			CardData object = new CardData();
			object.setImagaData(bmp[i]);
			object.setNameData(name[i]);
			object.setAtackData(atack[i]);
			object.setDefenceData(defence[i]);
			object.setExplainData(explain[i]);
			
			objects.add(object);//��̔z�񂩂�CardData�N���X�̃��X�g�𐶐�
		}
		//�A�_�v�^�[�Z�b�g
		CardDataAdapter ad = new CardDataAdapter(this,0,objects);
		lv.setAdapter(ad);
	/*�f�[�^�x�[�X�I���*/
		
		
		//�o�b�N�O���E���h�摜�ݒ�
		ll.setBackgroundResource(R.drawable.background);
		cardNameText.setBackgroundResource(R.drawable.name); 
		atackText.setBackgroundResource(R.drawable.atack);
		defenceText.setBackgroundResource(R.drawable.defence);
		explainText.setBackgroundResource(R.drawable.explain); 
		atackText.setBackgroundResource(R.drawable.list);
		defenceText.setBackgroundResource(R.drawable.list); 
		explainText.setBackgroundResource(R.drawable.list); 
		
		//���낢��Z�b�g
		setContentView(ll);
		ll.addView(rl,new LinearLayout.LayoutParams(Const.rx(0.4), Const.ry(1)));
		ll.addView(lv,new LinearLayout.LayoutParams(Const.rx(0.6), Const.ry(1)));
		rl.addView(cardNameText, cardNameTextParam);
		rl.addView(card, cardParam);
		rl.addView(atackText,atackTextParam);
		rl.addView(defenceText,defenceTextParam);
		rl.addView(explainText,explainTextParam);
		rl.addView(okButton, okParam);
		rl.addView(backButton,backParam);
		
		okButton.setOnClickListener(new SampleClickListener());
		backButton.setOnClickListener(new SampleClickListener());
		lv.setOnItemClickListener(new SampleItemClickListener());
	}
	
	///////////////////////�ȉ��A���X�i///////////////////////////////
	class SampleItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String selectedCard = DataBase.myCards.get(arg2);
			card.setImageResource(CardInformation.GetCardInformaionFromName(selectedCard).getCard_id());
			atackText.setText(CardInformation.GetCardInformaionFromName(selectedCard).getName());		
			defenceText.setText(CardInformation.GetCardInformaionFromName(selectedCard).getName());
			explainText.setText(CardInformation.GetCardInformaionFromName(selectedCard).getName());
			cardNameText.setText(CardInformation.GetCardInformaionFromName(selectedCard).getName());	
			/*CardData selectCard = objects.get(arg2);			/*ListView�̃^�b�`���ꂽ�����ɑ�������CardData���A
																	CArdData��ArrayList���玝���Ă��Ă�*//*
			atackText.setText(selectCard.getAtackData());		//CardData�N���X�̃��\�b�h���g���āA�Z�b�g������
			defenceText.setText(selectCard.getDefenceData());	//CardData�N���X�̃��\�b�h���g���āA�Z�b�g������
			explainText.setText(selectCard.getExplainData());	//CardData�N���X�̃��\�b�h���g���āA�Z�b�g������
			cardNameText.setText(selectCard.getNameData());		//CardData�N���X�̃��\�b�h���g���āA�Z�b�g������
			card.setImageBitmap(selectCard.getImageData());		//CardData�N���X�̃��\�b�h���g���āA�Z�b�g������
			*/
		}
	}
	
	
	class SampleClickListener implements android.view.View.OnClickListener{
		public void onClick(View v) {
			if(v.equals(okButton))
			{
				Intent intent = new Intent();
				intent.setClass( OpenGLSurfaceView.c , com.example.glsurfaceview.DeckEdit.class);
				
				OpenGLSurfaceView.c.startActivity(intent);
				finish();
			}
			else if(v.equals(backButton))
			{
				Intent intent = new Intent();
				intent.setClass( OpenGLSurfaceView.c , com.example.glsurfaceview.DeckEdit.class);
				
				OpenGLSurfaceView.c.startActivity(intent);
				finish();
			}
		}
		
	}

	
	  
	
	

}