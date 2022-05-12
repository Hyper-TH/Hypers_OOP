package c20361521;

import ie.tudublin.*;

// import ddf.minim.AudioBuffer;
// import ddf.minim.AudioInput;
// import ddf.minim.AudioPlayer;
// import ddf.minim.Minim;
// import processing.core.PApplet;

public class RoughRenders extends Visual {

    // Rain1 rn;
    // Minim minim; // Connect to minim
    // AudioInput ai; // How to connect to mic
    // AudioPlayer ap;
    // AudioBuffer ab; // Samples

    float[] lerpedBuffer;
    
    public void settings() {
        //size(1000, 1000, P3D);
        // size(1024, 500);
        size(1000, 1000, P3D);
        // fullScreen(P3D, SPAN); // Try this for full screen multiple monitor support :-) Be careful of exceptions!
        
    }

    float y = 200;
    float lerpedY = y;

    int which = 0;

    public void setup() {

        startMinim();
                
        // Call loadAudio to load an audio file to process 
        loadAudio("Shelter.mp3");   
        // minim = new Minim(this);
        // //ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ap = minim.loadFile("heroplanet.mp3", width);
        // ap.play();
        // ab = ap.mix; // Connect the buffer to the mp3 file
        // //ab = ai.mix; 
        colorMode(HSB);
        lerpedBuffer = new float[width];

        //rn = new Rain1();
        
    }

    public void keyPressed() {
        if (keyCode >= '0' && keyCode <= '6') {
            which = keyCode - '0';
        }
        if (keyCode == ' ') {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
        // }
        // if (keyCode == UP)
        // {
        //     twoCubes = ! twoCubes;
        // }
    }

    float lerpedAverage = 0;
    private float angle = 0;

    // private boolean twoCubes = false;

    // Has to be render, but it wont work for some
    public void draw() {

        background(0);
        stroke(255);
        // float halfHeight = height / 2;
        float average = 0;
        float sum = 0;
        
        
        try
        {
            // Call this if you want to use FFT data
            calculateFFT(); 
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }

        // Call this is you want to use frequency bands
        calculateFrequencyBands(); 
        // Call this is you want to get the average amplitude
        calculateAverageAmplitude();        
        
        // Calculate the average of the buffer
        for (int i = 0; i < getAudioBuffer().size(); i ++)
        {
            sum += abs(getAudioBuffer().get(i));
        }
        average = sum / getAudioBuffer().size();
        
        // Move lerpedAverage 10% closer to average every frame
        lerpedAverage = getSmoothedAmplitude(); // NOT AN ARRAY
        
        // lerpedBuffer = getSmoothedBands();
        
        switch (which)
        {
            // let ab.get(i) be getAudioBuffer().size()
            
            // Gayzels
            case 0:
            {
                // Iterate over all the elements in the audio buffer
                // for (int i = 0; i < ab.size(); i++) {
                for (int i = 0; i < getAudioBuffer().size(); i++) {

                    // float c = map(i, 0, ab.size(), 0, 255);
                    // float c = map(i, 0, getAudioBuffer().size(), 0, 255);
                    // stroke(c, 255, 255);
                    // // lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
                    // lerpedBuffer[i] = lerp(lerpedBuffer[i], getAudioBuffer().size(), 0.1f);
                    float c = map(i, 0, getAudioBuffer().size(), 0, 255);
                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], getAudioBuffer().get(i), 0.1f);

                    // line(i, halfHeight - lerpedBuffer[i] * halfHeight * 4, halfHeight + lerpedBuffer[i] * halfHeight * 4, i);
                    // line(i, halfHeight - lerpedBuffer[i] * halfHeight * 4, halfHeight + lerpedBuffer[i] * halfHeight * 4, i);
                    line(i, height - lerpedBuffer[i] * height * 4, height + lerpedBuffer[i] * height * 4, i);
                }        
                break;
            }   

            //  WORKING RENDERS START HERE
            // 2D squares
            case 4:
            {
                float c = map(average, 0, 1, 0, 255);
                stroke(c, 255, 255);        
                strokeWeight(2);
                noFill();
                rectMode(CENTER);
                float size = 50 + (lerpedAverage * 500);
                rect(width / 2, height / 2, size, size);
                break;
            }

            // GoodTrip
            case 5:
            {
                float r = 1f;
                int numPoints = 3;
                float thetaInc = TWO_PI / (float) numPoints;
                strokeWeight(2);                
                float lastX = width / 2, lastY = height / 2;
                for(int i = 0 ; i < 1000 ; i ++)
                {
                    float c = map(i, 0, 300, 0, 255) % 255.0f;
                    stroke(c, 255, 255, 100);
                    float theta = i * (thetaInc + lerpedAverage * 5);
                    float x = width / 2 + sin(theta) * r;
                    float y = height / 2 - cos(theta) * r;
                    r += 0.5f + lerpedAverage;
                    line(lastX, lastY, x, y);
                    lastX = x;
                    lastY = y;
                }
                // ??
                break;
            }

            // 3D Cube
            case 6:
            {
                lights();
                strokeWeight(2);
                float c = map(lerpedAverage, 0, 1, 0, 255);
                stroke(c, 255, 255);
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

                break;
            }
        }        
    }
}