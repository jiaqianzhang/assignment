package ie.tudublin;

import processing.core.PApplet;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;

public class Circles extends PApplet {
    Minim minim;
    AudioPlayer audioPlayer;
    AudioBuffer audioBuffer;
    BeatDetect beatDetect;

    ExpandingCircles ec;

    int mode = 0;

    public void keyPressed() {
        if (key >= '0' && key <= '9') {
            mode = key - '0';
        }
        if (keyCode == ' ') { // Use the integer code for the spacebar key
            if (audioPlayer.isPlaying()) {
                audioPlayer.pause();
            } else {
                audioPlayer.rewind();
                audioPlayer.play();
            }
        }
    }

    public void settings() {
        size(1024, 1000, P3D);
    }

    public void setup() {
        minim = new Minim(this);
        audioPlayer = minim.loadFile("Believer.mp3", 1024);
        audioPlayer.play();

        beatDetect = new BeatDetect();
        beatDetect.detectMode(BeatDetect.FREQ_ENERGY);

        ec = new ExpandingCircles(this);

        // Add the following line to call the main method
        PApplet.runSketch(new String[] { "ie.tudublin.Circles" }, this);
    }

    int bgColor = color(255, 255, 255);
    int heartColor = color(255, 0, 0);
    float beatSize = 10;
    float beatMaxSize = 100;
    float beatSpeed = 0.5f;
    float beatDecay = 0.9f;
    float threshold = 50;
    float beatMinSize;
    float radius = 0;
    float centerX = width / 2;
    float centerY = height / 2;

    public void draw() {
        background(bgColor);

        // Check for beats
        beatDetect.detect(audioPlayer.mix);
        if (beatDetect.isOnset()) {
            radius = 0;
        }

        // Draw circle
        noFill();
        strokeWeight(3);
        stroke(heartColor);
        ellipse(centerX, centerY, radius, radius);

        // Update circle size
        radius += beatSize;
        if (radius > beatMaxSize) {
            radius = beatMinSize;
        }
        beatSize *= beatDecay;

        ec.setMode(mode);
        ec.setThreshold(threshold);
        ec.setBeatSize(beatSize);
        ec.setBeatMaxSize(beatMaxSize);
        ec.setBeatSpeed(beatSpeed);
        ec.setBeatDecay(beatDecay);
        ec.setHeartColor(heartColor);
        ec.setCenter(centerX, centerY);
        ec.setRadius(radius);
        ec.draw();
    }

}
