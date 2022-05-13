// Needs colour
// Needs orbital speed changed to match music

package c20361521;

import java.util.Vector;

import processing.core.PApplet;
import processing.core.PVector;

public class Solar extends PApplet{

    Planet orbiter;

    public void settings()
    {
        //rendering using processing 3D rendering
        size(600, 600, P3D);
        //Idk how to use camera :(
        // camera((float) 70.0, (float) 35.0, (float) 120.0, (float) 50.0, (float) 50.0, (float) 0.0, 
        // (float) 0.0, (float) 1.0, (float) 0.0);
    }

    public void setup() 
    {
       background(0);
       orbiter = new Planet(50, 0, 0);
       //second arrguments is for levels (of moons)
       orbiter.spawnMoons(1, 1);
       
    }

    //we'll move these to myVisuals at some point :tails
    public void draw()
    {
        lights();
        //translate(width/2, height/2);     //moving the planets without camera
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

    Object v = processing.core.PVector.random3D();

    Planet (float r, float d, float o)
    {
        //in java we gotta initalise new vectors too :(
        Vector <Float> v = new Vector<Float>();
        
        //random 3d vector pointing in space of length 1
        // Object v = processing.core.PVector.random3D();
        radius = r;
        distance = d;
        v.mult(distance);   //scale  vector by distance
        //so not on same line
        angle = random(TWO_PI);
        //had to type cast these, was reading it as double double, what a dummy
        orbitSpeed =  random((float)0.1,(float) 0.3);
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
            //float r = (float) (radius * 0.5);
            float r = (float) radius/(level * 2);


            //float d = (float) random (75, 300); //old line
            float d = random(radius + r);

            //Code needs to be changed here to match speeds to music
            //probably need to divide it to keep it slow enough
            //minus one value to go one direction, plus another value to go other way
            // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            float o =  random((float)-0.1, (float) 0.1);

            planets[i] = new Planet(r, d, o);

            //want planets to spawn own moons
            //infinate loop without levels
            if (level < 2)
            {
                //number of moons
                //might need to change to int
                float num = 1; //(random ((float) 0, (float) 4));
                planets[i].spawnMoons(5, level + 1);
            }
            
        }
    }

    public void render()
    {  
        //whatever 
        pushMatrix();
        noStroke(); //removes polygons
        fill(255);  //white
        fill(255, 100);

        //picking a vector that points straight out
        PVector v2 = new PVector(1, 0, 1);
        //p is cross product of v, the vector that's actually coming out from main orbit object
        PVector p = v.cross(v2);
        //rotate from the x, y and z by angle
        rotate(angle, p.x, p.y, p.z);

        //translate(distance, 0); //old line for 2d
        translate(v.x, v.y, v.z);
        sphere(radius); //for 3d
        //ellipse(0, 0, radius*2, radius*2);    //for 2d
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