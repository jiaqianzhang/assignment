package ie.tudublin;

import java.io.Serial;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import processing.core.PApplet;

public class Rain extends PApplet
{
    Minim m;
    AudioPlayer ap;
    AudioBuffer ab;

    int mode = 0;

    public void keyPressed()
    {
        if (key >= '0' && key <= '9')
        {
            mode = key - '0';
        }
        if (keyCode == ' ')
        {
            if (ap.isPlaying()) 
            {
                ap.pause();
            } 
            else 
            {
                ap.rewind();
                ap.play();
            }
        }
    }

    public void settings()
    {
        size(1024, 1000, P3D);
    }

    BeatDetect beat;

    public void setup() 
    {
        m = new Minim(this);
        ap = m.loadFile("Believer.wav", 1024);
        ap.play();
        ab = ap.mix;
        // colorMode(HSB);
        beat = new BeatDetect();
        beat.detectMode(BeatDetect.FREQ_ENERGY);
    }

    
    Serial port;
    int bgColor = color(0, 50, 80);

    public void draw() 
    {
        background(bgColor);
        analyzeMusic();
        drawCircles();
    }

    int circleColor = color(0, 150, 255);
    float beatSize = 10;
    float beatMaxSize = 100;
    float beatDecay = 0.9f;

    public void analyzeMusic()
    {
        beat.detect(ap.mix);
        if (beat.isOnset())
        {
            circleColor = color(random(255), random(255), random(255));
            beatSize = beatMaxSize;
        }
        beatSize *= beatDecay;
    }

    float beatSpeed = 0.5f;
    float threshold = 50;
    private float beatMinSize;

    public void drawCircles()
    {
        float circleSize = map(ap.left.level(), 0, 1, 50, 200);

        noStroke();
        fill(circleColor, 50);
        ellipse(width/2, height/2, beatSize, beatSize);

        for (int i = 0; i < 4; i++)
        {
            float expandingSize = circleSize * (i+1);
            fill(circleColor, 50-(i*5));
            ellipse(width/2, height/2, beatSize + expandingSize, beatSize + expandingSize);
        }

        beatSize -= beatSpeed;
        if (beatSize < beatMinSize)
        {
            beatSize = beatMinSize;
        }
    }

    public void stop()
    {
        ap.close();
        m.stop();
        super.stop();
    }
}