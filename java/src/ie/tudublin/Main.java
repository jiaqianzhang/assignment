package ie.tudublin;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new MyVisual());		
	}

	public static void m()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new m());
    }

	public static void main(String[] args)
	{
<<<<<<< HEAD
		m();
=======
		Main main = new Main();
		main.startUI();			
>>>>>>> cde7e0c8fb7a7ce2bf7a7cfe74e8b88254a4d529
	}
}