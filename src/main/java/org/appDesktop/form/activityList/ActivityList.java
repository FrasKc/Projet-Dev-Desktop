package org.appDesktop.form.activityList;

import com.mongodb.client.MongoCollection;
import lombok.Getter;
import org.appDesktop.model.Activity;
import org.appDesktop.service.DatabaseService;
import org.bson.Document;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Getter
public class ActivityList {
    private JList<Activity> activityList;
    private DatabaseService databaseService;
    private JPanel panel;
    private JScrollPane scrollPane;

    public ActivityList() {
        activityList = new JList<>();

        // Récupérez les activités à partir de MongoDB
        List<Activity> activities = getAllActivities();

        // Créez un modèle de liste personnalisé pour les activités
        DefaultListModel<Activity> listModel = new DefaultListModel<>();
        for (Activity activity : activities) {
            listModel.addElement(activity);
        }
        activityList.setModel(listModel);

        activityList.setCellRenderer(new ActivityListCellRenderer());

        // Ajoutez un ListSelectionListener pour détecter la sélection d'un élément de la liste
        activityList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Activity selectedActivity = activityList.getSelectedValue();
                    if (selectedActivity != null) {
                        showActivityDetails(selectedActivity);
                    }
                }
            }
        });

        // Créez le JScrollPane avec la JList
        scrollPane = new JScrollPane(activityList);

        // Ajoutez le JScrollPane au panneau
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    private List<Activity> getAllActivities() {
        databaseService = new DatabaseService();
        MongoCollection<Document> collection = databaseService.getCollection("activity");
        return databaseService.getActivityController(collection).getAllActivitiesOfTheWeek();
    }

    private void showActivityDetails(Activity activity) {
        String details = "ID: " + activity.getUserId() + "\n"
                + "Nom: " + activity.getName() + "\n"
                + "Date: " + activity.getDate() + "\n"
                + "Durée: " + activity.getDuration() + " minutes\n"
                + "RPE: " + activity.getRpe() + "\n"
                + "Load: " + activity.getLoad();

        JOptionPane.showMessageDialog(panel, details, "Détails de l'activité", JOptionPane.INFORMATION_MESSAGE);
    }

    // ListCellRenderer personnalisé pour afficher les éléments de la liste de manière jolie
    private class ActivityListCellRenderer implements ListCellRenderer<Activity> {
        private JPanel panel;
        private JLabel nameLabel;
        private JLabel durationLabel;
        private JLabel rpeLabel;
        private JLabel loadLabel;
        private JButton updateButton;

        public ActivityListCellRenderer() {
            panel = new JPanel(new BorderLayout());
            nameLabel = new JLabel();
            durationLabel = new JLabel();
            rpeLabel = new JLabel();
            loadLabel = new JLabel();
            updateButton = new JButton("Update");

            panel.add(nameLabel, BorderLayout.NORTH);
            panel.add(durationLabel, BorderLayout.CENTER);
            panel.add(rpeLabel, BorderLayout.WEST);
            panel.add(loadLabel, BorderLayout.EAST);
            panel.add(updateButton, BorderLayout.EAST);

            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Activity selectedActivity = activityList.getSelectedValue();
                    if (selectedActivity != null) {
                        openUpdateActivityFrame(selectedActivity);
                    }
                }
            });
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Activity> list, Activity value, int index, boolean isSelected, boolean cellHasFocus) {
            nameLabel.setText("<html><b>" + value.getName() + "</b></html>");
            durationLabel.setText("Durée: " + value.getDuration() + " minutes");
            rpeLabel.setText("RPE: " + value.getRpe());
            loadLabel.setText("Load: " + value.getLoad());

            if (isSelected) {
                panel.setBackground(new Color(63, 63, 63)); // Couleur d'arrière-plan pour les éléments sélectionnés
                panel.setForeground(Color.WHITE); // Couleur de texte pour les éléments sélectionnés
            } else {
                panel.setBackground(Color.lightGray); // Couleur d'arrière-plan pour les éléments non sélectionnés
                panel.setForeground(Color.BLACK); // Couleur de texte pour les éléments non sélectionnés
            }

            return panel;
        }
    }

    private void openUpdateActivityFrame(Activity activity) {
        // Code pour ouvrir une fenêtre ou un dialogue de mise à jour de l'activité
        // ...
    }
}
