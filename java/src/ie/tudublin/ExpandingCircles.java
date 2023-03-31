package ie.tudublin;

import processing.core.PApplet;

public class ExpandingCircles extends PApplet {
    float radius;
    float x, y;

    public ExpandingCircles(Circles circles) {
    }

    public void settings() {
        size(1024, 1000, P3D);
    }

    public void setup() {
        background(0);
        radius = 50;
        x = width/2;
        y = height/2;
    }

    public void draw() {
        background(0);
        noFill();
        stroke(255);
        strokeWeight(3);
        ellipse(x, y, radius, radius);

        radius += 1;
        if (radius > width) {
            radius = 50;
        }
    }

    public void setMode(int mode) {
    }

    public void setThreshold(float threshold) {
    }

    public void setBeatSize(float beatSize) {
    }

    public void setBeatMaxSize(float beatMaxSize) {
    }

    public void setBeatSpeed(float beatSpeed) {
    }

    public void setHeartColor(int heartColor) {
    }

    public void setBeatDecay(float beatDecay) {
    }

    public void setRadius(float radius2) {
    }

    public void setCenter(float centerX, float centerY) {
    }
}
