package com.hackaton.SabanaEmprende_api.Auth.Controller;

import com.hackaton.SabanaEmprende_api.Auth.Service.UsersService;
import com.hackaton.SabanaEmprende_api.Auth.dto.SignInResDTO;
import com.hackaton.SabanaEmprende_api.Auth.dto.SingInDto;
import com.hackaton.SabanaEmprende_api.Auth.dto.SingUpDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticación", description = "Endpoints para registro e inicio de sesión de usuarios")
public class UsersController {

    private final UsersService usersService;
    private final Environment env;

    @Operation(
            summary = "Registro de usuario",
            description = "Crea un nuevo usuario con los datos proporcionados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @PostMapping("/sign-up")
    public ResponseEntity<String> singUp(@RequestBody SingUpDto singUpDto) {
        return ResponseEntity.ok(usersService.createUser(singUpDto));
    }

    @Operation(
            summary = "Inicio de sesión",
            description = "Autentica al usuario y genera un JWT en una cookie HttpOnly."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Inicio de sesión correcto",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SignInResDTO.class))),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas", content = @Content)
    })
    @PostMapping("/sign-in")
    public ResponseEntity<SignInResDTO> signIn(
            @RequestBody SingInDto dto,
            HttpServletResponse response) {

        SignInResDTO resDto = usersService.singIn(dto);
        String jwt = usersService.generateJwt(resDto);
        ResponseCookie cookie = ResponseCookie.from("access_token", jwt)
                .httpOnly(true)
                .secure(env.matchesProfiles("prod"))
                .path("/")
                .sameSite("None")          // ⚠️ Necesario si tu front y back están en dominios/puertos distintos
                .maxAge(Duration.ofHours(1))
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.ok(resDto);
    }
}
