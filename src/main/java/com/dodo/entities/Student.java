package com.dodo.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "students_data")
public class Student extends PanacheEntityBase {

    @Id
    @GeneratedValue
    @Schema(readOnly = true)
    private UUID uuid;
    @Column(name = "first_name")
    @Schema(example = "Rivaldo")
    @NotNull
    private String firstName;
    @Column(name = "last_name")
    @Schema(example = "Lawalata")
    @NotNull
    private String lastName;
    @Schema(example = "ritza.kerz18@gmail.com")
    @Email
    private String email;
    @Column(name = "date_of_birth")
    @Schema(example = "3/28/2001")
    @NotNull
    private String dob;
    @Schema(example = "Backend Developer")
    private String occupation;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
