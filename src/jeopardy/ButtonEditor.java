/*
 * @author Sean Randunne, Shalin Mehta
 * @teacher(Ms. Denna)
 * @version(12/17/2017)
 * This is the ButtonEditor class 
 * which is used to perform actions when a button is clicked 
 */
package jeopardy;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

 
public class ButtonEditor extends DefaultCellEditor {
	private int row;
	
	protected JButton 	button;
	private String    	label;
	private boolean   	isPushed;
	Player 				plr1;
	Player 				plr2;
	Board 				b;
	int 				column;
	/*
	 * @param(JCheckBox checkBox - creates the checkBox)
	 * @param(Board b - the board with all of the questions)
	 * @param(int column - the column of the button)
	 * @param(Player plr1 - player 1)
	 * @param(Player plr2 - player 2)
	 * @param(int checkPlayer - checks who's turn it is)
	 * initializes all of the instance fields and creates the buttons
	 */
	public ButtonEditor(JCheckBox checkBox, Board b,int column,
			Player plr1,Player plr2) {
	    super(checkBox);
	    this.b = b;
	    this.column = column;
	    this.plr1 = plr1;
	    this.plr2 = plr2;
	    button = new JButton();
	    button.setOpaque(true);
	    button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	fireEditingStopped();
		      }
	    });
    }
	/*
	 * @param(JTable table - the Jeopardy Table)
	 * @param(Object value - the button value)
	 * @param(boolean isSelected - checks whether the button has been selcted or not)
	 * @param(int row - the row number)
	 * @param(int column - the column number)
	 * @return(Component button - the button itself)
	 * sets the label on the button and returns the button's value
	 */
	public Component getTableCellEditorComponent(JTable table, Object value,
                   boolean isSelected, int row, int column) {
	    if (isSelected) {
	    	button.setForeground(table.getSelectionForeground());
	    	button.setBackground(table.getSelectionBackground());
	    } 
	    else{
	    	button.setForeground(table.getForeground());
	    	button.setBackground(table.getBackground());
	    }
	    label = (value ==null) ? "" : value.toString();
	    button.setText( label );
	    isPushed = true;
	    return button;
  }
	//@return(The new label)
	//This method checks the user's input and makes sure that if they got it right 
	//they are awarded the proper amount of points
	public Object getCellEditorValue() 
	{
		Scanner s = new Scanner(System.in);
		if (isPushed && !label.equals("..."))  
		{
			//converts label String into a row number
			row = (new Integer(label)/100)-1;
                      
			if(ActiveTable.checkPlayer == 0){
				System.out.println("Here's your question " + plr1.getName());
				System.out.println("\n" + b.questions[row][column]);
				System.out.println("What is your anwser choice " + plr1.getName() + "?");
			}
			else
			{
				System.out.println("Here's your question " + plr2.getName());
				System.out.println("\n" + b.questions[row][column]);
				System.out.println("What is your anwser choice " + plr2.getName() + "?");
			}
			boolean keepGoing = true;
			int choice = 0;
			//repeats until user enters valid input
			do
			{
				if(s.hasNextInt())
				{
					choice = s.nextInt();
					if(choice > 4 || choice < 1)
					{
						System.out.println("Please enter valid input");
					}
					else
					{
						keepGoing = false;
					}
					
				}
				else
				{
					System.out.println("Please type in valid input");
					s.nextLine();
				}
			}
			while(keepGoing);
			int amountWon;
			if(choice == b.questions[row][column].getCorrAnwser())
			{
				
				if(ActiveTable.checkPlayer == 0)
				{
					System.out.println("You got the question right");
					amountWon = (row+1) * 100;
					plr1.incrementWinnings(amountWon);
					System.out.println("Yay you got $" + amountWon);
					System.out.println(plr1.getName() + 
							"'s current acount balance is " + plr1.getWinnings());
					ActiveTable.checkPlayer++; 
					System.out.println("Now it's " + plr2.getName() + "'s turn");
				}
				else
				{
					System.out.println("You got the question right");
					amountWon = (row+1) * 100;
					plr2.incrementWinnings(amountWon);
					System.out.println("Yay, you got $ " + amountWon);
					System.out.println(plr2.getName() + "'s account balance is $ " + plr2.getWinnings());
					ActiveTable.checkPlayer--;
					System.out.println("Now it's " + plr1.getName() + "'s turn");
				}
			}
			else//goes here if answer is incorrect
			{
				if(ActiveTable.checkPlayer==0)
				{
					System.out.println("Aww. you got it wrong.");
					System.out.println("The correct answer was answer choice " 
					+ b.questions[row][column].getCorrAnwser());
					System.out.println(plr1.getName() + " now has " + plr1.getWinnings() + " points!");
					ActiveTable.checkPlayer++;
					System.out.println("Now it's " + plr2.getName() + "'s turn");
				}
				else
				{
					System.out.println("Aww. you got it wrong.");
					System.out.println("The correct answer was answer choice " 
							+ b.questions[row][column].getCorrAnwser());
					System.out.println(plr2.getName() + " now has " + plr2.getWinnings() + " points!");
					ActiveTable.checkPlayer--;
					System.out.println("Now it's " + plr1.getName() + "'s turn");
				}
			}   	
		}
		isPushed = true;
		//s.close();
		return new String("...") ; 
  }
    
	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
  }
  
	protected void fireEditingStopped() {
		super.fireEditingStopped();
  }
}