package com.c.pr1;

import java.util.ArrayList;
import java.util.List;

public class CoinExchanger {
	private int money;
	private List<Coin> coins;

	public CoinExchanger(int money, List<Coin> coins) {
		super();
		this.money = money;
		this.coins = coins;
	}
	public CoinExchanger() {
		super();
	}
	public int getMoney() {
		return money;
	}
	public List<Coin> getCoins() {
		return coins;
	}
	
	
	public List<Object> ExchangeMethod_recursive(int result, int index, List<Coin> temp_coins) {
		String result_message = "";
		List<Object> resultList = new ArrayList<Object>();
		int result_temp = result;
		for (int j = 0 ; j < temp_coins.get(index).getNum() ; j++) {
			result += temp_coins.get(index).getAmount();
			
			if (result == money) {//계산이 완료되었을 경우
				if (index != 0) {
					result_message +=" + ";
				}
				result_message += "("+temp_coins.get(index).getAmount()+" * "+(j+1)+")";
				if (result == result_temp+(temp_coins.get(index).getAmount()*temp_coins.get(index).getNum())) {
					temp_coins.get(index).setNumMinus(1);
				}
				else {
					int amount = temp_coins.get(index).getAmount();
					int num = temp_coins.get(index).getNum();
					int minus = ((result_temp+(amount*num))-result)/amount;
					temp_coins.get(index).setNumMinus(minus+1);
				}
				break;
			}
		}
		if (result == money) {//계산이 완료되었을 경우
			resultList.add(result);
			resultList.add(result_message);
			return resultList;
		}
		if (index != 0) {
			result_message +=" + ";
		}
		result_message += "("+temp_coins.get(index).getAmount()+" * "+temp_coins.get(index).getNum()+")";
		if (index != temp_coins.size()-1) {
			List<Object> resultList_temp = ExchangeMethod_recursive(result, index+1, temp_coins);
			result = (int)resultList_temp.get(0);
			result_message += (String)resultList_temp.get(1);
		}
		else {
			temp_coins.get(index).setNumMinus(1);
		}
		resultList.add(result);
		resultList.add(result_message);
		return resultList;
		
	}
	
	public List<Coin> Temp_Coins(int start, int end) {
		List<Coin> temp_coins = new ArrayList<Coin>();
		for (int i=start ; i < end ; i+=1) {
			Coin temp = coins.get(i);
			temp_coins.add(new Coin(temp.getAmount(),temp.getNum()));
		}
		return temp_coins;
	}
	

	public void ExchangeMethod() {
		List<Coin> temp_coins = Temp_Coins(0, coins.size());
		//동전 가지수 배열 임시 복사 
		int way = 0;
		int start_size = 1;
		
		//for (int q = 0 ; q < 80 ; q+=1) {
		for (;temp_coins.size()>0;) {
			
			//System.out.println(temp_coins);
			List<Object> resultList = ExchangeMethod_recursive(0, 0, temp_coins);
			if ((int)resultList.get(0) >= money) {
				way += 1;
				System.out.println(money+" = "+resultList.get(1));
			}
			
			for (int i = 1 ; i < temp_coins.size() ; i+=1) 
			if (temp_coins.get(temp_coins.size()-i).getNum()<=0) {//끝에 있는 동전의 갯수가 0이라면?
				temp_coins.get(temp_coins.size()-i).setNum(coins.get(temp_coins.size()-i-1+start_size).getNum());
				temp_coins.get(temp_coins.size()-1-i).setNumMinus(1);
			}
			
			if (temp_coins.get(0).getNum()<=0) {//끝에 있는 동전의 갯수가 0이라면?
				if(temp_coins.size()>=1) {
					temp_coins.remove(0);
					start_size += 1;
				}
			}
			
		}
		
		
		
		System.out.println("총 "+way+"가지");
		
	}
	
	

}
