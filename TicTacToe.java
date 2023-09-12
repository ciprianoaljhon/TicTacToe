import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe extends JFrame implements ActionListener {

	/*
	 * !!! TODO !!! 8. Add Scoreboard 9. Replace String with JLabel after clicked
	 * for better UI
	 */
	int[][] combinations = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 4, 8 }, { 2, 4, 6 }, { 0, 3, 6 }, { 1, 4, 7 },
			{ 2, 5, 8 } };
	String[] occupied = { "", "", "", "", "", "", "", "", "" };
	boolean playerOneTurn = true;
	int turnNo = 0, pattern = 0;
	JButton[] comp = new JButton[9];

	public static void main(String[] args) {
		TicTacToe board = new TicTacToe();
		board.setVisible(true);
	}

	public TicTacToe() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(3, 3));
		setTitle("Tic Tac Toe");
		for (int i = 0; i < 9; i++) {
			comp[i] = new JButton();
			comp[i].addActionListener(this);
			comp[i].setFocusable(false);
			add(comp[i]);
		}
		setSize(420, 420);
		setLocationRelativeTo(null);
	}

	private void check(int clicked) {
		comp[clicked].setBackground(playerOneTurn ? Color.red : Color.blue);
		turnNo++;

		occupied[clicked] = playerOneTurn ? "X" : "O";

		if (turnNo > 3) {
			loop_1: for (int i = 0; i < combinations.length; i++) {
				loop_2: for (int j = 0; j < combinations[i].length; j++) {
					if (combinations[i][j] == clicked) {
						loop_3: for (int k = 0; k < combinations[i].length; k++) {
							if (occupied[combinations[i][k]] == (playerOneTurn ? "X" : "O")) {
								System.out.println(combinations[i][k]);
								pattern++;
								if (pattern == 3) {
									JOptionPane.showMessageDialog(null,
											playerOneTurn ? "Player One Wins" : "Player Two Wins");
									restart();
									break loop_1;
								}
							} else {
								pattern = 0;
								System.out.println("REA");
								break loop_3;
							}
						}
					}
				}
			}
		}
		if (turnNo > 8) {
			JOptionPane.showMessageDialog(null, "Draw!");
			restart();
		}
		playerOneTurn = !playerOneTurn;
	}

	private void restart() {
		this.dispose();
		main(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < comp.length; i++) {
			if (e.getSource() == comp[i]) {
				comp[i].setText(playerOneTurn ? "X" : "O");
				comp[i].setEnabled(false);
				comp[i].setFont(new Font("Times New Roman", Font.BOLD, 30));
				check(i);
			}
		}
	}
}
