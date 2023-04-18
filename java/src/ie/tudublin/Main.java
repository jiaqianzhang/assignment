package ie.tudublin;

public class Main
{	
	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new MyVisual());		
	}

	public static void sun()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new LauraSun());
    }

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startUI();		
		// sun();	
	}
}