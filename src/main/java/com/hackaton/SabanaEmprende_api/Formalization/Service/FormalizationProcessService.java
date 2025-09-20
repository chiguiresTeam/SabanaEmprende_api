package com.hackaton.SabanaEmprende_api.Formalization.Service;

import com.hackaton.SabanaEmprende_api.Formalization.Model.FormalizationProcess;
import com.hackaton.SabanaEmprende_api.Formalization.Repository.FormalizationProcessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormalizationProcessService {
    private final FormalizationProcessRepository formalizationProcessRepository;

    public List<FormalizationProcess> getAllProcesses() {
        return formalizationProcessRepository.findAll();
    }

    public FormalizationProcess getProcessById(Long id) {
        return formalizationProcessRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se pudo hallar el proceso de formalizacion con el id "+ id)
        );
    }
}
