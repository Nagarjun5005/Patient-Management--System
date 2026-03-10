package com.pm.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PatientRequestDTO {

    @NotBlank(message = "Name is Required")
    @Size(max = 50,message = "name shouldn't exceed more than 100")
    private String name;


    @NotBlank(message = "Email is Required")
    @Email(message = "should be a valid email")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    @NotNull(message = "date of birth is required")
    private String dateOfBirth;

    @NotBlank(message = "registered date cannot be null",groups = CreatePatientValidatorGroup.class)
    private String registeredDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String  getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }


}
