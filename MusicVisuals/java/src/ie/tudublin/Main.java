package ie.tudublin;

import Renders.MyVisuals;
import c20361521.*;
import Renders.*;

public class Main
{	

	// public void startUI()
	// {
	// 	String[] a = {"MAIN"};
    //     processing.core.PApplet.runSketch( a, new MyVisuals());		
	// }

	// public static void main(String[] args)5
	// {
	// 	Main main = new Main();
	// 	main.startUI();			
	// }

	public static void MyVisuals()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new MyVisuals());

    }

	public static void RoughRenders()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new RoughRenders());

    }

	public static void Butt()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Butterfly());

    }


	// public static void strings()
	// {
	// 	String[] a = {"MAIN"};
    // 	processing.core.PApplet.runSketch( a, new strings());

    // }

	public static void main(String[] args)
    {
        RoughRenders();
		// Butt();
    }
} 