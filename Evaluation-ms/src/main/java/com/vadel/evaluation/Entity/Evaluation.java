package com.vadel.evaluation.Entity;

import com.vadel.evaluation.Model.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateEvaluation;
    private Double note;
    @Lob
    private String commentaire;
    @Transient
    private Project project;
    private Long projectId;
}
