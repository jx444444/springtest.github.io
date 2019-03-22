package com.c.pr1;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Coin coin_10 = new Coin(10,2);
		Coin coin_5 = new Coin(5,3);
		Coin coin_1 = new Coin(1,5);
		List<Coin> coins = new ArrayList<Coin>();
		coins.add(coin_10);
		coins.add(coin_5);
		coins.add(coin_1);
		CoinExchanger coine = new CoinExchanger(20, coins);  
		
		//System.out.println(coine.getMoney());
		//System.out.println(coine.getCoins());
		coine.ExchangeMethod();
	}

}
