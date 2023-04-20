package ie.tudublin;
 
import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class ManarBrain
{
    Minim m;
    AudioPlayer ap;
    AudioBuffer ab;
    MyVisual p;
 
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;
 
    public ManarBrain(MyVisual p)
    {
        this.p = p;
    }

    float off = 0;
 
    float lerpedBuffer[] = new float[1024];
 
    public void draw()
    {
        p.background(0);
 
        p.pushMatrix();//seperating drawBrain function from other functions
        drawBrain();//function to draw the brain
        p.popMatrix();
 
        drawCircles();//function to draw the circles
        drawWaves();//function to draw the waveform
    }
         
    public void drawBrain()
    {
        p.strokeWeight(8);
        p.stroke(255, 105, 180);//color + weight of the line
        p.translate(p.width/2 , brainHeight, 0);//position of the brain on the screen
       
        p.beginShape();
 
        p.rotateY(p.frameCount * rotationSpeed);//makes the brain drawing rotate on the Y axis
 
 
        //drawing the brain
        //centre
        p.line(-165, -80, -200, 0); //1
 
        p.line(-90, -120, -165, -80); //2
 
        p.line(10, -130, -90, -120); //3
 
        p.line(60, -125, 10, -130); //4
 
        p.line(110, -110, 60, -125); //5
 
        p.line(200, -40, 110, -110); //6
       
        p.line(230, 40, 200, -40); //7
 
        p.line(215, 70, 230, 40); //8
 
        p.line(190, 100, 215, 70); //9
 
        p.line(100, 90, 190, 100); //10
 
        p.line(110, 190, 100, 90); //11
 
        p.line(60, 95, 110, 190); //12
 
        p.line(-30, 115, 60, 95); //13
 
        p.line(-120, 110, -30, 115); //14
 
        p.line(-110, 65, -120, 110); //15
 
        p.line(-180, 70, -110, 65); //16
 
        p.line(-200, 0, -180,70);//17
 
        //2 second lines from the centre
        p.line(-145, -60, 40, -180, 0, 40); //1 right
        p.line(-145, -60, -40, -180, 0, -40); //1 left
        p.line(-70, -100, 40, -145, -60, 40); //2 right
        p.line(-70, -100, -40, -145, -60, -40); //2 left
        p.line(30, -110, 40, -70, -100, 40 ); //3 right
        p.line(30, -110, -40, -70, -100, -40 ); //3 left
        p.line(80, -105, 40, 30,-110, 40); //4 right
        p.line(80, -105, -40, 30,-110, -40); //4 left
        p.line(90, -90, 40, 80, -105, 40);//5 right
        p.line(90, -90, -40, 80, -105, -40);//5 left
        p.line(180, -20, 40, 90, -90, 40);// 6 right
        p.line(180, -20, -40, 90, -90, -40);// 6 left
        p.line(210, 60, 40, 180, -20, 40);//7 right
        p.line(210, 60, -40, 180, -20, -40);//7 left
        p.line(180, 70, 40, 210, 60, 40);//8 right
        p.line(180, 70, -40, 210, 60, -40);//8 right
        p.line(100,50,40, 180, 70, 40);//10 right
        p.line(100,50,-40, 180, 70, -40);//10 left
        p.line(90,110,40,100,50,40);//11 right
        p.line(90,110,-40,100,50,-40);//11 left
        p.line(40,70,40,100,50,40);//12 right
        p.line(40,70,-40,100,50,-40);//12 right
        p.line(-80,85,40,40,70,40);
        p.line(-80,85,-40,40,70,-40);
        p.line(-70,30,40,-80,85,40);
        p.line(-70,30,-40,-80,85,-40);
        p.line(-160,40,40,-70,30,40);
        p.line(-160,40,-40,-70,30,-40);
        p.line(-180,0,40,-160,40,40);
        p.line(-180,0,-40,-160,40,-40);
 
        p.line(-90,-50,80,-120, 0,80);//1 right
        p.line(-90,-50,-80,-120, 0,-80);//1 left
 
        p.line(-30,-70,80,-90,-50,80);
        p.line(-30,-70,-80,-90,-50,-80);
 
        p.line(30,-80,80,-30,-70,80);
        p.line(30,-80,-80,-30,-70,-80);
 
        p.line(160,0,80,30,-80,80);
        p.line(160,0,-80,30,-80,-80);
 
        p.line(175,40, 80,160,0,80);
        p.line(175,40, -80,160,0,-80);
 
        p.line(80,25,80,175,40,80);
        p.line(80,25,-80,175,40,-80);
 
        p.line(70,45,80,80,25,80);
        p.line(70,45,-80,80,25,-80);
 
        p.line(-40,55,80,70,45,80);
        p.line(-40,55,-80,70,45,-80);
 
        p.line(-30,15,80,-40,55,80);
        p.line(-30,15,-80,-40,55,-80);
 
        p.line(-120,25,80,-30,15,80);
        p.line(-120,25,-80,-30,15,-80);
 
        p.line(-120, 0,80,-120,25,80);
        p.line(-120, 0,-80,-120,25,-80);
 
        //4 (connecting the lines together)
        p.line(-180,0,40,-165,-80,0);//1
        p.line(-180,0,-40,-165,-80,0);//1
 
        p.line(-180,0,40,-120, 0,80);
        p.line(-180,0,-40,-120, 0,-80);
        p.line(160,0,80, -120,0,80);
        p.line(160,0,-80, -120,0,-80);
 
        p.line(-200,0,0,-180,0,40);
        p.line(-200,0,0,-180,0,-40);
 
        p.line(-180,0,40,-180,70,0);
        p.line(-180,0,-40,-180,70,0);
 
        p.line(-180,70,0,-160,40,40);
        p.line(-180,70,0,-160,40,-40);
 
        p.line(-160,40,40,-110, 65,0);
        p.line(-160,40,-40,-110, 65,0);
 
        p.line(-70,30,40,-110,65,0);
        p.line(-70,30,-40,-110,65,0);
 
        p.line(200, -40,0,180, -20, 40);
        p.line(200, -40,0,180, -20, -40);
 
        p.line(215, 70,0,180, 70, 40);
        p.line(215, 70,0,180, 70, -40);
 
        p.line(230, 40,0,210, 60, 40);
        p.line(230, 40,0,210, 60, -40);//8
 
        p.line(190, 100,0,100,50,40);
        p.line(190, 100,0,100,50,-40);//9
 
        p. line(-180, 70,0,-120,25,80);
        p. line(-180, 70,0,-120,25,-80);
       
        p. line(180, 70, 40,70,45,80);
        p. line(180, 70, 40,70,45,-80);
       
        p. line(-145, -60, 40,-90,-50,80);
        p. line(-145, -60, -40,-90,-50,-80);
       
        p. line(-30,-70,80,-70, -100, 40);
        p. line(-30,-70,-80,-70, -100, -40);
       
        p. line(90, -90, 40,160,0,80);
        p. line(90, -90, -40,160,0,-80);
       
        p. line(90,-90,40,30,-80,80);
        p. line(90,-90,-40,30,-80,-80);
       
        p. line(-145,-60,40,-90,-120,0);//2
        p. line(-145,-60,-40,-90,-120,0);//2
       
        p. line(10,-130,0,30, -110,40);//3
        p. line(10,-130,0,30, -110,-40);//3
       
        p. line(60, -125,0,80, -105, 40);//4
        p. line(60, -125,0,80, -105, -40);//4
       
        p. line(110, -110,0,90, -90, 40);//5
        p. line(110, -110,0,90, -90, -40);//5
       
        p. line(110,190,0,90,110,40);//11
        p. line(110,190,0,90,110,-40);//11
       
        p. line(-120, 110,0,-70,30,40);
        p. line(-120, 110,0,-70,30,-40);
       
        p. line(230, 40,0,175,40,80);
        p. line(230, 40,0,175,40,-80);
        p.endShape();
    }//end drawBrain
 
    float angle = 0;
    float radius = 300;//size of the larger circle
 
    public void drawCircles()
    {
        // circle
        p.noStroke();
        p.noFill();
        p.ellipse(p.width/2, p.height/2, radius*2, radius*2);//size of the larger circle
   
   
        // rotating circles
        for (int i = 0; i < 10; i++)
        {
            float x = MyVisual.cos(angle + i * MyVisual.TWO_PI / 10) * radius + p.width/2;
            float y = MyVisual.sin(angle + i * MyVisual.TWO_PI / 10) * radius + p.height/2.5f;
            p.stroke(0,0,255);
            p.fill(0,0,255);
            p.ellipse(x, y, 20, 20);
        }
        angle += 0.05;//speed of the smaller rotating circles
    }//end function drawCircles
 
 
    float brainHeight = 350;
    float rotationSpeed = 0.02f;//rotation speed of the brain
   
    public void drawWaves()
    {
        float halfH = p.height/2 + 350;
        float average = 0;
        float sum = 0;
        off += 1;
 
        // calculate sum and average of the samples
        // lerp each element of buffer
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            sum += MyVisual.abs(ab.get(i));
            lerpedBuffer[i] = MyVisual.lerp(lerpedBuffer[i], ab.get(i), 0.1f);
        }
 
        average= sum / (float) ab.size();
 
        smoothedAmplitude = MyVisual.lerp(smoothedAmplitude, average, 0.1f);
 
        float a = (float)p.width / (float)ab.size() * 10; // width
        float space = (float)p.width * 1; //space between each line on the wave. spans across the full screen
        float w = space / 2;
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            p.stroke(243, 165, 0);
            float f = lerpedBuffer[i] * halfH/2 * 1; //height of the wave
            float x = (i * a) - w;
            p.line(x, halfH + f, x, halfH - f);
        }
    }//end drawWaves function
     
    public void stop()
    {
        ap.close();
        m.stop();
    }
}