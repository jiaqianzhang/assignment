package ie.tudublin;

import java.io.Serial;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import processing.core.PApplet;

public class Heart extends PApplet
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
        size(1024, 1000);
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
    int bgColor = color(255, 255, 255);

    public void draw() 
    {
        background(bgColor);
        analyzeMusic();
        drawHeart();
    }

    int heartColor = color(255,0,0);
    float beatSize = 10;
    float beatMaxSize = 100;
    float beatDecay = 0.9f;

    public void analyzeMusic()
    {
        beat.detect(ap.mix);
        if (beat.isOnset())
        {
            heartColor = color(random(255), random(255), random(255));
            beatSize = beatMaxSize;
        }
        beatSize *= beatDecay;
    }

    float beatSpeed = 0.5f;
    float threshold = 50;
    private float beatMinSize;

    public void drawHeart()
    {
        float heartSize = map(ap.left.level(), 0, 1, 50, 200) * 8;


        strokeWeight(4);
        stroke(255, 0, 0);
        fill(255, 0, 0);
        float bezierOffset = heartSize/6; // adjust this value to increase or decrease the spacing between the bezier curves
        beginShape();
        vertex(width/2, height/2 + heartSize/2);
        bezierVertex(width/4 - heartSize/2, height/2 - bezierOffset - heartSize/80, width/2 - heartSize/80, height/2 - heartSize/2 + bezierOffset, width/2, height/2 - heartSize/80);
        bezierVertex(width/2 + heartSize/80, height/2 - heartSize/2 + bezierOffset, width/1.35f + heartSize/2, height/2 - bezierOffset - heartSize/80, width/2, height/2 + heartSize/2);
        endShape(CLOSE);
        noStroke();
        fill(bgColor);
        ellipse(width/2 - heartSize/4, height/2 - heartSize/4, beatSize, beatSize);
        ellipse(width/2 + heartSize/4, height/2 - heartSize/4, beatSize, beatSize);
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