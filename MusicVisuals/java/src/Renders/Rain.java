package Renders;

import processing.core.PApplet;

public class Rain extends PApplet
{

	final int SLENGTH = 100;
	final int DLENGTH = 100;
    //ArrayList<rain> drops = new ArrayList<rain>();
    Drops[] drops = new Drops[DLENGTH];
	//Star[] stars = new Star[SLENGTH];

    public void settings() 
    {
        size(640, 360);
    }

    public void setup()
    {
        //color(HSB);
        background(230, 230, 250);
        smooth();
        for(int i = 0; i < DLENGTH; i++)
        {
            drops[i] = new Drops();
			System.out.println("INITIALIZED");
        }
		//for(int i = 0; i < SLENGTH; i++)
		//{
		//	stars[i] = new Star();
		//}
    }

    public void draw()
    {
		background(0);
        for(int i = 0; i < DLENGTH; i++)
        {
            drops[i].render(this);
            drops[i].fall(this);
        }
		//for(int i = 0; i < SLENGTH; i++)
		//{
		//	stars[i].render(this);
		//	stars[i].update(this);
		//}
    }

}


class Drops extends PApplet
{
    
    //variables
    float x = random(640); //drop starts in middle
    float y = random(-500, -50); //drop starts at top of screen
    float z = random(0, 20);
    //drop longer if closer, and shorter if further away
    float length = map(z, 0, 20, 10, 20);
    //closer drops are faster
    float yspeed = map(z, 0, 20, 4, 10);

    //where the rain will fall
    public void fall(PApplet pa) 
	{
        //fall
        y += yspeed;
        //gravity
        float plummet = map(z, 0, 20, 0, (float) 0.2);
        yspeed = yspeed + plummet;

        if (y > 360)
        {
            y = pa.random(-200, -100);
            yspeed = map(z, 0, 20, 4, 10);
        }

    }

    //how it will show on screen
    public void render(PApplet pa)
	{
		pa.stroke(255);
        float z = pa.random(1, 20);
        //1px when far, 3px when close
        float thicc = map(z, 0, 20, 1, 3);
        System.out.println(thicc);
        //strokeWeight(3);
        pa.line(x, y, x, y+10);

    }
}
