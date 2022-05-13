package Renders;

import ie.tudublin.*;
import processing.core.PApplet;
 
public class ExhibitB extends Visual
{
    MyVisuals mv;

    public ExhibitB(MyVisuals mv)
    {
        this.mv = mv;
    }

    public void render()
    {
        /* START 3D CUBES */
        float average = 0;
        float sum = 0;
        float lerpedAverage = 0;

        //Average of the buffer
        for(int i =0; i < ab.size(); i++)
        {
            sum += abs(ab.get(i));
        }

        average = sum / ab.size();

        //Calculate the average amplitutde
        lerpedAverage = lerp(lerpedAverage,average, 0.1f);

        //Shading
        mv.lights();
        mv.strokeWeight(2);

        float c = PApplet.map(lerpedAverage * 10, 0, 1, 0, 255);
        mv.stroke(c, 255, 255);
        mv.noFill();

        //Set cubiod rotation to the song
        mv.angle += 0.01f + mv.getSmoothedAmplitude() / 5;

        float f = 100 + (100 * lerpedAverage * 10);

        //Side Boxes
        //Left
        mv.pushMatrix();
            mv.translate(mv.width / 4 - 120, mv.height / 2);

            mv.rotateY(mv.angle);
            mv.rotateX(mv.angle);
            mv.box(f);
        mv.popMatrix();

        //Right
        mv.pushMatrix();
            mv.translate(mv.width * 0.75f + 120, mv.height / 2);

            mv.rotateY(mv.angle);
            mv.rotateX(mv.angle);
            mv.box(f);   
        mv.popMatrix();
        /* END 3D CUBES */
    }    
}   
 // end Main class