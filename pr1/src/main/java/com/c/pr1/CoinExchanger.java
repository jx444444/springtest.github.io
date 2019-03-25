// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CoinExchanger.java

package com.c.pr1;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.c.pr1:
//            Coin

public class CoinExchanger
{

    public CoinExchanger(int money, List coins)
    {
        this.money = money;
        this.coins = coins;
    }

    public CoinExchanger()
    {
    }

    public int getMoney()
    {
        return money;
    }

    public List<Coin> getCoins()
    {
        return coins;
    }

    public List ExchangeMethod_recursive(int result, int index, List<Coin> temp_coins)
    {
        String result_message = "";
        List resultList = new ArrayList();
        int result_temp = result;
        for(int j = 0; j < (temp_coins.get(index)).getNum(); j++)
        {
            result += (temp_coins.get(index)).getAmount();
            if(result == money)
            {
                if((temp_coins.get(index)).getNum() > 0)
                {
                    if(index != 0)
                        result_message = (new StringBuilder(String.valueOf(result_message))).append(" + ").toString();
                    result_message = (new StringBuilder(String.valueOf(result_message))).append("(").append((temp_coins.get(index)).getAmount()).append(" * ").append(j + 1).append(")").toString();
                }
                if(result == result_temp + (temp_coins.get(index)).getAmount() * (temp_coins.get(index)).getNum())
                {
                    (temp_coins.get(index)).setNumMinus(1);
                } else
                {
                    int amount = (temp_coins.get(index)).getAmount();
                    int num = (temp_coins.get(index)).getNum();
                    int minus = ((result_temp + amount * num) - result) / amount;
                    (temp_coins.get(index)).setNumMinus(minus + 1);
                }
                break;
            }
            while((temp_coins.get(index)).getAmount() > money && (temp_coins.get(index)).getNum() > 0) 
                if(result == result_temp + (temp_coins.get(index)).getAmount() * (temp_coins.get(index)).getNum())
                {
                    (temp_coins.get(index)).setNumMinus(1);
                } else
                {
                    int amount = (temp_coins.get(index)).getAmount();
                    int num = (temp_coins.get(index)).getNum();
                    int minus = ((result_temp + amount * num) - result) / amount;
                    (temp_coins.get(index)).setNumMinus(minus + 1);
                }
        }

        if(result == money)
        {
            resultList.add(Integer.valueOf(result));
            resultList.add(result_message);
            return resultList;
        }
        if(temp_coins.get(index).getNum() > 0)
        {
            if(index != 0)
                result_message = (new StringBuilder(String.valueOf(result_message))).append(" + ").toString();
            result_message = (new StringBuilder(String.valueOf(result_message))).append("(").append((temp_coins.get(index)).getAmount()).append(" * ").append((temp_coins.get(index)).getNum()).append(")").toString();
        }
        if(index != temp_coins.size() - 1)
        {
            List resultList_temp = ExchangeMethod_recursive(result, index + 1, temp_coins);
            result = ((Integer)resultList_temp.get(0)).intValue();
            result_message = (new StringBuilder(String.valueOf(result_message))).append((String)resultList_temp.get(1)).toString();
        } else
        {
            (temp_coins.get(index)).setNumMinus(1);
        }
        resultList.add(Integer.valueOf(result));
        resultList.add(result_message);
        return resultList;
    }

    public List<Coin> Temp_Coins(int start, int end)
    {
        List temp_coins = new ArrayList();
        for(int i = start; i < end; i++)
        {
            Coin temp = coins.get(i);
            temp_coins.add(new Coin(temp.getAmount(), temp.getNum()));
        }

        return temp_coins;
    }

    public List<Coin> ExchangeMethod()
    {
        List<Coin> temp_coins = Temp_Coins(0, coins.size());
        int way = 0;
        int start_size = 1;
        String result_message = "";
        while(temp_coins.size() > 0) 
        {
            System.out.println(temp_coins);
            List resultList = ExchangeMethod_recursive(0, 0, temp_coins);
            if(((Integer)resultList.get(0)).intValue() == money)
            {
                way++;
                result_message = (new StringBuilder(String.valueOf(result_message))).append(money).append(" = ").append(resultList.get(1)).append("<br>").toString();
            }
            for(int i = 1; i < temp_coins.size(); i++)
                if((temp_coins.get(temp_coins.size() - i)).getNum() < 0)
                {
                    (temp_coins.get(temp_coins.size() - i)).setNum((coins.get((temp_coins.size() - i - 1) + start_size)).getNum());
                    (temp_coins.get(temp_coins.size() - 1 - i)).setNumMinus(1);
                }

            if((temp_coins.get(0)).getNum() <= 0)
            {
                temp_coins.remove(0);
                start_size++;
            }
        }
        List returnArray = new ArrayList();
        returnArray.add((new StringBuilder("\uCD1D ")).append(way).append("\uAC00\uC9C0").toString());
        returnArray.add(result_message);
        return returnArray;
    }

    private int money;
    private List<Coin> coins;
}
