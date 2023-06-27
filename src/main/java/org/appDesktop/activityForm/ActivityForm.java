package org.appDesktop.activityForm;

import javax.swing.*;

public class ActivityForm {

    public JPanel getRootPane() {
        return rootPane;
    }

    private JPanel rootPane;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;

    public JButton getRetourButton() {
        return retourButton;
    }

    public JButton getAjouterButton() {
        return ajouterButton;
    }

    private JButton retourButton;
    private JButton ajouterButton;
}
