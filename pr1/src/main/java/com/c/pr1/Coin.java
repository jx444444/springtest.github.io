package com.c.pr1;

public class Coin{
	private int amount; //동전금액
	private int num; //갯수
	
	public Coin(int amount, int num) {
		super();
		this.amount = amount;
		this.num = num;
	}
	public int getAmount() {
		return amount;
	}
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	public void setNumMinus(int num) {
		this.num -= num;
	}
	
	@Override
	public String toString() {
		return "Coin [amount=" + amount + ", num=" + num + "]";
	}
	
	
	
	
	
}
