package section8;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import acm.program.ConsoleProgram;

public class DataStructures extends ConsoleProgram {
	
    /**保存起始点和能够到达的目的地*/
	private Map<String, ArrayList<String>> routes = new HashMap<String, ArrayList<String>>();

	/**保存用户选择的地点*/
	private ArrayList<String> selectedPlaces = new ArrayList<String>();

	/**
	 * 首先读取文件保存航班信息
	 * 展示欢迎文字
	 * 根据用户选择的起始点展示对应的目的地
	 * 展示最终用户选择的结果
	 */
	public void run() {
		readFile();
		dispalyWelcomeMsg();
		selectPlace();
		displayResult();
	}
	
	/**
	 * 读取flights.txt文件的航班信息
	 * 根据“->”分隔每一行的路线，将起始点保存进map中的key中，目的地保存进map的value中，value为ArrayList
	 */
	public void readFile() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("flights.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			while (true) {
				String line = reader.readLine();
				if (line == null)
					break;
				if (line.trim().isEmpty()) //需要去掉空格之后再判断是否为空
					continue;
				String departure = line.split("->")[0].trim();
				String destination = line.split("->")[1].trim();
				if (routes.containsKey(departure)) {
					routes.get(departure).add(destination);
				} else {
					ArrayList<String> destinations = new ArrayList<String>();
					destinations.add(destination);
					routes.put(departure, destinations);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 展示欢迎文字，将能够选择的起点展示出来，即map中的key值
	 */
	public void dispalyWelcomeMsg() {
		println("Welcome to Flight Planner!");
		println("Here's a list of all the cities in our database:");
		for (String departure : routes.keySet()) {
			println(departure);
		}
		println("Let's plan a round-trip route!");
	}
	
	/**
	 * 由用户输入起点，根据起点展示能够到达的目的地
	 * 如果输入的地点不在目的地列表中，提示，然后需要重新输入目的地
	 * 当起点与终点相同时结束
	 */
	public void selectPlace() {
		String startCity = readLine("Enter the starting city: ");
		selectedPlaces.add(startCity);
		while (true) {
			showDestination(startCity);
			String destination = readLine("Where do you want to go from " + startCity + "?");
			if (routes.get(startCity).contains(destination)) {
				startCity = destination;
				selectedPlaces.add(startCity);
			} else {
				println("You can't get to that city by a direct flight.");
			}
			if (selectedPlaces.get(0).equals(startCity)) //当回到最开始的地点时退出循环
				break;
		}
	}
	
	/**
	 * 将起点作为map中的key值，展示value中的目的地列表
	 */
	public void showDestination(String departure) {
		println("From " + departure + " you can fly directly to: ");
		Iterator<String> it = routes.get(departure).iterator();
		while (it.hasNext()) {
			println(it.next());
		}
	}
	
	/**
	 * 展示最终结果，使用StringBuilder拼接字符串性能更好
	 */
	public void displayResult() {
		println("The route you've chosen is: ");
		StringBuilder places = new StringBuilder();
		for (int i = 0; i < selectedPlaces.size(); i++) {
			places.append(selectedPlaces.get(i));
			if (i != selectedPlaces.size() - 1) { //最后一个地点后面不再需要“->”
				places.append(" -> ");
			}
		}
		println(places.toString());
	}
}
