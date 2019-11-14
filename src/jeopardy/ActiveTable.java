package jeopardy;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

  
/*Author: Shalin Mehta, Sean Randunne
 * @version 1.0 11/09/98
 * @teacher(Mrs. Denna)
 * This is the Jeopardy Driver class which is used to control all of the UI actions
 */

/*
The constructor creates the interactive table, 
along wiht each button to create a comprehensive board for the user
@ param: the two player objects
*/

public class ActiveTable extends JFrame 
{
	public static int checkPlayer = 0;
	public ActiveTable(Player plr1,Player plr2) throws FileNotFoundException, IOException{
    super( "Jeopardy(2 Player Mode)" );
    final String HUNDRED 		= "100";
    final String TWO_HUNDRED 	= "200";
    final String THREE_HUNDRED 	= "300";
    final String FOUR_HUNDRED 	= "400";
    Board b = new Board();
    b.setBoard();
    //creating with the headers and labels for the buttons
    DefaultTableModel dm = new DefaultTableModel();
    dm.setDataVector(new Object[][]{{HUNDRED,HUNDRED,HUNDRED,HUNDRED},
                                    {TWO_HUNDRED,TWO_HUNDRED,TWO_HUNDRED,TWO_HUNDRED},
                                    {THREE_HUNDRED,THREE_HUNDRED,THREE_HUNDRED,THREE_HUNDRED},
                                    {FOUR_HUNDRED,FOUR_HUNDRED,FOUR_HUNDRED,FOUR_HUNDRED}},
                     new Object[]{"Rappers","Java","Sports","Movies"});
                      
    JTable table = new JTable(dm);
    table.getColumn("Rappers").setCellRenderer(new ButtonRenderer());
    table.getColumn("Rappers").setCellEditor(new ButtonEditor(new JCheckBox(),b,0, plr1,plr2));
    table.getColumn("Java").setCellRenderer(new ButtonRenderer());
    table.getColumn("Java").setCellEditor(new ButtonEditor(new JCheckBox(),b,1, plr1,plr2));
    table.getColumn("Sports").setCellRenderer(new ButtonRenderer());
    table.getColumn("Sports").setCellEditor(new ButtonEditor(new JCheckBox(),b,2, plr1,plr2));
    table.getColumn("Movies").setCellRenderer(new ButtonRenderer());
    table.getColumn("Movies").setCellEditor(new ButtonEditor(new JCheckBox(),b,3, plr1,plr2));
    JScrollPane scroll = new JScrollPane(table);
    
    //creating the image
    ImageIcon imgIcon = new ImageIcon("Jeopardy_Title.jpg");
    Image image = imgIcon.getImage(); 
    Image newimg = image.getScaledInstance(200, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
    JLabel lbl = new JLabel(new ImageIcon(newimg));
    //creating the exit button
    JButton btn = new JButton("Exit");
    btn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e)
        {
        	if(plr1.getWinnings() > plr2.getWinnings())
        	{
        		System.out.println("The winner is " + plr1.getName());
        	}
        	else if (plr2.getWinnings() > plr1.getWinnings())
        	{
        		System.out.println("The winner is " + plr2.getName());
        	}
        	else
        	{
        		System.out.println("There was a tie.");
        	}
        	System.out.println(plr1.getName() + " recieved " + plr1.getWinnings());
        	System.out.println(plr2.getName() + " recieved " + plr2.getWinnings());
        	System.exit(0);
        }
    });
    
    this.setLayout(new BorderLayout());
    getContentPane().add(lbl, BorderLayout.NORTH);
    getContentPane().add( scroll ,  BorderLayout.CENTER);
    getContentPane().add( btn,  BorderLayout.SOUTH );
    this.pack();
    this.setSize( 800, 400 );
    this.setVisible(true);
  }
  /*
  The main method initializes the user's names 
  and takes their input for their answer choice
  */
  public static void main(String[] args) throws FileNotFoundException, IOException {
	  Scanner s = new Scanner(System.in);
	  
	  System.out.println("Welcome to Jeopardy!!.");
	  System.out.println("The rules of the game are simple. "
	  		+ "There are 2 players who switch off answering questions. "
	  		+ "The game can be ended by clicking the exit button and the totals "
	  		+ "and the winner will be posted");
	  //get player1's name
	  System.out.println("Player 1 please enter your name: ");
	  String name1 = "";
	  boolean keepGoing1 = true;
	  do
	  {
		  if(s.hasNextInt())
		  {
			  System.out.println("Please type in valid input");
			  s.nextLine();
		  }
		 
		  else
		  {
			  name1 = s.nextLine();
			  keepGoing1 = false;
		  }
		 
	  }
	  while(keepGoing1);
	  
 	 Player plr1 = new Player(name1);
 	 System.out.println(" ");
 	 //get player 2's name
 	 System.out.println("Player 2 please enter your name: ");
 	String name2 = "";
 	boolean keepGoing2 = true;
 	do
 	{
		 if(s.hasNextInt())
	 	 {
	 		 System.out.println("Please type in valid input");
	 		s.nextLine();
	 	 }
		 
		 else
		 {
			 name2 = s.nextLine();
			 keepGoing2 = false;
		 }
		 
	}
	while(keepGoing2);
 	System.out.println(plr1.getName() + " please click on "
 			+ "the question you want to answer");
 	Player plr2 = new Player(name2);
 	
    ActiveTable frame = new ActiveTable(plr1,plr2);
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }
}