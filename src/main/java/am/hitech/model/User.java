package am.hitech.model;

import am.hitech.model.enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private String password;

    @JsonIgnore
    private Integer verificationCode;

    @JsonIgnore
    private Integer passwordToken;

    private String status;

    @Column(name = "role_id")
    @Enumerated(EnumType.ORDINAL)
    private Roles role;
//    @ManyToOne
//    @JoinColumn(name = "role_id",nullable = false)
//    private Role role;
}
