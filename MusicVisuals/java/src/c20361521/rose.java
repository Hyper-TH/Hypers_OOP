package c20361521;

import processing.core.PApplet;

//https://en.wikipedia.org/wiki/Rose_(mathematics)

public class rose extends PApplet
{
    float d = 1;
    float n = 2;
    int flowers = 6;
    float spin = 0;

    public void settings() 
    {
        size(400, 400);
    }

    public void setup()
    {
        color(HSB);
        background(0);
        smooth();
        // cycles();
    }

    public void draw() 
    {
        
        //background();
        translate(width / 2, height / 2);

        beginShape();
        float k = n / d;
        stroke(255);
        noFill();
        strokeWeight(1);

        float raid = radians(spin);
        rotate(raid);
        for (float a = 0; a < TWO_PI * d; a += 0.02) 
        {
            //colour
            float colour = PApplet.map(a, 0, PApplet.TWO_PI * d, 0, 255);
            stroke(colour, 255, 255);

            //first
            float r = 200 * cos(k * a);
            float x = r * cos(a);
            float y = r * sin(a);
            vertex(x, y);
            //how do you lerp?
            // point(20 *(x * sv.lerpedAverage), 20 * (y * PApplet.lerpedAverage));


            //second
            r = 200 * cos((k-1) * a);
            x = r * cos(a);
            y = r * sin(a);
            vertex(x, y);
            //basiclaly, not a big deal, this doesn't work
            // point(20 *(x * sv.lerpedAverage), 20 * (y * sv.lerpedAverage));
        }
        d += 1;
        n += 1;
        spin +=0.1;
        System.out.println("Bruh");
        endShape(CLOSE);
    }
}
