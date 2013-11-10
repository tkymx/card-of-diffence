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
	 // 特定の行(position)のデータを得る
		 CardData item = (CardData)getItem(position);
	 
	 // convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
	 if (null == convertView) {
	 convertView = layoutInflater_.inflate(R.layout.carddata_layout, null);
	 }
	 
	 // CustomDataのデータをViewの各Widgetにセットする
	 ImageView imageView;
	 imageView = (ImageView)convertView.findViewById(R.id.image);
	 imageView.setImageBitmap(item.getImageData());
	 
	 TextView textView;
	 textView = (TextView)convertView.findViewById(R.id.text);
	 textView.setText(item.getTextData());
	 TextView textView1;
	 textView1 = (TextView)convertView.findViewById(R.id.text1);
	 textView1.setText(item.getTextData());
	 TextView textView2;
	 textView2 = (TextView)convertView.findViewById(R.id.text2);
	 textView2.setText(item.getTextData());
	 TextView textView3;
	 textView3 = (TextView)convertView.findViewById(R.id.text3);
	 textView3.setText(item.getTextData());
	 
	 
	 return convertView;
	 }
	}
