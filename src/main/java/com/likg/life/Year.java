package com.likg.life;

import java.util.ArrayList;
import java.util.List;

/**
 * 年消费
 */
public class Year {

	private int name;
	
	private double mony;
	
	private List<Month> monthList = new ArrayList<Month>();

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

	public List<Month> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<Month> monthList) {
		this.monthList = monthList;
	}

	/**
	 * 追加条目
	 * @param m 月
	 * @param d 日
	 * @param item 条目
	 */
	public void appendItem(int m, int d, Item item) {
		//追加金额
		this.mony = this.mony + item.getMoney();
		
		//根据月数字获取月对象
		Month nowMonth = null;
		for(Month month : this.monthList) {
			if(month.getName() == m) {
				nowMonth = month;
				break;
			}
		}
		
		//还不存在
		if(nowMonth == null) {
			nowMonth = new Month();
			nowMonth.setName(m);
			this.monthList.add(nowMonth);
		}
		
		//追加条目
		nowMonth.appendItem(d, item);
	}
	
	
}
