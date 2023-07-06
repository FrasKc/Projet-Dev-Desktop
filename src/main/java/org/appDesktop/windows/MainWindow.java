package org.appDesktop.windows;

import org.appDesktop.form.activityForm.ActivityForm;
import org.appDesktop.form.activityList.ActivityList;
import org.appDesktop.form.userForm.UserForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.appDesktop.service.UserService.getUserId;

public class MainWindow extends JFrame {
    private JPanel mainPanel;
    private JPanel activityPanel;
    private JPanel userPanel;
    private JPanel activityListPanel;
    private JButton userButton;
    private JButton activityButton;
    private JButton activityListButton;

    public MainWindow() {
        setTitle("My Foster CalculaaaaaaaTOOOR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setMinimumSize(new Dimension(700, 400));

        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());

        String userId = getUserId() ;

        if(userId.isEmpty()) {
            userPanel = new UserForm().getRootPane();
            mainPanel.add(userPanel, "user");
        } else {
            userPanel = new UserForm(getUserId()).getRootPane();
            activityPanel = new ActivityForm().getRootPane();
            activityListPanel = new ActivityList().getPanel();

            mainPanel.add(activityListPanel, "activity-list");
            mainPanel.add(userPanel, "user");
            mainPanel.add(activityPanel, "activity");

            userButton = new JButton("Modifier utilisateur");
            ButtonActionListener buttonUserActionListener = new ButtonActionListener("user");
            userButton.addActionListener(buttonUserActionListener);

            activityButton = new JButton("Ajouter une activité");
            ButtonActionListener buttonActivityActionListener = new ButtonActionListener("activity");
            activityButton.addActionListener(buttonActivityActionListener);

            activityListButton = new JButton("Mes activités");
            ButtonActionListener buttonActivityListActionListener = new ButtonActionListener("activity-list");
            activityListButton.addActionListener(buttonActivityListActionListener);

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(userButton);
            buttonPanel.add(activityListButton);
            buttonPanel.add(activityButton);

            getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        }

        getContentPane().add(mainPanel);

        setVisible(true);
    }

    class ButtonActionListener implements ActionListener {
        String panel;

        ButtonActionListener(String panel) {
            this.panel= panel;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, this.panel);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
        });
    }
}
