package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Main {

	static Random rand = new Random();

	public static void main(String[] args) {
		Main m = new Main();
		System.out.println(m.randName(500));
	}

	public String randName(int length) {
		String name = "";
		System.out.println(getHashes());

		return name;
	}

	String[][] getLetterList() {
		// Reading the file
		List<String> records = new ArrayList<String>();
		String filename = "src/main/input.txt";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = reader.readLine()) != null) {
				records.add(line);
			}
			reader.close();
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
			return null;
		}

		// Converting the strings into seperated values
		String[][] probabilities = new String[records.size()][2];
		for (int i = 0; i < records.size(); i++) {
			for (int i2 = 0; i2 < records.get(i).length() - 2; i2++) {
				System.out.println(records.get(i).substring(i2, i2 + 3));
			}
		}
		return probabilities;
	}

	HashMap<String, Integer> getHashes() {
		// Reading the file
		List<String> records = new ArrayList<String>();
		String filename = "src/main/input.txt";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = reader.readLine()) != null) {
				records.add(line);
			}
			reader.close();
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
			return null;
		}

		// Converting the strings into seperated values
		HashMap<String, Integer> probabilities = new HashMap<String, Integer>();
		for (int i = 0; i < records.size(); i++) {
			for (int i2 = 0; i2 < records.get(i).length() - 2; i2++) {
				String sub = records.get(i).substring(i2, i2 + 3);
				if(probabilities.containsKey(sub)){
					probabilities.replace(sub, probabilities.get(sub)+1);
				} else {
					probabilities.put(sub, 1);
				}
			}
		}
		return probabilities;
	}

	String getProbability(String[] lettersList, double[] lettersProbability) {
		// 1/rank = proabability
		// Imagine chosing a random angle on a pie chart with different
		// segmentsItem[] items = ...;

		// Compute the total weight of all items together
		double totalWeight = 0.0d;
		for (int i = 0; i < lettersProbability.length; i++) {
			totalWeight += lettersProbability[i];
		}
		// Now choose a random item
		int randomIndex = -1;
		double random = Math.random() * totalWeight;
		for (int i = 0; i < lettersList.length; ++i) {
			random -= lettersProbability[i];
			if (random <= 0.0d) {
				randomIndex = i;
				break;
			}
		}
		return lettersList[randomIndex];
	}
}