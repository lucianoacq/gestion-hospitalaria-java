package org.jcr.entidades;

import java.time.LocalDate;
import java.time.Period;
import org.jcr.enums.TipoSangre;

public abstract class Persona {

    private final String nombre;
    private final String apellido;
    private final String dni;
    private final LocalDate fechaNacimiento;
    private final TipoSangre tipoSangre;

    public Persona(String nombre, String apellido, String dni,
                   LocalDate fechaNacimiento, TipoSangre tipoSangre) {

        Validaciones.textoObligatorio(nombre, "Nombre");
        Validaciones.textoObligatorio(apellido, "Apellido");

        if (dni == null || (dni.length() != 7 && dni.length() != 8)) {
            throw new IllegalArgumentException("DNI inválido");
        }

        for (int i = 0; i < dni.length(); i++) {
            if (!Character.isDigit(dni.charAt(i))) {
                throw new IllegalArgumentException("DNI inválido");
            }
        }

        if (fechaNacimiento == null) {
            throw new IllegalArgumentException(
                "Fecha de nacimiento inválida"
            );
        }

        if (tipoSangre == null) {
            throw new IllegalArgumentException(
                "Tipo de sangre inválido"
            );
        }

        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoSangre = tipoSangre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public TipoSangre getTipoSangre() {
        return tipoSangre;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public int getEdad() {
        return Period.between(
            fechaNacimiento, LocalDate.now()
        ).getYears();
    }
}