// package ie.tudublin;

import java.io.Serial;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import processing.core.PApplet;

public class LauraRain<color> extends PApplet
{
    Minim m;
    AudioPlayer ap;
    AudioBuffer ab;

    //int mode = 0;
    int numDrops = 100; // set the number of drops to 25
    float[] dropX = new float[numDrops];
    float[] dropY = new float[numDrops];
    float lightningProbability = 0.05f; // set the probability of lightning occurring

    public void keyPressed()
    {
        // if (key >= '0' && key <= '9')
        // {
        //     mode = key - '0';
        // }
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
    private int c1;

    public void draw() 
    {
        background(0);
        drawSun();
        drawSeawaves();
        drawLightning();
        drawRaindrops();
    }

    // Draw sun with surrounding shapes
    public void drawSun()
    {
        lights();//lighting for the spheres 
        ambientLight(50, 50, 50);// illumination 
        specular(255, 255, 255);//highlights for spheres

        // Define variables to control the shape of the sun and surrounding
        float sunSize = 200;
        int sunDetailLevel = 13;
        float surroundingSize = 400;
        int surroundingDetailLevel = 6;

        background(0);//black
        pushMatrix(); // drawSun push
        translate(width/2, height/2, -400); // move ball to center of canvas
        rotateY((float)(frameCount * 0.009)); // rotation speed
 
        // Draw surrounding shapes
        noStroke();
        for (int i = 0; i < 20; i++)
        {
            float angle = map(i, 0, 20, 0, TWO_PI);//calculates rotation 
            pushMatrix();// saves the current position 
            rotateY(angle + frameCount * 0.01f);// rotates surrounding spheres
            translate(surroundingSize, 0, 0);
            fill(255, 100, 0);
            sphereDetail(surroundingDetailLevel);
            sphere(sunSize / 4);
            popMatrix();// used to restore the previous position 
        }
        
        // Draw sun
        for (int i = 0; i < 50; i++) //itrerates 50 times
        {
            pushMatrix();
            float offset = random(10, 20);
            translate(offset, 0, 0);
            // Set color of each sphere
            fill(255,165,0);
            sphereDetail(sunDetailLevel);
            sphere(sunSize); // size of the sphere
            stroke(255);
            popMatrix(); // restore overall transformation 
        }
        popMatrix(); // drawSun pop
    }

    public void drawStars()
    {
        // Draw background stars
        for (int i = 0; i < 200; i++) 
        {
            float x = random(-width, width);//genegrates random x,y, z coordinates of each star
            float y = random(-height, height);
            float z = random(-2000, 0);
            pushMatrix();
            translate(x, y, z);// translates the drawing 
            noStroke();
            fill(255);
            sphere(2);
            popMatrix();
        }
    }

    // drawing sea waves at the bottom of the screen
    public void drawSeawaves()
    {
        // Draw sea waves
        for (int j = 0; j < 3; j++) // add 3 waves
        {
            float waveOffset = j * 40; // adjust the offset of each wave
        
            // Set the fill and stroke colors for the wave
            fill(33, 150, 243, 100); // blue
            stroke(33, 150, 243); // blue
            strokeWeight(2);
        
            beginShape();
            curveVertex(0, height);
            curveVertex(0, height);
            for (int i = 0; i <= width; i += 20)
            {
                curveVertex(i, height - 50 * sin(i * 0.01f + frameCount * 0.05f + waveOffset)); // adjust wave height, wave offset, frame count 
            }
            curveVertex(width, height); // ending point of curve and the height
            curveVertex(width, height);
            endShape();

            // Draw foam outline
            noFill();
            stroke(255, 255, 255, 150); // white
            strokeWeight(4);
        
            beginShape();
            curveVertex(0, height);// first control point of curve
            curveVertex(0, height);
            for (int i = 0; i <= width; i += 10)
            {
                curveVertex(i, height - 50 * sin(i * 0.01f + frameCount * 0.05f + waveOffset) - 10 * cos(i * 0.02f + frameCount * 0.07f + waveOffset)); // adjust foam height and width using sin and cos
            }
            curveVertex(width, height);
            curveVertex(width, height);
            endShape();
        }
    }

    // drawing lightning flashing on the screen
    public void drawLightning()
    {
        //Lightning  
        if (random(1) < lightningProbability)
        {
            for (int i = 0; i < 5; i++)
            {
                // set random start and end positions for the lightning bolt
                float startX = random(width);
                float endX = random(width);
                float startY = 0;
                float endY = height - 50; // made lightining strike longer
                
                // draw the lightning bolt
                noFill();
                beginShape();
                vertex(startX, startY);
                for (int j = 0; j < 50; j++)
                {
                    float x = lerp(startX, endX, (float) (j / 30.0)); // calculate x and y coordinates
                    float y = lerp(startY, endY, (float) (j / 30.0)) + random(-50, 50);// y coorrdinate 

                    float thickness = 10 * pow(sin((float) (j / 30.0) * PI), 2); // vary lightning bolt thickness

                    int c = color(map(j, 0, 30, 255, 0), map(j, 0, 30, 0, 255), map(j, 0, 30, 128, 255)); // rainbow colours
                    stroke(c, 255 - j * 5); // fade out stroke
                    vertex(x, y);
                }
                vertex(endX, endY);
                endShape();
            }
        }
    }

    // drawing rain drops
    public void drawRaindrops()
    {
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