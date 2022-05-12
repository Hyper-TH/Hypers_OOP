package Renders;

import ie.tudublin.*;

public class MyVisuals extends Visual
{    
    ExhibitA eA;
    ExhibitB eB;
    ExhibitC eC;
    Butterfly bb;

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
        bb = new Butterfly();
        // abv = new AudioBandsVisual(this);
      
    }

    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
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
        
        // Calculate the average of the buffer
        for (int i = 0; i < getAudioBuffer().size(); i ++)
        {
            sum += abs(getAudioBuffer().get(i));
        }
        average = sum / getAudioBuffer().size();
        
        // Move lerpedAverage 10% closer to average every frame
        lerpedAverage = getSmoothedAmplitude(); // NOT AN ARRAY

        // eA.render();
        // eB.render();
        eC.draw();
        // bb.draw();     
    }
}
