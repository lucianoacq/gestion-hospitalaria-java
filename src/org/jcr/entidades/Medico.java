package org.jcr.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jcr.enums.EspecialidadMedica;
import org.jcr.enums.TipoSangre;

public class Medico extends Persona {

    private final Matricula matricula;
    private final EspecialidadMedica especialidad;
    private Departamento departamento;
    private final List<Cita> citas;

    public Medico(String nombre, String apellido, String dni,
                  LocalDate fechaNacimiento, TipoSangre tipoSangre,
                  String numeroMatricula,
                  EspecialidadMedica especialidad) {

        super(nombre, apellido, dni, fechaNacimiento, tipoSangre);

        if (especialidad == null) {
            throw new IllegalArgumentException("Especialidad inválida");
        }

        // El médico crea su propia matrícula.
        Matricula nuevaMatricula = new Matricula(numeroMatricula);

        this.especialidad = especialidad;
        this.matricula = nuevaMatricula;
        this.citas = new ArrayList<>();
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public EspecialidadMedica getEspecialidad() {
        return especialidad;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento nuevoDepartamento) {

        if (this.departamento == nuevoDepartamento) {
            return;
        }

        // Validar antes de modificar la relación.
        if (nuevoDepartamento != null
                && nuevoDepartamento.getEspecialidad() != especialidad) {
            throw new IllegalArgumentException(
                "La especialidad del médico no coincide con la del departamento"
            );
        }

        if (this.departamento != null) {
            this.departamento.quitarMedicoInterno(this);
        }

        this.departamento = nuevoDepartamento;

        if (nuevoDepartamento != null) {
            nuevoDepartamento.agregarMedicoInterno(this);
        }
    }

    public void addCita(Cita cita) {
        if (cita != null && !citas.contains(cita)) {
            citas.add(cita);
        }
    }

    public List<Cita> getCitas() {
        return Collections.unmodifiableList(citas);
    }
}