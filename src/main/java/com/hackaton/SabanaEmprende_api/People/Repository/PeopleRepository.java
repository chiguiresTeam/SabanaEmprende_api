package com.hackaton.SabanaEmprende_api.People.Repository;

import com.hackaton.SabanaEmprende_api.People.Model.PeopleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<PeopleModel, Long> {
}
