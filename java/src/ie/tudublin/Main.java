package ie.tudublin;

import processing.core.PApplet;

public class Main
{	
	public static void musicVisual()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new MusicVisual());
    }

	public static void Circles()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Circles());
    }

	public static void Puddle()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Puddle());
    }

	public static void Rain()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Rain());
    }

	public static void visuals()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Visuals());
    }
	public static void Rainbow()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Rainbow());
    }

	public static void Sphere()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Sphere() {
			
		});
    }

	
	public static void main(String[] args)
	{
		Rainbow();
	}
}