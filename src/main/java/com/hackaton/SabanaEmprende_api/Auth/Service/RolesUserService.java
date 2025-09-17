package com.hackaton.SabanaEmprende_api.Auth.Service;

import com.hackaton.SabanaEmprende_api.Auth.Model.RolesUserModel;
import com.hackaton.SabanaEmprende_api.Auth.Repository.RolesUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolesUserService {
    private final RolesUserRepository rolesUserRepository;

    public RolesUserModel findById(int id) {
        return rolesUserRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se encontro el id de la role del usuario")
        );
    }
}
