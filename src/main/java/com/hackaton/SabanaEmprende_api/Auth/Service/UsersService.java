package com.hackaton.SabanaEmprende_api.Auth.Service;

import com.hackaton.SabanaEmprende_api.Auth.Model.RolesUserModel;
import com.hackaton.SabanaEmprende_api.Auth.Model.UserModel;
import com.hackaton.SabanaEmprende_api.Auth.Repository.UsersRepository;
import com.hackaton.SabanaEmprende_api.Auth.dto.SignInResDTO;
import com.hackaton.SabanaEmprende_api.Auth.dto.SingInDto;
import com.hackaton.SabanaEmprende_api.Auth.dto.SingUpDto;
import com.hackaton.SabanaEmprende_api.Common.Components.JwtUtil;
import com.hackaton.SabanaEmprende_api.People.Model.PeopleModel;
import com.hackaton.SabanaEmprende_api.People.Service.PeopleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final PeopleService peopleService;
    private final PasswordEncoder passwordEncoder;
    private final RolesUserService rolesUserService;
    private final JwtUtil jwtUtil;

    public String createUser(@Valid SingUpDto dto){
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());
        PeopleModel people = peopleService.getPeopleById(dto.getPeopleId());
        RolesUserModel rolUser = rolesUserService.findById(dto.getRolUserId());
        UserModel user = new UserModel(dto.getEmail(),encryptedPassword,people,rolUser);
        usersRepository.save(user);
        return "Usuario creado exitosamente";
    }

    public SignInResDTO singIn(@Valid SingInDto dto){
        UserModel userBd = usersRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found by email" + dto.getEmail()));
        if(!passwordEncoder.matches(dto.getPassword(), userBd.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        return new SignInResDTO(
                userBd.getEmail(),
                userBd.getPeople().getFirstName() + " " + userBd.getPeople().getLastName(),
                userBd.getPeople().getCc(),
                userBd.getRolesUser().getName()
        );
    }

        public String generateJwt(SignInResDTO dto) {
            return jwtUtil.generateToken(dto.getEmail(),
                    dto.getRole(),
                    dto.getFullName(),
                    dto.getPersonId()
            );
        }
}
