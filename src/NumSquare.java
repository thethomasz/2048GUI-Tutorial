import javax.swing.*;
import java.awt.*;

/**
 * Created by Thomas on 2014-12-14.
 */
public class NumSquare extends JComponent {
    private static final int SCALE = 100;
    private static final int BORDER = SCALE / 20;
    private static final int FONT_SIZE = (int) (SCALE * 0.4);
    private static final Font FONT = new Font("Consolas", Font.PLAIN, FONT_SIZE);
    private int value;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.add(new NumSquare(16));
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public NumSquare(int value) {
        this.value = value;
        setFont(FONT);
        setPreferredSize(new Dimension(SCALE, SCALE));
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void paintComponent(Graphics g) {
        ((Graphics2D)g).setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        Color color;
        if (value == 0) {
            color = Color.CYAN;
        } else {
            int len = Integer.numberOfTrailingZeros(value);
            color = Color.getHSBColor(len / 11.0f, 0.8f, 0.5f);
        }
        g.setColor(color);
        g.fillRoundRect(BORDER, BORDER,
                getWidth() - (BORDER * 2),
                getHeight() - (BORDER * 2),
                SCALE / 3,
                SCALE / 3);
        g.setColor(Color.LIGHT_GRAY);
        FontMetrics metrics = getFontMetrics(FONT);
        g.drawString(String.valueOf(value),
                getWidth() / 2 - metrics.stringWidth(String.valueOf(value)) / 2,
                getHeight() / 2 + metrics.getAscent() / 3);
    }
}
