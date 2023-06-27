package org.appDesktop.windows;

import org.appDesktop.activityForm.ActivityForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("Une fenêtre");

        Toolkit tk = Toolkit.getDefaultToolkit();
        setBounds(0,0, 600, 400);
        // Centre une fenetre
        setLocationRelativeTo(null);

        ActivityForm form = new ActivityForm();
        JPanel rootPane = form.getRootPane();

        //On créé le container (ou JPannel)
        Container cp = this.getContentPane();

        cp.add(rootPane);

        setVisible(true);

        ActionListener buttonListener = new ButtonListener();
        form.getAjouterButton().addActionListener(buttonListener);
        form.getAjouterButton().setActionCommand("ajouterButton");
        form.getRetourButton().addActionListener(buttonListener);
        form.getRetourButton().setActionCommand("retourButton");
    }
    class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String commande = e.getActionCommand();
            if (commande.equals("ajouterButton")) {
                System.out.println("Save");
            }
            else if (commande.equals("retourButton")) {
                // action liée au bouton cancel
                System.out.println("Cancel");
            }
        }
    }

    public static void main(String[] args) {JFrame window = new MainWindow();}
}
