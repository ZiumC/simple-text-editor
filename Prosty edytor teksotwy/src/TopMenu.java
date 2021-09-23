import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;


public class TopMenu extends JComponent {

    private Job job = new Job();
    private Home home = new Home();
    private School school = new School();
    private JFrame fr;
    private JTextArea jta;
    private int arrLengthOfFieldsFILE;
    private int arrLengthOfFieldsEDIT;
    private int arrLengthOfMENUBAR;
    private int numberOfFontSizes;
    private int fontSizes[];
    private String colorTextNames[];
    private String colorBackgroundNames[];
    private Color colorsForText[];
    private Color colorsForBackground[];
    private Font fontStyleForAllTypesOfColors;
    private Font defaultTextFont;
    private BottomBar bottomBar;
    private AppFileChooser fileChooser;
    private ButtonGroup groupButtonChangeColorText;
    private ButtonGroup groupButtonChangeColorBackground;


    //główny pasek
    private JMenuBar menuBar;


    TopMenu(JFrame frame, JTextArea jta, BottomBar bottomBar) {
        fontStyleForAllTypesOfColors = new Font("Dialog", Font.BOLD, 13);
        defaultTextFont = new Font("Dialog", Font.BOLD, 10);
        jta.setFont(defaultTextFont);
        jta.getDocument().addDocumentListener(new TextChangeHandler());

        {
            //tutaj nastepuje cala modyfikacja wszystkich guzikow z kolorami (tla/tekstu) oraz iloscia rozmarow trzcionek
            arrLengthOfFieldsFILE = 1;
            arrLengthOfFieldsEDIT = 1;
            arrLengthOfMENUBAR = 1;
            numberOfFontSizes = 9;

            colorTextNames = new String[]{"Green", "Orange", "Red", "Black", "White", "Yellow", "Blue"};
            colorBackgroundNames = new String[]{"Green", "Orange", "Red", "Black", "White", "Yellow", "Blue", "Gray"};
            colorsForText = new Color[]{Color.green, Color.orange, Color.red, Color.black, Color.white, Color.yellow, Color.blue};
            colorsForBackground = new Color[]{Color.green, Color.orange, Color.red, Color.black, Color.white, Color.yellow, Color.blue, Color.gray.brighter()};
        }

        fr = frame;

        initializeMainMenuBar();
        fr.setJMenuBar(menuBar);
        this.jta = jta;
        this.bottomBar = bottomBar;

        fileChooser = new AppFileChooser(jta, frame);
    }

    private IconCircle[] iconsForText() {
        IconCircle circleOfArray[] = new IconCircle[colorTextNames.length];

        for (int i = 0; i < circleOfArray.length; i++) {
            IconCircle c = new IconCircle(10, 10);
            c.setColor(colorsForText[i]);
            circleOfArray[i] = c;
        }
        return circleOfArray;
    }

    private IconCircle[] iconsForBackGround() {
        IconCircle circleOfArray[] = new IconCircle[colorBackgroundNames.length];

        for (int i = 0; i < circleOfArray.length; i++) {
            IconCircle c = new IconCircle(10, 10);
            c.setColor(colorsForBackground[i]);
            circleOfArray[i] = c;
        }
        return circleOfArray;
    }


    private void initializeMainMenuBar() {
        menuBar = new JMenuBar();

        for (int i = 0; i < arrLengthOfMENUBAR; i++) {
            menuBar.add(initializeFold()[i]);
        }

    }

    private JMenu[] initializeFold() {
        JMenu menuFile = new JMenu("File");
        JMenu menuEdit = new JMenu("Edit");
        JMenu mAddress = new JMenu("Address");
        JMenu mSetAddress = new JMenu("Set Address");
        JMenu menuOptions = new JMenu("Options");

        menuEdit.add(mAddress);
        menuEdit.add(mSetAddress);

        menuOptions.add(txtColor());
        menuOptions.add(backGroundColor());
        menuOptions.add(fontSizes());

        JMenu jMenu[] = {menuFile, menuEdit, menuOptions};


        for (int i = 0; i < arrLengthOfFieldsFILE; i++) {
            if (i == 3) {
                menuFile.addSeparator();
            }
            menuFile.add(initializeFieldsFILE()[i]);
        }

        for (int i = 0; i < arrLengthOfFieldsEDIT; i++) {
            mAddress.add(initializeFieldsEDIT_Address()[i]);
        }

        for (int i = 0; i < initializeFieldsEDIT_SetAddress().length; i++) {
            mSetAddress.add(initializeFieldsEDIT_SetAddress()[i]);
        }

        arrLengthOfMENUBAR = jMenu.length;

        return jMenu;
    }

