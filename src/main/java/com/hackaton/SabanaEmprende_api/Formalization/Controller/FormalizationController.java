package com.hackaton.SabanaEmprende_api.Formalization.Controller;

import com.hackaton.SabanaEmprende_api.Formalization.Model.FormalizationProcess;
import com.hackaton.SabanaEmprende_api.Formalization.Service.FormalizationProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/formalization")
@RequiredArgsConstructor
public class FormalizationController {
    private final FormalizationProcessService formalizationProcessService;
    @GetMapping("/process")
    public ResponseEntity<List<FormalizationProcess>> processFormalization() {
        List<FormalizationProcess> response = formalizationProcessService.getAllProcesses();
        return ResponseEntity.ok(response);
    }

}
