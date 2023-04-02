package ie.tudublin;

public class Main
{	
	public static void heart()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Heart());
    }

	public static void rain()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Rain());
    }

	public static void main(String[] args)
	{
		rain();
	}
}