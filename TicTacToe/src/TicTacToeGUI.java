import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TicTacToeGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private TicTacToe ttt;
	
	private JPanel board, message;
	private JButton[] buttons;
	private JLabel status;
	/**
	 * Constructor - creates a new instance of the tic tac toe object
	 * 
	 */
	public TicTacToeGUI()
	{
		/**
		 * 
		 *  TODO:
		 *  create an instance of the Tic Tac Toe object
		 *  
		 *  creates the panels for board and message.
		 *  set title to Tic Tac Toe
		 *  set the size of the window to 300 x 300
		 *  add the board and message to the content pane of the frame
		 */
	}
	
	/**
	 * createBoard creates the panel for the 3x3 button matrix
	 * adds to the frame
	 * 
	 * @return
	 */
	private JPanel createBoard()
	{
		/**
		 * 	TODO:
		 * 		Create a JPanel with a grid layout that has 3 rows, 3 columns
		 * 		instantiate the buttons array with the size of 9 (3x3)
		 * 		
		 * 		In a loop [0, 9):
		 * 			create a button
		 * 			on this button add an actionCommand for its index
		 * 			addActionListner to the button
		 * 			then add the button to the buttons array
		 * 			and add it to the buttons panel
		 * 
		 *  	return the panel
		 * 
		 */
		return null;
	}
	
	
class ButtonListener implements ActionListener 
{
	/**
	 * Override for actionPerformed
	 * 
	 * every button that has this actionlistener. This will call method 
	 * when the action is performed, each button has a click event; 
	 * whenever it is clicked, this is called.
	 * 
	 * the method grabs the index from the actionCommand
	 * call the TicTacToe marking method using the index from the button
	 * 
	 * if marked, then check for winners, or draw. if neither, continue
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int index = Integer.parseInt(e.getActionCommand());
		boolean marked = ttt.mark(index);
		
		if(marked){
			int turn = ttt.getTurn();
			if(turn % 2 == 0){
				buttons[index].setBackground(Color.BLUE);
				// set Background color to Blue	
			}
			else{
				buttons[index].setBackground(Color.RED);
				// set Background color to Red
			}
			// call change status text method
			if(ttt.checkForWinners()){
				
				int player = 0;
				if(turn % 2 == 0)
					player = 2;
				else
					player = 1;
				
				// use the JOptionPane to display a message stating the player x wins
				// then disable the action listeners using the removeAllActionListeners method
			}
			else if(ttt.isFull())
			{
				// use the JOptionPane to display a message stating its a draw
				// then disable the action listeners using the removeAllActionListeners method
			}
		}
	}
}
	/**
	 * creates a status bar in a new panel, adds message label
	 * 
	 * @return new Panel
	 */
	private JPanel createStatus()
	{
		JPanel m = new JPanel();
		m.setPreferredSize(new Dimension(300, 25));
		status = new JLabel("");
		m.add(status);
		
		return m;
	}
	

	
	/**
	 *   removes the actionListener from the button
	 */
	public void removeAllActionListeners()
	{
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++){
				ActionListener[] al = buttons[i * 3 + j].getActionListeners();
				for(ActionListener a:al)
					buttons[i * 3 + j].removeActionListener(a);
			}
	}
	
	/**
	 *  changes the status message after someone picks a block
	 * @param index
	 * @param turn
	 */
	private void changeStatusText(int index, int turn)
	{
		int player = 0;
		if(turn % 2 == 0)
			player = 2;
		else
			player = 1;
		
		String text = "Player " + player + " played: ";
		
		switch(index){
		case 0: text += " Top Left Corner"; break;
		case 1: text += " Top Center"; break;
		case 2: text += " Top Right Corner"; break;
		case 3: text += " Middle Left"; break;
		case 4: text += " Middle Center"; break;
		case 5: text += " Middle Right"; break;
		case 6: text += " Bottom Left Corner"; break;
		case 7: text += " Bottom Center"; break;
		case 8: text += " Bottom Right Corner"; break;
		}
		status.setText(text);
	}	
	
	/** Testing Method
	 * 
	 *  this method makes sure the tic tac toe works
	 */
	public void test()
	{
		System.out.println();
		ttt.printBoard();
		
		ttt.mark(0 * 3 + 2);
		if(ttt.checkForWinners()){
			System.out.printf("Awesome");
		}
		System.out.println();
		ttt.printBoard();
		
		ttt.mark(1* 3 + 0);
		if(ttt.checkForWinners()){
			System.out.printf("Awesome");
		}
		System.out.println();
		ttt.printBoard();
		
		ttt.mark(1* 3 + 2);
		if(ttt.checkForWinners()){
			System.out.printf("Awesome");
		}
		System.out.println();
		ttt.printBoard();
		
		ttt.mark(0* 3 + 0);
		if(ttt.checkForWinners()){
			System.out.printf("Awesome");
		}
		System.out.println();
		ttt.printBoard();
		
		ttt.mark(2* 3 + 2);
		if(ttt.checkForWinners()){
			System.out.printf("Awesome");
		}
		System.out.println();
		ttt.printBoard();
	}
}
