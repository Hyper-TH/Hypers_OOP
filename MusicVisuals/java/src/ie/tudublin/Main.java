package ie.tudublin;

import c20361521.*;

public class Main
{	

	// public void startUI()
	// {
	// 	String[] a = {"MAIN"};
    //     processing.core.PApplet.runSketch( a, new MyVisuals());		
	// }

	// public static void main(String[] args)
	// {
	// 	Main main = new Main();
	// 	main.startUI();			
	// }

	public static void Rain()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new rain());

    }

	public static void main(String[] args)
    {
        Rain();
    }
} 