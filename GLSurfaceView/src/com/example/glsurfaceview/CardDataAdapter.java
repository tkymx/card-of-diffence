package com.example.glsurfaceview;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CardDataAdapter extends ArrayAdapter<CardData> {
	private LayoutInflater layoutInflater_;
	 
	 public CardDataAdapter(Context context, int textViewResourceId, List<CardData> objects) {
	 super(context, textViewResourceId, objects);
	 layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 }
	 
	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) {
	 
		 // ����̍s(position)�̃f�[�^�𓾂�
		 CardData item = (CardData)getItem(position);
		 
		 // convertView�͎g���񂵂���Ă���\��������̂�null�̎������V�������
		 if (null == convertView) 
		 {			 
			 convertView = layoutInflater_.inflate(R.layout.carddata_layout, null);
		 }
		
		 // CustomData�̃f�[�^��View�̊eWidget�ɃZ�b�g����
		 ImageView imageView;
		 imageView = (ImageView)convertView.findViewById(R.id.image);
		 imageView.setImageBitmap(item.getImageData());
		 TextView textView;
		 textView = (TextView)convertView.findViewById(R.id.name);
		 textView.setText(item.getNameData());
		 textView.setTextSize(Const.ry(0.04));
		 TextView textView1;
		 textView1 = (TextView)convertView.findViewById(R.id.atack);
		 textView1.setText(item.getAtackData());
		 textView1.setTextSize(Const.ry(0.025));
		 TextView textView2;
		 textView2 = (TextView)convertView.findViewById(R.id.defence);
		 textView2.setText(item.getDefenceData());
		 textView2.setTextSize(Const.ry(0.025));
		 TextView textView3;
		 textView3 = (TextView)convertView.findViewById(R.id.explain);
		 textView3.setText(item.getExplainData());
		 textView3.setTextSize(Const.ry(0.015));
		 
		 convertView.setBackgroundResource(R.drawable.list); 
		 
		 return convertView;
	 }
	}