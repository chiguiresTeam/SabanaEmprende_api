package com.hackaton.SabanaEmprende_api.Companies.Service;

import com.hackaton.SabanaEmprende_api.Companies.Repository.FormalizationLevelRepository;
import com.hackaton.SabanaEmprende_api.Formalization.Model.FormalizationLevelModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormalizationLevelService {
    private final FormalizationLevelRepository formalizationLevelRepository;

    public List<FormalizationLevelModel> getAllFormalizationLevels() {
        return formalizationLevelRepository.findAll();
    }

    public FormalizationLevelModel getFormalizationLevel(int id) {
        return formalizationLevelRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No formalization level found with id: " + id)
        );
    }
}
