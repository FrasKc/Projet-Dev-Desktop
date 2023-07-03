package org.appDesktop.windows;

import org.appDesktop.form.activityForm.ActivityForm;
import org.appDesktop.form.userForm.UserForm;
import org.appDesktop.model.Activity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JPanel mainPanel;
    private JPanel activityPanel;
    private JPanel userPanel;
    private JToggleButton navigationButton;

    public MainWindow() {
        setTitle("Application de navigation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setMinimumSize(new Dimension(600, 400));

        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());

        activityPanel = new ActivityForm().getRootPane();
        userPanel = new UserForm().getRootPane();

        mainPanel.add(activityPanel, "activity");
        mainPanel.add(userPanel, "user");

        getContentPane().add(mainPanel);

        navigationButton = new JToggleButton("Afficher le formulaire utilisateur");
        navigationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
                if (navigationButton.isSelected()) {
                    cardLayout.show(mainPanel, "user");
                    navigationButton.setText("Afficher l'activité");
                } else {
                    cardLayout.show(mainPanel, "activity");
                    navigationButton.setText("Afficher le formulaire utilisateur");
                }
            }
        });
        getContentPane().add(navigationButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
        });
    }
}
