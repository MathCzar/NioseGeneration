import processing.core.PApplet;
import processing.core.PImage;
import queasycam.QueasyCam;

public class Terrain {

    private QueasyCam cam;

    private PImage ramp;

    private float scale;

    private int length, width;

    private float amplitude;
    private float distance;

    public Terrain(PApplet app, QueasyCam cam) {

        scale = 10;

        length = 200;
        width = 200;

        //maximum height of the mountain
        amplitude = 400;

        //the "smoothness" of the mountains
        distance = 0.03f;

        this.cam = cam;

        ramp = null;
        try {

            //load color ramp
            ramp = app.loadImage("res/Ramp.png");

        }catch(Exception e) {}
    }

    public void draw(PApplet app) {

        float xPos = cam.position.x;
        float zPos = cam.position.z;

        //sets the stroke to false
        app.noStroke();

        //move our terrain down
        app.pushMatrix();
        app.translate(xPos-length*scale/2, 20, zPos-width*scale/2);

        //draw a strip
        app.fill(0, 255, 0);
        for(int x = 0; x < length; x++) {
            app.beginShape(app.TRIANGLE_STRIP);
            for (int z = 0; z < width; z++) {

                float y = app.noise((x + xPos) * distance, (z + zPos) * distance);
                y = y * y * amplitude;
                int colory = (int)Math.pow(y/amplitude, 2);
                int color = ramp.pixels[340-(int)(y/amplitude*340)];
                app.fill(color);
                app.vertex(x * scale, y, z * scale);

                float y1 = app.noise((x + 1 + xPos) * distance, (z + zPos) * distance);
                y1 = y1 * y1 * amplitude;
                int colory1 = (int)Math.pow(y1/amplitude, 2);
                int color1 = ramp.pixels[340-(int)(y1/amplitude*340)];
                app.fill(color1);
                app.vertex((x + 1) * scale, y1, z * scale);
            }
            app.endShape();
        }


        //undo movement
        app.popMatrix();

    }

}
