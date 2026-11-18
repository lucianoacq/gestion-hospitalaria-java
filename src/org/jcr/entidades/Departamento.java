package org.jcr.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jcr.enums.EspecialidadMedica;

public class Departamento {

    private final String nombre;
    private final EspecialidadMedica especialidad;
    private Hospital hospital;

    private final List<Medico> medicos;
    private final List<Sala> salas;

    public Departamento(String nombre, EspecialidadMedica especialidad) {

        Validaciones.textoObligatorio(nombre, "Nombre del departamento");

        if (especialidad == null) {
            throw new IllegalArgumentException(
                "Especialidad del departamento inválida"
            );
        }

        this.nombre = nombre;
        this.especialidad = especialidad;
        this.medicos = new ArrayList<>();
        this.salas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public EspecialidadMedica getEspecialidad() {
        return especialidad;
    }

    public Hospital getHospital() {
        return hospital;
    }

    // Relación bidireccional Hospital - Departamento
    public void setHospital(Hospital nuevoHospital) {

        if (this.hospital == nuevoHospital) {
            return;
        }

        if (this.hospital != null) {
            this.hospital.quitarDepartamentoInterno(this);
        }

        this.hospital = nuevoHospital;

        if (nuevoHospital != null) {
            nuevoHospital.agregarDepartamentoInterno(this);
        }
    }

    // Relación bidireccional Departamento - Medico
    public void agregarMedico(Medico medico) {

        if (medico == null) {
            throw new IllegalArgumentException("Médico inválido");
        }

        if (medico.getEspecialidad() != especialidad) {
            throw new IllegalArgumentException(
                "La especialidad del médico no coincide con el departamento"
            );
        }

        medico.setDepartamento(this);
    }

    // Métodos internos para sincronizar las relaciones
    void agregarMedicoInterno(Medico medico) {
        if (!medicos.contains(medico)) {
            medicos.add(medico);
        }
    }

    void quitarMedicoInterno(Medico medico) {
        medicos.remove(medico);
    }

    // Composición: el departamento crea sus propias salas
    public Sala crearSala(String numero, String tipo) {

        Sala nuevaSala = new Sala(numero, tipo, this);
        salas.add(nuevaSala);

        return nuevaSala;
    }

    public List<Medico> getMedicos() {
        return Collections.unmodifiableList(medicos);
    }

    public List<Sala> getSalas() {
        return Collections.unmodifiableList(salas);
    }
}