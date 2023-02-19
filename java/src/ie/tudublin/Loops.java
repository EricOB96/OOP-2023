package ie.tudublin;

import processing.core.PApplet;

public class Loops extends PApplet {

	
	public void settings() {
		size(1000, 1000);
        cx = width / 2;
        cy = height / 2;
	}

    float cx;
    float cy;

    int mode = 0;

	public void setup() {

        colorMode(HSB);
        
	}



	public void keyPressed() {
		if(keyCode >= '0' && keyCode <= '9')
        {
            mode = key - '0';
            println(mode);
        }
	}


	public void draw() {
		background(0);
		noStroke();
		
        switch (mode)
        {
            case 0:
            {
                float w = 200;
                float h = 50;
                rectMode(CENTER);

                if(mouseX > cx - (w/2) && mouseX < cx + (w/2) && mouseY > cy - (h/2) && mouseY < cy + (h/2))
                {
                    fill(50,255,255);
                }
                else{
                    fill(200,255,255);
                }

                rect(cx, cy, w, h);

                break;
            }

            case 1:
            {
                fill(50,147,255);
                

                if(mouseX < cx  && mouseY < cy)
                {
                    rect(0,0,cx,cy);
                }
                else if(mouseX > cx && mouseY < cy)
                {
                    rect(cx,0,cx,cy);
                }
                else if(mouseX < cx && mouseY > cy)
                {
                    rect(0,cy,cx,cy);
                }
                else
                {
                    rect(cx,cy,cx,cy);
                }

                break;
            }

            case 2:
            {
                int i = 0;
                int numRectangles = (int)(mouseX / 10.0f);
                float w = width /  (float)numRectangles;
                float gap = 255 /  (float)numRectangles;

                for( i = 0; i < numRectangles; i++)
                {
                    fill(i * gap, 255, 255);
                    rect(i * w, 0, w, height);
                }

                break;

            }

            case 3:
            {
                rectMode(CORNER);
                int i = 0;
                int numSquares = (int) mouseX / 10;
                float w = width / (float)numSquares;
                float gap = 255 / (float)numSquares;

                for(i = 0; i < numSquares; i++)
                {
                    fill(i * gap, 255, 255);
                    rect(i * w, i * w, w, w);
                }

                break;

            }

            case 4:
            {
                rectMode(CORNER);
                int i = 0;
                int numSquares = (int)mouseX / 10;
                float w = width / (float)numSquares;
                float gap = 255 / (float)numSquares;

                for(i = 0; i < numSquares; i++)
                {
                    fill(i * gap, 255, 255);
                    rect(i * w, i * w, w, w);
                    rect(width - ((i + 1) * w), i *w, w, w);
                }

                break;

            }

            case 5:
            {
                int i = 0;
                int numCircles = (int)(mouseX / 10.0f);
                float w = width / (float) numCircles;
                float gap = 255 / (float) numCircles;

                for(i = 0; i < numCircles; i ++)
                {
                    fill(i * gap, 255, 255);
                    ellipse(w / 2 + (i * w), cy, w, w);
                }

                break;

            }
        }
		
		

	}
}


