// 2021 - 2022 OOP

package ie.tudublin;

import processing.core.PApplet;

public class Arrays extends PApplet 
{
    float[] rainfall = { 45, 37, 55, 27, 38, 50, 79, 48, 104, 31, 100, 58 };

    // float[] a1 = new float[100];
    // float[] a2; // Will throw null pointer exception
    
    String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", 
                        "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

    float[] a1 = new float[100];
    float[] a2;

    int minIndex = 0;
    int maxIndex  = 0;

    int mode = 0;

    public void keyPressed() 
    {
		if (key >= '0' && key <= '9') 
        {
			mode = key - '0';
		}
        
		println(mode);
	}       

    public void settings()
    {
        size(500, 500);
    }

    public void setup()
    {
        for(int i = 0 ; i < rainfall.length ; i ++)
        {
            println(rainfall[i] + "\t" + months[i]);
        }
        for(float r:rainfall)
        {
            println(r);
        }
        
        int j = 0;

        for(float r:rainfall)
        {
            println(r + "\t" + months[j]);
            j++;
        }

        for(int i = rainfall.length - 1; i >= 0 ; i -- )
        {
            println(rainfall[i] + "\t" + months[i]);
        }

        for(int i = 1 ; i < rainfall.length ; i ++)
        {
            if (rainfall[i] < rainfall[minIndex])
            {
                minIndex = i;
            }
            else if (rainfall[i] > rainfall[maxIndex])
            {
                maxIndex = i;
            } 
        }

        println("Max rainfall: " + rainfall[maxIndex] + " in month " + months[maxIndex]);
        println("Min rainfall: " + rainfall[minIndex] + " in month " + months[minIndex]);

    }

    public void draw()
    {
        switch (mode) 
        {
            // Simplified bar chart
			case 0:
            {
                background(0);
                colorMode(HSB);
                noStroke();

                float w = width / (float) rainfall.length;  // width of each bar
                
                // For every rainfall
                for(int i = 0 ; i < rainfall.length ; i ++)
                {
                    // Map top left corner of rect
                    float x = map(i, 0, rainfall.length, 0, width);
                    // Map height of rect
                    float h = map(rainfall[i], 0, rainfall[maxIndex], 0, -height);
                    // Map color
                    int c = (int) map(i, 0, rainfall.length, 0, 255);
                    
                    fill(c, 255, 255);
                    rect(x, height, w, h);

                    // Label at center of bar
                    fill(255);
                    textAlign(CENTER, CENTER);
                    text(months[i], x + (w / 2), height - 50);
                }

                break;
            }

            // Bar chart
            case 1:
            {
                background(0);
                stroke(255);
                
                float border = width * 0.1f;    // Size of each (cube)
    
                // Draw the axis
                line(border, border, border, height - border);                      // Vertical
                line(border, height - border, width - border, height - border);     // Horizontal
                
                for(int i = 0 ; i <= 120; i += 10)
                {
                    float y = map(i, 0, 120, height - border, border);
                    
                    line(border - 5, y, border, y);
                    
                    fill(255);
                    textAlign(CENTER, CENTER);
                    text(i, border / 2, y);
                }
               
                float w = (width - (border * 2.0f)) / (float) rainfall.length;
                
                for(int i = 0 ; i < rainfall.length; i ++)
                {
                    // Map top left corner position
                    float x = map(i, 0, rainfall.length, border, width-border);                    
                    // Map height of rect
                    float h = map(rainfall[i], 0, 120, 0, -height + (border * 2.0f)); 
                    // Map color
                    float c = map(i, 0, rainfall.length, 0, 255);
                    
                    stroke(255);
                    fill(c, 255, 255);
                    
                    rect(x, height - border, w, h);

                    // Label at center of bar
                    fill(255);
                    text(months[i], x + (w / 2), height - (border / 2));
                }

                break;
            }

            // Pie chart (size dependent on mouseX position)
            case 2:
            {
                background(0);
                stroke(255);
                noFill();
                noStroke();

                float r = mouseX;
                float cx = width / 2;   // Mid point x coord
                float cy = height / 2;  // Mid point y coord
                float start = 0;        // Starting angle
                float tot = 0;

                //circle(cx, cy, r * 2.0f);
                for(float f:rainfall)
                {
                    tot += f;
                }
                
                for(int i = 0 ; i < rainfall.length ; i ++)
                {
                    // Map angle
                    // Range from 0 to sum of rainfall
                    // Range from 0 to 2pi
                    float val = map(rainfall[i], 0, tot, 0, TWO_PI);

                    // Map color
                    float c = map(i, 0, rainfall.length, 0, 255);
                    
                    // Draw arc
                    fill(c, 255, 255);
                    arc(cx, cy, r * 2, r * 2, start, start + val, PIE);
                    
                    // Set angle
                    float theta = start + (val * 0.5f);

                    // Text at center of arc
                    float x = cx + cos(theta) * (r * 1.2f); // Outside a bit of radius
                    float y = cy + sin(theta) * (r * 1.2f);
                    
                    fill(255);
                    text(months[i], x, y);

                    // Change previous angle to current angle (for next arc)
                    start = start + val;
                }

                break;
            }
        } // end switch()        
    }    
} // end class
