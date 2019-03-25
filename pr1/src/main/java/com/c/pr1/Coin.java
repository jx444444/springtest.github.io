// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Coin.java

package com.c.pr1;


public class Coin
{

    public Coin(int amount, int num)
    {
        this.amount = amount;
        this.num = num;
    }

    public int getAmount()
    {
        return amount;
    }

    public int getNum()
    {
        return num;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

    public void setNumMinus(int num)
    {
        this.num -= num;
    }

    public String toString()
    {
        return (new StringBuilder("Coin [amount=")).append(amount).append(", num=").append(num).append("]").toString();
    }

    private int amount;
    private int num;
}
