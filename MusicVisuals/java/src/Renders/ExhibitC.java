package Renders;

import ie.tudublin.*;
import processing.core.*;

public class ExhibitC extends Visual
{
    MyVisuals mv;

    public ExhibitC(MyVisuals mv)
    {
        this.mv = mv;
    }

    //for similar noises
    float yoff = 0;

    public void render()
    {
        // /* START BUTTERFLY */
        // // put in the center
        // mv.translate(mv.width/2, mv.height/2);
        
        // //rotate(PI/ 2);
        // float flap = (float) 0.01;
        
        // float angle;
        // float radius = 100;
        // mv.stroke(255);
        // mv.fill(255, 50);
        // mv.strokeWeight(1);
        
        // //delta is the number of points
        // float delta = PI / 100;
        
        // // noise value
        // // float dx = (float) 0.5;
        
        // //sound varibale should be between 0 and 1
        // // float xoff = 0;
        // float xoff = 0;
        // float yoff = 0;
        
        // // beginShape() begins recording vertices for a shape
        // for (int i = 0; i < ab.size(); i++)
        // {
        //     // mv.beginShape();
            
        //     float dx = ab.get(i);
        //     for(angle = -PI/2; angle <= PI/2; angle += delta)
        //     {
        //         float no = noise(xoff, yoff);
        //         //sin(2 * angle) rose maths
        //         radius = sin(2 * angle) * map(no, 0, 1, 50, 100);
        //         float xAx = sin(frameCount * flap) * radius * cos(angle);
        //         float yAx = sin(yoff) * radius * sin(angle);
                
        //         //perlin noise values --change for audio
        //         xoff += dx;
                
        //         //make a continuous shape 
        //         //point(xAx, yAx);
        //         mv.vertex(xAx, yAx);
                
        //     }
        //     // mv.endShape();
        // }
        
        // for (int i = 0; i < ab.size(); i++)
        // {
        //     // mv.beginShape();
        //     float dx = ab.get(i);
        //     //Right side
        //     xoff = 0;
        //     //angle > 3*PI to go in opposite direction
        //     for(angle = PI/2; angle <= 3*PI/2; angle += delta)
        //     {
        //         float no = noise(xoff, yoff);
        //         radius = sin(2 * angle) * map(no, 0, 1, 50, 100);
        //         float xAx = sin(frameCount * flap) * radius * cos(angle);
        //         float yAx = sin(yoff) * radius * sin(angle);
                
        //         //perlin noise values --change for audio
        //         xoff -= dx;
                
        //         //make a continuous shape 
        //         mv.vertex(xAx, yAx);
        //     }
        //     // mv.endShape();
        //     yoff += 0.01;
        // }
        // // When endshape() is called, all of image data defined since the previous call to beginShape() is written into the image buffer.
        /* END BUTTERFLY */

        /* START CIRCLE AND WAVEFORMS */
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

        // Waves
        for (int i = 0; i < ab.size(); i++) 
        {
            float c = PApplet.map(i, 0, ab.size(), 0, 255);
            mv.stroke(c, 255, 255);
            mv.lerpedBuffer[i] = PApplet.lerp(mv.lerpedBuffer[i], ab.get(i), 0.1f);

            // Left
            mv.line(0, i * 2f, mv.lerpedBuffer[i] * mv.height, i * 2f);
            
            // Right
            mv.line(mv.width, i * 2f, mv.width - (mv.lerpedBuffer[i] * mv.height), i  * 2);
            
            // Top
            mv.line(i * 3, 0, i * 3, mv.lerpedBuffer[i] * mv.height);
            
            // Bottom
            mv.line(i * 3, mv.height, i * 3, mv.height - (mv.lerpedBuffer[i] * (mv.height / 2) * 4));
        }    

        /* START CIRCLE */
        float d = PApplet.map(average, 0, 1, 0, 255);
        mv.stroke(d, 255, 255);        
        mv.strokeWeight(5);
        mv.noFill();
        
        // See the difference lerping makes? It smooths out the jitteryness of average, so the visual looks smoother
        //ellipse(width / 4, 100, 50 + average * 500, 50 + average * 500);
        mv.ellipse(mv.width / 2, mv.height / 2, 50 + (lerpedAverage * 5000), 50 + (lerpedAverage * 5000));  
        /* END CIRCLE */
        /* END CIRCLE AND WAVEFORMS */
    }
}

