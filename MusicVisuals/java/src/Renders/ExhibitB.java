package Renders;

import ie.tudublin.*;
 
public class ExhibitB extends Visual 
{
    MyVisuals mv;

    public ExhibitB(MyVisuals mv)
    {
        this.mv = mv;
    }

    
    // Scattered 3d squares
    // Something else running in the background
    public void render() 
    {
        float average = 0;
        float sum = 0;
        float angle = 0;
        float lerpedAverage = 0;

        //Average of the buffer
        for(int i =0; i < ab.size(); i++)
        {
            sum += abs(ab.get(i));
        }

        average = sum / ab.size();

        //Calculate the average amplitutde
        lerpedAverage = lerp(lerpedAverage,average, 0.1f);


        /* START 3D CUBE */
        mv.lights();
        float c = map(lerpedAverage, 0, 1, 0, 255);
        mv.stroke(c, 255, 255);
        noFill();
        //fill(100, 255, 255);
        angle += 0.01f;
        float s = 100 + (100 * lerpedAverage * 10);
        
        // if (! twoCubes)
        // {
        //     translate(width / 2, height / 2, 0);
        //     rotateY(angle);
        //     rotateX(angle);
        //     box(s);
        // }
        // else
        // {
        //     pushMatrix();
        //     translate(width / 4, height / 2, 0);
        //     rotateY(angle);
        //     rotateX(angle);
        //     box(s);
        //     popMatrix();

        //     pushMatrix();
        //     translate(width * 0.75f, height / 2, 0);
        //     rotateY(angle);
        //     rotateX(angle);
        //     box(s);
        //     popMatrix();
        // }
        pushMatrix();
        translate(width / 4, height / 2, 0);
        rotateY(angle);
        rotateX(angle);
        box(s);
        popMatrix();

        pushMatrix();
        translate(width * 0.75f, height / 2, 0);
        rotateY(angle);
        rotateX(angle);
        box(s);
        popMatrix();
        /* END 3D CUBE */

        // ANOTHER RENDER HERE
    }

} // end Main class
