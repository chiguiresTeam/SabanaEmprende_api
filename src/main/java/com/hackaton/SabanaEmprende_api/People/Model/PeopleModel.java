package com.hackaton.SabanaEmprende_api.People.Model;

import com.hackaton.SabanaEmprende_api.People.dto.PeopleDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "people")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PeopleModel {
    @Id
    private Long cc;

    private String firstName;
    private String lastName;

    private String phone;
    
    public PeopleModel fromDto(PeopleDto dto){
        this.cc = dto.getCc();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.phone = dto.getPhone();
        return this;
    }
}
