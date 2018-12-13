package application;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * 
 * @author Brandon West
 * File: Scores.java
 * Main Class: TestScores.java
 *
 */
public class Scores
{
	/**
	 * @param - _mean holds the value for the mean (average)
	 * @param - _deviation holds the value for the calculated standard deviation
	 * @param - _bestScore holds the value for the determined best score
	 * @param - _letterGrades creates the array for the use of determining letter grades of students
	 */
	private double _mean = 0, _deviation = 0, _bestScore = 0;
	double[] _letterGrades;
	
	/**
	 * This method creates a new array _letterGrades using the totalMarks, a counter and by going through
	 * the previously sorted array reverses it and takes the largest value to return to the user
	 * 
	 * @param grades holds the student array from TestScorse class
	 * @param totalMarks holds the totalMarks from the TestScores class
	 */
	public void findBest( double[] grades, int totalMarks )
	{
		 _letterGrades = new double[ totalMarks ];
		 // counter is to keep track of the index for _letterGrades[]
		int counter = 0;

		if ( getMean() > 0 || getDeviation() > 0 )
		{
			try
			{
				// reverses the array input through looping backwards
				for ( int index = grades.length - 1; index >= 0; index--)
				{
					_letterGrades [ counter ] = grades[ index ];
					counter++;
				}
				// catches the array index out of bounds exception and reports to the user
			}catch ( ArrayIndexOutOfBoundsException error )
			{
				System.out.println( error + "Exiting.\n" );		
			}
			//prints out the array reversed, and the highest score.
			System.out.println( "Highest to lowest grades: " + Arrays.toString( _letterGrades ) );
			setBestScore( _letterGrades [ 0 ] );
			System.out.println( "The best grade is: " + getBestScore() + ".\n");
		}
		else 
			System.out.println( "Mean and Standard Deviation must be calculated before finding the best score." );
	}// end of findBest method

	/**
	 * This method finds the mean of all the grades from the student array from the TestScores class,
	 * it adds them all up and divides by the total.
	 * 
	 * @param grades holds the student array from testScores
	 */
	public void findMean ( double[] grades )
	{
		/**
		 * Scores holds the indexed number from the grades array
		 * Total holds the number of times the array has been looped through 
		 */
		double scores = 0;
		int total = 0;
		
		try
		{
			// for loop to set all the grades [ row ] values into scores and adds to the total
			for (int row = 0; row < grades.length; row++ )
			{
				scores = grades[ row ] + scores;
				total++;				
			}
			if ( scores > 0 )
			{
				// sets the mean through the scores divided by total
				this.setMean ( scores / total);
				total = 0;
				System.out.printf( "The mean of the grades is: %.2f.\n\n", getMean());				
			}
			else
				System.out.println ("There must be scores input into the system before calculating the mean." );
			
			// array index out of bounds exception reporting to the user
		}catch ( ArrayIndexOutOfBoundsException error )
		{
			System.out.println( error + ". Exiting.\n" );
		}
	}// end of find mean

	/**
	 * This method finds the sample standard deviation for the marks input by subtracting the mean from each score
	 * squaring the result, adding the result to the previously calculated score, then square rooting the final calculation
	 * by total - 1
	 * 
	 * @param grades holds the student array from TestScores class
	 * 
	 */
	public void findDeviation( double[] grades )
	{
		/**
		 * scores holds the scores from grade[] minus the mean
		 * scores 1 holds the square of the scores result
		 * scores 2 holds the result of scores 1 plus scores2
		 * total holds the amount of times looping
		 */
		double scores = 0, scores1 = 0, scores2 =0 ;
		int total = 0;

		// if mean is properly prior to the calculation of the standard Deviation
		if ( getMean() > 0 )
		{
			try 
			{
				//for loop to calculate the score - mean, score squared, score1 + score2
				for (int row = 0; row < grades.length; row++ )
				{
					scores = grades[ row ] - this.getMean();
					scores1 = Math.pow( scores , 2);
					scores2 = scores1 + scores2; // over writing both! 
					total++;				
				}
				//square roots the scores2 divided by total minus 1 and sets the deviation
				this.setDeviation( Math.sqrt( scores2 / (total - 1 ) ) );
				total = 0;
				System.out.printf ( "The Standard deviation of the students grades is: %.2f.\n\n", getDeviation() );			
			//catch array index out of bounds and reports to user
			}catch ( ArrayIndexOutOfBoundsException error )
			{
				System.out.println( error + ". Exiting.\n" );
			}
		}
		// if the mean isn't calculated prior to calculation attempt of Standard Deviation
		else
			System.out.println( "Must calculate mean before attempting the calculation of standard deviation.");
	}// end of findDeviation method call
	
