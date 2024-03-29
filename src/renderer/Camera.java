package renderer;

import primitives.*;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

import static primitives.Util.isZero;
import java.util.stream.*;
/**
 * a class of a camera
 * @author Mikhal Levy and Eliana Grajower
 */
public class Camera {
    private Point P0;
    private Vector v_t0;
    private Vector v_up;
    private Vector v_right;
    private double height;
    private double width;
    private double distance;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;
    private int amountRowPixels;
    private int amountColumnPixels;
    private  double debugPrint;
private int threadsCount = 0;
    private static final int SPARE_THREADS = 2;
//    public Camera setMultithreading(int threads) {
//        if (threads < 0)
//            throw new IllegalArgumentException("Multithreading parameter must be 0 or higher");
//        if (threads != 0)
//            this.threadsCount = threads;
//        else {
//            int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
//            this.threadsCount = cores <= 2 ? 1 : cores;
//        }
//        return this;
//    }
//    private void ImageThreaded() {
//        final int nX = imageWriter.getNx();
//        final int nY = imageWriter.getNy();
//        final Pixel thePixel = new Pixel(nY, nX);
//        // thePixel.initialize(nY, nX,0.26);
//        // Generate threads
//        Thread[] threads = new Thread[threadsCount];
//        for (int i = threadsCount - 1; i >= 0; --i) {
//            threads[i] = new Thread(() -> {
//                Pixel pixel = new Pixel(nX,nY);
//                while (thePixel.nextPixel(pixel))
//                    castRay(nX, nY, pixel.col, pixel.row);
//            });
//        }
//        // Start threads
//        for (Thread thread : threads)
//            thread.start();
//
//        // Print percents on the console
//        thePixel.printPixel();
//
//        // Ensure all threads have finished
//        for (Thread thread : threads)
//            try {
//                thread.join();
//            } catch (Exception e) {
//            }
//
//        if (thePixel.nextPixel())
//            System.out.print("\r100%");
//    }
    /**
     * constructor
     *
     * @param P0   a point on the camera
     * @param v_t0 a vector the goes from the camera straight to the view plane.
     * @param v_up a vector that goes from the camera upwards
     */
    public Camera(Point P0, Vector v_t0, Vector v_up) {
        if (!isZero(v_up.dotProduct(v_t0)))
            throw new IllegalArgumentException("the 2 vectors are not verticals to each other");
        this.P0 = P0;
        this.v_t0 = v_t0.normalize();
        this.v_up = v_up.normalize();
        this.v_right = this.v_t0.crossProduct(this.v_up);
    }

    /**
     * getter
     * @return a P0
     */
    public Point getP0() {
        return P0;
    }

    /**
     * getter
     * @return a v_to
     */
    public Vector getV_t0() {
        return v_t0;
    }

    /**
     * getter
     * @return a v_up
     */
    public Vector getV_up() {
        return v_up;
    }

    /**
     * getter
     * @return a v_right
     */
    public Vector getV_right() {
        return v_right;
    }

    /**
     * getter
     * @return a height
     */
    public double getHeight() {
        return height;
    }

    /**
     * getter
     * @return a width
     */
    public double getWidth() {
        return width;
    }

    /**
     * getter
     * @return a distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * sets the size of the view plane
     * @param width width of the view plane
     * @param height height of the view plane
     * @return the size of the view plane
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * sets the pixel
     * @param amountRowPixels row
     * @param amountColumnPixels column
     * @return camera
     */
    public Camera setPixels(int amountRowPixels, int amountColumnPixels) {
        this.amountRowPixels = amountRowPixels;
        this.amountColumnPixels = amountColumnPixels;
        return this;
    }

