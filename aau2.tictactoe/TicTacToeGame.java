import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToeGame implements ActionListener {

    public static void main(String[] args) {
        new TicTacToeGame(); // This creates an instance of the TicTacToeGame class and runs the constructor
    }

    JFrame frame = new JFrame();
    JPanel textPanel = new JPanel();
    JPanel gridPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel inputPanel = new JPanel();
    JLabel textfield = new JLabel();
    JTextField input = new JTextField();
    JButton[] gridBtn = new JButton[9];
    JButton Start, Replay = new JButton();
    Random random = new Random();
    String username = "";
    int moves = 0;
    boolean won= false;

    TicTacToeGame() { //setting up everything
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(243, 141, 206));
        frame.setTitle("Tic-Tac-Toe");
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(182, 80, 28));
        textfield.setForeground(new Color(243, 222, 28));
        textfield.setFont(new Font("Comic Sans", Font.PLAIN, 69));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setOpaque(true);
        textPanel.setLayout(new BorderLayout());

        gridPanel.setLayout(new GridLayout(3, 3));
        gridPanel.setBackground(new Color(182, 141, 243));

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        Start = new JButton("Start");
        Start.addActionListener(this);
        Start.setPreferredSize(new Dimension(400, 400));
        buttonPanel.add(Start,BorderLayout.NORTH);
        Replay = new JButton("Re-play");
        Replay.addActionListener(this);
        Replay.setPreferredSize(new Dimension(400, 400));
        buttonPanel.add(Replay,BorderLayout.SOUTH);

        input = new JTextField(40);
        inputPanel.add(input);

        Dimension d = new Dimension(200,200);
        for (int i = 0; i < 9; i++) {
            gridBtn[i] = new JButton();
            gridBtn[i].setPreferredSize(d);
            gridPanel.add(gridBtn[i]);
            gridBtn[i].setFont(new Font("Comic Sans", Font.BOLD, 120));
            gridBtn[i].setForeground(new Color(0, 0, 255));
            gridBtn[i].setFocusable(false);
            gridBtn[i].setEnabled(false);
            gridBtn[i].addActionListener(this);
        }

        textPanel.add(textfield);

        frame.add(textPanel, BorderLayout.NORTH);
        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.EAST);
        frame.add(inputPanel, BorderLayout.SOUTH);
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        frame.setSize(520,600);

        JMenu menu;
        JMenuItem item;

        menu = new JMenu("Settings");
        menubar.add(menu);

        item = new JMenuItem("Reset");
        item.addActionListener(e -> menuRestart());
        menu.add(item);
        item = new JMenuItem("Quit");
        item.addActionListener(e -> menuQuit());
        menu.add(item);

        Replay.setEnabled(false);

        textfield.setText("Please enter username");
    }

    public void menuRestart() { //restart option of the menu
        frame.dispose();
        new TicTacToeGame();
    }

    public void menuQuit() {  //quit option of the menu
        frame.dispose();
    }

    public void startGame() { //resets it to the state at the beginning in a safe way
        won= false;
        textfield.setText(username + ", it is your turn to move!");
        moves = 0;
        for (int i = 0; i < 9; i++) {
            gridBtn[i].setFont(new Font("Comic Sans", Font.BOLD, 150));
            gridBtn[i].setForeground(new Color(0, 0, 255));
            gridBtn[i].setFocusable(false);
            gridBtn[i].setEnabled(true);
            gridBtn[i].setBackground(null);
        }

        Start.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) { 
        if (e.getSource() == Start) {
            username = input.getText();
            if (!username.equals("")) {
                startGame();
                Start.setEnabled(false);
            }
        } else if (e.getSource() == Replay) {
            for (int i = 0; i < 9; i++) {
                gridBtn[i].setText("");

            }
            startGame();
            Replay.setEnabled(false);

        }
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == gridBtn[i]) {
                Replay.setEnabled(true);
                if (gridBtn[i].getText() == "") {
                    gridBtn[i].setForeground(new Color(255, 0, 0));
                    gridBtn[i].setText("O");
                    checkWin();
                    if (moves==9&&won==false) {
                        textfield.setText("Match is Tied.");
                        for (int j = 0; j < 9; j++) {
                            gridBtn[j].setEnabled(false);
                        }
                        break;
                    }
                    
                    if (won==false)
                    {
                    computerMove();
                    checkWin();   
                }
                }
            }
        }
    }

    public void computerMove() { //the computer checks which squares are the closest to the other occupied player squares

        int maxCount = 0;
        int[] closeToSquare = new int[9];
        int numOfBestMoves = 0;
        for (int i = 0; i < 9; i++) {
            int count = 0;
            if (gridBtn[i].getText() == "") {
                switch (i) {
                    case 0:
                        if (gridBtn[1].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[2].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[3].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[6].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[4].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[8].getText().equals("O")) {
                            count++;
                        }
                        break;
                    case 1:
                        if (gridBtn[0].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[2].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[4].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[7].getText().equals("O")) {
                            count++;
                        }
                        break;
                    case 2:
                        if (gridBtn[0].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[1].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[5].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[8].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[4].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[6].getText().equals("O")) {
                            count++;
                        }
                        break;
                    case 3:
                        if (gridBtn[0].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[6].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[4].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[5].getText().equals("O")) {
                            count++;
                        }
                        break;
                    case 4:
                        if (gridBtn[0].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[1].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[2].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[3].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[5].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[6].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[7].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[8].getText().equals("O")) {
                            count++;
                        }
                        break;
                    case 5:
                        if (gridBtn[2].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[7].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[3].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[4].getText().equals("O")) {
                            count++;
                        }
                        break;
                    case 6:
                        if (gridBtn[0].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[3].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[4].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[2].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[7].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[8].getText().equals("O")) {
                            count++;
                        }
                        break;
                    case 7:
                        if (gridBtn[1].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[4].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[6].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[8].getText().equals("O")) {
                            count++;
                        }
                        break;
                    case 8:
                        if (gridBtn[0].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[4].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[2].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[5].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[6].getText().equals("O")) {
                            count++;
                        }
                        if (gridBtn[7].getText().equals("O")) {
                            count++;
                        }
                        break;
                }
                if (maxCount < count)
                    maxCount = count;

            }
            closeToSquare[i] = count;
        }


        for (int i = 0; i < 9; i++) {
            if (closeToSquare[i] == maxCount)
                numOfBestMoves++;
        }

        int[] bestMoves = new int[numOfBestMoves];
        int count = 0;
        for (int i = 0; i < 9; i++) {
            if (closeToSquare[i] == maxCount) {
                bestMoves[count] = i;
                count++;
            }
        }
        Random rand = new Random();
        gridBtn[bestMoves[rand.nextInt(numOfBestMoves)]].setText("X");
    }

    public void checkWin() { //checks wins

        moves++;
        if (moves > 4) {
            if ((gridBtn[0].getText() == "X") && (gridBtn[1].getText() == "X") && (gridBtn[2].getText() == "X")) {
                xWins(0, 1, 2);
            } else if ((gridBtn[0].getText() == "X") && (gridBtn[4].getText() == "X")
                    && (gridBtn[8].getText() == "X")) {
                xWins(0, 4, 8);
            } else if ((gridBtn[0].getText() == "X") && (gridBtn[3].getText() == "X")
                    && (gridBtn[6].getText() == "X")) {
                xWins(0, 3, 6);
            } else if ((gridBtn[1].getText() == "X") && (gridBtn[4].getText() == "X")
                    && (gridBtn[7].getText() == "X")) {
                xWins(1, 4, 7);
            } else if ((gridBtn[2].getText() == "X") && (gridBtn[4].getText() == "X")
                    && (gridBtn[6].getText() == "X")) {
                xWins(2, 4, 6);
            } else if ((gridBtn[2].getText() == "X") && (gridBtn[5].getText() == "X")
                    && (gridBtn[8].getText() == "X")) {
                xWins(2, 5, 8);
            } else if ((gridBtn[3].getText() == "X") && (gridBtn[4].getText() == "X")
                    && (gridBtn[5].getText() == "X")) {
                xWins(3, 4, 5);
            } else if ((gridBtn[6].getText() == "X") && (gridBtn[7].getText() == "X")
                    && (gridBtn[8].getText() == "X")) {
                xWins(6, 7, 8);
            }

            else if ((gridBtn[0].getText() == "O") && (gridBtn[1].getText() == "O") && (gridBtn[2].getText() == "O")) {
                oWins(0, 1, 2);
            } else if ((gridBtn[0].getText() == "O") && (gridBtn[3].getText() == "O")
                    && (gridBtn[6].getText() == "O")) {
                oWins(0, 3, 6);
            } else if ((gridBtn[0].getText() == "O") && (gridBtn[4].getText() == "O")
                    && (gridBtn[8].getText() == "O")) {
                oWins(0, 4, 8);
            } else if ((gridBtn[1].getText() == "O") && (gridBtn[4].getText() == "O")
                    && (gridBtn[7].getText() == "O")) {
                oWins(1, 4, 7);
            } else if ((gridBtn[2].getText() == "O") && (gridBtn[4].getText() == "O")
                    && (gridBtn[6].getText() == "O")) {
                oWins(2, 4, 6);
            } else if ((gridBtn[2].getText() == "O") && (gridBtn[5].getText() == "O")
                    && (gridBtn[8].getText() == "O")) {
                oWins(2, 5, 8);
            } else if ((gridBtn[3].getText() == "O") && (gridBtn[4].getText() == "O")
                    && (gridBtn[5].getText() == "O")) {
                oWins(3, 4, 5);
            } else if ((gridBtn[6].getText() == "O") && (gridBtn[7].getText() == "O")
                    && (gridBtn[8].getText() == "O")) {
                oWins(6, 7, 8);
            }
            
        }
    }

    public void xWins(int x1, int x2, int x3) { //looks nice when they win
        won=true;
        gridBtn[x1].setBackground(Color.RED);
        gridBtn[x2].setBackground(Color.RED);
        gridBtn[x3].setBackground(Color.RED);
        for (int i = 0; i < 9; i++) {
            gridBtn[i].setEnabled(false);
        }
        textfield.setText("Computer Wins");
    }

    public void oWins(int x1, int x2, int x3) { //looks nice when they win
        won=true;
        gridBtn[x1].setBackground(Color.GREEN);
        gridBtn[x2].setBackground(Color.GREEN);
        gridBtn[x3].setBackground(Color.GREEN);
        for (int i = 0; i < 9; i++) {
            gridBtn[i].setEnabled(false);
        }
        textfield.setText(username + ", you win!!!");
    }

}