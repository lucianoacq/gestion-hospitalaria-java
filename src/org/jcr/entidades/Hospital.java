package org.jcr.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hospital {

    private final String nombre;
    private final String direccion;
    private final String telefono;

    private final List<Departamento> departamentos;
    private final List<Paciente> pacientes;

    public Hospital(String nombre, String direccion, String telefono) {

        Validaciones.textoObligatorio(nombre, "Nombre del hospital");
        Validaciones.textoObligatorio(direccion, "Dirección");
        Validaciones.textoObligatorio(telefono, "Teléfono");

        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;

        this.departamentos = new ArrayList<>();
        this.pacientes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    // Relación bidireccional Hospital - Departamento
    public void agregarDepartamento(Departamento departamento) {

        if (departamento == null) {
            throw new IllegalArgumentException("Departamento inválido");
        }

        departamento.setHospital(this);
    }

    void agregarDepartamentoInterno(Departamento departamento) {
        if (!departamentos.contains(departamento)) {
            departamentos.add(departamento);
        }
    }

    void quitarDepartamentoInterno(Departamento departamento) {
        departamentos.remove(departamento);
    }

    // Relación bidireccional Hospital - Paciente
    public void agregarPaciente(Paciente paciente) {

        if (paciente == null) {
            throw new IllegalArgumentException("Paciente inválido");
        }

        paciente.setHospital(this);
    }

    void agregarPacienteInterno(Paciente paciente) {
        if (!pacientes.contains(paciente)) {
            pacientes.add(paciente);
        }
    }

    void quitarPacienteInterno(Paciente paciente) {
        pacientes.remove(paciente);
    }

    public List<Departamento> getDepartamentos() {
        return Collections.unmodifiableList(departamentos);
    }

    public List<Paciente> getPacientes() {
        return Collections.unmodifiableList(pacientes);
    }
}