package org.jcr.entidades;

final class Validaciones {

    private Validaciones() {
        // Evita instanciar esta clase auxiliar.
    }

    static void textoObligatorio(String valor, String campo) {

        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(
                campo + " no puede estar vacío ni ser nulo"
            );
        }
    }
}