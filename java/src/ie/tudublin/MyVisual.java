package ie.tudublin;

public class MyVisual extends Visual
{    
    JiaHeart jh;
    LauraSun ls;
    ManarBrain mb;

    public void settings()
    {
        //size(1024, 500);
        // Use this to make fullscreen
        //fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        fullScreen(P3D, SPAN);
    }

    public void setup()
    {
        startMinim();

      // Call loadAudio to load an audio file to process 
        loadAudio("Believer.mp3");

        // Call this instead to read audio from the microphone
        startListening(); 

        JiaHeart jh = new JiaHeart();
        LauraSun ls = new LauraSun();
        mb = new ManarBrain();
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
            jh.render();
            break;

            case 2: 
            ls.render();
            break;

            case 3:
            mb.render();
            break;

            default:
            break;
        }
    }
}