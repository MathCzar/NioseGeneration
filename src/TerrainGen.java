import processing.core.PApplet;
import queasycam.QueasyCam;

public class TerrainGen extends PApplet {

    private QueasyCam cam;
    private Terrain terrain;

    public static void main(String[] args) {

        PApplet.main("TerrainGen");

    }

    public void settings() {

        size(800, 800, P3D);

    }

    public void setup() {

        //set up camera
        this.cam = new QueasyCam(this);
        perspective(PI/3, width/(float)height, 0.01f, 10000);
        this.cam.speed *= 0.1;

        //set up terrain
        this.terrain = new Terrain(this, cam);

    }

    public void draw() {

        //draw background
        background(0, 150, 200);

        lights();
        this.terrain.draw(this);

    }

}
