package com.hackaton.SabanaEmprende_api.Auth.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles_user_app")
@Getter
@Setter
public class RolesUserModel {
    @Id
    private Integer id;
    private String name;
}
