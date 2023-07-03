package org.appDesktop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class User {
    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    private String gender;
}
