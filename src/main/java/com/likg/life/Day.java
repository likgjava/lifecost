package com.likg.life;

import java.util.ArrayList;
import java.util.List;

/**
 * 日消费
 */
public class Day {

	private int name;
	
	private double money;
	
	private List<Item> itemList = new ArrayList<Item>();

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	/**
	 * 追加条目
	 * @param item 条目
	 */
	public void appendItem(Item item) {
		//追加金额
		this.money = this.money + item.getMoney();
		
		this.itemList.add(item);
	}
	
}
