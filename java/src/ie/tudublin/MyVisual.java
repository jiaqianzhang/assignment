package ie.tudublin;

import java.io.Serial;

import ddf.minim.analysis.BeatDetect;

// import processing.core.PApplet;

public class MyVisual extends Visual
{  
    LeftHeart lh;
    RightHeart rh;  
    JiaHeart jh;
    LauraSun ls;
    ManarBrain mb;
    Serial port;
    BeatDetect beat;

    public void setPort(Serial port) {
        this.port = port;
    }
 

    public void setBeat(BeatDetect beat) {
        this.beat = beat;
    }


    public Serial getPort() {
        return port;
    }


    public BeatDetect getBeat() {
        return beat;
    }

    public void settings()
    {
        // Use this to make fullscreen 
        fullScreen(P3D);
    }

    public void setup()
    {
        startMinim();
                
        // Call loadAudio to load an audio file to process 
        loadAudio("Believer.mp3");

        // Call this instead to read audio from the microphone
        //startListening(); 

        beat = new BeatDetect(); // Create a new object of type BeatDetect

        lh = new LeftHeart(this, beat, port);
        rh = new RightHeart(this, beat, port);
        jh = new JiaHeart(this, beat, port);
        ls = new LauraSun(this, beat);
        mb = new ManarBrain(this);
    }

    int visual;

    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }

        if (key == '1')
        {
            visual = 1;
        }
        if (key == '2')
        {
            visual = 2;
        }
        if (key == '3')
        {
            visual = 3;
        }
        if (key == '4')
        {
            visual = 4;
        }
        if (key == '5')
        {
            visual = 5;
        }
        // if (key == '6')
        // {
        //     visual = 6;
        // }
    }

    public void draw()
    {
        background(0);
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

        switch(visual)
        {
            case 1:
                lh.render();
                break;
            
            case 2:
                rh.render();
                break;

            case 3:
                jh.render();
                break;

            case 4: 
                ls.render();
                break; 

            case 5:
                mb.render();
                break;
            
            // case 6:
            //     ck.render();
            //     break;

            default:
                break;
        }
    }
}
