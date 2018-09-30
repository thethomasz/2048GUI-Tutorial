import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Thomas on 2014-12-14.
 */
public class GameFrame extends JFrame {
    JPanel panelMain;
    GamePanel panelGame;
    JPanel panelButtons;
    JButton btnLeft;
    JButton btnRight;
    JButton btnUp;
    JButton btnDown;
    GameCode game;

    public GameFrame() {
        super("2048");
        panelMain = new JPanel();
        BoxLayout layout = new BoxLayout(panelMain, BoxLayout.Y_AXIS);
        panelMain.setLayout(layout);
        add(panelMain);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    game.slideLeft();
                    game.addNew2();
                    updateNumSquares();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    game.slideRight();
                    game.addNew2();
                    updateNumSquares();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    game.slideUp();
                    game.addNew2();
                    updateNumSquares();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    game.slideDown();
                    game.addNew2();
                    updateNumSquares();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        game = new GameCode(4, 4);

        panelGame = new GamePanel(game.COLUMNS, game.ROWS);
        panelMain.add(panelGame);

        panelButtons = new JPanel();
        panelButtons.setBackground(Color.LIGHT_GRAY);
        panelButtons.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        btnLeft = new JButton("Slide Left");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panelButtons.add(btnLeft, constraints);
        ActionListener left = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.slideLeft();
                game.addNew2();
                updateNumSquares();
            }
        };
        btnLeft.addActionListener(left);
        btnLeft.setFocusable(false);

        btnRight = new JButton("Slide Right");
        constraints.gridx = 2;
        constraints.gridy = 1;
        panelButtons.add(btnRight, constraints);
        ActionListener right = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.slideRight();
                game.addNew2();
                updateNumSquares();
            }
        };
        btnRight.addActionListener(right);
        btnRight.setFocusable(false);

        btnUp = new JButton("   Slide Up   ");
        constraints.gridx = 1;
        constraints.gridy = 0;
        panelButtons.add(btnUp, constraints);
        ActionListener up = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.slideUp();
                game.addNew2();
                updateNumSquares();
            }
        };
        btnUp.addActionListener(up);
        btnUp.setFocusable(false);

        btnDown = new JButton("Slide Down");
        constraints.gridx = 1;
        constraints.gridy = 2;
        panelButtons.add(btnDown, constraints);
        ActionListener down = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.slideDown();
                game.addNew2();
                updateNumSquares();
            }
        };
        btnDown.addActionListener(down);
        btnDown.setFocusable(false);

        panelMain.add(panelButtons);

        game.addNew2();
        game.addNew2();
        updateNumSquares();
    }

    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void updateNumSquares() {
        for (int i = 0; i < game.COLUMNS; i++) {
            for (int j = 0; j < game.ROWS; j++) {
                panelGame.setValue(i, j, game.getCellValue(i, j));
            }
        }
        panelGame.repaint();
        if (game.canPlay() == false) {
            gameOver();
        }
    }

    public void gameOver() {
        String[] options = {"New Game", "Exit"};
        int result = JOptionPane.showOptionDialog(
                this,
                "Game over.\nYour score was " + game.getScore(),
                "Game Over!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);
        if (result == JOptionPane.YES_OPTION) {
            newGame();
        } else {
            System.exit(0);
        }
    }

    public void newGame() {
        game = new GameCode(4, 4);
        game.addNew2();
        game.addNew2();
        updateNumSquares();
        pack();
    }
}
