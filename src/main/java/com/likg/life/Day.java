package com.likg.life;

import java.util.ArrayList;
import java.util.List;

/**
 * 日消费
 */
public class Day {

	private int name;
	
	private double mony;
	
	private List<Item> itemList = new ArrayList<Item>();

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public double getMony() {
		return mony;
	}

	public void setMony(double mony) {
		this.mony = mony;
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
		this.mony = this.mony + item.getMoney();
		
		this.itemList.add(item);
	}
	
}
