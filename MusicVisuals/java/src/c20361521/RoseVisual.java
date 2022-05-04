package c20361521;

import processing.core.*;

public class RoseVisual {

    MyVisuals mv;

    public RoseVisual(MyVisuals mv)
    {
        this.mv = mv; 
    }

    float d = 8;
    float n = 5;

    public void render() 
    {
        float k = n / d;
        mv.background(51);
        // mv.translate(mv.width / 2, mv.height / 2);

        mv.beginShape();
        mv.stroke(255);
        mv.noFill();
        mv.strokeWeight(1);

        for (float a = 0; a < PApplet.TWO_PI * d; a += 0.02) 
        {
            float r = 200 * PApplet.cos(k * a);
            float x = r * PApplet.cos(a);
            float y = r * PApplet.sin(a);
            mv.vertex(x, y);
        }
        mv.endShape(PApplet.CLOSE);
    }
}

