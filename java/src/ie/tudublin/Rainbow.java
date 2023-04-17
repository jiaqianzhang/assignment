package ie.tudublin;

import java.io.Serial;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import processing.core.PApplet;

public class Rainbow<color> extends PApplet
{
    Minim m;
    AudioPlayer ap;
    AudioBuffer ab;

    int mode = 0;
    int numDrops = 100; // set the number of drops to 25
    float[] dropX = new float[numDrops];
    float[] dropY = new float[numDrops];
    float lightningProbability = 0.01f; // set the probability of lightning occurring

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
    int bgColor = color(0);
    private int c1;

    public void draw() {

    background(bgColor);
    noFill();
    stroke(255);
    pushMatrix();
    translate(500, (float) (height*0.35), -200);
    rotateY((float) (frameCount * 0.01));
    sphere(280);
    popMatrix();
         
  // Draw blue sea waves
    for (int j = 0; j < 3; j++)// add 3 waves
    { 
        float waveOffset = j * 40; // adjust the offset of each wave
        fill(12, 100, 167);
        stroke(255, 255, 255);
        strokeWeight(3);
        beginShape();
        curveVertex(0, height);
        curveVertex(0, height);

        for (int i = 0; i <= width; i += 20)
        {
            curveVertex(i, height - 100 * sin(i * 0.01f + frameCount * 0.05f + waveOffset)); // add wave offset
        }

        curveVertex(width, height);
        curveVertex(width, height);
        endShape();

        // Draw foam outline
        noFill();
        stroke(255, 255, 255, 100);
        strokeWeight(6);
        beginShape();
        curveVertex(0, height);
        curveVertex(0, height);

        for (int i = 0; i <= width; i += 10) 
        {
            curveVertex(i, height - 100 * sin(i * 0.01f + frameCount * 0.05f + waveOffset) - 10 * cos(i * 0.02f + frameCount * 0.07f + waveOffset));
        }

        curveVertex(width, height);
        curveVertex(width, height);
        endShape();

        }
    
        // Add bubbles
        fill(255, 255, 255, 50);
        noStroke();
        
        
        for (int i = 0; i < 5; i++)// create 5 bubbles at random positions
        { 
            float x = random(width);
            float y = random(height*0.5f, height*0.8f);
            float size = random(20, 50);
            ellipse(x, y, size, size); // draw bubble
        }

        
            
        if (random(3) < lightningProbability) 
        {
            // draw multiple lightning bolts
            for (int i = 0; i < 5; i++) {
                // set random start and end positions for the lightning bolt
                float startX = random(width);
                float endX = random(width);
                float startY = 0;
                float endY = height - 50; // made lightining strike longer

                // draw the lightning bolt
                strokeWeight(8);
                stroke(255, 215, 0);
                noFill();
                beginShape();
                vertex(startX, startY);
                for (int j = 0; j < 10; j++) {
                float x = lerp(startX, endX, (float) (j / 10.0));
                float y = lerp(startY, endY, (float) (j / 10.0)) + random(-20, 20);
                vertex(x, y);
                }
                vertex(endX, endY);
                endShape();
            }
        }
        
            
            // define colors
            int c3 = color(150, 200, 255); // light blue
            int c4 = color(200, 230, 255); // lighter blue
            
            // draw rain drops
            for (int i = 0; i < dropX.length; i++) 
            {
            float dropLength = random(10, 100); // generate a random length for each rain drop
            float yPos = dropY[i] + dropLength; // calculate the y position of the bottom of the rain drop
            float percentY = yPos / height; // calculate the percentage of the screen height that the rain drop is at
            
            // interpolate between colors based on the rain drop's position
            if (percentY <= 0.5)
            {  
                stroke(lerpColor(c1, c3, percentY * 2));
            } 
            
            else
            {
                stroke(lerpColor(c3, c4, (float) ((percentY - 0.5) * 2)));
            }
            
            strokeWeight(random(2, 4)); // set a random stroke weight for each rain drop
            line(dropX[i], dropY[i], dropX[i], yPos); // draw the rain drop as a line
            
            dropY[i] += 10; // move the rain drop downwards
            
            // if the rain drop is out of the screen, reset its position
            if (yPos > height)
            {
                dropX[i] = random(width);
                dropY[i] = random(-100, 0) - dropLength; // subtract the drop length to prevent overlapping
            }

            }
    }


    public void stop()
    {
        ap.close();
        m.stop();
        super.stop();
    }
      
}

