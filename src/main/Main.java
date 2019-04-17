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
		System.out.println(m.randName(10,10));
	}

	public String randName(int length, int portionSize) {
		HashMap<String, Double> morphemes = getHashes(portionSize);
		List<String> keysAsArray = new ArrayList<String>(morphemes.keySet());
		String portion = keysAsArray.get(rand.nextInt(keysAsArray.size())).substring(0,portionSize-1);
		String name = portion;
		while(name.length() < length) {
			String randPortion = spitOutPossibilities(morphemes, portion, portionSize);
			if(randPortion == null)
				break;
			if(randPortion != "") {
				name += randPortion.charAt(portionSize-1);
				portion = randPortion.substring(1,portionSize);
			}
		}
		return name.substring(0,1).toUpperCase() + name.substring(1,name.length());
	}

	HashMap<String, Double> getHashes(int portionSize) {
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
		HashMap<String, Double> probabilities = new HashMap<String, Double>();
		for (int i = 0; i < records.size(); i++) {
			for (int i2 = 0; i2 < records.get(i).length() - portionSize + 1; i2++) {
				String sub = records.get(i).substring(i2, i2 + portionSize);
				if(probabilities.containsKey(sub)){
					probabilities.replace(sub, probabilities.get(sub)+1);
				} else {
					probabilities.put(sub, 1.0);
				}
			}
		}
		return probabilities;
	}
	
	String spitOutPossibilities(HashMap<String, Double> probabilities, String target, int portionSize) {
		ArrayList<String> lettersList = new ArrayList<String>();
		ArrayList<Double> lettersProbability = new ArrayList<Double>();
		for(String key : probabilities.keySet()) {
			if(key.substring(0, portionSize - 1).equals(target)) {
				lettersList.add(key);
				lettersProbability.add(probabilities.get(key));
			}
		}
		return getProbability(lettersList, lettersProbability);
	}

	String getProbability(ArrayList<String> lettersList,ArrayList<Double> lettersProbability) {
		// 1/rank = proabability
		// Imagine chosing a random angle on a pie chart with different
		// segmentsItem[] items = ...;

		// Compute the total weight of all items together
		double totalWeight = 0.0d;
		for (int i = 0; i < lettersProbability.size(); i++) {
			totalWeight += lettersProbability.get(i);
		}
		// Now choose a random item
		int randomIndex = -1;
		double random = Math.random() * totalWeight;
		for (int i = 0; i < lettersList.size(); ++i) {
			random -= lettersProbability.get(i);
			if (random <= 0.0d) {
				randomIndex = i;
				break;
			}
		}
		if(randomIndex == -1)
			return "";
		return lettersList.get(randomIndex);
	}
}