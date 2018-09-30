import com.sun.rowset.internal.Row;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Thomas on 2014-12-15.
 */
public class GamePanel extends JPanel {
    private int COLUMNS;
    private int ROWS;
    private NumSquare[][] numbers;

    public GamePanel(int xSize, int ySize) {
        init(xSize, ySize);
    }

    public void init(int xSize, int ySize) {
        removeAll();
        COLUMNS = xSize;
        ROWS = ySize;
        setLayout(new GridLayout(ROWS, COLUMNS));
        numbers = new NumSquare[COLUMNS][ROWS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                numbers[col][row] = new NumSquare(0);
                add(numbers[col][row]);
            }
        }
    }

    public int getValue(int col, int row) {
        return numbers[col][row].getValue();
    }

    public void setValue(int col, int row, int n) {
        numbers[col][row].setValue(n);
    }
}
