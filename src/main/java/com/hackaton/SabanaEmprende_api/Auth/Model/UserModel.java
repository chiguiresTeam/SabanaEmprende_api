package com.hackaton.SabanaEmprende_api.Auth.Model;

import com.hackaton.SabanaEmprende_api.People.Model.PeopleModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users_app")
@Getter
@Setter
@NoArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "people_id")
    private PeopleModel people;

    @ManyToOne
    @JoinColumn(name = "role_user_id")
    private RolesUserModel rolesUser;

    public UserModel(String email,String encryptedPassword,PeopleModel people,RolesUserModel rolUser){
        this.email = email;
        this.password = encryptedPassword;
        this.people = people;
        this.rolesUser = rolUser;
    }
}
