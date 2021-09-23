import javax.swing.*;
import java.awt.*;

public class BottomBar extends JComponent {

    private JLabel bg;
    private JLabel fg;
    private Color fgLastUsedColors[] = new Color[2];
    private Color bgLastUsedColors[] = new Color[2];
    private int fgIndex;
    private int bgIndex;
    private IconCircle iconFG;
    private IconCircle iconBG;
    private JLabel size;
    private JLabel state;

    BottomBar() {
        fgIndex = 0;
        bgIndex = 0;
        iconFG = new IconCircle(1, 1);
        iconBG = new IconCircle(1, 1);
        state = new JLabel("new");
        fg = new JLabel("fg");
        bg = new JLabel("bg");
        size = new JLabel("size");
    }


    public JPanel getLeftComp() {

        JPanel bottomLeftPanel = new JPanel();
        bottomLeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        bottomLeftPanel.add(iconFG);
        bottomLeftPanel.add(fg);
        bottomLeftPanel.add(iconBG);
        bottomLeftPanel.add(bg);
        bottomLeftPanel.add(size);

        return bottomLeftPanel;
    }

    public void setIconFG(int w, int h) {
        if (fgIndex > 0) {
            iconFG.setS(w, h);
            iconFG.repaint();
        }
    }

    public void setIconBG(int w, int h) {
        if (bgIndex > 0) {
            iconBG.setS(w, h);
            iconBG.repaint();
        }
    }

    public JPanel getRightComp() {
        JPanel bottomRightPanel = new JPanel();
        bottomRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottomRightPanel.add(state);
        return bottomRightPanel;
    }

    public void setLastTxtSize(String t) {
        size.setText(t);
    }

    public JLabel getState() {
        return state;
    }

    public void setFgLastUsedColors(Color c) {
        fgIndex++;
        fgLastUsedColors[0] = c;
        Color tmp = fgLastUsedColors[0];
        fgLastUsedColors[0] = fgLastUsedColors[1];
        fgLastUsedColors[1] = tmp;
        if (fgIndex > 1) {
            iconFG.setForeground(fgLastUsedColors[0]);
        }
        iconFG.repaint();

    }

    public void setBgLastUsedColors(Color c) {
        bgIndex++;
        bgLastUsedColors[0] = c;
        Color tmp = bgLastUsedColors[0];
        bgLastUsedColors[0] = bgLastUsedColors[1];
        bgLastUsedColors[1] = tmp;

        if (bgIndex > 1) {
            iconBG.setColor(bgLastUsedColors[0]);
        }

        iconBG.repaint();
    }

}