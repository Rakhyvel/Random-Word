package main;

import java.util.Random;

public class Main {

	static Random rand = new Random();

	public static void main(String[] args) {
		System.out.println(randName(3));
	}

	public static String randName(int length) {
		String name = "";
		double r;
		for (int i = 0; i < length; i++) {
			r = rand.nextDouble();
			if (r < .5) {
				if (i == 0) {
					String tempName = findConsonant().toUpperCase();
					if (tempName == "'" || tempName == " " || tempName == "-") {
						tempName = "T";
					}
					name = name + tempName;
				} else {
					name = name + findConsonant();
				}
				name = name + findVowel();
			}
			if (r > 0.5 && r < 0.75) {
				if (i == 0) {
					String tempName = findConsonant().toUpperCase();
					if (tempName == "'" || tempName == " " || tempName == "-") {
						tempName = "T";
					}
					name = name + tempName;
				} else {
					name = name + findConsonant();
				}
				name = name + findVowel();

				if (i == length - 1) {
					String tempName = findConsonant();
					if (tempName == "'" || tempName == " " || tempName == "-") {
						tempName = "r";
					}
					name = name + tempName;
				} else {
					name = name + findConsonant();
				}
			}
			if (r > 0.75) {
				if (i == 0) {
					name = name + findVowel().toUpperCase();
				} else {
					name = name + findVowel();
				}
				if (i == length - 1) {
					String tempName = findConsonant();
					if (tempName == "'" || tempName == " " || tempName == "'") {
						tempName = "n";
					}
					name = name + tempName;
				} else {
					name = name + findConsonant();
				}
			}
		}
		return name;
	}

	public static String findConsonant() {
		String[] consonant = new String[] { "t", "n", "r", "s", "d", "l", "m", "g", "k", "h", "v", " ", "f", "c", "-", "p", "'", "b", "j", "y", "x", "z", "q" };
		double[] consonantWeight = new double[] { 8.89, 7.88, 7.88, 5.32, 4.9, 4.81, 3.55, 3.44, 3.24, 2.85, 2.55, 2.1, 1.81, 1.71, 1.66, 1.57, 1.5, 1.31, 0.9, 0.49, 0.11, 0.04, 0.01 };
		// 1/rank = proabability
		// Imagine chosing a random angle on a pie chart with different
		// segmentsItem[] items = ...;

		// Compute the total weight of all items together
		double totalWeight = 0.0d;
		for (int i = 0; i < consonantWeight.length; i++) {
			totalWeight += consonantWeight[i];
		}
		// Now choose a random item
		int randomIndex = -1;
		double random = Math.random() * totalWeight;
		for (int i = 0; i < consonant.length; ++i) {
			random -= consonantWeight[i];
			if (random <= 0.0d) {
				randomIndex = i;
				break;
			}
		}
		return consonant[randomIndex];
	}

	public static String findVowel() {
		String[] consonant = new String[] { "a", "e", "i", "o", "u" };
		double[] consonantWeight = new double[] { 10.04, 9.85, 5.01, 4.06, 1.86 };
		// 1/rank = proabability
		// Imagine chosing a random angle on a pie chart with different
		// segmentsItem[] items = ...;

		// Compute the total weight of all items together
		double totalWeight = 0.0d;
		for (int i = 0; i < consonantWeight.length; i++) {
			totalWeight += consonantWeight[i];
		}
		// Now choose a random item
		int randomIndex = -1;
		double random = Math.random() * totalWeight;
		for (int i = 0; i < consonant.length; ++i) {
			random -= consonantWeight[i];
			if (random <= 0.0d) {
				randomIndex = i;
				break;
			}
		}
		return consonant[randomIndex];
	}
}
