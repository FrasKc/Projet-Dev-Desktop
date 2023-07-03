package org.appDesktop.windows;

import org.appDesktop.form.userForm.UserForm;

import javax.swing.*;
import java.awt.*;

public class UserWindow extends JFrame {
    UserForm form = new UserForm();
    public UserWindow() {
        super("Une fenêtre");

        Toolkit tk = Toolkit.getDefaultToolkit();
        setBounds(0,0, 600, 400);
        // Centre une fenetre
        setLocationRelativeTo(null);

        JPanel rootPane = form.getRootPane();

        //On créé le container (ou JPannel)
        Container cp = this.getContentPane();

        cp.add(rootPane);

        setVisible(true);
    }

    public static void main(String[] args) {JFrame window = new UserWindow();}
}
