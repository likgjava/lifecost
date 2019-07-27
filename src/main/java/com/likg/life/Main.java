package com.likg.life;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 主类
 */
public class Main {
	
	/** 源文件路径 */
	//public static final String SOURCE_FILE_PATH = "C:/Users/Administrator/Desktop/消费记录.txt";
	public static final String SOURCE_FILE_PATH = "D:\\likg\\likg_java/消费记录.txt";
	
	/** 导出HTML文件路径 */
	//public static final String EXPORT_HTML_PATH = "C:/Users/Administrator/Desktop/消费记录.html";
	public static final String EXPORT_HTML_PATH = "D:\\likg\\likg_java/消费记录.html";
	
	/** 存放所有消费记录 */
	private List<Year> yearList = new ArrayList<Year>();
	
	/**
	 * 初始化，获取所有消费记录数据并组装
	 */
	private void init() {
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(SOURCE_FILE_PATH), "GBK"));
			
			String nowDay = "";
			String line = br.readLine();
			while(line != null) {
				//日期一行
				if(line.matches("\\d{4}\\.\\d{1,2}\\.\\d{1,2}\\s*")) {
					nowDay = line.trim();
				}
				//消费类型一行
				else if(line.matches("消费类型.*")) {
				}
				//共计一行
				else if(line.contains("共计")) {
				}
				//月总计一行
				else if(line.contains("月总计")) {
				}
				//内容一行
				else if(line.matches(".*\\s*\\d*\\.?\\d{1,2}\\s*")) {
					StringTokenizer nameValue = new StringTokenizer(line.trim());
					String name = nameValue.nextToken();
					String value = nameValue.nextToken();
					
					Item item = new Item(name, Double.parseDouble(value));
					this.appendItem(nowDay, item);
				}
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 追加条目
	 * @param day 日期
	 * @param item 条目
	 */
	private void appendItem(String day, Item item) {
		//获取年、月、日
		String[] ymd = day.split("\\.");
		int y = Integer.parseInt(ymd[0]);
		int m = Integer.parseInt(ymd[1]);
		int d = Integer.parseInt(ymd[2]);
		
		//根据年数字从容器中获取年对象
		Year nowYear = null;
		for(Year year : this.yearList) {
			if(year.getName() == y) {
				nowYear = year;
				break;
			}
		}
		
		//还不存在
		if(nowYear == null) {
			nowYear = new Year();
			nowYear.setName(y);
			this.yearList.add(nowYear);
		}
		
		//追加条目
		nowYear.appendItem(m, d, item);
		
	}
	
	/**
	 * 导出HTML
	 */
	public void exportToHtml(){
		StringBuilder html = new StringBuilder();
		html.append("<!DOCTYPE html>").append("\n");
		html.append("<html>").append("\n");
		html.append("<head>").append("\n");
		html.append("<meta charset='utf8'>").append("\n");
		html.append("<title>消费记录</title>").append("\n");
		html.append("<style>").append(this.getCss()).append("</style>").append("\n");
		html.append("</head>").append("\n");
		html.append("<body>").append("\n");
		html.append("<div class='container'>").append("\n");
		//月消费总计
		html.append("<table>");
		for(Year y : this.yearList) {
			for(Month m : y.getMonthList()) {
				html.append("<tr>");
				String ym = y.getName() + "." + m.getName();
				html.append("<td><a href='#").append(ym).append("'>").append(ym).append("</a></td>");
				html.append("<td align='right'>").append(m.getMoney()).append("</td>");
				html.append("</tr>");
			}
		}
		html.append("</table>").append("\n");
		
		//消费明细
		for(Year y : this.yearList) {
			for(Month m : y.getMonthList()) {
				html.append("<table>");
				html.append("<tr class='title'>");
				html.append("<td><a href='#'>↑</a></td>");
				String ym = y.getName() + "." + m.getName();
				html.append("<td id='").append(ym).append("'>").append(ym).append("</td>");
				html.append("<td align='right'>").append(m.getMoney()).append("</td>");
				html.append("</tr>");
				for(Day d : m.getDayList()) {
					html.append("<tr>");
					html.append("<td align='center'>").append(d.getName()).append("</td>");
					html.append("<td>");
					html.append("<ul>");
					for(Item i : d.getItemList()) {
						html.append("<li>");
						html.append("<span>").append(i.getName()).append("</span>");
						html.append("<span class='m'>").append(i.getMoney()).append("</span>");
						html.append("</li>");
					}
					html.append("</ul>");
					html.append("</td>");
					html.append("<td align='right'>").append(d.getMoney()).append("</td>");
					html.append("</tr>");
				}
				html.append("</table>").append("\n");
			}
		}
		html.append("</div>").append("\n");
		html.append("</body>").append("\n");
		html.append("</html>");
		
		//生产html文件
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(EXPORT_HTML_PATH), "utf-8"));
			bw.write(html.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {}
			}
		}
	}
	
	private String getCss(){
		StringBuffer css = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/main.css"), "GBK"));
			String line = br.readLine();
			while(line != null) {
				css.append(line).append("\n");
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {}
			}
		}
		return css.toString();
	}
	
	public static void main(String[] args) {
		System.out.println("开始导出文件...");
		
		Main main = new Main();
		main.init();
		main.exportToHtml();
		
		System.out.println("导出文件完毕！");
	}
	
}