    /**
     * sets the distance of the view plane from the camera
     *
     * @param distance the distance of the view plane from the camera
     * @return the distance of the view plane from the camera
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * constructs the ray through the pixels
     *
     * @param nX number of columns
     * @param nY number of lines
     * @param j  index of pixels
     * @param i  index of pixels
     * @return a constructed ray through the pixels
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point Pc = P0.add(v_t0.scale(distance));
        //ratio
        double Ry = height / nY;
        double Rx = width / nX;
        double Yi = -(i - (nY - 1) / 2d) * Ry;
        double Xj = (j - (nX - 1) / 2d) * Rx;
        //Pixel[i,j] center:
        Point Pij = Pc;
        if (isZero(Xj) && isZero(Yi)) {
            return new Ray(P0, Pij.subtract(P0));
        }
        if (isZero(Xj)) {
            Pij = Pij.add(v_up.scale(Yi));
            return new Ray(P0, Pij.subtract(P0));
        }
        if (isZero(Yi)) {
            Pij = Pij.add(v_right.scale(Xj));
            return new Ray(P0, Pij.subtract(P0));
        }
        Pij = Pc.add(v_right.scale(Xj).add(v_up.scale(Yi)));

        return new Ray(P0, Pij.subtract(P0));
    }

    /**
     * sets the image writer
     * @param imageWriter image
     * @return camera
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * sets the ray tracer
     * @param rayTracer traces the ray
     * @return camera
     */
    public Camera setRayTracer(RayTracerBasic rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * renders the image
     * @return a camera
     */
    public Camera renderImage() {
        try {
            if (imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }
            if (rayTracer == null) {
                throw new MissingResourceException("missing resource", rayTracer.getClass().getSimpleName(),"");
            }
           //multithreadig
            //rendering the image
            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();
           IntStream.range(0,nY).parallel().forEach(i->{
               IntStream.range(0,nX).parallel().forEach(j->{
                   castRay(nX,nY,j,i);
                   Pixel.pixelDone();
                   Pixel.printPixel();
               });
           });


        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
        return this;
    }

    /**
     * the grid where the image is printed
     * @param interval number
     * @param color the color of the grid
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("missing image writer", "Camera", "in print grid");
        for (int j = 0; j < imageWriter.getNx(); j++)
            for (int i = 0; i < imageWriter.getNy(); i++)
                if (j % interval == 0 || i % interval == 0)
                    imageWriter.writePixel(j, i, color);
    }

    /**
     * writes to the image
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("missing image writer", "Camera", "in writeTorImage");
        imageWriter.writeToImage();
    }

    /**
     * constructs ray through a pixel
     * @param nX nx
     * @param nY ny
     * @param j index
     * @param i index
     * @return list of rays
     */
    public List<Ray> constructRays(int nX, int nY, int j, int i) {
        if (amountColumnPixels <= 0 || amountRowPixels <= 0) {
            return List.of(constructRay(nX, nY, j, i));
        }
        Point Pc = P0.add(v_t0.scale(distance));
        List<Ray> rays = new LinkedList<>();
//ratio
        double Ry = height / nY;
        double Rx = width / nX;
        double Yi = -(i - (nY - 1) / 2d) * Ry;
        double Xj = (j - (nX - 1) / 2d) * Rx;
//Pixel[i,j]center:
        Point Pij = Pc;
        if (!isZero(Yi)) {
            Pij = Pij.add(v_up.scale(Yi));
        }
        if (!isZero(Xj)) {
            Pij = Pij.add(v_right.scale(Xj));
        }
        Ry = Ry / amountColumnPixels;
        Rx = Rx / amountRowPixels;
        for (int k = 0; k < amountRowPixels; k++) {
            for (int l = 0; l < amountColumnPixels; l++) {

                Point point = Pij;
                double Yii = -(k -
                        (amountColumnPixels - 1) / 2d) *
                        Ry;
                double Xjj = -(l -
                        (amountRowPixels - 1) / 2d) * Rx;
                if (!isZero(Yii)) {
                    point = point.add(v_up.scale(Yii
                    ));
                }
                if (!isZero(Xjj)) {
                    point = point.add(v_right.scale(
                            Xjj));
                }
                rays.add(new Ray(P0, point.subtract(P0)));
            }
        }
        return rays;
    }

    /**
     * cast the rays and merge the rays to color and write the pixels with the colors
     * @param nX
     * @param nY
     * @param i
     * @param j
     */
    private void castRay(int nX, int nY, int i, int j) {
        List<Ray> rays = constructRays(nX, nY, i, j);
        Color pixelColor = rayTracer.traceRays(rays);
        imageWriter.writePixel(i,j, pixelColor);
    }

    /**
     * sets the print
     * @param v number
     * @return camera
     */
    public Camera setDebugPrint(double v) {
        this.debugPrint=v;
        return this;
    }
}
