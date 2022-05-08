package c20361521;

import ie.tudublin.*;

// import processing.core.PApplet;
// import processing.core.PFont;
import processing.core.*;

public class strings extends Visual
{

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
        f = createFont("Arial", 16, true); // Arial, 16 point, anti-aliasing on 
       //  size(200, 200);

        // Initialize headline offscreen
        x = width;
    }

    void render() 
    {
        background(255);
        fill(0);
      
        // Display headline at x location
        textFont(f, 16);
        textAlign (LEFT);
      
        // A specific String from the array is displayed according to the value of the "index" variable.
        text(headlines[index], x, height-20); 
      
        // Decrement x
        x = x - 3;
      
        // If x is less than the negative width, then it is off the screen
        // textWidth() is used to calculate the width of the current String.
        float w = textWidth(headlines[index]); 
        if (x < -w) {
          x = width;
          // index is incremented when the current String has left the screen in order to display a new String.
          index = (index + 1) % headlines.length;
        }
    }
}
