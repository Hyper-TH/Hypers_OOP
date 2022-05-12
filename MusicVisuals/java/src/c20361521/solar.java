package c20361521;

import processing.core.PApplet;
import processing.core.PVector;

public class solar extends PApplet{

    Planet orbiter;

    public void settings()
    {
        size(600, 600);
    }

    public void setup() 
    {
       background(0);
       orbiter = new Planet(50, 0);
       orbiter.spawnMoons(5);
       
    }

    public void draw()
    {
        translate(width/2, height/2);
        orbiter.render();
    }

}


class Planet extends PApplet
{
    PVector pos;
    float radius;
    float angle;
    float distance;
    Planet[] planets;


    Planet (float r, float d)
    {
        radius = r;
        distance = d;
        //so not on same line
        angle = random(TWO_PI);
    }

    void spawnMoons(int total)
    {
        planets = new Planet[total];
        for (int i = 0; i < planets.length; i++)
        {
            float r = (float) (radius * 0.5);
            float d = random(75, 300);
            planets[i] = new Planet(r, d);
        }
    }

    public void render()
    {  
        //whatever 
        pushMatrix();
        fill(255, 100);
        translate(distance, 0);
        rotate(angle);
        ellipse(0, 0, radius*2, radius*2);
        if(planets != null)
        {
            for(int i = 0; i < planets.length; i++)
            {
                planets[i].render();
            }
        }
        popMatrix();
    }

}