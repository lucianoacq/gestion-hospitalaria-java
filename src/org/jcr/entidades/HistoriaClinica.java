package org.jcr.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoriaClinica {

    private final String numeroHistoria;
    private final Paciente paciente;

    private final List<String> diagnosticos;
    private final List<String> tratamientos;
    private final List<String> alergias;

    // Constructor sin public: solo accesible desde el paquete
    HistoriaClinica(Paciente paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente inválido");
        }

        this.paciente = paciente;

        this.numeroHistoria = "HC-" + paciente.getDni()
                + "-" + LocalDate.now().getYear();

        this.diagnosticos = new ArrayList<>();
        this.tratamientos = new ArrayList<>();
        this.alergias = new ArrayList<>();
    }

    public String getNumeroHistoria() {
        return numeroHistoria;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void agregarDiagnostico(String diagnostico) {
        if (diagnostico != null && !diagnostico.trim().isEmpty()) {
            diagnosticos.add(diagnostico);
        }
    }

    public void agregarTratamiento(String tratamiento) {
        if (tratamiento != null && !tratamiento.trim().isEmpty()) {
            tratamientos.add(tratamiento);
        }
    }

    public void agregarAlergia(String alergia) {
        if (alergia != null && !alergia.trim().isEmpty()) {
            alergias.add(alergia);
        }
    }

    public List<String> getDiagnosticos() {
        return Collections.unmodifiableList(diagnosticos);
    }

    public List<String> getTratamientos() {
        return Collections.unmodifiableList(tratamientos);
    }

    public List<String> getAlergias() {
        return Collections.unmodifiableList(alergias);
    }
}