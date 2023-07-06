package org.appDesktop.windows;

import org.appDesktop.form.activityForm.ActivityForm;
import org.appDesktop.form.activityList.ActivityList;
import org.appDesktop.form.userForm.UserForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JPanel mainPanel;
    private JPanel activityPanel;
    private JPanel userPanel;
    private JPanel activityListPanel;
    private JButton userButton;
    private JButton activityButton;
    private JButton activityListButton;

    public MainWindow() {
        setTitle("Application de navigation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setMinimumSize(new Dimension(700, 400));

        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());

        //activityPanel = new ActivityForm().getRootPane();
        userPanel = new UserForm().getRootPane();
        activityListPanel = new ActivityList().getPanel();

        mainPanel.add(activityListPanel, "activity-list");
        mainPanel.add(activityPanel, "activity");
        mainPanel.add(userPanel, "user");

        getContentPane().add(mainPanel);

        userButton = new JButton("Aller à la page utilisateur");
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
                cardLayout.show(mainPanel, "user");
            }
        });

        activityButton = new JButton("Aller à la page d'activité");
        activityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
                cardLayout.show(mainPanel, "activity");
            }
        });

        activityListButton = new JButton("Aller à la liste d'activités");
        activityListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
                cardLayout.show(mainPanel, "activity-list");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(userButton);
        buttonPanel.add(activityButton);
        buttonPanel.add(activityListButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
        });
    }
}
