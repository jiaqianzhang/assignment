package ie.tudublin;

import java.io.Serial;
import ddf.minim.analysis.BeatDetect;

public class Clock
{
    MyVisual v;
    BeatDetect beat;
    Serial port;

    public Clock(MyVisual v)
    {
        this.v = v;
    }

    public void render()
    {
        draw();
    }

    int cx, cy;
    float secondsRadius;
    float minutesRadius;
    float hoursRadius;
    float clockDiameter;

    // CLOCK OF DOOM 
    public void draw()
    {
        v.background(0);
        drawClock();
    }

    public void drawClock()
    {
        v.size(640, 360);
        v.stroke(255);
        
        int radius = MyVisual.min(v.width, v.height) / 2;
        secondsRadius = MyVisual.radius * 0.72f;
        minutesRadius = MyVisual.radius * 0.60f;
        hoursRadius = MyVisual.radius * 0.50f;
        clockDiameter = MyVisual.radius * 1.8f;
        
        cx = v.width / 2;
        cy = v.height / 2;
        
        // Draw the clock background
        v.fill(25, 25, 112);
        v.noStroke();
        v.ellipse(cx, cy, clockDiameter, clockDiameter);
        
        // Calculate the angles for the hands
        float s = MyVisual.map(MyVisual.second(), 0, 60, 0, MyVisual.TWO_PI) - MyVisual.HALF_PI;
        float m = MyVisual.map(MyVisual.minute() + MyVisual.norm(MyVisual.second(), 0, 60), 0, 60, 0, MyVisual.TWO_PI) - MyVisual.HALF_PI; 
        float h = MyVisual.map(MyVisual.hour() + MyVisual.norm(MyVisual.minute(), 0, 60), 0, 24, 0, MyVisual.TWO_PI * 2) - MyVisual.HALF_PI;
        
        // Draw the hands of the clock
        v.stroke(255, 165, 0);
        v.strokeWeight(1);
        v.line(cx, cy, cx + MyVisual.cos(s) * secondsRadius, cy + MyVisual.sin(s) * secondsRadius);
        v.stroke(255, 215, 0);
        v.strokeWeight(2);
        v.line(cx, cy, cx + MyVisual.cos(m) * minutesRadius, cy + MyVisual.sin(m) * minutesRadius);
        v.stroke(255, 255, 0);
        v.strokeWeight(4);
        v.line(cx, cy, cx + MyVisual.cos(h) * hoursRadius, cy + MyVisual.sin(h) * hoursRadius);
        
        // Draw the minute ticks
        v.strokeWeight(1);
        v.stroke(255);
        for (int a = 0; a < 360; a += 6)
        {
        float angle = MyVisual.radians(a);
        float x1 = cx + MyVisual.cos(angle) * (clockDiameter/2 - 2);
        float y1 = cy + MyVisual.sin(angle) * (clockDiameter/2 - 2);
        float x2 = cx + MyVisual.cos(angle) * (clockDiameter/2 - 10);
        float y2 = cy + MyVisual.sin(angle) * (clockDiameter/2 - 10);
        v.line(x1, y1, x2, y2);
        }
    }
  }