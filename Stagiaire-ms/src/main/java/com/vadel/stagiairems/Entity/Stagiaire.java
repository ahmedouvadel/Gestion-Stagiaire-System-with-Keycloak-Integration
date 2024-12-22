package com.vadel.stagiairems.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vadel.stagiairems.Model.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stagiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private int tel;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    /*@Transient
    @JsonIgnore
    private Project project;
    private Long projectId;*/
}
