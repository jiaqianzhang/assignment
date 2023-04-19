// java program that draws a full heart, left heart and right heart that beats by detecting beats from a music file
package ie.tudublin;
 
import java.io.Serial;
 
import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
 
public class JiaHeart
{
    Minim m;
    AudioPlayer ap;
    AudioBuffer ab;
    MyVisual p;
 
    public JiaHeart(MyVisual p)
    {
        this.p = p;
    }
 
    // keyboard
    public void keyPressed()
    {
        if (p.keyCode == ' ') // press spacebar
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
        p.size(1024, 1000);
    }
 
    BeatDetect beat; // variable for detecting beat
 
    public void setup()
    {
        m = new Minim(p); // initialise minim library
        ap = m.loadFile("Believer.wav", 1024); // load music file
        ap.play(); // play audio file
        beat = new BeatDetect(); // initialise BeatDetect class
        beat.detectMode(BeatDetect.FREQ_ENERGY); // set mode for detecting beats to freq. energy
    }
 
    Serial port; // declare a new Serial object
 
    public void draw()
    {
        p.background(0); // set background to black
        analyzeMusic(); // call method to detect beats in the audio
        if (p.key == '1') // if press 1 on keyboard
        {
            leftHeart(); // calls leftHeart
        }
        if (p.key == '2') // if press 2 on keyboard
        {
            rightHeart(); // calls rightHeart
        }
        if (p.key == '3') // if press 3 on keyboard
        {
            fullHeart(); // display fullHeart
        }
    }
 
    int heartColor;
    float beatSize = 10;
    float beatMaxSize = 100;
    float beatDecay = 0.9f;
 
    // analyse music to detect beats
    public void analyzeMusic()
    {
        beat.detect(ap.mix); // detect beats and updates
        if (beat.isOnset()) // if statement to check if a beat has been detected
        {
            heartColor = p.color(p.random(255), p.random(255), p.random(255)); // set heart colour to random of 255
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
        float heartSize = MyVisual.map(ap.left.level(), 0, 1, 50, 200) * 8; // calculate the size of the heart based on left level channel of audio, from 0-1 to range of 50-200, multiply by 8
 
        p.strokeWeight(4);
        p.stroke(255, 0, 0);
        p.fill(255, 0, 0);
 
        float bezierOffset = heartSize/6; // the spacing between the bezier curves
 
        p.beginShape(); // begin drawing shape
        p.vertex(p.width/2, p.height/2 + heartSize/2);
        p.bezierVertex(p.width/4 - heartSize/2, p.height/2 - bezierOffset - heartSize/80, p.width/2 - heartSize/80, p.height/2 - heartSize/2 + bezierOffset, p.width/2, p.height/2 - heartSize/80); // left heart
        p.bezierVertex(p.width/2 + heartSize/80, p.height/2 - heartSize/2 + bezierOffset, p.width/1.35f + heartSize/2, p.height/2 - bezierOffset - heartSize/80, p.width/2, p.height/2 + heartSize/2); // right heart
        p.endShape(); // end of drawing shape
        p.noStroke();
        p.fill(255, 255, 255);
        p.ellipse(p.width/2 - heartSize/4, p.height/2 - heartSize/4, beatSize, beatSize); // left oval shape  of heart
        p.ellipse(p.width/2 + heartSize/4, p.height/2 - heartSize/4, beatSize, beatSize); // right oval shape of the heart
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
        float heartSize = MyVisual.map(ap.left.level(), 0, 1, 50, 200) * 8; // calculate heart size based on left level channel of audio, from 0-1 to range of 50-200, multiply by 8
 
        p.strokeWeight(4);
        p.stroke(255, 0, 0);
        p.fill(255, 0, 0);
 
        float bezierOffset = heartSize/6; // the spacing between the bezier curves
       
        p.beginShape(); // start drawing
        p.vertex(p.width/2, p.height/2 + heartSize/2);
        p.bezierVertex(p.width/4 - heartSize/2, p.height/2 - bezierOffset - heartSize/80, p.width/2 - heartSize/80, p.height/2 - heartSize/2 + bezierOffset, p.width/2, p.height/2 - heartSize/80); // left heart
        p.endShape(); // end drawing
        p.noStroke();
        p.fill(255, 255, 255);
        p.ellipse(p.width/2 - heartSize/4, p.height/2 - heartSize/4, beatSize, beatSize); // roundness
        p.ellipse(p.width/2 + heartSize/4, p.height/2 - heartSize/4, beatSize, beatSize); // roundness
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
        float heartSize = MyVisual.map(ap.left.level(), 0, 1, 50, 200) * 8; // calculate heart size based on left level channel of audio, from 0-1 to range of 50-200, multiply by 8
 
        p.strokeWeight(4);
        p.stroke(255, 0, 0);
        p.fill(255, 0, 0);
 
        float bezierOffset = heartSize/6; // the spacing between the bezier curves
       
        p.beginShape(); // start drawing
        p.vertex(p.width/2, p.height/2 + heartSize/2);
        p.bezierVertex(p.width/2 + heartSize+75, p.height/2 - bezierOffset+20+ heartSize/80, p.width/1.85f + heartSize/80, p.height/2 - heartSize/2 + bezierOffset, p.width/2, p.height/2 - heartSize/100); // right heart
        p.endShape(); // end drawing
        p.noStroke();
        p.fill(255, 255, 255);
        p.ellipse(p.width/2 - heartSize/4, p.height/2 - heartSize/4, beatSize, beatSize); // right oval of heart
        p.ellipse(p.width/2 + heartSize/4, p.height/2 - heartSize/4, beatSize, beatSize); // left oval of heart
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
        p.stop(); // stop process in the superclass's stop()
    }
}