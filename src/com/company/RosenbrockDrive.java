package com.company;

public class RosenbrockDrive {

    public static void main(String[] args) {

        /*Complex comp = new Complex(21,43);
        Complex comp1 = new Complex(23,-43);
        comp.mul(comp1);
        Function fun = (x) -> Math.pow(x,4)*4;
        //System.out.println(fun.Function(4));
        */

        Rosenbrock r = new Rosenbrock(new My_Functions(),0,10,1000);

        r.Method();




    }
}
