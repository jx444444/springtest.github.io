// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Main.java

package com.c.pr1;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.c.pr1:
//            Coin, CoinExchanger

public class Main
{

    public Main()
    {
    }

    public static void main(String args[])
    {
        Coin coin_10000 = new Coin(10, 2);
        Coin coin_7500 = new Coin(5, 3);
        Coin coin_5000 = new Coin(1, 5);
        List coins = new ArrayList();
        coins.add(coin_10000);
        coins.add(coin_7500);
        coins.add(coin_5000);
        CoinExchanger coine = new CoinExchanger(20, coins);
        List resultmessageArray = coine.ExchangeMethod();
        resultmessageArray.set(1, ((String)resultmessageArray.get(1)).replaceAll("<br>", "\n"));
        System.out.println(resultmessageArray);
    }
}