    private JMenuItem[] initializeFieldsEDIT_SetAddress() {
        JMenuItem job = new JMenuItem("Job");
        job.setMnemonic('J');
        job.setActionCommand("SetJ");


        JMenuItem house = new JMenuItem("House");
        house.setMnemonic('H');
        house.setActionCommand("SetH");

        JMenuItem school = new JMenuItem("School");
        school.setMnemonic('S');
        school.setActionCommand("SetS");


        JMenuItem jmiE[] = {job, house, school};

        for (int i = 0; i < jmiE.length; i++) {
            jmiE[i].addActionListener(new AddressHandler_SetAddAddress());
        }

        return jmiE;
    }

    private JMenuItem[] initializeFieldsFILE() {
        JMenuItem mOpen = new JMenuItem("Open");
        mOpen.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        mOpen.setMnemonic('O');

        JMenuItem mSave = new JMenuItem("Save");
        mSave.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        mSave.setMnemonic('S');

        JMenuItem mSaveAs = new JMenuItem("Save As");
        mSaveAs.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));
        mSaveAs.setMnemonic('a');

        JMenuItem mExit = new JMenuItem("Exit");
        mExit.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
        mExit.setMnemonic('x');

        JMenuItem jmiF[] = {mOpen, mSave, mSaveAs, mExit};
        arrLengthOfFieldsFILE = jmiF.length;
        for (int i = 0; i < jmiF.length; i++) {
            jmiF[i].addActionListener(new FileHandler());
        }
        return jmiF;
    }

    private JMenuItem[] initializeFieldsEDIT_Address() {
        JMenuItem mJob = new JMenuItem("Job");
        mJob.setAccelerator(KeyStroke.getKeyStroke("ctrl shift P"));
        mJob.setMnemonic('J');

        JMenuItem mHouse = new JMenuItem("House");
        mHouse.setAccelerator(KeyStroke.getKeyStroke("ctrl shift D"));
        mHouse.setMnemonic('H');


        JMenuItem mSchool = new JMenuItem("School");
        mSchool.setAccelerator(KeyStroke.getKeyStroke("ctrl shift S"));
        mSchool.setMnemonic('S');


        JMenuItem jmiF[] = {mJob, mHouse, mSchool};
        for (int i = 0; i < jmiF.length; i++) {
            jmiF[i].addActionListener(new AddressHandler_SetAddAddress());
        }
        arrLengthOfFieldsEDIT = jmiF.length;
        return jmiF;
    }

    private JMenu txtColor() {
        JMenu mForeground = new JMenu("Foreground");
        groupButtonChangeColorText = new ButtonGroup();

        for (int i = 0; i < colorTextNames.length; i++) {

            JRadioButtonMenuItem fgColor = new JRadioButtonMenuItem(colorTextNames[i], iconsForText()[i], false);
            fgColor.getComponent().setForeground(colorsForText[i]);
            groupButtonChangeColorText.add(fgColor);
            fgColor.setActionCommand(String.valueOf(i));
            fgColor.addActionListener(new ForegroundHandler());

            fgColor.setFont(fontStyleForAllTypesOfColors);
            mForeground.add(fgColor);
        }


        return mForeground;
    }

    private JMenu backGroundColor() {
        JMenu mBackground = new JMenu("Background");
        groupButtonChangeColorBackground = new ButtonGroup();


        for (int i = 0; i < colorBackgroundNames.length; i++) {
            JRadioButtonMenuItem bgClor = new JRadioButtonMenuItem(colorBackgroundNames[i], iconsForBackGround()[i]);
            bgClor.isSelected();
            groupButtonChangeColorBackground.add(bgClor);
            bgClor.getComponent().setForeground(colorsForBackground[i]);
            bgClor.setActionCommand(String.valueOf(i));
            bgClor.addActionListener(new BackgroundHandler());

            bgClor.setFont(fontStyleForAllTypesOfColors);
            mBackground.add(bgClor);
        }


        return mBackground;
    }

    private JMenu fontSizes() {
        JMenu mFontSize = new JMenu("Font size");
        fontSizes = new int[numberOfFontSizes];
        int num = 6;

        for (int i = 0; i < numberOfFontSizes; i++) {
            num = num + 2;
            Font f = new Font("Drial", Font.BOLD, num);

            fontSizes[i] = num;

            JMenuItem fontSize = new JMenuItem(num + " pts");
            fontSize.setFont(f);
            fontSize.setPreferredSize(new Dimension(numberOfFontSizes * numberOfFontSizes, num + 6));
            fontSize.setActionCommand(String.valueOf(i));
            fontSize.addActionListener(new TextSizeHandler());

            mFontSize.add(fontSize);
        }
        return mFontSize;
    }

    //==================================================== LISTENERS:
    private class TextChangeHandler implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            bottomBar.getState().setText("modified");
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            bottomBar.getState().setText("modified");
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            bottomBar.getState().setText("modified");
        }

    }

    private class ForegroundHandler implements ActionListener {

        private int i;

        ForegroundHandler() {
            i = 0;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            i++;
            if (i > 0)
                bottomBar.setIconFG(10, 10);
            String source = e.getActionCommand();
            int selected = Integer.parseInt(source);
            jta.setForeground(colorsForText[selected]);
            bottomBar.setFgLastUsedColors(colorsForText[selected]);
            groupButtonChangeColorText.setSelected(groupButtonChangeColorText.getSelection(), true);
        }
    }

    private class BackgroundHandler implements ActionListener {

        private int i;

        BackgroundHandler() {
            i = 0;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            i++;
            if (i > 0)
                bottomBar.setIconBG(10, 10);


            String source = e.getActionCommand();
            int selected = Integer.parseInt(source);
            jta.setBackground(colorsForBackground[selected]);
            bottomBar.setBgLastUsedColors(colorsForBackground[selected]);
            groupButtonChangeColorBackground.setSelected(groupButtonChangeColorBackground.getSelection(), true);

        }
    }

    private class TextSizeHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String source = e.getActionCommand();
            int selected = Integer.parseInt(source);
            Font changeFontSize = new Font(defaultTextFont.getName(), Font.BOLD, fontSizes[selected]);
            jta.setFont(changeFontSize);
            bottomBar.setLastTxtSize(String.valueOf(fontSizes[selected]));
        }
    }

    private class AddressHandler_SetAddAddress implements ActionListener {


        private JMultiOptionPane jmop = new JMultiOptionPane();


        @Override
        public void actionPerformed(ActionEvent e) {
            String source = e.getActionCommand();


            if (source.equals("Job")) {

                if (job.toString().equals("")){
                    job.getMessage();
                }

                jta.append(job.toString() + " ");
            } else if (source.equals("House")) {

                if (home.toString().equals("")){
                    home.getMessage();
                }

                jta.append(home.toString() + " ");
            } else if (source.equals("School")) {

                if (school.toString().equals("")){
                    school.getMessage();
                }

                jta.append(school.toString() + " ");
            } else if (source.equals("SetJ")){

                jmop.setText(job.containter_addressInfo, job);
                job.setInput(jmop.getText());

            } else if (source.equals("SetH")){

                jmop.setText(home.containter_addressInfo, home);
                home.setInput(jmop.getText());

            } else if (source.equals("SetS")){

                jmop.setText(school.containter_addressInfo, school);
                school.setInput(jmop.getText());

            }
        }
    }

    private class FileHandler implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            String source = e.getActionCommand();
            if (source.equals("Open")) {
                fileChooser.openFile();

            } else if (source.equals("Save")) {
                fileChooser.saveFile();

                if (!fileChooser.getIsSaved())
                    bottomBar.getState().setText("saved");
                else
                    bottomBar.getState().setText("modified");

            } else if (source.equals("Save As")) {

                fileChooser.saveFileAS();
                if (!fileChooser.getIsSaved())
                    bottomBar.getState().setText("saved");
                else
                    bottomBar.getState().setText("modified");

            } else if (source.equals("Exit")) {
                int responseOfExit = JOptionPane.showConfirmDialog(null, "Exit without saving?");
                if (responseOfExit == 0) {
                    fr.dispose();
                } else {
                    fileChooser.saveFileAS();
                    try {
                        Thread.sleep(1100);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    fr.dispose();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}