	/**
	 * This method asks the user for input to populate the student array for TestScores, uses 
	 * scanner object input and totalMarks to set the bounds of the array
	 * 
	 * @param student holds the student array fromTestScores
	 * @param totalMarks holds the totalMarks from user for size of new student array
	 * @param input Scanner object from TestScores, used for user input1
	 * @return returns the student array to TestScores
	 */
	public double[] inputGrades( double[] student, int totalMarks, Scanner input )
	{
		/*
		 * marks is set to -1 to begin,holds the marks for the student from 0 to 100 only
		 * student is a new array in order to be populated and returned to the main method
		 */
		double marks = -1;	
		student = new double[ totalMarks ];
		
		try
		{
			// for loop controlled by index and student.length. inputs the number of marks between 0 and 100
			for ( int index = 0; index < student.length; index++ )
			{
				System.out.print( "Please input the mark for the students: " );
				marks = input.nextDouble();
				
				while ( marks > 100 || marks < 0 )
				{
					System.out.print( "Grades must be between 0 and 100. Please input the mark for the students: " );
					marks = input.nextDouble();
				}
				// student @ index sets marks from user
				student [index] = marks;
			}
		//catches the array out of bounds exception and reports to the user
		}catch ( ArrayIndexOutOfBoundsException error )
		{
			System.out.println( error + ". Exiting." );
		// catches the wrong input from user
		}catch ( InputMismatchException error )
		{
			System.out.println ( "Only Numebrs and Decimals allowed. Exiting..." );
			System.exit( 1 );
		}
		// returns student array to TotalScores
		return student;
	}//end of inputGrades method
	
