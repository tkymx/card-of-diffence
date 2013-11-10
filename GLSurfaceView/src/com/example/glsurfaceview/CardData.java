package com.example.glsurfaceview;

import android.graphics.Bitmap;

public class CardData {
	
	    private Bitmap imageData_;
	    private String textData_;
	    private String name;
	 
	    public void setImagaData(Bitmap image) {
	        imageData_ = image;
	    }
	 
	    public Bitmap getImageData() {
	        return imageData_;
	    }
	 
	    public void setTextData(String text) {
	        textData_ = text;
	    }
	 
	    public String getTextData() {
	        return textData_;
	    }
	    public void setnameData(String text) {
	        textData_ = text;
	    }
	 
	    public String getnameData() {
	        return textData_;
	    }

}
