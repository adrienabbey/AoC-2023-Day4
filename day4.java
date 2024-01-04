/* Advent of Code 2023, Day 4: Scratchcards
 * Adrien Abbey, Jan. 2024
 * Part One Solution: 20407
 * Part Two Solution: 23806951
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class day4 {
    /* Global Variables */
    public static String inputFileName = "input.txt";
    public static boolean testing = false;
    public static boolean partTwo = true;

    public static void main(String[] args) throws FileNotFoundException {
        // Load the input file into an array list of strings:
        ArrayList<String> inputStrings = loadInputStrings();

        // Track the number of copies of each card:
        int[] cardCopyList = new int[inputStrings.size()];
        for (int i = 0; i < inputStrings.size(); i++) {
            cardCopyList[i] = 1;
        }

        // Track the total score sum:
        int totalScore = 0;

        // Calculate using Part One's method:
        if (!partTwo) {
            // For each string in the input:
            for (String cardString : inputStrings) {
                // Find the card's value:
                int matches = findMatches(cardString);

                // Add that card's value to the total score:
                totalScore += Math.pow(2, matches - 1);
            }
        } else {
            // Calculate using Part Two's method.

            // For each card in the list:
            for (int i = 0; i < inputStrings.size(); i++) {
                // Find the number of matches for that card:
                int matches = findMatches(inputStrings.get(i));
                // Add that many copies of the subsequent cards:
                for (int j = 1; j <= matches; j++) {
                    cardCopyList[i + j] += cardCopyList[i];
                }
            }

            // Calculate the total score, which is the total number of cards:
            for (int count : cardCopyList) {
                totalScore += count;
            }
        }

        // Print out the score result:
        System.out.println("The total score is: " + totalScore);
    }

    public static ArrayList<String> loadInputStrings() throws FileNotFoundException {
        // Loads the contents of the input file,
        // returning an array list of strings.

        // Open the input file:
        File inputFile = new File(inputFileName);
        Scanner inputScanner = new Scanner(inputFile);

        // Start loading the contents of the input file into an array list:
        ArrayList<String> inputStrings = new ArrayList<String>();
        while (inputScanner.hasNextLine()) {
            inputStrings.add(inputScanner.nextLine());
        }

        // Close the scanner:
        inputScanner.close();

        // Return the loaded string array list:
        return inputStrings;
    }

    public static int findMatches(String cardString) {
        // Finds and returns the number of matches for the given card.

        // Separate the card name and number from the string:
        String[] firstSplit = cardString.split(":");

        // Separate the winning numbers from the given numbers:
        String[] secondSplit = firstSplit[1].split("\\|");

        // Parse the winning numbers and the given numbers:
        String[] winningNumbers = secondSplit[0].split(" ");
        String[] givenNumbers = secondSplit[1].split(" ");

        // Track the number of matches:
        int matches = 0;

        // Check the lists for any matches:
        for (String winningNumber : winningNumbers) {
            for (String givenNumber : givenNumbers) {
                // Exclude null strings:
                if (!givenNumber.isBlank() || !winningNumber.isBlank()) {
                    // Test code:
                    if (testing) {
                        // System.out.println(" Numbers being compared: " + givenInt + ", " +
                        // winningInt);
                    }

                    // Compare the numbers:
                    if (winningNumber.matches(givenNumber)) {
                        matches += 1;
                    }
                }
            }
        }

        // Test code:
        if (testing) {
            System.out.println(" Card string: " + cardString);
            System.out.println(" Given numbers: " + Arrays.toString(givenNumbers));
            System.out.println(" Winning numbers; " + Arrays.toString(winningNumbers));
            System.out.println(" Matches: " + matches);
            System.out.println(" - ");
        }

        // Return the number of matches:
        return matches;
    }
}