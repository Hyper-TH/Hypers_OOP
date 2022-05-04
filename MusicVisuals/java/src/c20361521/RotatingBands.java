package c20361521;

import processing.core.*;

public class RotatingBands  
{
    MyVisuals mv;

    float radius = 200;

    float smoothedBoxSize = 0;

    float rot = 0;

    public RotatingBands(MyVisuals mv)
    {
        this.mv = mv;
    }

    public void render()
    {
        // calculateAverageAmplitude();
        // try
        // {
        //     calculateFFT();
        // }
        // catch(VisualException e)
        // {
        //     e.printStackTrace();
        // }
        // calculateFrequencyBands();
        // background(0);
        // noFill();
        // stroke(255);
        // lights();
        // stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        // camera(0, -500, 500, 0, 0, 0, 0, 1, 0);
        // //translate(0, 0, -250);

        // rot += getAmplitude() / 8.0f;

        // rotateY(rot);
        float[] bands = mv.getSmoothedBands();

        for(int i = 0 ; i < bands.length ; i ++)
        {
            float theta = PApplet.map(i, 0, bands.length, 0, PApplet.TWO_PI);

            mv.stroke(PApplet.map(i, 0, bands.length, 0, 255), 255, 255);
            float x = PApplet.sin(theta) * radius;
            float z = PApplet.cos(theta) * radius;
            float h = bands[i];
            mv.pushMatrix();
            mv.translate(x, - h / 2 , z);
            mv.rotateY(theta);
            mv.box(50, h, 50);
            mv.popMatrix();
        }

    }
}
