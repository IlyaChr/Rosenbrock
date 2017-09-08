package com.company;

import java.util.ArrayList;

/**
 * Created by Ilya on 15.09.2016.
 */
public class SimpleIter {

    public static final Complex EPS = new Complex(0.00000000001,0.00000000001); //точность // глобальная переменная



    private SimpleIter(){};

    public  static Complex[] makeIter(Complex[][] A,Complex[] D) throws Exception {


        if ( (A[0].length != D.length) || (A.length != D.length) )throw  new Exception(); //проверка на не совпадение длин массивов

        ArrayList<Complex> Array = new ArrayList<Complex>();
        Complex[][] B = new Complex[A.length][A[0].length]; //просто  вспомогательный массив

        Complex[] X1 = new Complex[A.length]; // массив для результата вычеслений
        Complex[] X2 = new Complex[A.length]; // вспомогательный массив для результата вычеслений (для предыдущий итерации)

        int qua=0; // для подсчета кол-во итераций


        //System.out.println("\n");


        for (int k = 0, p = 0; k < B.length; k++) {               // удаление диагонального элемента из матрицы и замена его на свободный член из массива D
            for (int i = 0; i < B[k].length; i++) {

                if (k != i) {
                    B[k][i] = A[k][i].asterisk(new Complex(-1,0));
                } else {
                    Array.add(A[k][i]);
                    B[k][i] = D[p];
                    p++;
                }
            }
        }




        for (int k = 0; k < B.length; k++) {               // деление каждого элемента матрицы на коофицент при элементе который удалили
            for (int i = 0; i < B[k].length; i++) {

                B[k][i].div(Array.get(k));
                if (k == i)
                    X2[k] = B[k][i]; // присваеваем массиву с результатами начальное приближение равное свободному члену
                    X1[k] = B[k][i]; // присваеваем массиву с результатами начальное приближение равное свободному члену
            }
        }



        Array.clear();

        // printA(X2);
        //printA(B);



        boolean flag=false;




        do { //основной блок программы



            if (flag) {
                for (int i=0;i<X1.length;i++){
                    X2[i]=X1[i];
                }

            }


            for (int k = 0; k < B.length; k++) {
                Complex temp = new Complex(0,0);
                for (int i = 0; i < B[k].length; i++) {


                    if (k != i) {temp.add( B[k][i].asterisk( X1[i] ) ); }
                    else temp.add( B[k][i] );



                }
                X1[k]=temp; //Метод Зейделя

            }


            flag = true;
            //printA(X1);
            //System.out.println();
            if (qua>10000) {System.out.println("проверка на сходимость / превышено допустимое количество итераций "); throw new Exception(); }else //проверка на сходимость если нет сходимости то кидаем искл
            qua++;


        } while (   !(checkEps(X1,X2,EPS))  );  //программа выполняется пока не будет достигнута требуемая точность

        //System.out.println("\n"+qua+"\tитераций было проведено\t"+"чтобы достичь\t"+EPS+"\tточности");


        return X1;
    }


    public static void printAL (ArrayList<Double> al) //вывод списка
    {
        for (Double element : al)
        {
            System.out.print(element + " ");
        }
        System.out.println(" ");


    }

   public static  void printA(double[][] al)  //вывод double матрицы
    {
        for (int i = 0; i<al.length; i++) {
            for (int k = 0; k<al[i].length;k++){
                System.out.print(al[i][k]+ "    ");
            }
            System.out.println();
        }
    }

    public static void printA(double[] al)  //вывод double массива
    {

        for (double elem : al) {

            System.out.print(elem + "    ");

            System.out.println();
        }
    }


    static boolean checkEps(Complex[] a1, Complex[] a2,Complex Eps)//проверка точности
    {
        boolean flag=false;
        Complex temp = new Complex(0,0);

        for (int i=0; i < a1.length; i++ ){

            temp.add(  a1[i].abs().minus(a2[i].abs()) ) ;



        }


         //System.out.println("погрешность = " + temp);

        if (temp.mod() <= Eps.mod()) flag=true; // если точность удовлетворяет условию то true


        return flag;
    }


}



