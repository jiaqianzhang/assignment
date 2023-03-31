package ie.tudublin;

import java.io.Serial;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import processing.core.PApplet;

public class Puddle extends PApplet
{
    Minim m;
    AudioPlayer ap;
    AudioBuffer ab;

    int mode = 0;

    public void keyPressed() {
        if (key >= '0' && key <= '9')
        {
            mode = key - '0';
        }
        if (keyCode == ' ')
        { // Use the integer code for the spacebar key
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

    public void setup() 
    {
        m = new Minim(this);
        ap = m.loadFile("Believer.mp3", 1024);
        ap.play();
        // ab = ap.mix;
        // colorMode(HSB);
        beat = new BeatDetect();
        beat.detectMode(BeatDetect.FREQ_ENERGY);
    }

    BeatDetect beat;
    Serial port;
    int bgColor = color(0, 50, 80);
    int circleColor = color(0, 150, 255);
    float beatSize = 10;
    float beatMaxSize = 100;
    float beatSpeed = 0.5f;
    float beatDecay = 0.9f;
    float threshold = 50;
    private float beatMinSize;

    public void draw() 
    {
        background(bgColor);
        analyzeMusic();
        drawCircles();
    }

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

    public void drawCircles()
    {
        float circleSize = map(ap.left.level(), 0, 1, 50, 200);

        noStroke();
        fill(circleColor, 50);
        ellipse(width/2, height/2, beatSize, beatSize);
        
        for (int i = 0; i < 4; i++) {
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
 