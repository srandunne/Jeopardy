package jeopardy;
/*
 * @author Sean Randunne, Shalin Mehta
 * @teacher(Ms. Denna)
 * @version(12/17/2017)
 * This is the ButtonRenderer class 
 * which is used to render the buttons 
 */
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;



public class ButtonRenderer extends JButton implements TableCellRenderer {
  
  public ButtonRenderer() {
    setOpaque(true);
  }
  /*
  This methods helps render the buttons and style them
  @ param: 
  jtable table: our tbale creator
  Value: the point value/total
  is selected: whether the button is clicked or not
  hasFocus: whether the cursor is on the botton or not
  row: the rows in our table
  column: the columns in our table
           
  */
   
  public Component getTableCellRendererComponent(JTable table, Object value,
                   boolean isSelected, boolean hasFocus, int row, int column) {
    if (isSelected) {
     
    } else{
      setForeground(table.getForeground());
      setBackground(UIManager.getColor("Button.background"));
    }
    setText( (value ==null) ? "" : value.toString() );
    return this;
  }
}