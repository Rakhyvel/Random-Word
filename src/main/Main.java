package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
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
		double r;
		String[][] list = getLetterList();
		String[] morphemes = splitIntoLetters(list);
		double[] frequencies = splitIntoFrequencies(list);
		
		for(int i = 0; i < length; i++) {
			name += getProbability(morphemes, frequencies);
		}
		
		return name;
	}

	String[][] getLetterList() {
		// Reading the file
		List<String> records = new ArrayList<String>();
		String filename = "src/main/probabilities.csv";
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
		String[][] probabilities = new String[records.size()][3];
		for(int i = 0; i < records.size(); i++) {
			int lastSplit = -1;
			int index = 0;
			for(int i2 = 0; i2 < records.get(i).length();i2++) {
				if(records.get(i).charAt(i2) == ',') {
					probabilities[i][index] = records.get(i).substring(lastSplit+1,i2);
					lastSplit = i2;
					index+=1;
				}
			}
		}
		return probabilities;
	}
	
	String[] splitIntoLetters(String[][] list) {
		String[] array = new String[list.length];
		for(int i = 0; i < list.length; i++) {
			array[i] = list[i][0].toLowerCase();
		}
		return array;
	}
	
	double[] splitIntoFrequencies(String[][] list) {
		double[] array = new double[list.length];
		for(int i = 0; i < list.length; i++) {
			array[i] = Double.parseDouble(list[i][1]);
		}
		return array;
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
