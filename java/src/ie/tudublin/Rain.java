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
    float[] dropX = new float[100];
    float[] dropY = new float[100];

    float puddleY = height - 50; // the puddle is at the bottom of the screen



    public void keyPressed()
    {
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
        ab = ap.mix;

        beat = new BeatDetect();
        beat.detectMode(BeatDetect.FREQ_ENERGY);

        for (int i = 0; i < dropX.length; i++) 
        {
            dropX[i] = random(width);
            dropY[i] = random(height);
          }
          
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

        for (int i = 0; i < dropX.length; i++) {
            fill(255);
            ellipse(dropX[i], dropY[i], 5, 10);
            if (dropY[i] > puddleY) { // if the rain drop falls into the puddle
              fill(0, 0, 255); // change the fill color to blue for the puddle
              ellipse(dropX[i], puddleY, 10, 10); // draw a blue circle at the position of the puddle
            }
            dropY[i] += 10; // move the rain drop downwards
            if (dropY[i] > height) { // if the rain drop is out of the screen, reset its position
              dropX[i] = random(width);
              dropY[i] = random(-100, 0);
            }
          }
          
          
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
 