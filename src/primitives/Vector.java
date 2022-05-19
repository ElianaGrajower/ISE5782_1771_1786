package primitives;

public class Vector extends Point {
    /**
     * constructor
     * @param x
     * @param y
     * @param z
     */
    public Vector(double x, double y, double z){
        super(x, y, z);
        if (xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("vector can't be zero");
    }

    /**
     * constructor
     * @param xyz
     */
    public Vector(Double3 xyz){
        super(xyz);
        if (xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("vector can't be zero");
    }
    /**
     * checks if objects are equal to each other
     * @param object
     * @return true or false.
     */
    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }

    /**
     * add two vectors together
     * @param vector
     * @return the new vector
     */
    @Override
    public Vector add(Vector vector){
        return new Vector(vector.xyz.add(this.xyz));
    }

    /**
     * calculates the length of a vector squared
     * @return the vectors' length squared
     */
    public double lengthSquared(){
        Double3 productPoint= this.xyz.product(this.xyz);
        return productPoint.d1+productPoint.d2+productPoint.d3 ;
    }
    /**
     * calculates the length of a vector
     * @return the vectors' length
     */
    public double length(){
        return Math.sqrt(lengthSquared());

    }

    /**
     * multiplies a vector by a scalar
     * @param scalar
     * @return the calculation
     */
    public Vector scale(double scalar)
    {
        return new Vector(xyz.scale(scalar));
    }

    /**
     * dot product between two vectors
     * Dot Product – Let we have given two vector A = a1 * i + a2 * j + a3 * k and
     * B = b1 * i + b2 * j + b3 * k. Where i, j and k are the unit vector along the
     * x, y and z directions. Then dot product is calculated as
     * dot product = a1 * b1 + a2 * b2 + a3 * b3
     * @param vector
     * @return the calculation
     */
    public double dotProduct(Vector vector){
        double x1= xyz.product(vector.xyz).d1;
        double x2= xyz.product(vector.xyz).d2;
        double x3= xyz.product(vector.xyz).d3;
        return x1+x2+x3;
    }

    /**
     * cross product between two vectors
     * Cross Product – Let we have given two vector A = a1 * i + a2 * j + a3 * k and
     * B = b1 * i + b2 * j + b3 * k. Then cross product is calculated as
     * cross product = (a2 * b3 – a3 * b2) * i + (a3 * b1 – a1 * b3) * j + (a1 * b2 – a2 * b1) * k,
     * where [(a2 * b3 – a3 * b2), (a3 * b1 – a1 * b3), (a1 * b2 – a2 * b1)] are the
     * coefficient of unit vector along i, j and k directions.
     * @param vector
     * @return the calculation
     */
    public Vector crossProduct(Vector vector){
        double x=xyz.d2*vector.xyz.d3-xyz.d3*vector.xyz.d2;
        double y=xyz.d3*vector.xyz.d1-xyz.d1*vector.xyz.d3;
        double z=xyz.d1*vector.xyz.d2-xyz.d2*vector.xyz.d1;
        return new Vector(x,y,z);
    }

    /**
     * normalize a vector
     * @return
     */
    public Vector normalize(){
        return new Vector(xyz.reduce(length()));
    }

    /**
     * prints a vector type
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    }

}
