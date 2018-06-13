package com.stores.starter;

import java.io.BufferedReader;
import java.io.FileReader;

public class CounterStarter {

	public static void main(String ...args) throws Exception{
		System.out.println("Hello World");
		String line;
		
		BufferedReader inputReader = new BufferedReader(new FileReader("/Users/aprabhu/Documents/workspace/GroceryStoreCounters/grocery-store-counters/src/main/resources/input/input.txt"));
		
		while ((line = inputReader.readLine()) != null){
			System.out.println(line);
		}
	}
	
}
