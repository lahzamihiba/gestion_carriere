package com.hiba.gestion_carriere.model.autre.evaluation;

/*
@Entity
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ObjectifProfessionnel {
    @Id
    @GeneratedValue
    private Long idObjPro;

    @NonNull
    private String description;

    @NonNull
    private LocalDate dateDebut;

    @NonNull
    private LocalDate dateLimite;

    @ManyToMany
    @NonNull
    private Set<Competence>competences=new HashSet<>();

    @NonNull
    @OneToMany(mappedBy = "objPro")
    private Set<Test>tests=new HashSet<>();

    @NonNull
    @OneToMany
    private Set<Formation>formations=new HashSet<>();

    @Enumerated(EnumType.STRING)//en attente par default
    private Statut statut = Statut.EN_ATTENTE;
}
*/