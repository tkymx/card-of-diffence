package com.example.glsurfaceview;

import java.util.LinkedList;
import java.util.List;

public abstract class Node {
	private LinkedList<Sprite> list;
	private Node mother;
	
	public void add(Node node){}
	
	public void removeForMother(){}
	
	public List getList(){return list;}
	
	public void Update(){}
	
	public void Draw(){}
	
	protected abstract void onUpdate();
	
	protected abstract void onDraw();

}
