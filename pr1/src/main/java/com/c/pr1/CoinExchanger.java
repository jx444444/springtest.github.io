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
            
            if(result == money)//계산중에 결과가 맞아떨어질 경우
            {
                if((temp_coins.get(index)).getNum() > 0)//동전 갯수가 하나라도 남아 있을경우
                {
                    if(index != 0 && result > 0)//계산의 첫 부분만 빼고 맨 앞에는 +를 붙인다.
                        result_message += " + ";
                    result_message = (new StringBuilder(String.valueOf(result_message))).append("(").append((temp_coins.get(index)).getAmount()).append(" * ").append(j + 1).append(")").toString();
                    //그리고 계산식을 붙인다.
                }
                if(result == result_temp + (temp_coins.get(index)).getAmount() * (temp_coins.get(index)).getNum())
                {//계산 결과가 예상 계산 결과랑 맞아 떨어진다면(게산 전 결과값+동전의금액*동전갯수)
                    (temp_coins.get(index)).setNumMinus(1);
                  //그 동안 해당 동전의 갯수를 줄이며 다음 계산으로 직행한다.
                } else
                {
                    int amount = (temp_coins.get(index)).getAmount();
                    int num = (temp_coins.get(index)).getNum();
                    int minus = ((result_temp + amount * num) - result) / amount;
                    (temp_coins.get(index)).setNumMinus(minus + 1);
                  //지폐 양을 안 넘을 만하게 해당 동전의 갯수를 줄인다.
                }
                break;
            }
            
          //그렇지 않고 다음 계산 때 지폐 양을 넘어설 경우
            while((temp_coins.get(index)).getAmount() > money && (temp_coins.get(index)).getNum() > 0) 
                if(result == result_temp + (temp_coins.get(index)).getAmount() * (temp_coins.get(index)).getNum())
                {//계산 결과가 예상 계산 결과랑 맞아 떨어진다면(게산 전 결과값+동전의금액*동전갯수)
                    (temp_coins.get(index)).setNumMinus(1);
                    //그 동안 해당 동전의 갯수를 줄이며 다음 계산으로 직행한다.
                } else
                {
                    int amount = (temp_coins.get(index)).getAmount();
                    int num = (temp_coins.get(index)).getNum();
                    int minus = ((result_temp + amount * num) - result) / amount;
                    (temp_coins.get(index)).setNumMinus(minus + 1);
                    //지폐 양을 안 넘을 만하게 해당 동전의 갯수를 줄인다.
                }
        }

        if(result == money || temp_coins.get(index).getAmount() > money)//계산이 맞거나 넘어버리면 그대로 리턴한다.
        {
            resultList.add(Integer.valueOf(result));
            resultList.add(result_message);
            return resultList;
        }
        if(temp_coins.get(index).getNum() > 0)//해당 동전이 하나라도 남아있다면?
        {
            if(index != 0 && result > 0)//계산의 첫 부분만 빼고 맨 앞에는 +를 붙인다.
            	result_message += " + ";
            result_message = (new StringBuilder(String.valueOf(result_message))).append("(").append((temp_coins.get(index)).getAmount()).append(" * ").append((temp_coins.get(index)).getNum()).append(")").toString();
           //그리고 계산식을 붙인다.
        }
        if(index != temp_coins.size() - 1)//계산하는 데 쓸 동전 가짓수가 아직 남아있다면?
        {
            List resultList_temp = ExchangeMethod_recursive(result, index + 1, temp_coins);
            //그 다음의 동전을 사용하여 다시 재귀함수를 호출.
            result = ((Integer)resultList_temp.get(0)).intValue();
            //result에 얻은 계산 결과값을 축적한다.
            result_message = (new StringBuilder(String.valueOf(result_message))).append((String)resultList_temp.get(1)).toString();
        } else//계산하는 데 쓸 동전 가짓수가 끝에 다다른다면?
        {
            (temp_coins.get(index)).setNumMinus(1);//제일 끝 동전의 갯수를 하나 줄이며 다음 계산으로 직행.
        }
        resultList.add(Integer.valueOf(result));
        resultList.add(result_message);
        return resultList;
    }

    public List<Coin> Temp_Coins(int start, int end)
    {
        List<Coin> temp_coins = new ArrayList();
        for(int i = start; i < end; i++)
        {
            Coin temp = coins.get(i);
            temp_coins.add(new Coin(temp.getAmount(), temp.getNum()));
        }
        //생성할 때 받은 배열을 다시 복사.

        return temp_coins;
    }

    public List<Coin> ExchangeMethod()
    {
        List<Coin> temp_coins = Temp_Coins(0, coins.size());
        int way = 0;//게산방법 총 갯수
        int start_size = 1;//제일 앞에 있는 동전이 사라진 횟수+1
        String result_message = "";//총 계산법
        while(temp_coins.size() > 0) //계산에 쓸 동전들이 temp에서 완전히 사라질 때까지 계산한다.
        {
           // System.out.println(temp_coins);
            List resultList = ExchangeMethod_recursive(0, 0, temp_coins);
            if(((Integer)resultList.get(0)).intValue() == money)//계산이 맞아 떨어진다면 result_message에 축적시킴
            {
                way++;
                result_message = (new StringBuilder(String.valueOf(result_message))).append(money).append(" = ").append(resultList.get(1)).append("<br>").toString();
            }
            for(int i = 1; i < temp_coins.size(); i++)//
                if((temp_coins.get(temp_coins.size() - i)).getNum() < 0)
                	//제일 끝에 있는 동전의 갯수가 없을 경우 그 동전의 갯수를 복구하여, 
                	//그 앞에 있는 동전의 갯수를 하나 줄인다.
                {
                    (temp_coins.get(temp_coins.size() - i)).setNum((coins.get((temp_coins.size() - i - 1) + start_size)).getNum());
                    (temp_coins.get(temp_coins.size() - 1 - i)).setNumMinus(1);
                }

            if((temp_coins.get(0)).getNum() <= 0)//제일 앞에 있는 동전의 갯수가 없을 경우 그 동전을 삭제한다. 
            {
                temp_coins.remove(0);
                start_size++;
            }
        }
        List returnArray = new ArrayList();
        returnArray.add((new StringBuilder("\uCD1D ")).append(way).append("\uAC00\uC9C0").toString());
        returnArray.add(result_message);
        return returnArray;
        //깔끔함을 위해 배열[총 ~가지, 계산 결과]로 리턴함.
    }

    private int money;
    private List<Coin> coins;
}
