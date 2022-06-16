package unittests.myImages;

import geometries.*;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;

/**
 * our final picture
 * @author Mikhal Levi and Eliana Grajower
 */

public class image {
    private Scene scene = new Scene.SceneBuilder("House scene").build();
    @Test
    public void housePicture(){
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0,-1), new Vector(0, 1,0)) //
                .setVPSize(25, 25).setVPDistance(1000);
        Point A = new Point(2,-4.56733,3.26697);
        Point B = new Point(1.33949,-8.42337,5.64588);
        Point C = new Point(1.33949,-1.29055,5.64588);
        Point D = new Point(2,0.95004,3.26697);
        Point E = new Point(-5.22038,-4.48057,-0.17177);
        Point F = new Point(-6.45236,-8.42337,5.64588);
        Point G = new Point(-5.19457,0.96882,2.9306);
        Point H = new Point(-6.45236,-1.29055,5.64588);
        Point I = new Point(-2,4,8.77547);
        Point Jb = new Point(-25,-3,-7);
        Point Kb = new Point(25,-3,-7);
        Point Jg = new Point(-25,-3,-7);
        Point Kg = new Point(25,-3,-7);
        Point L = new Point(25,-25,0);
        Point M = new Point(-25,-25,0);
        Point N = new Point(-25,25,0);
        Point O = new Point(25,25,0);
        Color grass = new Color(39,167,29);
        Color sky = new Color(103,239,250);
        Color brown = new Color(50,0,0);


        scene.geometries.add(

                //building
                new Triangle(A,B,C).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(A,C,D).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(A,D,E).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(D,E,G).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(C,D,G).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(C,G,H).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(G,H,E).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(F,H,E).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(A,B,E).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(F,E,B).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(H,C,new Point(1.33949,-5.5,5.64588))
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(H,new Point(1.33949,-5.5,5.64588),new Point(-6.45236,-5.5,5.64588))
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(B,new Point(-1.5,-8.42,5.7),new Point(-1.5,-5.5,5.7))
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(B,new Point(1.33949,-5.5,5.64588),new Point(-1.5,-5.5,5.7))
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(F,new Point(-3.7,-8.42,5.7),new Point(-3.7,-5.5,5.7))
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(F,new Point(-6.45236,-5.5,5.64588),new Point(-3.7,-5.5,5.7))
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),

                //roof
                new Triangle(H,G,I).setEmission(new Color(RED))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(C,D,I).setEmission(new Color(RED))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(H,C,I).setEmission(new Color(RED))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(D,G,I).setEmission(new Color(RED))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),

                //grass
                new Triangle(M,Kg,L).setEmission(grass)
                       .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.3)),
                new Triangle(Kg,Jg,M).setEmission(grass)
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.3)),

                //sky
                new Triangle(Jb,N,Kb).setEmission(sky)
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.3)),
                new Triangle(N,O,Kb).setEmission(sky)
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.3)),

                //sun
                new Sphere(new Point(7.59,9.44,10),2).setEmission(new Color(YELLOW))
                        .setMaterial(new Material().setKD(0.001).setkS(0.5).setnShininess(100).setKt(0.6).setKr(0.3)),

                //birds
                new Triangle(new Point(-10,5,0),new Point(-9.94,2.67,3),
                        new Point(-9.46,4.15,0)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(new Point(-10,5,0),
                        new Point(-9.46,4.15,0),new Point(-8.33,3.91,3)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(new Point(-4.96,3.75,3),new Point(-7.06,4.44,0),
                        new Point(-6.21,3.93,0)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(new Point(-6.58,2.49,3),new Point(-7.06,4.44,0),
                        new Point(-6.21,3.93,0)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(new Point(-6.76,1.44,3),new Point(-8.61,2.79,0),
                        new Point(-7.87,1.82,0)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(new Point(-8.25,0.64,3),new Point(-8.61,2.79,0),
                        new Point(-7.87,1.82,0)).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(new Point(-3.7,-8.42,5.7),new Point(-3.7,-5.5,5.7),
                        new Point(-1.9,-9,6.1))
                        .setEmission(brown)
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(new Point(-1.9,-9,6.1),new Point(-3.7,-5.5,5.7),
                        new Point(-1.9,-6,6.1))
                        .setEmission(brown)
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),

                //windows
                new Triangle(new Point(-3.67,-2.60,5.7), new Point(-3.67,-3.90,5.7),
                        new Point(-5.03,-3.90,5.7))
                        .setEmission(brown)
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.2).setKr(0.3)),
                new Triangle(new Point(-5.03,-2.60,5.7),new Point(-3.67,-2.60,5.7),
                        new Point(-5.03,-3.90,5.7))
                        .setEmission(brown)
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.2).setKr(0.3)),
                new Triangle(new Point(-1.39,-2.60,5.7), new Point(-1.39,-3.90,5.7),
                        new Point(-0.03,-3.90,5.7))
                        .setEmission(brown)
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.2).setKr(0.3)),
                new Triangle(new Point(-0.03,-2.60,5.7),new Point(-1.39,-2.60,5.7),
                        new Point(-0.03,-3.90,5.7))
                        .setEmission(brown)
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.2).setKr(0.3)),

                //tree
                new Cylinder(new Ray(new Point(9.65,-12.2,6.5),new Vector(-0.05,12.5,6.5)),0.4,
                        3).setEmission(brown)
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Cylinder(new Ray(new Point(9.65,-8.2,3),new Vector(-0.05,8.2,3)),0.4,
                        3).setEmission(brown)
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Cylinder(new Ray(new Point(9.65,-4.2,1),new Vector(-0.05,3.73,1)),0.4,
                        3).setEmission(brown)
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Sphere(new Point(9.65,-10,8),2).setEmission(new Color(0,35,0))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0).setKr(0.3)),
                new Sphere(new Point(9.65,-5.5,4.5),2).setEmission(new Color(0,35,0))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0).setKr(0.3)),
                new Sphere(new Point(9.65,-1,2.5),2).setEmission(new Color(0,35,0))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0).setKr(0.3)),

                //clouds
                new Sphere(new Point(-7.92,6.92,0),1.5).setEmission(new Color(255,255,255))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.9).setKr(0.3)),
                new Sphere(new Point(-8.5,9.62,0),2).setEmission(new Color(255,255,255))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.9).setKr(0.3)),
                new Sphere(new Point(-3.01,10.14,0),1.5).setEmission(new Color(255,255,255))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.9).setKr(0.3)),
                new Sphere(new Point(-1.65,8.06,0),2.3).setEmission(new Color(255,255,255))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.9).setKr(0.3)),
                new Sphere(new Point(-5.41,8.46,0),2.7).setEmission(new Color(255,255,255))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.9).setKr(0.3)),

                //lamp
                new Polygon(new Point(2.9,-7.20,4),new Point(2.9,-4.15,4),
                        new Point(3.45,-4.15,4),new Point(3.45,-7.20,4)),
                new Sphere(new Point(3.21,-3.70,4),0.6).setEmission(new Color(YELLOW))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.4).setKr(0.3))


                );
        //birds/sun
        scene.lights.add(new SpotLight(new Color(yellow), new Point(-7.27,12.14,5.5),
                new Vector(-0.12,-8.55,5.5)));

        //sun
        scene.lights.add(new PointLight(new Color(yellow),new Point(6.59,8.44,10)));
        scene.lights.add(new DirectionalLight(new Color(800,500,0), new Vector(5.59,7.44,10)));

        //lamp light
        scene.lights.add(new PointLight(new Color(255,255,255),new Point(6.59,8.44,10)));

        camera.setImageWriter(new ImageWriter("House image", 1000, 1000)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

}
