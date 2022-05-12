package Renders;

import ie.tudublin.*;
import processing.core.PApplet;
//polar

public class Butterfly extends Visual
{

    public Butterfly()
    {

    }

    //for similar noises
    float yoff = 0;

    public void settings()
    {
        size(400, 400, P3D);
    }

    public void setup()
    {
        color(HSB);
        background(51);
        smooth();
    }

    public void render()
    {
        //put in the center
        translate(width/2, height/2);

        //rotate(PI/ 2);
        float flap = (float) 0.01;

        float angle;
        float radius = 100;
        stroke(255);
        fill(255, 50);
        strokeWeight(1);

        //delta is the number of points
        float delta = PI / 100;

        //noise value
        float dx = (float) 0.5;

        //sound varibale should be between 0 and 1
        // float xoff = 0;
        float xoff = getSmoothedAmplitude();

        // beginShape() begins recording vertices for a shape
        for (int i = 0; i < ab.size(); i++)
        {
            float yoff = ab.get(i);
            
            for(angle = -PI/2; angle <= PI/2; angle += delta)
            {
                float no = noise(xoff, yoff);
                //sin(2 * angle) rose maths
                radius = sin(2 * angle) * map(no, 0, 1, 50, 100);
                float xAx = sin(frameCount * flap) * radius * cos(angle);
                float yAx = sin(yoff) * radius * sin(angle);
                
                //perlin noise values --change for audio
                xoff += dx;

                //make a continuous shape 
                //point(xAx, yAx);
                vertex(xAx, yAx);
                
            }
        }
        
        //Right side
        xoff = 0;
        for (int i = 0; i < ab.size(); i++)
        {
            float yoff = ab.get(i);
            
            for(angle = PI/2; angle <= 3*PI/2; angle += delta)
            {
                float no = noise(xoff, yoff);
                radius = sin(2 * angle) * map(no, 0, 1, 50, 100);
                float xAx = sin(frameCount * flap) * radius * cos(angle);
                float yAx = sin(yoff) * radius * sin(angle);
                
                //perlin noise values --change for audio
                xoff -= dx;
                
                //make a continuous shape 
                vertex(xAx, yAx);
            }
        // When endshape() is called, all of image data defined since the previous call to beginShape() is written into the image buffer.
        }        //angle > 3*PI to go in opposite direction
        
        yoff += 0.01;
    }
}