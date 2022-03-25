// 2021-2022 OOP
package ie.tudublin;

import processing.core.PApplet;

public class Loops extends PApplet {

	// map(a, b, c, d, e);
	// a = inputvalue
	// b - c - start and end of the first range
	// d - e - start and and of the end range

	// map(-2, 10, 90, 200, 233);

	// Initialize mode within class scope
    int mode = 0;

	public void settings() 
	{
		size(500, 500);
	}

	public void setup() 
	{
		colorMode(HSB); // Default colourmode
	}

	public void keyPressed() 
	{
		if (key >= '0' && key <= '9') 
		{
			mode = key - '0';
		}
		println(mode);
	}

	float magicMap(float a, float b, float c, float d, float e) {
		float output;
		a -= b;
		c -= b;
		e -= d;

		output = ((a / c) * e) + d;

		return output;
	}

	float magicMap1(float a, float b, float c, float d, float e) {
		float r1 = c - b;
		float r2 = e - d;
		float howFar = a - b;

		return d + ((howFar / r1) * r2);
	}

	float offset = 0;

	public void draw() 
	{

		switch (mode) 
		{
			// Bars
			case 0:
			{
				background(0);
				colorMode(HSB);

				int bars = (int) (mouseX / 20.0f);  // Position divided by 20.0f = amount of bars
				float w = width / (float) bars;		// Width of display window divided by amount of bars
				
				for (int i = 0; i < bars; i++) 
				{	// For every bar
					noStroke();
					fill(map(i, 0, bars, 0, 255), 255, 255); 	 // Map each bar to colour range
					rect(map(i, 0, bars, 0, 500), 0, w, height); // Map each bar to its x position
				}

				break;
			}

			// Squares
			case 1: 
			{
				background(0);
				colorMode(HSB);

				int squares = (int) (mouseX / 20.0f);
				float w = width / (float) squares;				// Width of each square

				for (int i = 0; i < squares; i++) 
				{
					noStroke();
					fill(map(i, 0, squares, 0, 255), 255, 255); // Map each bar to its colour range
					
					float x = map(i, 0, squares, 0, width); 	// Map the position of the square (range of number of squares against range of width)
					
					rect(x, x, w, w);				
					rect((width - w) - x, x, w, w); 			// Opposite position of above 
				}

				break;
			}

			// Circle
			case 2:
			{
				background(0);
				colorMode(HSB);
				noStroke();

				int circles = (int) (mouseX / 20.f);
				float d = width / (float) circles;

				for (int i = 0; i < circles; i++)
				{
					fill(map(i, 0, circles, 0, 255), 255, 255);
					circle(map(i, 0, circles - 1, d / 2.0f, width - (d / 2.0f)), height / 2, d);
				}

				break;
			}

			// Circles
			case 3:
			{
				background(255);
				colorMode(HSB);
				noStroke();

				int circles = (int) (mouseX / 20.0f);	// For every 20px there's a circle
				offset += (mouseY / 100.0f);			// Speed for cycling colours		
				float d = width / (float) circles;		// Diameter of each circle
				
				for (int j = 0; j < circles; j++) 
				{
					for (int i = 0; i < circles; i++) 
					{
						float c = map((i + j + offset), 0, circles * 2, 0, 255) % 256; // Modulated by 256 for every frame
						
						fill(c, 255, 255);
						
						float x = map(i, 0, circles - 1, d / 2.0f, width - (d / 2.0f)); 
						float y = map(j, 0, circles - 1, d / 2.0f, width - (d / 2.0f)); 
						
						circle(x, y, d);
					}
				}

				break;
			}

			// Checkered board
			case 4:
			{
				background(0);
				colorMode(RGB);
				stroke(0, 255, 0);

				float border = width * 0.1f; 
				
				for(int i = -5; i <= 5; i ++)
				{
					float x = map(i, -5, 5, border, width - border); 
						
					line(x, border, x, height - border);	// Vertical lines
					line(border, x, width - border, x);	 	// Horizontal lines
					
					fill(255);
					text(i, x, border * 0.5f); // x
					text(i, border * 0.5f, x); // y
				}

				break;
			}

			// Star
			case 5:
			{
				background(0);
				colorMode(HSB);
				stroke(255, 255, 255);	
				
				float cx = width / 2;
				float cy = height / 2;	
				float radius = 200;		
				int points = (int) map(mouseX, 1, width, 5, 20);
				int sides = points * 2;
				float px = cx;
				float py = cy - radius; 
				
				for(int i = 0 ; i <= sides ; i ++)
				{
					float r = (i % 2 == 0) ? radius : radius / 2; 
					// float r = radius;
					float theta = map(i, 0, sides, 0, TWO_PI);
					float x = cx + sin(theta) * r;
					float y = cy - cos(theta) * r;
					
					//circle(x, y, 20);
					line(px, py, x, y);
					px = x;
					py = y;
				}

				break;
			}
		} // end switch()
	}
} // end class
