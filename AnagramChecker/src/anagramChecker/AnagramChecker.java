package anagramChecker;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class AnagramChecker {
	
	public static boolean anagramAddedToFile;
	public static String[] resultsArray = {};
	
	//Method to write the formatted results of anagram checker to txt file
	public static boolean writeToFile(String username, String s1, String s2, String result, String filepath) throws IOException {
		FileWriter myWriter = new FileWriter(filepath, true);
		myWriter.write(username + "," + s1 + "," + s2 + "," + result + "\n");
		myWriter.close();
		anagramAddedToFile = true;
		return anagramAddedToFile;
	}
	//Method to add a result to the resultsArray 
	//Not successfully implemented
	public static void addToResultsArray(String[] resultsArray, String username, String s1, String s2, String result) {
		//Create new results array 1 longer than original.
		String[] newResultsArray = new String[resultsArray.length + 1];
		//populate new results array with original data
		for (int i = 0; i < resultsArray.length; i++) {
			newResultsArray[i] = resultsArray[i];
		}
		//add new result record to the end of new results array
		String newRecord = username + "," + s1 + "," + s2 + "," + result;
		newResultsArray[resultsArray.length] = newRecord;
	}
	
	//This checks the resultsArray prior to execution of anagram checker
	public static boolean checkResultsArray(String[] resultsArray, String s1, String s2) {
		//checks the word strings are present in the array results
		for (int i = 0; i < resultsArray.length; i++) {
			if (resultsArray[i].contains(s1)) {
				if (resultsArray[i].contains(s2)) {
					//print message stating whether strings are anagrams or not, based on the final character(y/n) and return true
					String anagramResult = Character.toString(resultsArray[i].charAt(resultsArray[i].length()-1));
					if (anagramResult.equals("y")) System.out.println("Result present: " + s1 + " and " + s2 + " are anagrams");
					else if (anagramResult.equals("n")) System.out.println("Result present: " +s1 + " and " + s2 + " are anagrams");
					return true;
				}
			}
		}
		return false;
 	}
	
	//Method to check if 2 words are anagrams
	public static boolean anagramChecker(String username, String s1, String s2, String filepath) {
		boolean isAnagram;
		
		if (checkResultsArray(resultsArray, s1, s2)) {
			return true;
		}
		String result = "";
		//Words converted to character arrays and sorted to alphabetical order
		char[] cs1 = s1.toCharArray();
		char[] cs2 = s2.toCharArray();
		Arrays.sort(cs1);
		Arrays.sort(cs2);
		//If the sorted words are the same length with the exact same characters it is an anagram
		//isAnagram set to true or false and result string populated 
		if (cs1.length == cs2.length && Arrays.equals(cs1, cs2)) {
			System.out.println(s1 + " and " + s2 + " are anagrams");
			result = "y";
			isAnagram = true;
		} else {
			System.out.println(s1 + " and " + s2 + " are not anagrams");
			result = "n";
			isAnagram = false;
		}
		//writes results to files and prints confirmation message
		try {
			writeToFile(username, s1, s2, result, filepath);
			System.out.println("Successfully printed to results");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		//addToResultsArray(resultsArray, username, s1, s2, result);
		return isAnagram;
	}
	
	
	//A method to check inputs contain no spaces or numbers
	public static boolean validStringCheck(String anagramInput) {
		char[] validCharCheck = anagramInput.toCharArray();
		//each character in the word checked to ensure it is a letter
		for (int i = 0; i < validCharCheck.length; i++) {
			if (!Character.isLetter(validCharCheck[i])) {
				System.out.println(anagramInput + " is not a valid input, enter a string with no numbers, spaces or special characters");
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String filepath = "";
		//User can use default or hardcoded filepath  
		Scanner scanner = new Scanner(System.in); // Create a Scanner object
		System.out.println("Enter filepath: ");
		filepath = scanner.nextLine();
			
		
		//User prompted to enter anagram information
		System.out.println("Enter username string: ");
		String username = scanner.nextLine();
		System.out.println("Enter first string: ");
		String string1 = scanner.nextLine();
		string1 = string1.toLowerCase();
		//Checks if first string is valid before proceeding with second string
		boolean firstInputValid = validStringCheck(string1);
		if (firstInputValid) {
			System.out.println("Enter second string: ");
			String string2 = scanner.nextLine();
			string2 = string2.toLowerCase();
			//Check if second string is valid before proceeding with anagram checker
			boolean secondInputValid = validStringCheck(string2);
			if (secondInputValid) {
				anagramChecker(username, string1, string2, filepath);
			}
		}
		scanner.close();
}
}
