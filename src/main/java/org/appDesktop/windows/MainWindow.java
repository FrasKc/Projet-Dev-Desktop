package org.appDesktop.windows;

import org.appDesktop.activityForm.ActivityForm;
import org.appDesktop.model.Activity;
import org.appDesktop.repository.activity.ActivityRepositoryImpl;
import org.appDesktop.repository.activity.IActivityRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

public class MainWindow extends JFrame {
    ActivityForm form = new ActivityForm();
    public MainWindow() {
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
                String nameField = form.getName().getText();
                int durationField = (Integer) form.getDuration().getValue();
                int rpe = form.getRpeSlider().getValue();
                int year = (Integer) form.getAnnee().getValue();
                int month = (Integer) form.getMois().getValue();
                int day = (Integer) form.getJour().getValue();
                form.formatDate(day,month,year);
                LocalDate date = form.getDate();
                Activity activity = new Activity(nameField, date, durationField, rpe);

            }
            else if (commande.equals("retourButton")) {
                // action liée au bouton cancel
                System.out.println("Cancel");
            }
        }
    }

    public static void main(String[] args) {JFrame window = new MainWindow();}
}
