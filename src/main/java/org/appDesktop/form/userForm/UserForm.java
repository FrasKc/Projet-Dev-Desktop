package org.appDesktop.form.userForm;

import com.mongodb.client.MongoCollection;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.appDesktop.model.User;
import org.appDesktop.repository.user.UserRepositoryImpl;
import org.appDesktop.service.DatabaseService;
import org.bson.Document;

import javax.annotation.processing.Generated;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import static org.appDesktop.service.DateService.FormatDate;

@Getter
@Slf4j
public class UserForm {
    // Objet de la vue
    private JPanel rootPane;
    private JTextField textFirstname;
    private JTextField textLastname;
    private JSpinner spinnerDay;
    private JSpinner spinnerMonth;
    private JSpinner spinnerYear;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JButton cancelButton;
    private JButton validateButton;

    // Utils
    Calendar calendar = Calendar.getInstance();
    int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
    int currentMonth = calendar.get(Calendar.MONTH) + 1;  // +1 car les mois sont indexés à partir de 0
    int currentYear = calendar.get(Calendar.YEAR);
    DatabaseService databaseService = new DatabaseService();

    public UserForm() {
        spinnerDay.setModel(new SpinnerNumberModel(currentDay, 1, 31, 1));
        spinnerMonth.setModel(new SpinnerNumberModel(currentMonth, 1, 12, 1));
        spinnerYear.setModel(new SpinnerNumberModel(currentYear, 1900, currentYear, 1));
        spinnerYear.setEditor(new JSpinner.NumberEditor(spinnerYear, "#"));

        spinnerMonth.addChangeListener(e -> adjustDaySpinner());
        spinnerYear.addChangeListener(e -> adjustDaySpinner());

        validateButton.setEnabled(false);

        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(maleRadioButton);
        radioButtonGroup.add(femaleRadioButton);

        UserDocumentListener userDocumentListener = new UserDocumentListener();
        textFirstname.getDocument().addDocumentListener(userDocumentListener);
        textLastname.getDocument().addDocumentListener(userDocumentListener);

        UserActionListener userActionListener = new UserActionListener();
        maleRadioButton.addActionListener(userActionListener);
        femaleRadioButton.addActionListener(userActionListener);

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonModel selectedButtonModel = radioButtonGroup.getSelection();

                int day = (int)spinnerDay.getValue();
                int month = (int)spinnerMonth.getValue();
                int year = (int)spinnerYear.getValue();
                System.out.println(day +" "+ month +" "+ year);
                User newUser = new User(
                        textFirstname.getText(),
                        textLastname.getText(),
                        FormatDate(day, month, year),
                        selectedButtonModel == maleRadioButton.getModel() ? "male" : "female"
                );
                try {
                    databaseService.getUserController().saveUser(newUser);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    class UserDocumentListener implements DocumentListener {
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
            if(
                    textFirstname.getText().isEmpty()
                            || textLastname.getText().isEmpty()
                            || (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected())
            ) {
                validateButton.setEnabled(false);
            } else {
                validateButton.setEnabled(true);
            }
        }
    }

    class UserActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            check();
        }

        public void check() {
            if(
                    textFirstname.getText().isEmpty()
                            || textLastname.getText().isEmpty()
                            || (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected())
            ) {
                validateButton.setEnabled(false);
            } else {
                validateButton.setEnabled(true);
            }
        }
    }

    private void adjustDaySpinner() {
        int day = (int)spinnerDay.getValue();
        int month = (int)spinnerMonth.getValue();
        int year = (int)spinnerYear.getValue();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        spinnerDay.setModel(new SpinnerNumberModel(day, 1, maxDay, 1));

    }
}
