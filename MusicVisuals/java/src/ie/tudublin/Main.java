package ie.tudublin;

import Renders.MyVisuals;
import c20361521.*;
//import c20361521.*;
// import Renders.*;

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

	public static void rose()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new rose());

    }

	public static void main(String[] args)
    {
        // RoughRenders();
		MyVisuals();
    }

	
} 