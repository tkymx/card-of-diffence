package com.example.glsurfaceview;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DeckSelect extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout li = new LinearLayout(this);
        
        Button button = new Button(this);
        button.setOnClickListener(this);
        
        li.addView(button);
        
        setContentView(li);
        
    }
    
	@Override
	public void onClick(View v) {
		
		//トースト
		Toast.makeText(this, "wewewew", Toast.LENGTH_LONG);
		
		//スタートさせる
		OpenGLSurfaceView.GameStart();
		
		finish();
		
	}


}
