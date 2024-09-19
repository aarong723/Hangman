/* Aaron Gold
 * Rock Paper Scissors bot
 */

import java.util.*;
public class Hangman {

	public static void main(String[] args) {
		String user_word = "", guess_word = "", status = "", gallowTop = "+--+\n|  |", new_guess_word = "",
				gallowBottom = "\n|  \n+-----\n", gallowOneVar = "", gallowTwoVar = "", gallowThreeVar = "", previousGuesses = "";
		int user_word_length = 0, letter_val = 0, wrong_count = 0, right_count = 0;
		boolean finish = false;
		char letter = 'a', letter_status = 'y';	

		Scanner keyboard = new Scanner(System.in);

		System.out.println("This program plays a game of reverse hangman.\n"
				+ "You think up a word (by typing it on the computer) and I'll try to guess "
				+ "the letters.\nHow many letters are in your word?");
		user_word_length = keyboard.nextInt();

		System.out.println("Please enter the word for me to guess (letters only): ");
		user_word = keyboard.next().toUpperCase();

		//Creates initial gallows template
		gallowOneVar = gallowOne(0);
		gallowTwoVar = gallowTwo(0); 
		gallowThreeVar = gallowThree(0);

		//Makes blank template to match word length
		for (int i = 0; i < user_word_length; i++)
		{
			guess_word = guess_word + "-"; 
		}
		System.out.println(guess_word);
		new_guess_word = guess_word;
		System.out.println(gallowTop + gallowOneVar + gallowTwoVar + gallowThreeVar + gallowBottom); 
		while (right_count != user_word_length && finish == false)
		{
			int temp_right_count = 0;
			
			Random rand = new Random();
			letter_val = rand.nextInt(25) + 65;

			//Makes random letter guess
			letter =(char) (letter_val); 


			//Eliminates repeat guesses
			for (int j = 0; j <= previousGuesses.length()-1;  j++)
			{
				if (letter == previousGuesses.charAt(j))
				{
					letter_val = rand.nextInt(25) + 65; 
					letter =(char) (letter_val);
				}
			}
			//Adds guess to previous guesses
			previousGuesses += letter; 

			
			for (int x = 0; x < user_word_length; x++)
			{
				if (letter == user_word.charAt(x))
				{
					new_guess_word = new_guess_word.substring(0, x) + letter + new_guess_word.substring((x + 1)); //Adjusts string to have correct letters
					letter_status = 'y';
					temp_right_count ++;
				}
			}
			//Counter to know how many "strikes" the computer has
			if (guess_word == new_guess_word)
			{
				letter_status = 'n';
				wrong_count ++; 

			}
			//Builds adjusted gallows
			if (wrong_count == 1)
			{
				gallowOneVar = gallowOne(1); 
			}
			else if (wrong_count == 2)
			{
				gallowTwoVar = gallowTwo(1);
			}
			else if (wrong_count == 3)
			{
				gallowThreeVar = gallowThree(1);
			}
			else if (wrong_count == 4)
			{
				gallowThreeVar = gallowThree(2); 
			}
			else if (wrong_count == 5)
			{
				gallowTwoVar = gallowTwo(2);
			}
			else if (wrong_count == 6)
			{
				gallowTwoVar = gallowTwo(3);
				status = ("You beat me this time.");
				finish = true;
			}

			//Prints updated gallows
			System.out.println("I've got " + right_count + " of the " + user_word_length + " letters so far");
			System.out.println("I guess: " + letter + "\nIs that letter in your word? " + letter_status + "\n");

			//Corrects guessed word and right letter counter
			if(letter_status == 'y')
			{
				guess_word = new_guess_word;
				right_count += temp_right_count;
			}
			System.out.println(guess_word);
			System.out.println(gallowTop + gallowOneVar + gallowTwoVar + gallowThreeVar + gallowBottom + "\n"); 
	
			if (right_count == user_word_length)
			{
				finish = true;
				status = ("I won! Your word was " + user_word + ". Try again next time.");
			}
		}
		System.out.println(status);
		keyboard.close();
	}

	//Fist part of the gallows (head)
	public static String gallowOne(int add) 
	{
		String gallowString = "\n|  ";
		if (add == 1)
		{
			gallowString += "0";
		}
		return(gallowString);
	}

	//Second part of the gallows (arms and torso)
	public static String gallowTwo(int add) 
	{
		String gallowString = "\n| ";

		if (add >= 1)
		{
			gallowString += " |";
		}
		if (add >= 2)
		{
			gallowString += "\\";
		}
		if (add >= 3)
		{
			gallowString = gallowString.substring(0, 3) + "/" + gallowString.substring(4);
		}
		return(gallowString);
	}
	
	//Third part of the gallows (legs)
	public static String gallowThree(int add) 
	{
		String gallowString = "\n|  ";

		if (add >= 1)
		{
			gallowString += " \\";
		}
		if (add >= 2)
		{
			gallowString = gallowString.substring(0, 3) + "/" + gallowString.substring(4);
		}
		return(gallowString);
	}
}
