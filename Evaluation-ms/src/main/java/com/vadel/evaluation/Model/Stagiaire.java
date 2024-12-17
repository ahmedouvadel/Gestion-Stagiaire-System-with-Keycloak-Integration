package com.vadel.evaluation.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stagiaire {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private int tel;
    private LocalDate dateDebut;
    private LocalDate dateFin;
}
