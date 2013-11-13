package com.example.glsurfaceview;

import android.graphics.Bitmap;

public class CardData {
	
	    private Bitmap imageData_;
	    private String textData_;
	    private String name_;
	    private String atack_;
	    private String defence_;
	    private String explain_;
	    
	 
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
	    public void setAtackData(String text) {
	        atack_ = text;
	    }
	 
	    public String getAtackData() {
	        return atack_;
	    }
	    public void setDefenceData(String text) {
	        defence_ = text;
	    }
	 
	    public String getDefenceData() {
	        return defence_;
	    }
	    public void setNameData(String text) {
	        name_ = text;
	    }
	 
	    public String getExplainData() {
	        return explain_;
	    }
	    public void setExplainData(String text) {
	        explain_ = text;
	    }
	 
	    public String getNameData() {
	        return name_;
	    }

}
