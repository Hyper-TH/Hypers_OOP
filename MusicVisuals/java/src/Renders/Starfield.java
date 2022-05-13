package Renders;

// import java.util.*;
// import java.util.ArrayList;
// import java.util.Collection;

// import processing.core.*;

public class Starfield 
{

    MyVisuals mv;
    StarsVisual sv;
    StarsVisual[] stars = new StarsVisual[400];

    public Starfield(MyVisuals mv)
    {
        this.mv = mv;
    }


    // Star array
    // ArrayList<StarsVisual> Stars = new ArrayList<StarsVisual>();
    
    //Star[] stars = new Star[400];
    // Add stuff to list
    public void settings()
    {
        for (int i = 0; i < 400; i++)
        {
            stars[i] = new StarsVisual();
        }
    }

    public void draw() 
    {
        //speed of stars by mouse position
        //float speed = map(mouseX, 0, width, 0, 20);

        //fan from center
        mv.translate(mv.width/2, mv.height/2);
        for (int i = 0; i < stars.length; i++) 
        {
            stars[i].update();
            stars[i].render(mv); 
        }
    }
}

