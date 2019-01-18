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
	
	public static void displayResults(String phrase) {
		for (String word : phrase.split(" ")) {
			translate(word.trim());
		}
	}
	
	public static void translate(String word) {
		char punctuation = ' ';		
		if (isPunctuation(word.charAt(word.length() - 1))) {
			punctuation = word.charAt(word.length() - 1);
			word = word.substring(0, word.length() - 1);
		}	
		for (int i = 0; i < word.length(); i++) {
			char letter = word.charAt(i);
			if (word.indexOf('@') > 0 || isNumber(letter)) {
				System.out.print(word + punctuation + " ");
				return;
			}
			else if (isVowel(word.charAt(0))) {
				System.out.print(word + "way" + punctuation + " ");
				return;
			} else {
				if (isVowel(letter)) {
					String pigWord = word.substring(i, word.length()) + word.substring(0, i) + "ay";
					if (word.charAt(0) >= 65 || word.charAt(0) <= 90) {
						boolean caps = false;
						for (char character : word.toCharArray()) {
							if (character <= 65 || character >= 90) {
								break;
							} else {
								caps = true;
							}
						}
						if (!caps) {
							String wordUpper = pigWord.toUpperCase();
							String wordLower = pigWord.toLowerCase();
							System.out.print(wordUpper.charAt(0) + wordLower.substring(1, wordLower.length()) + punctuation + " ");	
							return;							
						}
					}
					System.out.print(pigWord + punctuation + " ");
					return;
				}
			}
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
		if (lastChar == '.' || lastChar == ',' || lastChar == '!' || lastChar == '?' || lastChar == ';' || lastChar == ':' || lastChar == '-') {
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
