package ProjectOOP;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.io.File;
public class Board{
    private static final long serialVersionUID = 1L;
    final int BoardWidth = 10;
    final int BoardHeight = 22;
    Timer timer;
    boolean Start = false;
    boolean Pause = false;
    JLabel statusbar;
    
    public Board(Tetris parent) {
        setFocusable(true);
        curPiece = new Shape();
        timer = new Timer(400, this);
        timer.start(); 
        statusbar =  parent.getStatusBar();
        addKeyListener(new TAdapter());
        clearBoard();  
    }

    public void actionPerformed(ActionEvent e) {
        if (FallingFinished) {
            FallingFinished = false;
            newPiece();
        } else {
            oneLineDown();
        }
    }

    int squareWidth() { return (int) getSize().getWidth() / BoardWidth; }
    int squareHeight() { return (int) getSize().getHeight() / BoardHeight; }
    

    //Initiate game start and continue the game when it is paused
    public void start(){
        if (Pause)
            return;
        Start = true;
        FallingFinished = false;
        numLinesRemoved = 0;
        clearBoard();
        newPiece();
        timer.start();
    }
    
    //Method to temporarily pause the game
    private void pause(){
        if (!Start)
            return;
        Pause = !Pause;
        if (Pause) {
            timer.stop();

            JOptionPane.showMessageDialog(null, "Game Paused", "Tetris", JOptionPane.INFORMATION_MESSAGE);
        } else {
            timer.start();
        }
        repaint();
    }

    //Set Background to the game panel
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        ImageIcon background = new ImageIcon(BackgroundPath);
        g.drawImage(background.getImage(),0,0,this.getWidth(),this.getHeight(),null);
    }

    //Display Game Over screen
    public void GameOver(){
        //GameOver screen formatting and display
        statusbar.setForeground(Color.RED);
        statusbar.setFont(new Font("Calibri", Font.BOLD, 50));
        statusbar.setText("GAME OVER");
        add(statusbar, BorderLayout.CENTER);
        int confirmed = JOptionPane.showConfirmDialog(null, "Thank you for playing!\n" + "Score: " 
        + String.valueOf(numLinesRemoved*100) + "\nWould you like to play again?" ,"Tetris", JOptionPane.YES_NO_OPTION);
    }

}
