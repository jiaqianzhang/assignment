package ie.tudublin;


import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import processing.core.PApplet;

public class m extends PApplet
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
        fullScreen(P3D);
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
    int bgColor = color(0, 0, 0);
    int brainColor = color(255,192,203);
    float beatSize = 100;
    float beatMaxSize = 100;
    float beatDecay = 0.9f;
    float brainHeight = 350; 
    float rotationSpeed = 0.05f;
    

    public void draw() 
    {
        background(bgColor);
        drawBrain(); 
    }


     public void drawBrain() 
     {
        strokeWeight(8);
        stroke(brainColor);
        translate(width / 2, brainHeight, 0);
        rotateY(frameCount * rotationSpeed);
    
        beginShape();

        //centre
        line(-165, -80, -200, 0); //1

        line(-90, -120, -165, -80); //2

        line(10, -130, -90, -120); //3

        line(60, -125, 10, -130); //4

        line(110, -110, 60, -125); //5

        line(200, -40, 110, -110); //6
        
        line(230, 40, 200, -40); //7

        line(215, 70, 230, 40); //8 

        line(190, 100, 215, 70); //9

        line(100, 90, 190, 100); //10

        line(110, 190, 100, 90); //11

        line(60, 95, 110, 190); //12

        line(-30, 115, 60, 95); //13

        line(-120, 110, -30, 115); //14

        line(-110, 65, -120, 110); //15

        line(-180, 70, -110, 65); //16

        line(-200, 0, -180,70);//17

        //2
        line(-145, -60, 40, -180, 0, 40); //1 right
        line(-145, -60, -40, -180, 0, -40); //1 left
        line(-70, -100, 40, -145, -60, 40); //2 right
        line(-70, -100, -40, -145, -60, -40); //2 left
        line(30, -110, 40, -70, -100, 40 ); //3 right
        line(30, -110, -40, -70, -100, -40 ); //3 left
        line(80, -105, 40, 30,-110, 40); //4 right
        line(80, -105, -40, 30,-110, -40); //4 left
        line(90, -90, 40, 80, -105, 40);//5 right
        line(90, -90, -40, 80, -105, -40);//5 left
        line(180, -20, 40, 90, -90, 40);// 6 right
        line(180, -20, -40, 90, -90, -40);// 6 left
        line(210, 60, 40, 180, -20, 40);//7 right
        line(210, 60, -40, 180, -20, -40);//7 left
        line(180, 70, 40, 210, 60, 40);//8 right
        line(180, 70, -40, 210, 60, -40);//8 right
        
        line(100,50,40, 180, 70, 40);//10 right
        line(100,50,-40, 180, 70, -40);//10 left
        line(90,110,40,100,50,40);//11 right
        line(90,110,-40,100,50,-40);//11 left
        line(40,70,40,100,50,40);//12 right
        line(40,70,-40,100,50,-40);//12 right
        line(-80,85,40,40,70,40);
        line(-80,85,-40,40,70,-40);
        line(-70,30,40,-80,85,40);
        line(-70,30,-40,-80,85,-40);
        line(-160,40,40,-70,30,40);
        line(-160,40,-40,-70,30,-40);
        line(-180,0,40,-160,40,40);
        line(-180,0,-40,-160,40,-40);

        //3
        line(-90,-50,80,-120, 0,80);//1 right
        line(-90,-50,-80,-120, 0,-80);//1 left

        line(-30,-70,80,-90,-50,80);
        line(-30,-70,-80,-90,-50,-80);

        line(30,-80,80,-30,-70,80);
        line(30,-80,-80,-30,-70,-80);

        line(160,0,80,30,-80,80);
        line(160,0,-80,30,-80,-80);
        
        line(175,40, 80,160,0,80);
        line(175,40, -80,160,0,-80);
    
        line(80,25,80,175,40,80);
        line(80,25,-80,175,40,-80);

        line(70,45,80,80,25,80);
        line(70,45,-80,80,25,-80);

        line(-40,55,80,70,45,80);
        line(-40,55,-80,70,45,-80);

        line(-30,15,80,-40,55,80);
        line(-30,15,-80,-40,55,-80);

        line(-120,25,80,-30,15,80);
        line(-120,25,-80,-30,15,-80);

        line(-120, 0,80,-120,25,80);
        line(-120, 0,-80,-120,25,-80);

        //4 (connects)
        line(-180,0,40,-165,-80,0);//1
        line(-180,0,-40,-165,-80,0);//1

        line(-180,0,40,-120, 0,80);
        line(-180,0,-40,-120, 0,-80);
        line(160,0,80, -120,0,80);
        line(160,0,-80, -120,0,-80);

        line(-200,0,0,-180,0,40);
        line(-200,0,0,-180,0,-40);

        line(-180,0,40,-180,70,0);
        line(-180,0,-40,-180,70,0);

        line(-180,70,0,-160,40,40);
        line(-180,70,0,-160,40,-40);

        line(-160,40,40,-110, 65,0);
        line(-160,40,-40,-110, 65,0);

        line(-70,30,40,-110,65,0);
        line(-70,30,-40,-110,65,0);

        line(200, -40,0,180, -20, 40);
        line(200, -40,0,180, -20, -40);

        line(215, 70,0,180, 70, 40);
        line(215, 70,0,180, 70, -40);

        line(230, 40,0,210, 60, 40);
        line(230, 40,0,210, 60, -40);//8

        line(190, 100,0,100,50,40);
        line(190, 100,0,100,50,-40);//9

        line(-180, 70,0,-120,25,80);
        line(-180, 70,0,-120,25,-80);
        
        line(180, 70, 40,70,45,80);
        line(180, 70, 40,70,45,-80);

        line(-145, -60, 40,-90,-50,80);
        line(-145, -60, -40,-90,-50,-80);

        line(-30,-70,80,-70, -100, 40);
        line(-30,-70,-80,-70, -100, -40);

        line(90, -90, 40,160,0,80);
        line(90, -90, -40,160,0,-80);

        line(90,-90,40,30,-80,80);
        line(90,-90,-40,30,-80,-80);

        line(-145,-60,40,-90,-120,0);//2
        line(-145,-60,-40,-90,-120,0);//2

        line(10,-130,0,30, -110,40);//3
        line(10,-130,0,30, -110,-40);//3

        line(60, -125,0,80, -105, 40);//4
        line(60, -125,0,80, -105, -40);//4

        line(110, -110,0,90, -90, 40);//5
        line(110, -110,0,90, -90, -40);//5

        line(110,190,0,90,110,40);//11
        line(110,190,0,90,110,-40);//11 

        line(-120, 110,0,-70,30,40);
        line(-120, 110,0,-70,30,-40);

        line(230, 40,0,175,40,80);
        line(230, 40,0,175,40,-80);

    }
      
    public void stop()
    {
        ap.close();
        m.stop();
        super.stop();
    }
}
