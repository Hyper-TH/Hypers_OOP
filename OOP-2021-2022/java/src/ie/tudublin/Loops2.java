// 2020-2021 OOP

package ie.tudublin;

import processing.core.PApplet;

public class Loops2 extends PApplet {

    public void settings() 
    {
        size(500, 500);

        // Default center position
        cx = width / 2;
        cy = height / 2;        
    }

    // Initialize mode within class scope
    int mode = 0;

    // Initialize variables for circle position within class scope
    float cx;
    float cy;

    float offset = 0;

    public void keyPressed() {
        // the value of mode will be the number of the 
        // number key pressed
        if (keyCode >= '0' && keyCode <= '9')
        {
            mode = keyCode - '0';
        }
    }

    public void setup() 
    {
        // Hue Saturation Brightness
        colorMode(HSB); // Default colourmode
    }

    public void draw() 
    {
        noStroke(); // Default to no strokes
        
        switch (mode)
        {
            // Change block colour when hovered
            case 0:
            {
                background(0);
                rectMode(CENTER); 
                  
                float w = 200;
                float h = 50;
                            
                // If mouse is touching the position of the block
                if (mouseX > cx -  (w /2) && mouseX < cx + (w/2) && mouseY > cy - (h/2) && mouseY < cy + (h/2))
                {
                    // Colour it yellow
                    fill(50, 255, 255);                
                }
                else
                {
                    // Colour it purple
                    fill(200, 255, 255);
                }
                rect(cx, cy, w, h);
                
                break;
            }                
            
            // Square moves on mouse position
            case 1:
            {
                background(0);
                colorMode(HSB);
                rectMode(CORNER);  
                
                fill(50, 255, 255);   

                // Mouse at top left corner
                if (mouseX < cx && mouseY < cy)
                {
                    rect(0, 0, cx, cy);
                }
                // Mouse at top right corner
                else if (mouseX > cx && mouseY < cy)
                {
                    rect(cx, 0, cx, cy);
                }
                // Mouse at bottom left corner
                else if (mouseX < cx && mouseY > cy)
                {
                    rect(0, cy, cx, cy);
                }
                // Mouse at bottom right corner
                else
                {
                    rect(cx, cy, cx, cy);
                }

                break;
            }

            // Bars
            case 2:
            {
                background(0);
                colorMode(HSB);
                rectMode(CORNER); 

                int bars = (int) (mouseX / 20.0f);  // Cast amount of bars from float to int (Every 20 pixels, create new bar)
                float w = width / (float) bars;     // Width of each bars
                float cgap = 255 / (float) bars;    // Colour gap 

                // For every bar
                for(int i = 0 ; i < bars ; i++)
                {
                    fill(i * cgap, 255, 255);   
                    rect(i * w, 0, w, height);    // Top left corner x coordinate  
                }

                break;
            }
            
            // Circles
            case 3:
            {
                background(0);
                colorMode(HSB);

                int numCircles = (int)(mouseX / 10.0f); // Cast amount of circles from float to int
                float w = width / (float) numCircles;   // Width of each circle
                float cgap = 255 / (float) numCircles;  // Colour gap

                for(int i = 0 ; i < numCircles ; i++)
                {
                    fill(cgap * i, 255, 255);
                    ellipse(w / 2 + (i * w), cy, w, w);
                }

                break;
            }

            // Skeletal Star
            case 4:
            {
                background(255);
                colorMode(HSB);
                stroke(0);
                
                int numLines = 5;
                float theta = TWO_PI / (float) numLines; // Hypotenuse-Adjacent angle
                float radius = 100;                      

                // For every line
                for(int i = 0 ; i < numLines ; i++)
                {
                    float angle = theta * i;            
                    float x = sin(angle) * radius;      
                    float y = cos(angle) * radius;

                    line(cx, cy, cx + x, cy + y);                    
                }

                break;
            }
                
            // Squares
            case 5:
            {
                background(0);
                colorMode(HSB);
                rectMode(CORNER);

                int numRects = (int) mouseX / 10;       // INT Position of mouseX divided by 10 (number of rects)
                float w = width / (float) numRects;     // Width of each rects
                float cgap = 255 / (float) numRects;    // Color gap 

                // For every rect
                for(int i = 0 ; i < numRects ; i++)
                {
                    fill(cgap * i, 255, 255);
                    rect(i * w, i * w, w, w);
                    rect(width - ((i + 1) * w), i * w, w, w); // Opposite position of above
                }

                break;
            }

            // Circle
            case 6:
            {
                background(0);
                colorMode(HSB);

                int numCircles = (int) mouseX / 10;     // Number of circles
                float cgap = 255 / (float) numCircles;  // Colour gap
                float gap = width / (float) numCircles; // Gap between each circle 
                float w = width;                        // Main outer circle

                // For loop until hit inner circle
                for(int i = numCircles ; i >= 1 ; i--)
                {
                    fill(i * cgap, 255, 255);
                    w = i * gap;
                    ellipse(cx, cy, w, w);
                    
                }  
                
                break;
            }
            

            // Static circles with changing colours depending on mouse x position
            case 7:
            {
                background(0);
                colorMode(HSB);

                offset += (mouseX / 100);                       // Position of mouse / 100 (speed?)
                int numCircles = 20;                            // 20x20
                float w = width / (float)numCircles;            // Width of each circle
                float cgap = 255 / (numCircles + numCircles);   // Colour gap

                // For every y? circle
                for(int i = 0 ; i < numCircles ; i ++)
                {
                    // For every x? circle
                    for(int j = 0 ; j < numCircles ; j ++)
                    {
                        float c = ((cgap * (i + j)) + offset) % 255; 

                        fill(c, 255, 255);
                        ellipse((w / 2) + w * j, (w / 2) + w * i, w, w);
                    }
                }

                break;
            }
           
            // Multi cornered shape depending on mouse x position
            case 8:
            {
                background(0);
                colorMode(HSB);
                stroke(255);

                int sides = (mouseX / 50);
                float theta = TWO_PI / (float) sides;
                float radius = 200;

                // For every side
                for(int i = 1 ; i <= sides ; i ++)
                {
                    float x1 = sin(theta * (i - 1)) * radius;
                    float y1 = cos(theta * (i - 1)) * radius;
                    float x2 = sin(theta * i) * radius;
                    float y2 = cos(theta * i) * radius;

                    line(cx + x1, cy + y1, cx + x2, cy* + y2);
                }

                break;
            }
        } // end switch()
    }
} // end class
