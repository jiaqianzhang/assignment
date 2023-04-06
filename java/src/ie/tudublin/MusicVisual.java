package ie.tudublin;

import java.io.Serial;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import processing.core.PApplet;

public class MusicVisual extends PApplet
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
        //size(512, 500);
        fullScreen();
    }

    public void setup() 
    {
        m = new Minim(this);
        ap = m.loadFile("Believer.mp3", 1024);
        ap.play();
        beat = new BeatDetect();
        beat.detectMode(BeatDetect.FREQ_ENERGY);
    }

    BeatDetect beat;
    Serial port;
    int bgColor = color(255, 255,255);
    int heartColor = color(255,0,0);
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
        drawHeart();
    }

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

    public void drawHeart()
    {
        float heartSize = map(ap.left.level(), 0, 1, 50, 200) * 8;

        // stroke(0); // set the stroke color to black
        // strokeWeight(2); // set the stroke weight to 2 pixels
        // line(511.5f, 300, 511.5f, 900); // draw a line from (50, 50) to (350, 350)

        strokeWeight(4);
        stroke(heartColor);
        fill(heartColor);
        float bezierOffset = heartSize/6; 
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