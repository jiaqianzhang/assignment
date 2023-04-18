package ie.tudublin;
 
import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import ddf.minim.AudioInput;
 
public class ManarBrain extends PApplet
{
    Minim m;
    AudioPlayer ap;
    AudioBuffer ab;
    AudioInput ai;
 
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;
 
    public void keyPressed()
    {
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
        ab = ap.mix;
 
        y = height/500;//circle 
        smoothedY = y;  
    }
 
    float off = 0;
 
    float lerpedBuffer[] = new float[1024];
 
    public void draw()
    {
        background(0);
 
        pushMatrix();//seperating drawBrain function from other functions
        drawBrain();//function to draw the brain
        popMatrix();
 
        drawCircles();//function to draw the circles
        drawWaves();//function to draw the waveform
    }
         
    public void drawBrain()
    {
        strokeWeight(8);
        stroke(255, 105, 180);//color + weight of the line
        translate(width/2 , brainHeight, 0);//position of the brain on the screen
       
        beginShape();
 
        rotateY(frameCount * rotationSpeed);//makes the brain drawing rotate on the Y axis
 

        //drawing the brain
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
 
        //2 second lines from the centre
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
 
        //3 third lines from the centre
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
 
        //4 (connecting the lines together)
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
        endShape(CLOSE);
    }//end drawBrain
 
    float angle = 0;
    float radius = 300;//size of the larger circle
 
    public void drawCircles()
    {
        // circle
        noStroke();
        noFill();
        ellipse(width/2, height/2, radius*2, radius*2);//size of the larger circle
   
   
        // rotating circles
        for (int i = 0; i < 10; i++)
        {
            float x = cos(angle + i * TWO_PI / 10) * radius + width/2;
            float y = sin(angle + i * TWO_PI / 10) * radius +height/2.5f;
            stroke(0,0,255);
            fill(0,0,255);
            ellipse(x, y, 20, 20);
        }
        angle += 0.05;//speed of the smaller rotating circles
    }//end function drawCircles
 

    float brainHeight = 350;
    float rotationSpeed = 0.02f;//rotation speed of the brain
   
    public void drawWaves()
    {
        float halfH = height/2 + 350;
        float average = 0;
        float sum = 0;
        off += 1;
 
        // calculate sum and average of the samples
        // lerp each element of buffer
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
        }
 
        average= sum / (float) ab.size();
 
        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
 
        float a = (float)width / (float)ab.size() * 10; // width
        float space = (float)width * 1; //space between each line on the wave. spans across the full screen
        float w = space / 2;
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            stroke(243, 165, 0);
            float f = lerpedBuffer[i] * halfH/2 * 1; //height of the wave
            float x = (i * a) - w;
            line(x, halfH + f, x, halfH - f);
        }
    }//end drawWaves function
     
    public void stop()
    {
        ap.close();
        m.stop();
    }

    public void render() {
        return render;
    }
}