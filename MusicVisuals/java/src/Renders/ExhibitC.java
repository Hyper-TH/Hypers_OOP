package Renders;

import ie.tudublin.*;
public class ExhibitC extends Visual
{
    MyVisuals mv;

    public ExhibitC(MyVisuals mv)
    {
        this.mv = mv;
    }

    //for similar noises
    float yoff = 0;

    public void draw()
    {
        /* START BUTTERFLY */
        //put in the center
        mv.translate(mv.width/2, mv.height/2);
        
        //rotate(PI/ 2);
        float flap = (float) 0.01;
        
        float angle;
        float radius = 100;
        mv.stroke(255);
        mv.fill(255, 50);
        mv.strokeWeight(1);
        
        //delta is the number of points
        float delta = PI / 100;
        
        // noise value
        // float dx = (float) 0.5;
        
        //sound varibale should be between 0 and 1
        // float xoff = 0;
        float xoff = 0;
        float yoff = 0;
        
        // beginShape() begins recording vertices for a shape
        for (int i = 0; i < ab.size(); i++)
        {
            mv.beginShape();
            
            float dx = ab.get(i);
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
                mv.vertex(xAx, yAx);
                
            }
            mv.endShape();
        }
        
        for (int i = 0; i < ab.size(); i++)
        {
            mv.beginShape();
            float dx = ab.get(i);
            //Right side
            xoff = 0;
            //angle > 3*PI to go in opposite direction
            for(angle = PI/2; angle <= 3*PI/2; angle += delta)
            {
                float no = noise(xoff, yoff);
                radius = sin(2 * angle) * map(no, 0, 1, 50, 100);
                float xAx = sin(frameCount * flap) * radius * cos(angle);
                float yAx = sin(yoff) * radius * sin(angle);
                
                //perlin noise values --change for audio
                xoff -= dx;
                
                //make a continuous shape 
                mv.vertex(xAx, yAx);
            }
            mv.endShape();
            yoff += 0.01;
        }
        // When endshape() is called, all of image data defined since the previous call to beginShape() is written into the image buffer.
        /* END BUTTERFLY */
    }
    
    // Rose?
}
