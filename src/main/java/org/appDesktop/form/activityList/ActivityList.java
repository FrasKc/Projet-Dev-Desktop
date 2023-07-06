package org.appDesktop.form.activityList;

import com.mongodb.client.MongoCollection;
import org.appDesktop.model.Activity;
import org.appDesktop.service.DatabaseService;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ActivityList extends JFrame {
    private JList<Activity> activityList;
    private DatabaseService databaseService;

    public ActivityList() {
        setTitle("Liste des activités");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Récupérez les activités à partir de MongoDB
        List<Activity> activities = getAllActivities();

        // Créez un modèle de liste personnalisé pour les activités
        DefaultListModel<Activity> listModel = new DefaultListModel<>();
        for (Activity activity : activities) {
            listModel.addElement(activity);
        }

        // Créez la JList avec le modèle de liste et utilisez un ListCellRenderer personnalisé
        activityList = new JList<>(listModel);
        activityList.setCellRenderer(new ActivityListCellRenderer());

        // Ajoutez un MouseListener pour détecter les clics sur les éléments de la liste
        activityList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Double clic
                    Activity selectedActivity = activityList.getSelectedValue();
                    if (selectedActivity != null) {
                        showActivityDetails(selectedActivity);
                    }
                }
            }
        });

        // Ajoutez la JList dans un JScrollPane pour permettre le défilement
        JScrollPane scrollPane = new JScrollPane(activityList);

        // Configurez le layout de la fenêtre
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        // Ajustez la taille du JPanel pour qu'il soit plus grand par défaut
        int initialWidth = 800;
        int initialHeight = 600;
        setPreferredSize(new Dimension(initialWidth, initialHeight));

        pack();
        setLocationRelativeTo(null);
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

        JOptionPane.showMessageDialog(this, details, "Détails de l'activité", JOptionPane.INFORMATION_MESSAGE);
    }

    // ListCellRenderer personnalisé pour afficher les éléments de la liste de manière jolie
    private class ActivityListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof Activity) {
                Activity activity = (Activity) value;

                // Personnalisez```java
                // Personnalisez l'apparence de l'élément de la liste ici
                String labelText = "<html><b>" + activity.getName() + "</b><br>"
                        + "Durée: " + activity.getDuration() + " minutes<br>"
                        + "RPE: " + activity.getRpe() + "<br>"
                        + "Load: " + activity.getLoad() + "</html>";
                setText(labelText);

                // Personnalisez les couleurs de fond pour les éléments sélectionnés et non sélectionnés
                if (isSelected) {
                    setBackground(new Color(63, 63, 63)); // Couleur d'arrière-plan pour les éléments sélectionnés
                    setForeground(Color.WHITE); // Couleur de texte pour les éléments sélectionnés
                } else {
                    setBackground(Color.lightGray); // Couleur d'arrière-plan pour les éléments non sélectionnés
                    setForeground(Color.WHITE); // Couleur de texte pour les éléments non sélectionnés
                }
            }

            return this;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ActivityList activityList = new ActivityList();
            activityList.setVisible(true);
        });
    }
}