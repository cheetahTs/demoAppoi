@Entity

public class dr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String especialidad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }
    public Long getapellidoPaterno() {
        return apellidoPaterno;
    }

    public void setapellidoPaterno(Long apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getapellidoMaterno() {
        return apellidoMaterno;
    }

    public void setapellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public Long getespecialidad() {
        return especialidad;
    }

    public void setespecialidad(Long especialidad) {
        this.especialidad = especialidad;
    }
}
