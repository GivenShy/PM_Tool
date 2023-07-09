package am.hitech.model.dto.request;

import am.hitech.model.enums.Roles;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserRequestDTO {

    @NotBlank(message = "last name can't be null")
    private String firstName;

    @NotBlank(message = "last name can't be null")
    private String lastName;

    @Pattern(regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")
    private String email;

    @Size(min = 3, max = 15, message = "password must be between 3 to 15 characters")
    private String password;

    @NotBlank
    private String status;

    @NotNull
    private Roles role;
}
