package c20361521;

import processing.core.PApplet;
// import java.util.*;
// import java.util.ArrayList;
// import java.util.Collection;

public class rain extends PApplet{

    Drops dr;[]
    //variable
    // ArrayList<Drops> drops = new ArrayList<Drops>();
    Drops[] drops = new Drops[100];
    
    float currDrop;

    public void settings() 
    {
        size(640, 360);
    }

    public void setup()
    {
        //color(HSB);
        background(230, 230, 250);
        smooth();

        for(int i = 0; i < 100; i++)
        {
            drops[i] = new Drops();
            // System.out.println("Current Index: " + drops[i]);
        }
    }

    public void draw()
    {
        for(int i = 0; i < 100; i++)
        {
            System.out.println(drops[i].getClass().getSimpleName()); // Class c20361521.Drops
            System.out.println("Current Drop:" + drops[i]);
            
            drops[i].render(); // Program fails here
            drops[i].fall();
        }
    }

}


class Drops extends rain
{
    //variables
    float x = random(width); //drop starts in middle
    float y = random(-500, -50); //drop starts at top of screen
    float z = random(0, 20);

    //drop longer if closer, and shorter if further away
    float length = map(z, 0, 20, 10, 20);
    //closer drops are faster
    float yspeed = map(z, 0, 20, 4, 10);

    //how it will show on screen
    public void render(){

        // float z = random(1, 20);
        //1px when far, 3px when close
        // float thicc = map(z, 0, 20, 1, 3);

        // System.out.println("Value of thicc: " + thicc);
        
        // strokeWeight(thicc);
        stroke(138, 43, 226);
        line(x, y, x, y + 10);

    }

    //where the rain will fall
    public void fall() {

        //fall
        y = y + yspeed;
        //gravity
        float plummet = map(z, 0, 20, 0, (float) 0.2);
        yspeed = yspeed + plummet;

        if (y > height)
        {
            y = random(-200, -100);
            yspeed = map(z, 0, 20, 4, 10);
        }

    }

    
}