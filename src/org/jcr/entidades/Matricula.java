package org.jcr.entidades;

public class Matricula {

    private final String numero;

    // Constructor sin public para respetar la composición con Medico.
    public Matricula(String numero) {

        if (numero == null || !numero.startsWith("MP-")) {
            throw new IllegalArgumentException("Matrícula inválida");
        }

        String digitos = numero.substring(3);

        if (digitos.length() < 4 || digitos.length() > 6) {
            throw new IllegalArgumentException(
                "La matrícula debe tener entre 4 y 6 dígitos"
            );
        }

        for (int i = 0; i < digitos.length(); i++) {
            if (!Character.isDigit(digitos.charAt(i))) {
                throw new IllegalArgumentException(
                    "La matrícula solo puede contener números después de MP-"
                );
            }
        }

        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }
}