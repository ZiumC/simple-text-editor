import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class IconCircle extends JPanel implements Icon {

    private int width;
    private int height;
    private Color color;

    IconCircle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(width + width, height / 2, width, height);
        g2.setColor(color);
        g2.fill(circle);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(color);
        g.fillOval(0,0,width,height);
    }

    public void setS(int w, int h){
        width = w;
        height = h;
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
        return height;
    }
}
