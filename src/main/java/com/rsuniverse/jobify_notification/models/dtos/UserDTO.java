package com.rsuniverse.jobify_notification.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rsuniverse.jobify_notification.models.enums.UserRole;
import com.rsuniverse.jobify_notification.models.enums.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO implements Serializable {

    private String id;
    
    @NotBlank(message = "Last name cannot be blank")
    private String fullName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @JsonIgnore
    @NotBlank(message = "Password cannot be blank")
    private String password;

    private String createdBy; 
    private String updatedBy; 

    @NotNull(message = "Roles cannot be null")
    private Set<UserRole> roles;

    @NotNull(message = "Status cannot be null")
    private UserStatus status;
}
