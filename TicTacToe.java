import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private static final int SIZE = 3;
    private JButton[][] buttons;
    private boolean playerXTurn = true;

    public TicTacToe() {
        buttons = new JButton[SIZE][SIZE];
        setLayout(new GridLayout(SIZE, SIZE));

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        if (!buttonClicked.getText().equals("")) {
            return;
        }

        if (playerXTurn) {
            buttonClicked.setText("O");
        } else {
            buttonClicked.setText("X");
        }

        playerXTurn = !playerXTurn;
        checkForWinner();
    }

    private void checkForWinner() {
        // Check rows and columns
        for (int i = 0; i < SIZE; i++) {
            if (checkLine(buttons[i][0], buttons[i][1], buttons[i][2]) ||
                checkLine(buttons[0][i], buttons[1][i], buttons[2][i])) {
                return;
            }
        }

        // Check diagonals
        if (checkLine(buttons[0][0], buttons[1][1], buttons[2][2]) ||
            checkLine(buttons[0][2], buttons[1][1], buttons[2][0])) {
            return;
        }

        // Check for draw
        boolean draw = true;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (buttons[i][j].getText().equals("")) {
                    draw = false;
                    break;
                }
            }
        }
        if (draw) {
            JOptionPane.showMessageDialog(this, "The game is a draw!");
            resetBoard();
        }
    }

    private boolean checkLine(JButton b1, JButton b2, JButton b3) {
        if (!b1.getText().equals("") && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText())) {
            JOptionPane.showMessageDialog(this, "Player " + b1.getText() + " wins!");
            resetBoard();
            return true;
        }
        return false;
    }

    private void resetBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j].setText("");
            }
        }
        playerXTurn = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
