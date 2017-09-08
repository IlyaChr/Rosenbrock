package com.company;

import java.util.ArrayList;

/**
 * Двухстадийный метод Розенброка
 */

public class Rosenbrock  {

    private static final Complex alpha_one = new Complex(0.09705048233513194,0.1441824711215367);

    private static final Complex alpha_two = new Complex(0.1886638033791538,0.06177441689689114);

    private static final Complex betta_one = new Complex(0.04833419895509594,-0.3205959705202483);

    private static final Complex betta_two = new Complex(0.9516658010449041,-1.696774337833587);

    private static final Complex c_two_one = new Complex(0.1730887968652113,-0.1694095699539014);

    private static final Complex a_two_one = new Complex(0.5359744564304916,-0.9665922748484184);

    private static final int QUA = 2; //количество диф уравнений

    private static final int DegOfExt = 15; //степень экстраполяции


    private static double h;   //шаг

    private static Function fun;

    private static double a,b; // приделы

    private int kol_steps;

    private double X; //X (изменяется на велечину шага)

    double[] Y; // вектор содержащий y и z





    Rosenbrock(Function fun,double a,double b,int kol_steps){

        this.kol_steps = kol_steps;
        this.fun = fun;
        this.a = a;
        this.b = b;
        double temp = (Math.abs(this.a-this.b) )/kol_steps;
        this.h = temp;


        X = a; //X (изменяется на велечину шага)
        Y = new double[QUA]; // вектор содержащий y и z


        /**
         * начальные условия
         */

        /*для y=sin(x) */
       // Y[0] = 0;      //Y
        //Y[1] = 1;      //Z


        /*для y=cos(x) */
        Y[0] = 1;      //Y
        Y[1] = 0;      //Z

        /*для y=x*cos(x) */
       // Y[0] = 0;      //Y
        //Y[1] = 1;      //Z


        /*для y=3*arctg(x)*sin(x) */
       // Y[0] = 0;      //Y
       // Y[1] = 0;      //Z

    }


    public void Method(){

        ArrayList<Double> Result_X = new ArrayList<Double>(); // контенеры для x,y,z нужны для вывода в фаил
        ArrayList<Double> Result_Y = new ArrayList<Double>();
        ArrayList<Double> Result_Z = new ArrayList<Double>();

        double[] R = new double[QUA]; // оценивает главный член погрешности
        double[] Y_temp = new double[QUA]; // вспомогательная переменная содержит временно ответ


        Result_X.add( X ); //добавление начальных значений в контейнер
        Result_Y.add( Y[0]);
        Result_Z.add( Y[1]);

        int percent = Math.round(kol_steps/100); // для вывода процентов в консоль
        int percentStep = 0;



        /**
         * основной блок программы
         */




        for (int i = 0 ; i<kol_steps ; i++ ) {


                X+=h; // добавляем шаг

                try {

                    Y = rechardson(Y,X,DegOfExt);



                    //System.out.println(Y[0]);


                    //Y = coreMethod(X,Y,fun,h);

                    //R = Matrix.divOnNumber(Matrix.minus(Y_2h, Y), 127.0);
                    //Y = Matrix.plus(Y_2h,R);
                    //Matrix.printA(R);


                } catch (Exception e) {
                    e.printStackTrace();
                }


                Result_X.add(X);
                Result_Y.add(Y[0]);
                Result_Z.add(Y[1]);


               // if   ( ( i % percent  ) == 0)  {
                 //   System.out.println(++percentStep +" % ");
               //}


        }


        /**
         * вывод в фаил
         */

        //Matrix.printAL(Result_X) ;
        //Matrix.printInFile(Result_X,"X","X_out");
        //Matrix.printInFile(Result_Y,"Y","Y_out");

        Matrix.printInFile(Result_X,Result_Y,"X and Y","data");



    }




    /**
     Экстраполяция Ричардсона
     */

    /* минимальная степень экстраполяции 2 */
    private static double[] rechardson( double[] Y , double  X , int N  ) throws Exception {


        if (N<2) throw new Exception();


        double[][] Richardson = new double[N][N];

        double[] Richardson_D_SysOne = new double[N];

        double[] Richardson_D_SysTwo = new double[N];


        double[] RichTemp = new double[QUA];

        double[] Answere = new double[QUA];




        for (int j = 0; j<Richardson.length ; j++)
        {
            for (int k = 0; k<Richardson[j].length ; k++)
            {

                if (k==0) Richardson[j][k] = 1; else {

                    Richardson[j][k] = Math.pow(h / (j + 1), 7 + (k - 1));
                }

            }

        }



        for ( int i = 0 ; i<Richardson_D_SysOne.length ; i++){

            RichTemp = getRichardson(Y,X,i+1,fun,h);

            Richardson_D_SysOne[i] = RichTemp[0];

            Richardson_D_SysTwo[i] = RichTemp[1];

            //System.out.println(RichTemp[0]);
        }



        //System.out.println();


            RichTemp = Gauss.makeGauss(Richardson,Richardson_D_SysOne);

            Answere[0] = RichTemp[0];






            RichTemp = Gauss.makeGauss(Richardson,Richardson_D_SysTwo);

            Answere[1] = RichTemp[0];


        return Answere;
    }






