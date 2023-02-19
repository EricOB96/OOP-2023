package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class StarMap extends PApplet
{
	ArrayList<Star> stars = new ArrayList<Star>();

    int startStar = -1;
    int endStar = -1;

	public void settings()
	{
		size(500, 500);
	}

	public void setup() {
		colorMode(RGB);
		
		loadStars();
		printStars();

        border = width * 0.1f;

	}

	void printStars()
	{
		for(int i = 0 ; i < stars.size() ; i ++)
		{
			println(stars.get(i));
		}
	}

	void displayStars()
	{
		for(int i = 0 ; i < stars.size() ; i ++)
		{
			stars.get(i).render(this);
		}
	}

	public void loadStars()
	{
		Table table = loadTable("HabHYG15ly.csv", "header");
 		for(TableRow r:table.rows())
 		{
 			Star s = new Star(r);
 			stars.add(s);
 		}
	}

	public void drawGrid()
	{
		
		float border = width * 0.1f;

        textAlign(CENTER, CENTER);

		for(int i = -5 ; i <= 5 ; i ++)
		{
			float x = map(i, -5, 5, border, width - border);
            float y = map(i, -5, 5, border, height - border);

            stroke(0,255,255);

			line(x, border, x, height - border);
			line(border, y, width - border, y);

            fill(255);

			
			text(i, x, border / 2);
			text(i, border / 2, y);
		}

		//float f = map(5, 0, 10, 100, 200);
		//float f1 = map1(5, 0, 10, 100, 200);
		
	}

	float map1(float a, float b, float c, float d, float e)
	{
		float r1 = c -b;
		float r2 = e - d;

		float howFar = a - b;

		return d + ((howFar / r1) * r2);
	}

    float border;

    public void mouseClicked()
    {
        for(int i = 0; i < stars.size(); i++)
        {
            Star s = stars.get(i);

            float x = map(s.getxG(), -5, 5, border, width - border);
            float y = map(s.getyG(), -5, 5, border,  height - border);

            if(dist(mouseX, mouseY, x, y) < s.getAbsMag() / 2)
            {
                if(startStar == -1)
                {
                    startStar = i;
                    break;
                }
                else if(endStar == - 1)
                {
                    endStar = i;
                    break;
                }
                else
                {
                    startStar = i;
                    endStar = -1;
                }
            }

        }
    }
		
	public void draw()
	{	
		background(0);	

		drawGrid();
		displayStars();

        if(startStar != -1 && endStar == -1)
        {
            Star s = stars.get(startStar);
            stroke(255,255,0);

            float x = map(s.getxG(), -5, 5, border, width - border);
            float y = map(s.getyG(), -5, 5, border, height - border);

            line(x,y, mouseX, mouseY);
        }
        else if(startStar != -1 && endStar != -1)
        {
            Star s = stars.get(startStar);

            float x1 = map(s.getxG(),-5, 5, border,width - border);
            float y1 = map(s.getyG(), -5, 5, border, height - border);

            Star s1 = stars.get(endStar);

            float x2 = map(s.getxG(),-5, 5, border,width - border);
            float y2 = map(s.getyG(), -5, 5, border, height - border);

            line(x1, y1, x2, y2);

            float dist = dist(s.getxG(), s.getyG(), s.getzG(),s1.getxG(), s1.getyG(), s1.getzG());

            stroke(255);

            textAlign(CENTER,CENTER);

            text("Distance between " + s.getDisplayName() + " and " + s1.getDisplayName() + " is " + dist + " parsecs", width /2 , height - (border / 2));
        }
	}
}
