package com.hackaton.SabanaEmprende_api.Auth.Repository;

import com.hackaton.SabanaEmprende_api.Auth.Model.RolesUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesUserRepository extends JpaRepository<RolesUserModel, Integer> {
}
