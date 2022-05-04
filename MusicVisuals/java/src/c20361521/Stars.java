package c20361521;

import processing.core.*;

public class Stars {
    
    MyVisuals mv;
    float cy = 0;

    public Stars(MyVisuals mv)
    {
        this.mv = mv;
    }

    public void render()
    {

    }
}

class Starfield 
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
            stars[i].render(mv); // Theres two renders rn
        }
    }
}

class StarsVisual 
{
    MyVisuals mv;
    Starfield st;

    public StarsVisual(MyVisuals mv, Starfield st)
    {
        this.mv = mv;
    }

    float x;
    float y;
    float z;

    float px;
    float py;
    float pz;
    public Object sv;

    StarsVisual() 
    {
        x = mv.random(-mv.width, mv.width);
        y = mv.random(-mv.height, mv.height);
        z = mv.random(0, mv.width);
        pz = z;
    }

    public void update() 
    {
        float speed = 100;
        z = z - speed;
        if (z < 1)
        {
            z = mv.width;
            x = mv.random(-mv.width, mv.width);
            y = mv.random(-mv.height, mv.height);
            pz = z;
        }
    }

    void render(PApplet pa)
    {
        mv.fill(255);
        mv.noStroke();

        float sx = PApplet.map(x / z, 0, 1, 0, mv.width);
        float sy = PApplet.map(x / z, 0, 1, 0, mv.height);

        float r = PApplet.map(z, 0, mv.width, 16, 0);
        mv.ellipse(sx, sy, 8, r);

        float px = PApplet.map(x / pz, 0, 1, 0, mv.width);
        float py = PApplet.map(x / pz, 0, 1, 0, mv.height);

        pz = z;
        mv.stroke(255);
        mv.line(px, py, sx, sy);

    }

}
