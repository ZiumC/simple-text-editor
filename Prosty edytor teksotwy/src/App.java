import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    private BottomBar bottomBar = new BottomBar();
    private JPanel northPanel = new JPanel();
    private TopMenu topMenuBar;
    private JTextArea jTextArea = new JTextArea(15, 50);



    App() {
        int height, weight;
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        height = (int)size.getHeight()-60;
        weight = (int)size.getWidth();
        defOptions(this);

        setTitle("Simple Editor - without title");
        topMenuBar = new TopMenu(this, jTextArea, bottomBar);

        northPanel.add(topMenuBar);

        add(northPanel, BorderLayout.PAGE_START);
        getContentPane().add(textField(), BorderLayout.CENTER);

        bottomBar.add(bottomBar.getLeftComp());
        bottomBar.add(bottomBar.getRightComp());
        bottomBar.setLayout(new BoxLayout(bottomBar, BoxLayout.X_AXIS));
        add(bottomBar, BorderLayout.PAGE_END);


        setSize(weight,height);

    }


    private JPanel textField() {
        JPanel centerTextField = new JPanel();
        JScrollPane scrollPane = new JScrollPane(jTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        centerTextField.setLayout(new BorderLayout(10, 10));
        jTextArea.setBackground(Color.gray.brighter());
        centerTextField.add(scrollPane);

        return centerTextField;
    }

    private void defOptions(JFrame jf) {
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);

    }
}
