package com.company;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Ilya on 26.02.2017.
 */
public class Matrix {

    public Matrix(){}

    public static Complex[][] identity(int n){// возвращает еденичную матрицу заданного размера

        Complex[][] A = new Complex[n][n];


        for (int i=0 ;  i<A.length; i++){
            for (int j=0 ; j<A[0].length ; j++){

                if (j==i) A[i][j] = new Complex(1,0);
                else A[i][j]= new Complex(0,0);


            }
        }

        return A;
    }


    public static void printAL (ArrayList<Complex> al) //вывод списка
    {
        for (Complex element : al) {
            System.out.println(element + " ");
        }


    }


    public static void printInFile ( ArrayList<Complex> al ,String nameData, String nameFile ){

        File file = new File(nameFile+".txt");
        String complex;
        BufferedWriter writer = null;

        try {

        writer = new BufferedWriter( new FileWriter(file));

        writer.write(nameData);
        writer.newLine();


            for (Complex element : al){

                complex = element.getReString();

                writer.write(complex);
                writer.newLine();

            }

            writer.flush();



        } catch (IOException e) {
        e.printStackTrace();
        }


        finally {
            try {
                if(writer!=null)
                    writer.close();
            }catch (Exception ex){
                System.out.println("Error in clossing the BufferedWriter"+ex);
            }
        }

    }



    public static void printInFile ( ArrayList<Double> a1,ArrayList<Double> a2 ,String nameData, String nameFile ){

        File file = new File(nameFile+".txt");
        String complex;
        BufferedWriter writer = null;

        try {

            writer = new BufferedWriter( new FileWriter(file));

            writer.write(nameData);
            writer.newLine();


            for (int i = 0 ; ( i<a1.size() || i<a2.size() ) ; i++ ) {

                complex = a1.get(i).toString();
                writer.write(complex);
                writer.append('\t');
                writer.append('\t');
                writer.append('\t');

                complex = a2.get(i).toString();
                writer.write(complex);

                writer.newLine();
            }


            writer.flush();



        } catch (IOException e) {
            e.printStackTrace();
        }


        finally {
            try {
                if(writer!=null)
                    writer.close();
                System.out.println("file complete");
            }catch (Exception ex){
                System.out.println("Error in clossing the BufferedWriter"+ex);
            }
        }

    }





    public static  void printA(Complex[][] al)  //вывод double матрицы
    {
        for (int i = 0; i<al.length; i++) {
            for (int k = 0; k<al[i].length;k++){
                System.out.print(al[i][k]+ "    ");
            }
            System.out.println();
        }
    }

    public static void printA(Complex[] al)  //вывод double массива
    {

        for (Complex elem : al) {

            System.out.print(elem + "    ");

            System.out.println();
        }
    }


    /**
     *умножение матриц
     */
    public static Complex[][] matrix_multiplication(Complex[][] A ,Complex[][] B) throws Exception{

        if (A[0].length!=B.length) throw new Exception();
        Complex[][] C = new Complex[A.length][B[0].length];
        Complex temp= new Complex(0,0);

        for (int i = 0; i<A.length; i++) {

            for (int k = 0; k<B[0].length; k++) {

                for (int j = 0; j < A[i].length; j++) {

                    temp.add( A[i][j].asterisk( B[j][k] ) );

                }


                C[i][k]=temp;
                temp.setRe(0);
                temp.setlm(0);


            }
        }

        return C;
    }

    public static Complex[][] minus(Complex[][] A , Complex D[]) throws Exception{

        if (A[0].length!=D.length) throw new Exception();

        Complex[][] C = new Complex[A.length][A[0].length];


        for (int i = 0 ; i<A.length ; i++){
            for (int j = 0 ; j<A[0].length ; j++){

                C[i][j] = A[i][j].minus(D[j]);

            }


        }


        return C;
    }

    public static Complex[] minus(Complex[] A , Complex D[]) throws Exception{

        if (A.length!=D.length) throw new Exception();

        Complex[] C = new Complex[A.length];


        for (int i = 0 ; i<A.length ; i++){

                C[i] = A[i].minus(D[i]);

        }

        return C;
    }

    public static double[] minus(double[] A , double D[]) throws Exception{

        if (A.length!=D.length) throw new Exception();

        double[] C = new double[A.length];


        for (int i = 0 ; i<A.length ; i++){

            C[i] = A[i]-D[i];

        }

        return C;
    }




    public static Complex[][] minus(Complex[][] A , Complex D[][]) throws Exception{

        if ( (A.length!=D.length) || (A[0].length != D[0].length) )  throw new Exception();

        Complex[][] C = new Complex[A.length][A[0].length];


        for (int i = 0 ; i<A.length ; i++){
            for (int j = 0 ; j<A[0].length ; j++){

                C[i][j] = A[i][j].minus(D[i][j]);

            }


        }


        return C;
    }


    public static Complex[][] plus(Complex[][] A , Complex D[]) throws Exception{

        if (A[0].length!=D.length) throw new Exception();

        Complex[][] C = new Complex[A.length][A[0].length];


        for (int i = 0 ; i<A.length ; i++){
            for (int j = 0 ; j<A[0].length ; j++){

                C[i][j] = A[i][j].plus(D[j]);

            }


        }


        return C;
    }


    public static Complex[] plus(Complex[] A , Complex D[]) throws Exception{

        if (A.length!=D.length) throw new Exception();

        Complex[] C = new Complex[A.length];


        for (int i = 0 ; i<A.length ; i++){

                C[i] = A[i].plus(D[i]);


        }


        return C;
    }


    public static double[] plus(double[] A , double D[]) throws Exception{

        if (A.length!=D.length) throw new Exception();

        double[] C = new double[A.length];


        for (int i = 0 ; i<A.length ; i++){

            C[i] = A[i]+D[i];


        }


        return C;
    }


    public static Complex[] plus(Complex A , Complex D[]) {


        Complex[] C = new Complex[D.length];


        for (int i = 0 ; i<D.length ; i++){

            C[i] = A.plus(D[i]);


        }


        return C;
    }


    public static Complex[][] multiplicationOnNumber(Complex[][] A , Complex D) {



        Complex[][] C = new Complex[A.length][A[0].length];

        for (int i = 0 ; i<A.length ; i++){
            for (int j = 0 ; j<A[0].length ; j++){

                C[i][j] = A[i][j].asterisk(D);

            }


        }


        return C;
    }

    public static Complex[] multiplicationOnNumber(Complex[] A , double D) {



        Complex[] C = new Complex[A.length];

        for (int i = 0 ; i<A.length ; i++){

                C[i] = A[i].asterisk(new Complex(D,0.0));


        }


        return C;
    }


    public static Complex[] multiplicationOnNumber(Complex[] A , Complex D) {



        Complex[] C = new Complex[A.length];

        for (int i = 0 ; i<A.length ; i++){

            C[i] = A[i].asterisk(D);


        }


        return C;
    }



    public static double[] multiplicationOnNumber(double[] A , double D) {



        double[] C = new double[A.length];

        for (int i = 0 ; i<A.length ; i++){

            C[i] = A[i]*D;
        }


        return C;
    }



    public static double[] divOnNumber(double[] A , double D) {



        double[] C = new double[A.length];

        for (int i = 0 ; i<A.length ; i++){

            C[i] = A[i]/D;


        }


        return C;
    }

    public static double[] getReal(Complex[] A) {

        double[] C = new double[A.length];

        for (int i = 0 ; i<A.length ; i++){

            C[i] = A[i].getRe();

        }


        return C;
    }



}
