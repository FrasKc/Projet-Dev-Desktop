package org.appDesktop.form.activityList;

import com.mongodb.client.MongoCollection;
import org.appDesktop.form.activityForm.ActivityForm;
import org.appDesktop.model.Activity;
import org.appDesktop.service.DatabaseService;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActivityDetailsDialog extends JDialog {
    private JTextArea detailsTextArea;
    private JButton okButton;
    private JButton updateButton;

    private  JButton deleteButton;

    private Activity activity;

    private DatabaseService databaseService;

    public ActivityDetailsDialog(Frame owner, String title, boolean modal, Activity activity) {
        super(owner, title, modal);
        initComponents(activity);
        layoutComponents();
        attachListeners();
        pack();
        setLocationRelativeTo(owner);
        this.activity = activity;
    }

    private void initComponents(Activity activity) {
        String details = "User ID: " + activity.getUserId() + "\n"
                + "ID: " + activity.get_id() + "\n"
                + "Nom: " + activity.getName() + "\n"
                + "Date: " + activity.getDate() + "\n"
                + "Durée: " + activity.getDuration() + " minutes\n"
                + "RPE: " + activity.getRpe() + "\n"
                + "Load: " + activity.getLoad();

        detailsTextArea = new JTextArea(details);
        detailsTextArea.setEditable(false);

        okButton = new JButton("OK");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
    }

    private void layoutComponents() {
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(new JScrollPane(detailsTextArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(okButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(contentPane);
    }

    private void attachListeners() {

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    databaseService = new DatabaseService();
                    MongoCollection<Document> collection = databaseService.getCollection("activity");
                    databaseService.getActivityController(collection).deleteActivity(activity.get_id());

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                dispose();
            }
        });
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ActivityForm activityForm = new ActivityForm(null, "Activity Form", true, activity);
                activityForm.setVisible(true);
                dispose();
            }
        });
    }
}
