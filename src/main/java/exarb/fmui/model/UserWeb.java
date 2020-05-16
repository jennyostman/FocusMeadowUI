package exarb.fmui.model;

import lombok.Data;

/**
 * Model for the user containing the information the frontend needs
 */
@Data
public class UserWeb {

    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;

}
