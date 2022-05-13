package Renders;

import ie.tudublin.*;
import processing.core.PApplet;

public class MyVisuals extends Visual
{    
    ExhibitA eA;
    ExhibitB eB;
    ExhibitC eC;
    // Rain r;
    ExhibitD eD;
    ExhibitE eE;

    int visual = 0;

    float[] lerpedBuffer;

    public void settings()
    {
        size(1000, 1000, P3D);

        // Use this to make fullscreen
        //fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        //fullScreen(P3D, SPAN); 
    }

    public void setup()
    {
        startMinim();
                
        // Call loadAudio to load an audio file to process 
        loadAudio("Shelter.mp3");   

        colorMode(HSB);
        lerpedBuffer = new float[width];

        // Call this instead to read audio from the microphone
        // startListening(); 
        
        eA = new ExhibitA(this);
        eB = new ExhibitB(this);
        eC = new ExhibitC(this);
        //r = new Rain(this);
        eD = new ExhibitD(this);
        eE = new ExhibitE(this);
        // abv = new AudioBandsVisual(this);
      
    }

    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }

        if (keyCode >= '0' && keyCode <= '5') 
        {
            visual = keyCode - '0';
        }

    }

    float lerpedAverage = 0;
    private float angle = 0;

    public void draw()
    {
        background(0);
        stroke(255);

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

        
        // Move lerpedAverage 10% closer to average every frame
        lerpedAverage = getSmoothedAmplitude(); // NOT AN ARRAY
        
        switch (visual)
        {
            case 0:
            {
                eA.render();
                break;
            }

            case 1:
            {
                eB.render();
                break;
            }

            case 2:
            {
                eC.render();
                // r.render();
                //r.fall();
                break;
            }

            case 3:
            {
                // eD.render();
                break;
            }

            case 4:
            {
                // eE.render();
                break;
            }
             
        }
    }
}
