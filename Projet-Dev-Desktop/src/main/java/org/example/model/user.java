package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class user {
    private String firstname;
    private String lastname;
    private Date birthDate;
    private String gender;
}
