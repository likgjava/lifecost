package com.likg.life;

// 专项消费
// 例如：专项:幼儿园:体验费		7500
public class SpecialItem {
    private String type; // 类型
    private String date; // 日期
    private String name; // 费用名称
    private double money; // 金额

    public SpecialItem() {
    }

    public SpecialItem(String type, String date, String name, double money) {
        this.type = type;
        this.date = date;
        this.name = name;
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
