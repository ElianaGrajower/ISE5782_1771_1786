package primitives;

public class Vector extends Point {
    public Vector(double x, double y, double z){
        super(x, y, z);
        if (xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("vector can't be zero");
    }

    public Vector(Double3 xyz){
        super(xyz);
        if (xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("vector can't be zero");
    }

    public double lengthSquared(){
        return 0;
    }

    public double length(){
        return Math.sqrt(lengthSquared());

    }

    public double dotProduct(Vector vector){
        return 0;
    }

    public Vector crossProduct(Vector vector){
        return null;
    }

    public Vector normalize(){
        return new Vector(xyz.reduce(length()));
    }

}
