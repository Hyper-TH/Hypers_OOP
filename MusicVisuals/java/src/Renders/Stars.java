package Renders;

// import java.util.*;
import processing.core.PApplet;
// import java.io.*;

public class Stars extends PApplet{
    float x;
    float y;
    float z;

    float px;
    float py;
    float pz;

    

    public Stars() {
        x = random(-width, width);
        y = random(-height, height);
        z = random(0, width);
        pz = z;
    }

    //float speed = map(mouseX, 0, width, 0, 20);
    public void update(PApplet pa) {
        float speed = 100;
        z = z - speed;
        if (z < 1){
            z = pa.width;
            x = pa.random(-width, width);
            y = pa.random(-height, height);
            pz = z;
        }
    }

    void render(PApplet pa)
	{
        pa.fill(255);
        pa.noStroke();

        float sx = map(x / z, 0, 1, 0, width);
        float sy = map(x / z, 0, 1, 0, height);

        float r = map(z, 0, width, 16, 0);
        pa.ellipse(sx, sy, 8, r);

        float px = map(x / pz, 0, 1, 0, width);
        float py = map(x / pz, 0, 1, 0, height);

        pz = z;
        pa.stroke(255);
        pa.line(px, py, sx, sy);

    }

}