    private static double[] coreMethod(double X,double Y[],Function fun,double h){



        Complex[][] sysOne = new Complex[QUA][QUA]; // первая система по k1
        Complex[][] sysTwo = new Complex[QUA][QUA]; // вторая система по k2
        Complex[] D_1 = new Complex[QUA]; // свободный коэф
        Complex[] D_2 = new Complex[QUA]; // свободный коэф
        double[] Y_sysTwo = new double[QUA]; // точка для якобиана второй системы для первой не нужна т.к. там просто Y
        double[] D_sysTwo = new double[QUA]; // точка для свободный коэф
        Complex[] k1 = new Complex[QUA]; //вспомогательный вектор k1
        Complex[] k2 = new Complex[QUA]; //вспомогательный вектор k2


        try {

            sysOne = Matrix.minus(Matrix.identity(QUA), Matrix.multiplicationOnNumber(jacobi(fun, Y , X), alpha_one.asterisk(new Complex(h,0.0))));

            D_1 = Matrix.multiplicationOnNumber(getFunction(fun, Y , X) , h );

            //k1 = SimpleIter.makeIter( sysOne , D_1 );

            k1 = GaussComplex.makeGauss( sysOne,D_1);





            Y_sysTwo = Matrix.plus(Y, Matrix.multiplicationOnNumber ( Matrix.getReal ( Matrix.multiplicationOnNumber(k1,a_two_one) ) ,h )  );

            sysTwo = Matrix.minus(Matrix.identity(QUA), Matrix.multiplicationOnNumber(jacobi(fun, Y_sysTwo ,X), alpha_two.asterisk(new Complex(h,0.0))));

            D_sysTwo = Matrix.plus(Y, Matrix.multiplicationOnNumber ( Matrix.getReal ( Matrix.multiplicationOnNumber(k1,c_two_one) ) ,h )  );

            D_2 = Matrix.multiplicationOnNumber(getFunction(fun, D_sysTwo ,X) , h);

            //k2 = SimpleIter.makeIter( sysTwo , D_2 );

            k2 = GaussComplex.makeGauss( sysTwo , D_2);


            /**
             * рукурентная формула
             */

            Y = Matrix.plus(Y , Matrix.getReal(  Matrix.plus( Matrix.multiplicationOnNumber(k1,betta_one), Matrix.multiplicationOnNumber(k2,betta_two) )  )  );




        } catch (Exception e) {
            e.printStackTrace();
        }



        return Y;
    }



    private static Complex[][] jacobi( Function fun ,double[] Y,double X){

        Complex[][] jacodi = new Complex[QUA][QUA];

        for (int i =0 ; i< jacodi.length; i++){
            for (int j = 0; j< jacodi[0].length ; j++){

                if (i==0 && j==0) jacodi[i][j] = fun.dfYfunctionOne(new Complex(X,0.0),new Complex(Y[0],0.0),new Complex(Y[1],0.0));

                if (i==0 && j==1) jacodi[i][j] = fun.dfZfunctionOne(new Complex(X,0.0),new Complex(Y[0],0.0),new Complex(Y[1],0.0));

                if (i==1 && j==0) jacodi[i][j] = fun.dfYfunctionTwo(new Complex(X,0.0),new Complex(Y[0],0.0),new Complex(Y[1],0.0));

                if (i==1 && j==1) jacodi[i][j] = fun.dfZfunctionTwo(new Complex(X,0.0),new Complex(Y[0],0.0),new Complex(Y[1],0.0));

            }


        }

        return jacodi;

    }


    private static Complex[] getFunction( Function fun , double[] Y ,double X){

        Complex[] Function = new Complex[QUA];

        for (int i =0 ; i< Function.length; i++) {

            if (i==0) Function[i] = fun.functionOne(new Complex(X,0.0),new Complex(Y[0],0.0),new Complex(Y[1],0.0)) ;

            if (i==1) Function[i] = fun.functionTwo(new Complex(X,0.0),new Complex(Y[0],0.0),new Complex(Y[1],0.0))  ;


        }

        return Function;
        }








    private static boolean checkEps(Complex[] R,Complex Eps)//проверка точности
    {
        boolean flag=false;

        Complex temp= new Complex(0,0);

        for (int i=0; i < R.length; i++ ){

            temp.add( R[i] ) ;

        }

        temp.slash(new Complex(R.length,R.length));


        if (temp.mod() <= Eps.mod()) flag=true; // если точность удовлетворяет условию то true


        return flag;
    }



    /*возвращает новую итерацию Розенброка с необходимым кол-вом разбиений */
    private static double[] getRichardson(double[] Y_Temp , double  X ,int parts, Function fun , double h ){




        double[] Y_Richardson = new double[Y_Temp.length];

        double X_Temp = X-h;

        double h_Temp = h/parts;



        X_Temp += h_Temp;

        Y_Richardson = coreMethod(X_Temp,Y_Temp,fun,h_Temp);


        for (int i=0 ; i<parts-1 ; i++){

            X_Temp += h_Temp;

            Y_Richardson = coreMethod(X_Temp,Y_Richardson,fun,h_Temp);


        }


        return Y_Richardson;
    }






}
