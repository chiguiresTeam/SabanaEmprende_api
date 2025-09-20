package com.hackaton.SabanaEmprende_api.Formalization.Service;

import com.hackaton.SabanaEmprende_api.Formalization.Repository.FormalizationProcessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FormalizationProcessCompaniesService {
    private final FormalizationProcessRepository formalizationProcessRepository;
}
