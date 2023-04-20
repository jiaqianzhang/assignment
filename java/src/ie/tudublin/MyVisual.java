package ie.tudublin;

import java.io.Serial;

import ddf.minim.analysis.BeatDetect;

public class MyVisual extends Visual
{    
    JiaHeart jh;
    LauraSun ls;
    ManarBrain mb;
    BeatDetect beat;
    Serial port;

    public BeatDetect getBeat() {
        return beat;
    }

    public void setBeat(BeatDetect beat) {
        this.beat = beat;
    }

    public Serial getPort() {
        return port;
    }

    public void setPort(Serial port) {
        this.port = port;
    }

    public void settings()
    {
        size(1024, 500);
        
        // Use this to make fullscreen
        //fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        //fullScreen(P3D, SPAN); 
    }

    public void setup()
    {
        startMinim();
                
        // Call loadAudio to load an audio file to process 
        loadAudio("Believer.wav");

        // Call this instead to read audio from the microphone
        startListening(); 
        // beat = new BeatDetect();
        // port = new Serial();
        
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
                jh.draw();
                break;

            case 2: 
                ls.draw();
                break;

            case 3:
                mb.draw();
                break;

            default:
            break;
        }
    }
}
