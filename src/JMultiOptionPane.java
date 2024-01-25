import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class JMultiOptionPane {

    private JTextField arrayOfTextFields[];
    private String inputInfoText[];
    private String enteredText[];

    public void setText(String inputInfoText[], Address a) {
        this.inputInfoText = inputInfoText;

        enteredText = new String[inputInfoText.length];

        arrayOfTextFields = new JTextField[inputInfoText.length];
        for (int i = 0; i < arrayOfTextFields.length; i++) {


            arrayOfTextFields[i] = new JTextField(inputInfoText[i]);
            arrayOfTextFields[i].setForeground(Color.gray);
            int finalI = i;


            arrayOfTextFields[i].addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (arrayOfTextFields[finalI].getText().equals(inputInfoText[finalI])) {
                        arrayOfTextFields[finalI].setText("");
                        arrayOfTextFields[finalI].setForeground(Color.black);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (arrayOfTextFields[finalI].getText().isEmpty()) {
                        arrayOfTextFields[finalI].setForeground(Color.gray);
                        arrayOfTextFields[finalI].setText(inputInfoText[finalI]);
                    }
                }
            });
        }

        Object[] message = new Object[inputInfoText.length];
        for (int i = 0; i < inputInfoText.length; i++) {
            message[i] = arrayOfTextFields[i];
        }


        JOptionPane.showConfirmDialog(null, message, "Enter your " + a.getClass().getName() + " address.", JOptionPane.OK_CANCEL_OPTION);

    }


    public String[] getText() {

        for (int i = 0; i < arrayOfTextFields.length; i++) {
            if (arrayOfTextFields[i].getText().equals(inputInfoText[i])) {
                arrayOfTextFields[i].setText("");
            } else {
                enteredText[i] = arrayOfTextFields[i].getText();
            }
        }
        return enteredText;
    }

}
