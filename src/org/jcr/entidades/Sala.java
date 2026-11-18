package org.jcr.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sala {

    private final String numero;
    private final String tipo;
    private final Departamento departamento;
    private final List<Cita> citas;

    // Constructor sin public: la sala la crea su departamento.
    Sala(String numero, String tipo, Departamento departamento) {

        Validaciones.textoObligatorio(numero, "Número de sala");
        Validaciones.textoObligatorio(tipo, "Tipo de sala");

        if (departamento == null) {
            throw new IllegalArgumentException(
                "Departamento inválido"
            );
        }

        this.numero = numero;
        this.tipo = tipo;
        this.departamento = departamento;
        this.citas = new ArrayList<>();
    }

    public String getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public Departamento getDepartamento() {
        return departamento;
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