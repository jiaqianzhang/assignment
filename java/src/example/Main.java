package ie.tudublin;


public class Main
{	
	private static char key;

	public static void heart()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new JiaHeart());
    }

	public static void rain()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Rain());
    }

	public static void brain()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new ManarBrain());
    }

	public static void main(String[] args)
	{
	}
}