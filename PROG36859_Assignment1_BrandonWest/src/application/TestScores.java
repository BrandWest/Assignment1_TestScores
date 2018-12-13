package application;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Brandon West
 * Name: Brandon West
 * Assignment: Grade Calculator
 * Program: Honours Bachelor of Applied Information Sciences (Information Systems Security)
 * 
 * This program computes the final scores of students from either a predetermined set of inputs or inputs from the user.
 */
public class TestScores
{
	public static void main ( String[] args )
	{
		/**
		 * @param - totalMarks holds number for the size of the array
		 * @param - choice holds user choice for switch statement
		 * @param - preDefined holds the choice if the user is going to use preDefined inputs or not
		 * @param - holds the user input marks
		 * 
		 */
		int totalMarks = 5, choice = 0, preDefined = 0;
		double marks = 0;

		/**
		 * @param - object Sercure random created named: random
		 * @param - Scanner object created called input
		 * @param - Scores object created 
		 * @param - double array created size 4 ( max number of marks)
		 */
		SecureRandom random = new SecureRandom();
		Scanner input = new Scanner(System.in);
		Scores scores = new Scores();
		double[] student  = new double [ 4 ];
		
		System.out.println( "Welcome to the grade calculator machine. Programmed by Brandon West.\n" );
				
		try
		{
			/**
			 *  @param - preDefined set by user input
			 */
			while( preDefined == 0 || preDefined > 2 )
			{
				System.out.print ( "Would you like to use pre-determined marks and names? 1 for yes, 2 for no: " );
				preDefined = input.nextInt();				
			}
			// if preDefined is set to 1, creates pre defined inputs
			if ( preDefined == 1 )
			{
				// SecureRandom sets the numbers in the array
				for ( int index = 0; index < totalMarks; index++ )
				{
					marks = random.nextInt ( 100 ) + 1;
					student [ index ] = marks;
					totalMarks = 4;
				}
				// array is sorted
				System.out.print( "Pre Determined students made: " + Arrays.toString( student ) + "\n" );
				scores.sortingScores( student );
				mainMenu();
				choice = input.nextInt();
			}
			// else if preDefined == 2 for user input
			else if ( preDefined == 2 )
			{
				// preDefined was set to 2, user input required
				mainMenu();
				choice = input.nextInt();
			}
			// an invalid input was input for the preDefined variable, exits the program.
			else 
			{
				System.out.println( "Invalid input, exiting." );
				System.exit( 1 );				
			}
			// while loop to control the switch statement, controlled by user and variable choice
			while ( choice != 7 )
			{
				 // switch statement controlled by user input
				switch ( choice )
				{
					//case 1 - Input the marks either by the user, or tells the user the array has been initalized.
					case 1: 		
						if ( preDefined == 1 )
						{
							System.out.println( "Pre-Defined inputs set.\n" );
							break;
						}
						// user inputs the totalMarks to set the size of the array
						else
						{
							System.out.print( "How many marks to be input: ");
							// User can only set an array of size 1-4
							while ( totalMarks > 4 || totalMarks < 0)
							{
								totalMarks = input.nextInt();
								if ( totalMarks > 5 )
								{
									System.out.print( "Only 5 marks allowed. Try again: " );
									totalMarks = input.nextInt();
								}							
						}
						//method call to scores class to inputGrades, arguments of student, totalMarks, scanner object input
						student = scores.inputGrades ( student, totalMarks, input );
						// method call to the scores class, sortingScores, student array as argument
						scores.sortingScores( student );
						}
						break;
					// user selects case 2 - find the mean
					case 2:
						// method call to the scores class, find mean. student array as argument
						scores.findMean( student );
						break;
					//user selects case 3 - finds the standard deviation
					case 3:		
						// calls method findDeviation from scores class, student as argument 
						scores.findDeviation( student );
						break;
					// user selects case 4 - find the best mark
					case 4:
						// method call to scores class - findBest with student and totalMarks as argument
						scores.findBest( student, totalMarks );
						break;
					// user selects case 5 - Output all results and letter grades
					case 5:
						// calls scores method outputResult - student and totalMarks as arguments
						scores.outputResult( );
						break;
					// case 6 selected by user - marks for a single student - preDefined, scanner input and SecureRandom random as arguments
					case 6:
						//calls scores method studentMatrix
						scores.studentMatrix( preDefined, input, random );
						break;			
					//default case, user makes an error.
					default:
						System.out.println( "Improper input, try again.\n\n " );
						mainMenu();
						choice = input.nextInt();
						break;
						
				}// end of switch
				mainMenu();
				choice = input.nextInt();
			}// end of while
			System.out.println( "Thank-you for using the Grade Calculator Machine!" );				
			System.exit( 0 );	
		// catch for input mismatch errors
		}catch ( InputMismatchException error )
		{
			System.out.println ( "Numbers only, exiting." + error );
		}
		input.close();// close inputs
	}// end of main method
	
	/**
	 *  Main Menu method, for user interface
	 */
	public static void mainMenu()
	{
		System.out.println( "1. Enter grades." );
		System.out.println( "2. Find the Mean of the Class." );
		System.out.println( "3. Find Standard Deviation of Class." );
		System.out.println( "4. Find the Best Mark Input." );
		System.out.println( "5. Outputs All Results and Find Letter Grade Equivalent.");
		System.out.println( "6. Student 2D Arrays." );
		System.out.println( "7. Exit.");
		System.out.print ("Choice: " );
	}
}// end of mainMenu method 
