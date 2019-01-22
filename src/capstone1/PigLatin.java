package capstone1;
import java.util.Scanner;

public class PigLatin {
	static Scanner scnr = new Scanner(System.in);
	
	public static void main(String[] args) {
		boolean retry = true;
		
		header();
		while (retry) {
			displayResults(userInput());
			retry = retry();
		}
		exit();
		scnr.close();
	}
	
	public static void header() {
		System.out.println("Welcome to the Pig Latin Tranlator!\n");
	}
	
	///////get and return user input
	public static String userInput() {
		boolean valid = false;
		String input = " ";
		while (!valid) {
			System.out.println("Enter phrase to translate: ");
			input = scnr.nextLine();
			valid = validateString(input);			
		}
		return input;
	}
	
	////////break string into word and send each to translate
	public static void displayResults(String phrase) {
		for (String word : phrase.split(" ")) {
			translate(word.trim());
		}
	}
	////////translate one word at a time
	public static void translate(String word) {
		char punctuation = ' ';	
		boolean caps = false;	
		String way = "way";
		String ay = "ay";	
		
		
		if (!translateUnchanged(word)) {//if word should be changed before printing
			//check for punctuation, store as char, remove from word
			if (isPunctuation(word.charAt(word.length() - 1))) {
				punctuation = word.charAt(word.length() - 1);
				word = word.substring(0, word.length() - 1);
			}	
			//check for if all caps, if true, caps suffix
			if (translateCaps(word)) {
				way = way.toUpperCase();
				ay = ay.toUpperCase();
				caps = true;
			}
			//check if first letter is vowel, if true print translation
			if (isVowel(word.charAt(0))) {
				System.out.print(word + way);
				charPrintSpace(punctuation);
			//else shift characters and print translation
			} else {
				//check each character to find first vowel
				for (int i = 0; i < word.length(); i ++) {
					char letter = word.charAt(i);
					if (isVowel(letter)) {
						//shift characters and create new word
						String pigWord = word.substring(i, word.length()) + word.substring(0, i) + ay;
						//check for title case & caps, if true, alter case and print
						if (word.charAt(0) >= 65 || word.charAt(0) <= 90) {
							if (!caps) {
								String wordUpper = pigWord.toUpperCase();
								String wordLower = pigWord.toLowerCase();
								System.out.print(wordUpper.charAt(0) + wordLower.substring(1, wordLower.length()));	
								charPrintSpace(punctuation);
								return;							
							}
						}
						//if lower case, print
						System.out.print(pigWord);
						charPrintSpace(punctuation);
						return;
					}					
				}
			}
		}
	}
		
	/////////check if word should print unchanged
	public static boolean translateUnchanged(String word) {
		for (char letter : word.toCharArray()) {
			if (letter == '@' || isNumber(letter)) {
				System.out.println(word + " ");
				return true;		
			}	
		}
		return false;
	}
	
	////////check if word is all caps
	public static boolean translateCaps(String word) {
		for (char character : word.toCharArray()) {
			if (character <= 65 || character >= 90) {
				return false;
			} 
		}
		return true;
	}
	
	/////////////check if punctuation or space is present and print accordingly
	private static void charPrintSpace(char print) {
		System.out.print(print);
		if (print != ' ') {	
			System.out.print(" ");
		}
	}
	
	private static boolean validateString(String input) {
		if (input.length() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isNumber(char character) {
		if (character == '0' || character == '1' || character == '2' || character == '3' || character == '4' || character == '5' || character == '6' || character == '7' || character == '8' || character == '9') {
			return true;
		}
		else {
			return false;
		}
	}
	
	private static boolean isPunctuation(char lastChar) {
		if (lastChar == '.' || lastChar == ',' || lastChar == '!' || lastChar == '?' || lastChar == ';' || lastChar == ':' || lastChar == ')' || lastChar == '-') {
			return true;
		}
		else {
			return false;
		}
	}
	
	private static boolean isVowel(char letter) {
		if ((letter == 'a') || (letter == 'e') || (letter == 'i') || (letter == 'o') || (letter == 'u') || (letter == 'A') || (letter == 'E') || (letter == 'I') || (letter == 'O') || (letter == 'U')) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private static boolean retry() {
		char first = ' ';
		System.out.println("\nTranslate another line? (y/n) ");
		if (scnr.hasNextLine()) {
			first = scnr.next().charAt(0);
		}
		
		while(first != 'y' && first != 'Y' && first != 'n' && first != 'N') {
			System.out.println("What was that?... type 'y' to continue or 'n' to exit");
			if (scnr.hasNext()) {
				first = scnr.next().charAt(0);
			}
		}		
		if (first == 'y' || first == 'Y') {
			scnr.nextLine();
			return true;
		}
		else {
			return false;
		}
	}

	public static void exit() {
		System.out.println("GOODBYE!");
	}
}
