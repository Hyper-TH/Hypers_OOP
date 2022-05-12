package c20361521;

import Renders.MyVisuals;
// import ie.tudublin.Visual;
import processing.core.*;


public class CubeVisual 
{
    MyVisuals mv;
    boolean twocubes = false;

    public CubeVisual(MyVisuals mv)
    {
        this.mv = mv;
        // cy = this.mv.height / 2;
    }

    // public void settings()
    // {
    //     mv.size(800, 800, PApplet.P3D);
    //     PApplet.println("CWD: " + System.getProperty("user.dir"));
    //     //fullScreen(P3D, SPAN);
    // }

    public void keyPressed()
    {
        if (mv.key == ' ')
        {
            mv.getAudioPlayer().cue(0);
            mv.getAudioPlayer().play();
            
        }
        if (mv.key == '7')
        {
            twocubes = ! twocubes;

        }
    }

    // public void setup()
    // {
    //     mv.colorMode(PApplet.HSB);
    //     mv.noCursor();
        
    //     mv.setFrameSize(256);

    //     mv.startMinim();
    //     mv.loadAudio("Shelter.mp3");
    //     //getAp().play();
    //     //startListening(); 
        
    // }

    float smoothedBoxSize = 0;

    public void render()
    {
        mv.calculateAverageAmplitude();
        mv.background(0);
        mv.noFill();
        mv.lights();
        mv.stroke(PApplet.map(mv.getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        mv.camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
        mv.translate(0, 0, -250);
               
        float boxSize = 50 + (mv.getAmplitude() * 300);//map(average, 0, 1, 100, 400); 
        smoothedBoxSize = PApplet.lerp(smoothedBoxSize, boxSize, 0.2f);        
        if (twocubes)
        {
            mv.pushMatrix();
            mv.translate(-100, 0, 0);
            mv.rotateY(angle);
            mv.rotateX(angle);
            mv.box(smoothedBoxSize);
            //strokeWeight(1);
            //sphere(smoothedBoxSize);
            mv.popMatrix();
            mv.pushMatrix();
            mv.translate(100, 0, 0);
            mv.rotateY(angle);
            mv.rotateX(angle);
            mv.strokeWeight(5); 
            mv.box(smoothedBoxSize);
            mv.popMatrix();
        }
        else
        {
            mv.rotateY(angle);
            mv.rotateX(angle);
            //strokeWeight(1);
            //sphere(smoothedBoxSize/ 2);            
            mv.strokeWeight(5);
            
            mv.box(smoothedBoxSize);
        }
        angle += 0.01f;
    }
    float angle = 0;
}

// public class CubeVisual extends Visual
// {
    
//     boolean twocubes = false;

//     public void settings()
//     {
//         size(800, 800, P3D);
//         println("CWD: " + System.getProperty("user.dir"));
//         //fullScreen(P3D, SPAN);
//     }

//     public void keyPressed()
//     {
//         if (key == ' ')
//         {
//             getAudioPlayer().cue(0);
//             getAudioPlayer().play();
            
//         }
//         if (key == '1')
//         {
//             twocubes = ! twocubes;

//         }
//     }

//     public void setup()
//     {
//         colorMode(HSB);
//         noCursor();
        
//         setFrameSize(256);

//         startMinim();
//         loadAudio("Shelter.mp3");
//         //getAp().play();
//         //startListening(); 
        
//     }

//     float smoothedBoxSize = 0;

//     public void draw()
//     {
//         calculateAverageAmplitude();
//         background(0);
//         noFill();
//         lights();
//         stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
//         camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
//         translate(0, 0, -250);
               
//         float boxSize = 50 + (getAmplitude() * 300);//map(average, 0, 1, 100, 400); 
//         smoothedBoxSize = lerp(smoothedBoxSize, boxSize, 0.2f);        
//         if (twocubes)
//         {
//             pushMatrix();
//             translate(-100, 0, 0);
//             rotateY(angle);
//             rotateX(angle);
//             box(smoothedBoxSize);
//             //strokeWeight(1);
//             //sphere(smoothedBoxSize);
//             popMatrix();
//             pushMatrix();
//             translate(100, 0, 0);
//             rotateY(angle);
//             rotateX(angle);
//             strokeWeight(5); 
//             box(smoothedBoxSize);
//             popMatrix();
//         }
//         else
//         {
//             rotateY(angle);
//             rotateX(angle);
//             //strokeWeight(1);
//             //sphere(smoothedBoxSize/ 2);            
//             strokeWeight(5);
            
//             box(smoothedBoxSize);
//         }
//         angle += 0.01f;
//     }
//     float angle = 0;
// }