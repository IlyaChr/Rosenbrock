package com.company;

/**
 * Created by ILYA on 15.04.2017.
 * Решение СЛАУ методом Гаусса c выбором главного элемента
 *
 */
public class Gauss {

    public static void Guuss(){}




    public static double[] makeGauss(double[][] A,double[] D){


        double[] X = new double[D.length];

        double[][] A_2 = new double[A.length][A[0].length];

        double[] D_2 = new double[A.length];



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

                double c = -A_2[k][i]/A_2[i][i];
                A_2[k][i] = 0;

                for (int j = i+1  ; j < A_2.length; j++){

                    A_2[k][j] =  c * A_2[i][j] + A_2[k][j];

                }
                    D_2[k] =  c * D_2[i] + D_2[k] ;
            }


            //System.out.println(i + "from" + A_2.length);
        }






        /*BACKWARD PROGRESS */
        for (int i = A_2.length-1  ; i > -1 ; i--) {
            double s = 0.0;

            for (int j = i+1 ; j < A_2[i].length ; j++) {

                s = s + A_2[i][j]*X[j];
            }

            X[i] = (D_2[i] - s)/A_2[i][i];


           // System.out.println(i );
        }

        return X;
    }


    /*Модификация метода Гаусса схема с выбором главного элемента */
    private static void swap(double[][] A,double[] D , int n){

        double temp;

        int number = n;

        // инициализируем буферную переменную диагональным элементом по умолчанию
        temp = A[n][n];


        // определяем строку где на первом месте наибольшый элемент
        for (int i = n  ; i < A.length; i++) {

            if (Math.abs(A[i][n]) > Math.abs(temp)) {

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
                //System.out.println("hi");

                temp = D[number];

                D[number] = D[n];

                D[n] = temp;
        }

    }

    /* метод для определения невязки */
    public static double check(double[][] A,double[] D ,double[] X){


        //double[] R = new double[X.length];

        double check = 0.0;

        for (int i = 0  ; i < A.length; i++) {

            double temp = 0.0;

            for (int j = 0  ; j < A.length; j++) {

                temp += A[i][j]*X[j];

                //System.out.println(temp);

            }

            //System.out.println(temp);

            check += (Math.abs((D[i] - temp)))/A.length;

        }

        //SimpleIter.printA(R);

        return check;
    }
}
