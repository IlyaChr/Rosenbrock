package com.company;

/**
 * Created by Ilya on 26.02.2017.
 */
public class My_Functions implements Function {
    @Override
    public Complex functionOne(Complex x, Complex y,Complex z){

        /*для y=sin(x) */
        /*для y=cos(x) */
        /*для y=x*cos(x) */
        /*для y=3*arctg(x)*sin(x) */
        return z ;

    }

    @Override
    public Complex functionTwo(Complex x, Complex y , Complex z) {

        /*для y=sin(x) */
        //return new Complex(-1.0,0.0).asterisk(y);

        /*для y=cos(x) */
        return (z.asterisk(z).minus(new Complex(Math.cos(x.getRe()),0.0))).plus(new Complex(Math.sin(x.getRe()),0.0).asterisk(z)) ;


         /*для y=x*cos(x) */
        //return (z.asterisk(z).minus(new Complex(2.0,0.0).asterisk(Complex.sin(x))).minus(Complex.cos(x).asterisk(x)).minus(Complex.cos(x).asterisk(z)).plus(x.asterisk(z).asterisk(Complex.sin(x)))) ;


        /*для y=3*arctg(x)*sin(x) */
       // return (z.asterisk(z).minus(new Complex(3.0,0.0).asterisk(Complex.atan(x)).asterisk(Complex.sin(x))).minus((new Complex(6.0,0.0).asterisk(x).asterisk(Complex.sin(x)))
             //   .slash(  ( Complex.pow(x,2).plus(new Complex(1.0,0.0)) ).asterisk( Complex.pow(x,2).plus(new Complex(1.0,0.0)) )    )).plus(  (new Complex(6.0,0.0).asterisk(Complex.cos(x)) )
             //   .slash(    Complex.pow(x,2).plus(new Complex(1.0,0.0))          )) .minus(  (new Complex(3.0,0.0).asterisk(Complex.sin(x)).asterisk(z) )
            //    .slash(    Complex.pow(x,2).plus(new Complex(1.0,0.0))          )) .minus( new Complex(3.0,0.0).asterisk(Complex.atan(x)).asterisk(Complex.cos(x)).asterisk(z))        ) ;


    }



    @Override
    public Complex dfZfunctionOne(Complex x, Complex y , Complex z)
    {

        /*для y=sin(x) */
        /*для y=cos(x) */
        /*для y=x*cos(x) */
        /*для y=3*arctg(x)*sin(x) */
        return new Complex(1.0,0.0);

    }

    @Override
    public Complex dfZfunctionTwo(Complex x, Complex y , Complex z) {

        /*для y=sin(x) */
        //return new Complex(0.0,0.0);

        /*для y=cos(x) */
        return ((new Complex(2.0,0)).asterisk(z)).plus(new Complex(Math.sin(x.getRe()),0.0));

        /*для y=x*cos(x) */
        //return ((new Complex(2.0,0)).asterisk(z)).minus(Complex.cos(x)).plus(Complex.sin(x).asterisk(x));


        /*для y=3*arctg(x)*sin(x) */
        //return     ((new Complex(2.0,0)).asterisk(z)).minus  ( (new Complex(3.0,0.0).asterisk(Complex.sin(x))) .slash( Complex.pow(x,2).plus(new Complex(1.0,0.0)) )  ) .minus( new Complex(3.0,0.0).asterisk(Complex.atan(x)).asterisk(Complex.cos(x)))      ;
    }

    @Override
    public Complex dfYfunctionOne(Complex x, Complex y , Complex z) {

        /*для y=sin(x) */
        /*для y=cos(x) */
        /*для y=x*cos(x) */
        /*для y=3*arctg(x)*sin(x) */
        return new Complex(0.0,0.0);

    }

    @Override
    public Complex dfYfunctionTwo(Complex x, Complex y , Complex z) {

        /*для y=sin(x) */
        return new Complex(-1.0,0.0);


        /*для y=cos(x) */
        /*для y=x*cos(x) */
        /*для y=3*arctg(x)*sin(x) */
       // return new Complex(0.0,0.0);
    }
}
