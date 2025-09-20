import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI {
    private JFrame frame;
    private JButton[][] buttons = new JButton[3][3];
    private char player = 'X';

    public TicTacToeGUI() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,3));

        // Initialize buttons
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                JButton button = new JButton("");
                button.setFont(new Font("Arial", Font.BOLD, 60));
                buttons[i][j] = button;
                panel.add(button);

                int row = i;
                int col = j;

                button.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        button.setText(String.valueOf(player));
                        button.setEnabled(false);

                        if(haveWon(row, col)){
                            JOptionPane.showMessageDialog(frame, "Player "+player+" wins!");
                            resetBoard();
                        } else if(isDraw()){
                            JOptionPane.showMessageDialog(frame, "It's a Draw!");
                            resetBoard();
                        } else {
                            player = (player=='X')?'O':'X';
                        }
                    }
                });
            }
        }

        frame.add(panel);
        frame.setVisible(true);
    }

    private boolean haveWon(int row, int col){
        // Check row
        if(buttons[row][0].getText().equals(""+player) &&
           buttons[row][1].getText().equals(""+player) &&
           buttons[row][2].getText().equals(""+player)) return true;

        // Check column
        if(buttons[0][col].getText().equals(""+player) &&
           buttons[1][col].getText().equals(""+player) &&
           buttons[2][col].getText().equals(""+player)) return true;

        // Diagonals
        if(buttons[0][0].getText().equals(""+player) &&
           buttons[1][1].getText().equals(""+player) &&
           buttons[2][2].getText().equals(""+player)) return true;

        if(buttons[0][2].getText().equals(""+player) &&
           buttons[1][1].getText().equals(""+player) &&
           buttons[2][0].getText().equals(""+player)) return true;

        return false;
    }

    private boolean isDraw(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(buttons[i][j].getText().equals("")) return false;
            }
        }
        return true;
    }

    private void resetBoard(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        player = 'X';
    }

    public static void main(String[] args){
        new TicTacToeGUI();
    }
}
