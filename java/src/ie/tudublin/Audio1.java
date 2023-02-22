package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio1 extends PApplet
{
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;

    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;
    float[] lerpedBuffer;

    public void keyPressed() {
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
	}

    public void settings()
    {
        size(1024, 1000, P3D);
        //fullScreen(P3D, SPAN);
    }

    public void setup()
    {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix; 

        // And comment the next two lines out
        ap = minim.loadFile("TameImpala_newPerson.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];

    }

    float off = 0;

    public void draw()
    {
        //background(0);
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        off += 1;
        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        }
        average= sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
        
        float cx = width / 2;
        float cy = height / 2;

        switch (mode) {
			case 0:
                background(0);
                for(int i = 0 ; i < ab.size() ; i ++)
                {
                    //float c = map(ab.get(i), -1, 1, 0, 255);
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    float f = ab.get(i) * halfH;
                    line(i, halfH + f, i, halfH - f);                    
                }
                break;
            case 1:
            {
                background(0);   
                float c = map(smoothedAmplitude,0, 0.5f, 0 , 255);
                
                stroke(c, 255, 255);

                float radius = map(smoothedAmplitude, 0, 0.1f, 50, 300);

                
                float px = cx;
                float py = cy - radius;

                int points = (int)map(mouseY, 0, 255, 3, 10);
                int sides = points * 2;

                for(int i = 0; i <= sides; i++)
                {

                    float r = (i % 2 == 0) ? radius : radius / 2;

                    float theta = map(i, 0, sides, 0, TWO_PI);
                    float x = cx + sin(theta) * r;
                    float y = cy - cos(theta) * r;

                    line(px, py, x, y);
                    circle(cx, cy, radius - 800);
                    noFill();

                    px = x;
                    py = y;

                    
                    
                }
                break;
            }

            case 2:
            {
                background(0);
                strokeWeight(3);
                noFill();

                float r = map(smoothedAmplitude,0, 0.5f, 100, 2000);
                float c = map(smoothedAmplitude, 0, 0.5f, 0, 255);

                stroke(c, 255, 255);
                
                circle(cx, cy, r);
                circle(cx, cy, r - 50);
                circle(cx, cy, r - 100);
                circle(cx, cy, r - 150);
                circle(cx, cy, r - 200);
                circle(cx, cy, r - 400);
                circle(cx, cy, r - 800);

                break;
            }

            case 3:
            {
               // Iterate over all the elements in the audio buffer
               for (int i = 0; i < ab.size(); i++) 
               {

                float c = map(i, 0, ab.size(), 0, 255);
                stroke(c, 255, 255);
          
                float f = ab.get(i) * halfH;     
                line(i, halfH - f * 0.5f, i, halfH + f * 0.5f);
                }     

                break;

              
            }

            case 4:
            {
                for (int i = 0; i < ab.size(); i++) {

                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                   
                    float f = ab.get(i) * halfH;
                    line(i, halfH - f * 0.6f, halfH + f * 0.6f, i);
                }        
                break;
            }

            case 5:
            {
                float r = 1f;
                int numPoints = 3;
                float thetaInc = TWO_PI / (float) numPoints;
                strokeWeight(2);                
                float lastX = width / 2, lastY = height / 2;
                for(int i = 0 ; i < 1000 ; i ++)
                {
                    float c = map(i, 0, 300, 0, 255) % 255.0f;
                    stroke(c, 255, 255, 100);
                    float theta = i * (thetaInc + smoothedAmplitude * 5);
                    float x = width / 2 + sin(theta) * r;
                    float y = height / 2 - cos(theta) * r;
                    r += 0.5f + smoothedAmplitude;
                    line(lastX, lastY, x, y);
                    lastX = x;
                    lastY = y;
                }
            }
               
            
        }
        


        
        // Other examples we made in the class
        /*
        stroke(255);
        fill(100, 255, 255);        
        
        circle(width / 2, halfH, lerpedA * 100);

        circle(100, y, 50);
        y += random(-10, 10);
        smoothedY = lerp(smoothedY, y, 0.1f);        
        circle(200, smoothedY, 50);
        */

    }        
}
