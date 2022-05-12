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
       orbiter = new Planet(50, 0, 0);
       //second arrguments is for levels (of moons)
       orbiter.spawnMoons(5, 0);
       
    }

    public void draw()
    {
        translate(width/2, height/2);
        orbiter.render();
        orbiter.orbit();
    }

}


class Planet extends PApplet
{
    PVector pos;
    float radius;
    float angle;
    float distance;
    Planet[] planets;

    float orbitSpeed;


    Planet (float r, float d, float o)
    {
        radius = r;
        distance = d;
        //so not on same line
        angle = random(TWO_PI);
        orbitSpeed = random(0.1, 0.3);
    }

    void orbit()
    {
        angle = angle + orbitSpeed;
        if(planets != null)
        {
            for (int i = 0; i < planets.length; i++)
            {
                planets[i].orbit();
            }
        }
        
    }

    //argument levels helps determine size and speed
    void spawnMoons(int total, int level)
    {
        planets = new Planet[total];
        for (int i = 0; i < planets.length; i++)
        {
            float r = (float) (radius * 0.5);
            float d = (float) random (75, 300);
            //Code needs to be changed here to match speeds to music
            //probably need to divide it to keep it slow enough
            float o = (float) random(0.02, 0.1);
            planets[i] = new Planet(r, d, o);
            //want planets to spawn own moons
            //infinate loop without levels
            //currently distances and sizes would be all over the place
            if (level < 2)
            {
                planets[i].spawnMoons(5, level + 1);
            }
            
        }
    }

    public void render()
    {  
        //whatever 
        pushMatrix();
        fill(255, 100);
        rotate(angle);
        translate(distance, 0);
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