package com.company;

/**
 * Created by Ilya on 23.02.2017.
 */
public interface Function {

    Complex functionOne (Complex x,Complex y,Complex z);
    Complex functionTwo (Complex x,Complex y,Complex z);

    Complex dfZfunctionOne (Complex x,Complex y,Complex z);
    Complex dfZfunctionTwo (Complex x,Complex y ,Complex z);

    Complex dfYfunctionOne (Complex x,Complex y , Complex z);
    Complex dfYfunctionTwo (Complex x,Complex y, Complex z);

}
