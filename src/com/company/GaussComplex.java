package com.company;

/**
 * Created by ILYA on 15.04.2017.
 * Решение СЛАУ методом Гаусса
 *
 */
public final class GaussComplex {

    public static void GuussComplex(){}




    public static Complex[] makeGauss(Complex[][] A,Complex[] D){


        Complex[] X = new Complex[D.length];

        Complex[][] A_2 = new Complex[A.length][A[0].length];

        Complex[] D_2 = new Complex[A.length];



        for (int i = 0  ; i < A.length; i++) {
            D_2[i] = D[i];
            for (int j = 0  ; j < A.length; j++) {
                A_2[i][j] = A[i][j];
            }
        }



        /*STRAIGHTWAY */
        for (int i = 0  ; i < A_2.length; i++) {

            swap(A_2,D_2,i);

            for (int k = i+1  ; k < A_2.length; k++) {

                Complex c = (new Complex(-1.0,0.0).asterisk(A_2[k][i])).slash(A_2[i][i]);
                A_2[k][i] = new Complex(0.0,0.0);

                for (int j = i+1  ; j < A_2.length; j++){


                    A_2[k][j] = (c.asterisk(A_2[i][j])).plus(A_2[k][j]);



                }


                D_2[k] = (c.asterisk(D_2[i])).plus(D_2[k]) ;



            }



        }


        /*BACKWARD PROGRESS */
        for (int i = A_2.length-1  ; i > -1 ; i--) {
            Complex s = new Complex(0.0 , 0.0);

            for (int j = i+1 ; j < A_2[i].length ; j++) {

                s = s.plus(A_2[i][j].asterisk(X[j]));
            }

            X[i] = (D_2[i].minus(s) ).slash( A_2[i][i]);
        }

        //check(A,D,X);


        return X;
    }


    /*Модификация метода Гаусса схема с выбором главного элемента */
    private static void swap(Complex[][] A,Complex[] D , int n){

        Complex temp;

        int number = n;

        // инициализируем буферную переменную диагональным элементом по умолчанию
        temp = A[n][n];


        // определяем строку где на первом месте наибольшый элемент
        for (int i = n  ; i < A.length; i++) {

            if ((A[i][n].mod()) > temp.mod()) {

                temp = A[i][n];

                number = i;

            }

            }


        /* если такой элемент есть (который больше в столбце начального элемента) то меняем начальную строку на эту*/
        if (number != n) {

                for (int j = 0  ; j < A.length; j++) {

                    temp = A[number][j];

                    A[number][j] = A[n][j];

                    A[n][j] = temp;


                }

                System.out.println("hi");

                temp = D[number];

                D[number] = D[n];

                D[n] = temp;


        }


    }

    public static void check(double[][] A,double[] D ,double[] X){


        double[] R = new double[X.length];

        for (int i = 0  ; i < A.length; i++) {

            double temp = 0;

            for (int j = 0  ; j < A.length; j++) {

                temp += A[i][j]*X[j];

                //System.out.println(temp);

            }

            //System.out.println(temp);

            R[i] = D[i] - temp;
        }


        SimpleIter.printA(R);


    }
}
