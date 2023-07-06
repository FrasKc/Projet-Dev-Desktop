package org.appDesktop.form.activityForm;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.appDesktop.controller.activity.ActivityControllerImpl;
import org.appDesktop.model.Activity;
import org.appDesktop.service.DatabaseService;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Calendar;
import lombok.Getter;

import static org.appDesktop.service.DateService.FormatDate;
import static org.appDesktop.service.UserService.getUserId;

@Getter
public class ActivityForm {
    private JPanel rootPane;

    private JTextField name;

    private LocalDate date;

    private JButton retourButton;
    private JButton ajouterButton;

    private JSpinner jour;
    private JSpinner mois;
    private JSpinner annee;

    private JSpinner duration;

    private JSlider rpeSlider;
    private JLabel rpeValue;

    public void setDate(LocalDate date) {
        this.date = date;
    }

    Calendar calendar = Calendar.getInstance();
    int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
    int currentMonth = calendar.get(Calendar.MONTH) + 1;  // +1 car les mois sont indexés à partir de 0
    int currentYear = calendar.get(Calendar.YEAR);
    DatabaseService databaseService;


    public ActivityForm(String activityId) {
        try {
            databaseService = new DatabaseService();
            MongoCollection<Document> collection = databaseService.getCollection("activity");
            ActivityControllerImpl activityController = databaseService.getActivityController(collection);
            displayActivityData(activityController.findActivityById(activityId));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    public ActivityForm() {
        duration.setValue(1);
        duration.setModel(new SpinnerNumberModel((int) duration.getValue(), 1, 100000, 1));

        jour.setModel(new SpinnerNumberModel(currentDay, 1, 31, 1));

        mois.setModel(new SpinnerNumberModel(currentMonth, 1, 12, 1));
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(mois, "00");
        mois.setEditor(editor);
        mois.addChangeListener(e -> adjustDaySpinner());

        annee.setModel(new SpinnerNumberModel(currentYear, 1900, currentYear, 1));
        annee.setEditor(new JSpinner.NumberEditor(annee, "#"));
        annee.addChangeListener(e -> adjustDaySpinner());

        adjustDaySpinner();

        rpeSlider.setMinimum(0);
        rpeSlider.setMaximum(10);
        rpeSlider.setValue(0);
        rpeSlider.addChangeListener(e -> {
            int rpe = rpeSlider.getValue();
            rpeValue.setText(String.valueOf(rpe));
        });

        InputListener inputListener = new InputListener();
        name.getDocument().addDocumentListener(inputListener);

        ActivityActionListener activityActionListener = new ActivityActionListener();
        ajouterButton.addActionListener(activityActionListener);
        ajouterButton.setEnabled(false);
    }

    class InputListener implements DocumentListener {

        public void changedUpdate(DocumentEvent e) {
            check();
        }
        public void removeUpdate(DocumentEvent e) {
            check();
        }
        public void insertUpdate(DocumentEvent e) {
            check();
        }

        public void check() {
            if (name.getText().trim().isEmpty()) {
                ajouterButton.setEnabled(false);
            } else {
                ajouterButton.setEnabled(true);
            }
        }
    }

    class ActivityActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int day = (int)jour.getValue();
            int month = (int)mois.getValue();
            int year = (int)annee.getValue();
            int duree = (int)duration.getValue();
            int rpe = rpeSlider.getValue();

            Activity newActivity = new Activity(
                    getUserId(),
                    name.getText(),
                    FormatDate(day, month, year),
                    duree,
                    rpe
            );

            try {
                databaseService = new DatabaseService();
                MongoCollection<Document> collection = databaseService.getCollection("activity");
                databaseService.getActivityController(collection).saveActivity(newActivity);
            } catch (Exception ex) {
                throw new RuntimeException();
            }
        }
    }

    private void adjustDaySpinner() {
        int year = (Integer) annee.getValue();
        int month = (Integer) mois.getValue();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        jour.setModel(new SpinnerNumberModel(1, 1, maxDay, 1));

    }

    public void displayActivityData(Activity activity) {
        if (activity != null) {
            // Remplir le nom de l'activité
            name.setText(activity.getName());

            // Remplir la durée de l'activité (assurez-vous que l'objet Activity a une propriété 'duration' correspondante)
            int durationValue = activity.getDuration();
            duration.setValue(durationValue);

            // Remplir la date de l'activité (assurez-vous que l'objet Activity a une propriété 'date' correspondante)
            LocalDate activityDate = activity.getDate();
            jour.setValue(activityDate.getDayOfMonth());
            mois.setValue(activityDate.getMonthValue());
            annee.setValue(activityDate.getYear());
            annee.setEditor(new JSpinner.NumberEditor(annee, "#"));

            // Remplir la valeur de RPE (assurez-vous que l'objet Activity a une propriété 'rpe' correspondante)
            int rpeValue3 = activity.getRpe();
            rpeSlider.setValue(rpeValue3);
            rpeValue.setText(String.valueOf(activity.getRpe()));
        }
    }

    public JPanel getRootPane() {
        return rootPane;
    }
}
