package com.likg.life;

/**
 * 消费明细
 */
public class Item {
	private String name;
	private double money;
	
	public Item() {
		super();
	}
	
	public Item(String name, double money) {
		super();
		this.name = name;
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

}
