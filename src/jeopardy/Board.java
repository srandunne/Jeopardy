package jeopardy;
/*
 * @author Sean Randunne, Shalin Mehta
 * @teacher(Ms. Denna)
 * @version(12/17/2017)
 * This is the Board class 
 * which is used to construct the 2-d array of Questions for the board
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Board {
	
	Question[][] questions;
	
	/*
        This methods creates our questions array
        */
	public Board() {
		
		this.questions = new Question[4][4];
	}
        /*
        This methods reads in our textfile and stores each question and anwser choice
        */
	public void setBoard()  throws IOException, FileNotFoundException
	{
	    Scanner inF = new Scanner(new File("JeopTxt.txt"));       // opens the file stream for input reading
	  
	    String question, ans1, ans2, ans3, ans4;
	    int corrAns, value;
	    
	    while(inF.hasNextLine())
	    {               
	    	
	    	for(int i = 0; i<questions.length; i++ )
	    	{
	    		int hundred = 100;
	    		
	    		for(int j = 0; j<questions[0].length; j++)
	    		{
	    		
	    			question = inF.nextLine();
	    		        ans1 = inF.nextLine();
	    		        ans2 = inF.nextLine();
	    		        ans3 = inF.nextLine();
	    		        ans4 = inF.nextLine();
	    		        corrAns = inF.nextInt();
	    		        
	    		        if(inF.hasNext())
	    		        {
	    		        	inF.nextLine();
	    		        }
	    		    	value = hundred;

	    			 questions[j][i] = new Question(question, ans1, ans2, ans3, ans4, corrAns, value);
	    			 hundred+=100;
	    		}    		
	    	}    	
	    }
	    inF.close();
	}
}