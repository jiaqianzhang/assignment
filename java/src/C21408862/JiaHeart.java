// java program that draws a full heart, left heart and right heart that beats by detecting beats from a music file 
package ie.tudublin;

import java.io.Serial;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import processing.core.PApplet;

public class JiaHeart extends PApplet
{
    Minim m;
    AudioPlayer ap;
    AudioBuffer ab;

    // keyboard 
    public void keyPressed() 
    {
		if (keyCode == ' ') // press spacebar
        {
            if (ap.isPlaying()) // if audio is playing
            {
                ap.pause(); // it stops it
            } 
            else // if its not
            {
                ap.rewind(); // rewind audio
                ap.play(); // and play
            }
        }
	}

    public void settings()
    {   
        // size of screen
        size(1024, 1000);
    }

    BeatDetect beat; // variable for detecting beat

    public void setup() 
    {
        m = new Minim(this); // initialise minim library
        ap = m.loadFile("Believer.wav", 1024); // load music file
        ap.play(); // play audio file
        beat = new BeatDetect(); // initialise BeatDetect class
        beat.detectMode(BeatDetect.FREQ_ENERGY); // set mode for detecting beats to freq. energy
    }

    Serial port; // declare a new Serial object

    public void draw() 
    {
        background(0); // set background to black
        analyzeMusic(); // call method to detect beats in the audio
        if (key == '1') // if press 1 on keyboard
        {
            leftHeart(); // calls leftHeart
        }
        if (key == '2') // if press 2 on keyboard
        {
            rightHeart(); // calls rightHeart
        }
        if (key == '3') // if press 3 on keyboard
        {
            fullHeart(); // display fullHeart
        }
    }

    int heartColor = color(255,0,0); 
    float beatSize = 10;
    float beatMaxSize = 100;
    float beatDecay = 0.9f;

    // analyse music to detect beats
    public void analyzeMusic()
    {
        beat.detect(ap.mix); // detect beats and updates
        if (beat.isOnset()) // if statement to check if a beat has been detected
        {
            heartColor = color(random(255), random(255), random(255)); // set heart colour to random of 255
            beatSize = beatMaxSize; // sets the size of the heart beat to the maximum size
        }
        beatSize *= beatDecay; // update size of heart by multiplying beatDecay to reduce size
    }

    float beatSpeed = 0.5f;
    float threshold = 50;
    private float beatMinSize;

    // method that draws full heart
    public void fullHeart()
    {
        float heartSize = map(ap.left.level(), 0, 1, 50, 200) * 8; // calculate the size of the heart based on left level channel of audio, from 0-1 to range of 50-200, multiply by 8

        strokeWeight(4);
        stroke(255, 0, 0);
        fill(255, 0, 0);

        float bezierOffset = heartSize/6; // the spacing between the bezier curves

        beginShape(); // begin drawing shape
        vertex(width/2, height/2 + heartSize/2);
        bezierVertex(width/4 - heartSize/2, height/2 - bezierOffset - heartSize/80, width/2 - heartSize/80, height/2 - heartSize/2 + bezierOffset, width/2, height/2 - heartSize/80); // left heart
        bezierVertex(width/2 + heartSize/80, height/2 - heartSize/2 + bezierOffset, width/1.35f + heartSize/2, height/2 - bezierOffset - heartSize/80, width/2, height/2 + heartSize/2); // right heart
        endShape(CLOSE); // end of drawing shape
        noStroke();
        fill(255, 255, 255);
        ellipse(width/2 - heartSize/4, height/2 - heartSize/4, beatSize, beatSize); // left oval shape  of heart
        ellipse(width/2 + heartSize/4, height/2 - heartSize/4, beatSize, beatSize); // right oval shape of the heart
        beatSize -= beatSpeed; // beat size is decresed by the beat speed so it becomes smaller over time
        // ensure that the circles dont become too small
        if (beatSize < beatMinSize) // if heart beat size is less than min. size
        {
            beatSize = beatMinSize; // sets to the min. 
        }
    }

    // method that draws left heart
    public void leftHeart()
    {
        float heartSize = map(ap.left.level(), 0, 1, 50, 200) * 8; // calculate heart size based on left level channel of audio, from 0-1 to range of 50-200, multiply by 8

        strokeWeight(4);
        stroke(255, 0, 0);
        fill(255, 0, 0);

        float bezierOffset = heartSize/6; // the spacing between the bezier curves
        
        beginShape(); // start drawing
        vertex(width/2, height/2 + heartSize/2); // strating at the middle of the screen
        bezierVertex(width/4 - heartSize/2, height/2 - bezierOffset - heartSize/80, width/2 - heartSize/80, height/2 - heartSize/2 + bezierOffset, width/2, height/2 - heartSize/80); // left heart
        endShape(CLOSE); // end drawing
        noStroke();
        fill(255, 255, 255);
        ellipse(width/2 - heartSize/4, height/2 - heartSize/4, beatSize, beatSize); // roundness
        ellipse(width/2 + heartSize/4, height/2 - heartSize/4, beatSize, beatSize); // roundness
        beatSize -= beatSpeed; // beat size is decresed by the beat speed so it becomes smaller over time
        // ensure that the circles dont become too small
        if (beatSize < beatMinSize) // if heart beat size is less than min. size
        {
            beatSize = beatMinSize; // sets to the min. 
        }
    }

    // method that draws only the right heart
    public void rightHeart()
    {
        float heartSize = map(ap.left.level(), 0, 1, 50, 200) * 8; // calculate heart size based on left level channel of audio, from 0-1 to range of 50-200, multiply by 8

        strokeWeight(4);
        stroke(255, 0, 0);
        fill(255, 0, 0);

        float bezierOffset = heartSize/6; // the spacing between the bezier curves
        
        beginShape(); // start drawing
        vertex(width/2, height/2 + heartSize/2); // middle of the screen
        bezierVertex(width/2 + heartSize+75, height/2 - bezierOffset+20+ heartSize/80, width/1.85f + heartSize/80, height/2 - heartSize/2 + bezierOffset, width/2, height/2 - heartSize/100); // right heart
        endShape(CLOSE); // end drawing
        noStroke();
        fill(255, 255, 255);
        ellipse(width/2 - heartSize/4, height/2 - heartSize/4, beatSize, beatSize); // right oval of heart
        ellipse(width/2 + heartSize/4, height/2 - heartSize/4, beatSize, beatSize); // left oval of heart
        beatSize -= beatSpeed; // beat size is decresed by the beat speed so it becomes smaller over time
        // ensure that the circles dont become too small
        if (beatSize < beatMinSize) // if heart beat size is less than min. size
        {
            beatSize = beatMinSize; // sets to the min. 
        }
    }


    // method that stops the program
    public void stop()
    {
        ap.close(); // audio stop
        m.stop(); // stop process in minim library
        super.stop(); // stop process in the superclass's stop() 
    }
}