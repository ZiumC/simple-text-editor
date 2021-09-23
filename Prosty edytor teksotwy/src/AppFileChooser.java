import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class AppFileChooser {

    private File fileIN;
    private File fileOut;
    private Scanner fileInput;
    private int response;
    private int responseSaveAs;
    private JFileChooser chooser;
    private JFileChooser saveFileAs;
    private JTextArea textArea;
    private JFrame frame;
    private String currentOpenedFilePath;
    private String currentSavedFilePath;
    private File cache;
    private final String appName = "Simple Editor - ";

    AppFileChooser(JTextArea jta, JFrame frame) {
        this.textArea = jta;
        this.frame = frame;
    }

    public void writeCache(String p) {
        cache = new File("cache.txt");

        try {
            FileWriter out = new FileWriter(cache);
            out.write("");
            if (p != null) {

                out.write(p);
            }
            out.close();
        } catch (Exception e) {
            System.err.println("Error with: writeCache();");
            e.printStackTrace();
        }
    }

    public String readCache() {
        String lastPath = "";
        File file = new File("cache.txt");
        if (file.exists()) {
            try {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    lastPath = sc.nextLine();
                }
            } catch (Exception e) {
                System.err.println("Problem with readCache()");
                e.printStackTrace();
            }
        }
        return lastPath;
    }

    public void openFile() {
        String lastPath;

        if (!readCache().equals("")) {
            chooser = new JFileChooser(readCache());
        } else {
            chooser = new JFileChooser(".");
        }

        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        response = chooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            String tmp = chooser.getCurrentDirectory().getPath() + chooser.getSelectedFile().getName();
            currentOpenedFilePath = tmp;
            fileIN = chooser.getSelectedFile();

            lastPath = chooser.getCurrentDirectory().getPath();
            writeCache(lastPath);

            try {
                fileInput = new Scanner(fileIN);
                if (fileIN.isFile()) {
                    frame.setTitle(appName + currentOpenedFilePath);

                    textArea.setText("");

                    while (fileInput.hasNextLine()) {
                        String line = fileInput.nextLine();
                        textArea.append(line + "\n");
                    }

                }
                fileInput.close();

            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }

    }

    public void saveFile() {
        if (!(fileOut == null)) {
            try {
                BufferedWriter oFile = new BufferedWriter(new FileWriter(fileOut));
                oFile.write(textArea.getText());
                oFile.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            saveFileAS();
        }
    }

    public void saveFileAS() {

        if (!readCache().equals("")) {
            saveFileAs = new JFileChooser(readCache());
        } else {
            saveFileAs = new JFileChooser(".");
        }


        saveFileAs.setApproveButtonText("Save As");
        responseSaveAs = saveFileAs.showOpenDialog(null);
        if (responseSaveAs == JFileChooser.APPROVE_OPTION) {
            fileOut = new File(saveFileAs.getSelectedFile() + "");
            if (fileOut == null) {
                return;
            }

            if (fileOut.exists()) {
                responseSaveAs = JOptionPane.showConfirmDialog(null, "Replace file?");
            }

            if (responseSaveAs == 0) {
                String tmp = saveFileAs.getCurrentDirectory().getPath() + saveFileAs.getSelectedFile().getName();
                currentSavedFilePath = tmp;
                frame.setTitle(appName + currentSavedFilePath);

                try {
                    BufferedWriter oFile = new BufferedWriter(new FileWriter(fileOut));
                    oFile.write(textArea.getText());
                    oFile.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

                }
            }

        }
    }

    public boolean getIsSaved() {
        return responseSaveAs > 0;
    }
}