	public void studentMatrix ( int preDefined, Scanner input, SecureRandom random )
	{
		String[][] studentMatrix;
		 int numberOfCourses = 0, total = 0, marks = 0;
		 double studentsAverage = 0;
		 String grades = "", studentName = "", temp = "";
		if ( preDefined == 1 )
		{
			studentName = "John Smith";
			studentMatrix = new String [ 5 ] [ 5 ];			
			studentMatrix [ 0 ][ 0 ] = "PROG1";
			studentMatrix [ 1 ][ 0 ] = "PROG2";
			studentMatrix [ 2 ][ 0 ] = "INFO3";
			studentMatrix [ 3 ][ 0 ] = "SYST4";
			studentMatrix [ 4 ][ 0 ] = "PROG5";
			try 
			{
				for ( int row = 0; row < studentMatrix.length; row++ )
				{
					for ( int col = 1; col < 5; col++ )
					{
						marks = random.nextInt( 100 ) + 1;
						grades = Integer.toString( marks );
						studentMatrix [ row ] [ col ] = grades;
					}
				}
				
			} catch ( ArrayIndexOutOfBoundsException error )
			{
				System.out.println( error + ". Exiting." );
			}
		}
		
		else
		{
			temp = input.nextLine();
			System.out.print ( "What is the name of the student? " );
			studentName = input.nextLine();
			System.out.print ( "How many programs are there?" );
			numberOfCourses = input.nextInt();
			
			// to add name array or something of the like if there is going to be more than 1 person.
			studentMatrix  = new String [ numberOfCourses ][ 5 ];
			
			try 
			{
				for ( int row = 0; row < studentMatrix.length; row++ )
				{
					System.out.print( "Please enter the name of the course to input marks: " );
					studentMatrix [ row ][ 0 ] = input.next();
					for ( int col = 0; col < 4; col++ )
					{
						System.out.print( "Please insert the " + (col + 1)+ " mark: " );	
						grades = input.next();
						studentMatrix [ row ] [ col + 1] = grades;
					}
				}
			} 
			catch ( ArrayIndexOutOfBoundsException error )
			{
				System.out.println( error + ". Exiting." );
			}
		}
		try
			{
				for ( int row = 0; row < studentMatrix.length; row++ )
				{
					for ( int col = 0; col < 4; col++ )
					{
						studentsAverage = Double.parseDouble( studentMatrix [ row ] [ col + 1 ] ) + studentsAverage;
						total++;
					}
				}

				studentsAverage = ( studentsAverage / total );
				
				System.out.println( "" );
				System.out.print ( "Students Name: " + studentName + "\n" );
				System.out.printf( "Grade info: %.2f.\n",  studentsAverage );
				System.out.println( "" );
				System.out.print( "Course  Lab Mark  MidTerm Mark  Final Mark  Assignment Mark \n");
				
				for ( int row = 0; row < studentMatrix.length; row++ )
				{
					for (int col = 0; col < 5; col++)
					{
						System.out.printf ( "%-12s",studentMatrix [ row ][ col ] );
					}
					System.out.print( "\n" );
				}
				System.out.print( "\n" );
				System.out.print( "\n" );
				
	
			}catch (ArrayIndexOutOfBoundsException error )
			{
				System.out.println( error + ". Exiting." );
			}
	}
	/**
	 * This method calculates the best scores and creates a bell curve based on them. Converting the 
	 * numbers to letter grades
	 */
	public void outputResult ()
	{
		// if the mean and standard deviation are over 0,means student array isn't empty
		if ( getMean() > 0 && getDeviation() > 0 )
		{
			try 
			{
				//for loop controlled by index and _letterGrades.length
				for ( int index = 0; index < _letterGrades.length; index++ )
				{
					// if statements that control the letter grades output through different ranges.
					if ( ( getBestScore() - _letterGrades [ index ] ) <= 10 )
						System.out.println ( "The grade: " + _letterGrades[index] + " is an A." );
					else if ( ( getBestScore() - _letterGrades [ index ] ) >= 10 && ( getBestScore() - _letterGrades [ index ] ) <= 20)
						System.out.println ( "The grade: " + _letterGrades [ index ] + " is a B." );
					else if ( ( getBestScore() - _letterGrades [ index ] ) > 20 && ( getBestScore() - _letterGrades [ index ] ) <= 30 )
						System.out.println ( "The grade: " + _letterGrades [ index ] + " is a C." );
					else if ( ( getBestScore() - _letterGrades [ index ] ) > 30 && ( getBestScore() - _letterGrades [ index ] ) <= 40 )
						System.out.println ( "The grade: " + _letterGrades [ index ] + " is a D." );
					else 
						System.out.println ( "The grade: " + _letterGrades [ index ] + " is an F." );
				}
				System.out.printf( "The mean of the grades is: %.2f.\n", getMean() );
				System.out.printf ( "The Standard deviation of the students grades is: %.2f.\n\n", getDeviation() );
				// catch the array out of bounds exception, reports to user
			}catch ( ArrayIndexOutOfBoundsException error )
			{
				System.out.println( error + ". Exiting." );
				// catch null pointer exception error and reports to user 
			}catch (NullPointerException error )
			{
				System.out.println( "You must find the mean, best mark, and standard deviation before computing the output letter grades.\n" );
			}			
		} // end if statement
		// else the mean and standard deviation needs to be set first
		else
			System.out.println( "Standard deviation and the mean must be calculated before Outputting all results.\n" );
	}// end of outputResults
	/**
	 * This method takes the student array and sorts it from lowest to highest
	 * 
	 * @param student holds the student array from TestScores
	 */
	public void sortingScores ( double[]  student )
	{
		// from Arrays class, takes student and sorts the array and outputs on screen
		Arrays.sort( student );	
		System.out.println( "The sorted marks are: " + Arrays.toString( student  ) + "\n" );	
	}// end of sortingScores method

	/**
	 * This method returns the mean
	 * @return _mean 
	 */
	public double getMean()
	{
		return _mean;
	}// end of getMean method

	/**
	 * This method sets _mean
	 * @param _mean holds the mean varible
	 */
	public void setMean(double _mean)
	{
		this._mean = _mean;
	}// end of setMean

	/** 
	 * This method gets the deviation for the student set marks
	 * @return _deviation to the method requiring
	 */
	public double getDeviation() 
	{
		return _deviation;
	}// end of getDeviation
	
	/**
	 * this method sets the deviation to _deviation
	 * @param _deviation holds the calculated deviation of the student set
	 */
	public void setDeviation(double _deviation)
	{
		this._deviation = _deviation;
	}	// end of setDeviation 
	
	/**
	 * This method returns the best score from the student array
	 * @return _bestScore 
	 */
	protected double getBestScore()
	{
		return _bestScore;
	}// end getBestScore method
	
	/**
	 * This method sets the best score from the student array
	 * @param _bestScore to the method
	 */
	protected void setBestScore(double _bestScore)
	{
		this._bestScore = _bestScore;
	}// end the setBestScore method
}// endScores Class
