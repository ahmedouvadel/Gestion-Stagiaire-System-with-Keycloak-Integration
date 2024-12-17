package com.vadel.projectms.Model;


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
