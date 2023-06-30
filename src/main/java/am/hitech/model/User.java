package am.hitech.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Integer verificationCode;

    private Integer passwordToken;

    private String status;

    @ManyToOne
    @JoinColumn(name = "role_id",nullable = false)
    private Role role;
}
