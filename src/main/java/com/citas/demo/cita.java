@Entity
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Consultorio consultorio;
    @ManyToOne
    private Paciente paciente;
    private LocalDateTime horarioConsulta;
    private boolean cancelada;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getnombre() {
        return nombre;
    }
     this.doctor = getnombre();
      
     public String getnumero() {
        return numero;
    }
     this.consultorio = getnumero();
      

     public Long getHorarioConsulta() {
        return horarioConsulta;
    }

    public void sethorarioConsulta(LocalDateTime horarioConsulta) {
        this.horarioConsulta = horarioConsulta;
    }
}

