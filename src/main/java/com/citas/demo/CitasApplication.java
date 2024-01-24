package com.citas.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CitasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitasApplication.class, args);
	}


	@Service
public class citasService {

    private final CitaRepository CitaRepository;

    @Autowired
    public citasService(CitaRepository CitaRepository) {
        this.CitaRepository = CitaRepository;
    }

    public void scheduleAppointment() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del paciente:");
        String patientName = scanner.nextLine();

        System.out.println("Ingrese la fecha y hora de la cita (formato: yyyy-MM-dd HH:mm):");
        String dateTimeStr = scanner.nextLine();

        // Conv fecha y hora:LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        // Crear la cita y guardarla en la base de datos
        Appointment appointment = new Appointment();
        appointment.setDateTime(dateTime);

		@Entity        
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
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
}

		Patient patient = new Patient();
        patient.setName(patientName);
        appointment.setPatient(patient);

        appointmentRepository.save(appointment);

        System.out.println("Cita programada correctamente");
    }
	@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByDoctorAndHorarioConsultaBetween(Doctor doctor, LocalDateTime start, LocalDateTime end);
    List<Cita> findByConsultorioAndHorarioConsultaBetween(Consultorio consultorio, LocalDateTime start, LocalDateTime end);
}


    public List<Appointment> getAllAppointments() {
        return CitaRepository.findAll();
    }

	public void agendarCita(Cita nuevaCita) {
        validCita(nuevaCita);
        citaRepository.save(nuevaCita);
    }

    public void editarCita(Cita citaEditada) {
        validarReglasDeNegocioParaEditarCita(citaEditada);

        // Lógica para editar la cita si todas las validaciones pasan
        citaRepository.save(citaEditada);
    }

    // Otros métodos y lógica según sea necesario

    private void validCita(Cita nuevaCita) {
        // Regla: No se puede agendar cita en un mismo consultorio a la misma hora.
        if (citaRepository.exists ByConsultorioAndHorarioConsulta(nuevaCita.getConsultorio(), nuevaCita.getHorarioConsulta())) {
            throw new RuntimeException("No se puede agendar cita en un mismo consultorio a la misma hora.");
        }
        if (citaRepository.exists ByDoctorAndHorarioConsulta(nuevaCita.getDoctor(), nuevaCita.getHorarioConsulta())) {
            throw new RuntimeException("No se puede agendar cita para un mismo Dr. a la misma hora.");
        }

      //2 horas de diferencia para el mismo día.
        LocalDateTime horaMinimaParaNuevaCita = nuevaCita.getHorarioConsulta().plusHours(2);
        if (citaRepository.existsByPacienteAndHorarioConsultaBetween(nuevaCita.getPaciente(), nuevaCita.getHorarioConsulta(), horaMinimaParaNuevaCita)) {
            throw new RuntimeException("No se puede agendar cita para un paciente a la misma hora o con menos de 2 horas de diferencia para el mismo día.");
        }
    }

	public List<Cita> consultarCitasPorHora(LocalDateTime start, LocalDateTime end) {
        return citaRepository.findByHorarioConsultaBetween(start, end);
    }

    public List<Cita> consultarCitasPorDoctorYHora(Doctor doctor, LocalDateTime start, LocalDateTime end) {
        return citaRepository.findByDoctorAndHorarioConsultaBetween(doctor, start, end);
    }

    public List<Cita> consultarCitasPorHoraDoctorYConsultorio(LocalDateTime horarioConsulta, Doctor doctor, Consultorio consultorio) {
        return citaRepository.findByHorarioConsultaAndDoctorAndConsultorio(horarioConsulta, doctor, consultorio);
    }

	
}
