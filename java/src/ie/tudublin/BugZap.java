package ie.tudublin;

import com.jogamp.graph.font.Font;

import processing.core.PApplet;

public class BugZap extends PApplet {

    public void settings() {
        size(500, 500);
    }

    // Player variables
    float playerX;
    float playerY;
    float playerSpeed = 5;
    float playerWidth = 40;
    float halfWidth = playerWidth / 2;

    // enemy (bug) variables
    float bugX, bugY, bugWidth = 30;
    float bugHalfWidth = bugWidth / 2;

    // highscore
    float highscoreX = 20;
    float highscoreY = 20;
    int highscore = 0;
    Font scoreFont;

    // lazer
    float lazerX;
    float lazerY;
    float lazer;

    public void setup() {

        background(0);

        resetBug(); // reset bug when killed

        // set player into starting position
        playerX = width / 2;
        playerY = height - 60;

    }

    public void resetScore() {
        highscore = 0;
    }

    public void resetBug() {
        bugX = random(halfWidth, width - bugHalfWidth);
        bugY = 50;
    }

    void drawScore() {
        text("Score: " + highscore, highscoreX, highscoreY);
    }

    void drawLaser() {
        lazerX = playerX;
        lazerY = playerY;
        lazer = bugY;
        line(lazerX, lazerY, lazerX, lazer);
    }

    void drawBug(float x, float y) {
        // draw bug
        strokeWeight(3);
        stroke(155);
        float bugHeight = bugWidth * 0.5f;

        line(x, y - bugHeight, x - bugHalfWidth, y);
        line(x, y - bugHeight, x + bugHalfWidth, y);

        line(x - bugHalfWidth, y, x - bugHalfWidth, y);
        line(x - bugHalfWidth, y, x + bugHalfWidth, y);

        float eyes = bugWidth * 0.1f;
        line(x - eyes, y - eyes, x - eyes, y - eyes * 2f);
        line(x + eyes, y - eyes, x + eyes, y - eyes * 2f);

    }

    void drawPlayer(float x, float y, float w) {
        strokeWeight(3);
        stroke(255);

        float playerHeight = w / 2;

        line(x - halfWidth, y + playerHeight, x + halfWidth, y + playerHeight);

        line(x - halfWidth, y + playerHeight, x - halfWidth, y + playerHeight * 0.5f);
        line(x + halfWidth, y + playerHeight, x + halfWidth, y + playerHeight * 0.5f);

        line(x - halfWidth, y + playerHeight * 0.5f, x - (halfWidth * 0.8f), y + playerHeight * 0.3f);
        line(x + halfWidth, y + playerHeight * 0.5f, x + (halfWidth * 0.8f), y + playerHeight * 0.3f);

        line(x - (halfWidth * 0.8f), y + playerHeight * 0.3f, x + (halfWidth * 0.8f), y + playerHeight * 0.3f);

        line(x, y, x, y + playerHeight * 0.3f);

    }

    public void keyPressed() {
        if (keyCode == LEFT) {
            if (playerX > halfWidth) {
                playerX -= playerSpeed;
                System.out.println("Left arrow pressed");
            }
        }

        if (keyCode == RIGHT) {
            if (playerX < width - halfWidth) {
                playerX += playerSpeed;
                System.out.println("Right arrow pressed");
            }
        }

        if (key == ' ') {
            if (playerX > bugX - bugHalfWidth && playerX < bugX + bugHalfWidth) {
                killBug();
            } else {
                line(lazerX, lazerY, lazerX, 0);
            }
            drawLaser();
        }
    }

    void moveBug() {
        if ((frameCount % 20) == 0) {
            bugX += random(-5, +5);
            if (bugX < bugHalfWidth) {
                bugX = bugHalfWidth;
            }

            if (bugX + bugHalfWidth > width) {
                bugX = width - halfWidth;
            }

            bugY++;
        }

        if (bugY == playerY && bugX == playerX) {
            setup();
        }
    }

    void bugHit() {
        if (playerX > bugX - bugHalfWidth && playerX < bugX + bugHalfWidth) {
            killBug();
        } else {
            line(lazerX, lazerY, lazerX, 0);
        }
    }

    void killBug() {
        // kill bug

        resetBug();
        highscore++;

    }

    public void draw() {
        background(0);
        fill(255);

        drawScore();

        drawPlayer(playerX, playerY, playerWidth);

        drawBug(bugX, bugY);
        moveBug();

    }
}
