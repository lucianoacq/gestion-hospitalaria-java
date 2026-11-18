package org.jcr.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jcr.enums.TipoSangre;

public class Paciente extends Persona {

    private final String telefono;
    private final String direccion;
    private final HistoriaClinica historiaClinica;
    private Hospital hospital;
    private final List<Cita> citas;

    public Paciente(String nombre, String apellido, String dni,
                    LocalDate fechaNacimiento, TipoSangre tipoSangre,
                    String telefono, String direccion) {

        super(nombre, apellido, dni, fechaNacimiento, tipoSangre);

        Validaciones.textoObligatorio(telefono, "Teléfono");
        Validaciones.textoObligatorio(direccion, "Dirección");

        this.telefono = telefono;
        this.direccion = direccion;
        this.historiaClinica = new HistoriaClinica(this);
        this.citas = new ArrayList<>();
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital nuevoHospital) {

        if (this.hospital == nuevoHospital) {
            return;
        }

        if (this.hospital != null) {
            this.hospital.quitarPacienteInterno(this);
        }

        this.hospital = nuevoHospital;

        if (nuevoHospital != null) {
            nuevoHospital.agregarPacienteInterno(this);
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