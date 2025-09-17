package com.hackaton.SabanaEmprende_api.Auth.Repository;

import com.hackaton.SabanaEmprende_api.Auth.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByEmail(String email);
}
