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

    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }
    @Override
    public Vector add(Vector vector){
        Double3 p=  vector.xyz.add(this.xyz);
        Vector newVector=new Vector(p);
        return newVector;
    }

    public double lengthSquared(){
        Double3 productPoint= this.xyz.product(this.xyz);
       return productPoint.d1+productPoint.d2+productPoint.d3 ;
    }

    public double length(){
        return Math.sqrt(lengthSquared());

    }
    public Vector scale(double scalar)
    {
        return new Vector(xyz.scale(scalar));
    }

    public double dotProduct(Vector vector){
        double x1= xyz.product(vector.xyz).d1;
        double x2= xyz.product(vector.xyz).d2;
        double x3= xyz.product(vector.xyz).d3;
        return x1+x2+x3;
    }

    public Vector crossProduct(Vector vector){
        double x=xyz.d2*vector.xyz.d3-xyz.d3*vector.xyz.d2;
        double y=xyz.d3*vector.xyz.d1-xyz.d1*vector.xyz.d3;
        double z=xyz.d1*vector.xyz.d2-xyz.d2*vector.xyz.d1;
        return new Vector(x,y,z);
    }

    public Vector normalize(){
        return new Vector(xyz.reduce(length()));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
