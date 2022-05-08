package c20361521;

import ie.tudublin.*;

// import processing.core.PApplet;
import processing.core.PFont;
// import processing.core.*;

public class Strings 
{
    MyVisuals mv;

    public Strings(MyVisuals mv)
    {
        this.mv = mv;
    }

    // An array of news headlines
    String[] headlines = 
    {
        "And it's a long way forward, so trust in me",
        "I'll give them shelter, like you've done for me",
        "And I know, I'm not alone, you'll be watching over us",
        "Until you're gone",
    };
        
    //declare an abject of type Pfont
    PFont f;  // Global font variable
    float x;  // horizontal location of headline
    int index = 0;

    public void setup()
    {
        //Create the font by referencing the font name and the function createFont()
        f = mv.createFont("Arial", 16, true); // Arial, 16 point, anti-aliasing on 
       //  size(200, 200);

        // Initialize headline offscreen
        x = mv.width;
    }

    public void draw() 
    {
        mv.background(255);
        mv.fill(0);
      
        // Display headline at x location
        mv.textFont(f, 16);
        // mv.textAlign (LEFT);
      
        // A specific String from the array is displayed according to the value of the "index" variable.
        mv.text(headlines[index], x, mv.height-20); 
      
        // Decrement x
        x = x - 3;
      
        // If x is less than the negative width, then it is off the screen
        // textWidth() is used to calculate the width of the current String.
        float w = mv.textWidth(headlines[index]); 
        if (x < -w) {
          x = mv.width;
          // index is incremented when the current String has left the screen in order to display a new String.
          index = (index + 1) % headlines.length;
        }
    }
}
