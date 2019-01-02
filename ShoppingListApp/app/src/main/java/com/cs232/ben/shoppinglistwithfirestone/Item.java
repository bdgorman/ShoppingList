package com.cs232.ben.shoppinglistwithfirestone;

public class Item {
	private String name;
	private String price;
	private int priority;

	public Item() {
		//empty constructor needed
	}

	public Item(String name, String price, int priority) {
		this.name = name;
		this.price = price;
		this.priority = priority;
	}

	public String getName() {
		return name;
	}

	public String getPrice() {
		return price;
	}

	public int getPriority() {
		return priority;
	}
}