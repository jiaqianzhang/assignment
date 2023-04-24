package ie.tudublin;

import processing.core.PApplet;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new MyVisual());		
	}
	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new LauraSun());		
	}
	
	public static void main(String[] args)
	{
		Main main = new Main();
		// main.startUI();			
		main.startUI();
	}
}