package org.appDesktop.form.activityForm;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.Date;

public class ActivityForm {
    private JPanel rootPane;
    private JTextField name;
    private JSpinner duration;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    private LocalDate date;
    private JTextField rpe;

    public void setName(JTextField name) {
        this.name = name;
    }

    public void setDuration(JSpinner duration) {
        this.duration = duration;
    }


    public JTextField getName() {
        return name;
    }

    public JSpinner getDuration() {
        return duration;
    }


    public JButton getRetourButton() {
        return retourButton;
    }

    public JButton getAjouterButton() {
        return ajouterButton;
    }

    private JButton retourButton;
    private JButton ajouterButton;
    private JSpinner jour;

    public JSpinner getJour() {
        return jour;
    }

    public JSpinner getMois() {
        return mois;
    }

    public JSpinner getAnnee() {
        return annee;
    }

    private JSpinner mois;
    private JSpinner annee;

    public JSlider getRpeSlider() {
        return rpeSlider;
    }

    private JSlider rpeSlider;
    private JLabel rpeValue;

    Calendar calendar = Calendar.getInstance();

    int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
    int currentMonth = calendar.get(Calendar.MONTH) + 1;  // +1 car les mois sont indexés à partir de 0
    int currentYear = calendar.get(Calendar.YEAR);
    public ActivityForm() {
        duration.setModel(new SpinnerNumberModel(1, 1, 100, 1));
        jour.setModel(new SpinnerNumberModel(currentDay, 1, 31, 1));
        mois.setModel(new SpinnerNumberModel(currentMonth, 1, 12, 1));
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(mois, "00");
        mois.setEditor(editor);
        annee.setModel(new SpinnerNumberModel(currentYear, 1900, currentYear, 1));
        annee.setEditor(new JSpinner.NumberEditor(annee, "#"));
        adjustDaySpinner();
        rpeSlider.setMinimum(0);
        rpeSlider.setMaximum(10);
        rpeSlider.setValue(0);
        rpeSlider.addChangeListener(e -> {
            int rpe = rpeSlider.getValue();
            rpeValue.setText(String.valueOf(rpe));
        });

        mois.addChangeListener(e -> adjustDaySpinner());

        annee.addChangeListener(e -> adjustDaySpinner());

        // Désactivez le bouton de validation initialement
        ajouterButton.setEnabled(false);

        InputListener inputListener = new InputListener();
        // Ajoutez un DocumentListener à nameTextField
        name.getDocument().addDocumentListener(inputListener);
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
            // Vérifiez si tous les champs sont remplis
            if (name.getText().trim().isEmpty()) {
                // Si nameTextField est vide, désactivez le bouton de validation
                ajouterButton.setEnabled(false);
            } else {
                // Sinon, activez le bouton de validation
                ajouterButton.setEnabled(true);
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


    public JPanel getRootPane() {
        return rootPane;
    }
}
