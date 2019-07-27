package com.likg.life;

import java.util.ArrayList;
import java.util.List;

/**
 * 月消费
 */
public class Month {

	private int name;
	
	private double mony;
	
	private List<Day> dayList = new ArrayList<Day>();

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

	public List<Day> getDayList() {
		return dayList;
	}

	public void setDayList(List<Day> dayList) {
		this.dayList = dayList;
	}

	/**
	 * 追加条目
	 * @param d 日
	 * @param item 条目
	 */
	public void appendItem(int d, Item item) {
		//追加金额
		this.mony = this.mony + item.getMoney();
		
		//根据日数字获取日对象
		Day nowDay = null;
		for(Day day : this.dayList) {
			if(day.getName() == d) {
				nowDay = day;
				break;
			}
		}
		
		//还不存在
		if(nowDay == null) {
			nowDay = new Day();
			nowDay.setName(d);
			this.dayList.add(nowDay);
		}
		
		//追加条目
		nowDay.appendItem(item);
	}
	
}
