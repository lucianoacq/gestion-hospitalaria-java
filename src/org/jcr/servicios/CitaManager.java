package org.jcr.servicios;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jcr.entidades.Cita;
import org.jcr.entidades.Paciente;
import org.jcr.entidades.Medico;
import org.jcr.entidades.Sala;
import org.jcr.excepciones.CitaException;

public class CitaManager implements CitaService {

    private static final int HORAS_ENTRE_CITAS = 2;

    private final List<Cita> citas;

    public CitaManager() {
        this.citas = new ArrayList<>();
    }

    @Override
    public Cita programarCita(Paciente paciente, Medico medico,
                             Sala sala, LocalDateTime fechaHora,
                             double costo) throws CitaException {

        // Validar que los datos obligatorios no sean nulos
        if (paciente == null || medico == null
                || sala == null || fechaHora == null) {

            throw new CitaException(
                "Los datos de la cita no pueden ser nulos"
            );
        }

        // La fecha debe ser futura
        if (!fechaHora.isAfter(LocalDateTime.now())) {
            throw new CitaException(
                "La fecha de la cita debe ser futura"
            );
        }

        // El costo debe ser positivo y finito
        if (!Double.isFinite(costo) || costo <= 0) {
            throw new CitaException(
                "El costo de la cita debe ser mayor a cero"
            );
        }

        // Comprobar que la especialidad del médico
        // coincida con la del departamento de la sala
        if (medico.getEspecialidad()
                != sala.getDepartamento().getEspecialidad()) {

            throw new CitaException(
                "La especialidad del médico no coincide con la sala"
            );
        }

        // Comprobar la disponibilidad del médico y de la sala
        for (Cita citaExistente : citas) {

            long minutosDiferencia = Math.abs(
                Duration.between(
                    citaExistente.getFechaHora(),
                    fechaHora
                ).toMinutes()
            );

            if (minutosDiferencia < HORAS_ENTRE_CITAS * 60) {

                if (citaExistente.getMedico() == medico) {
                    throw new CitaException(
                        "El médico no está disponible en ese horario"
                    );
                }

                if (citaExistente.getSala() == sala) {
                    throw new CitaException(
                        "La sala no está disponible en ese horario"
                    );
                }
            }
        }

        // Crear la cita después de superar las validaciones
        Cita nuevaCita = new Cita(
            paciente, medico, sala, fechaHora, costo
        );

        // Registrar la cita en el servicio
        citas.add(nuevaCita);

        // Registrar la cita en los objetos relacionados
        paciente.addCita(nuevaCita);
        medico.addCita(nuevaCita);
        sala.addCita(nuevaCita);

        return nuevaCita;
    }

    @Override
    public List<Cita> getCitasPorPaciente(Paciente paciente) {

        List<Cita> resultado = new ArrayList<>();

        for (Cita cita : citas) {
            if (cita.getPaciente() == paciente) {
                resultado.add(cita);
            }
        }

        return resultado;
    }

    @Override
    public List<Cita> getCitasPorMedico(Medico medico) {

        List<Cita> resultado = new ArrayList<>();

        for (Cita cita : citas) {
            if (cita.getMedico() == medico) {
                resultado.add(cita);
            }
        }

        return resultado;
    }

    @Override
    public List<Cita> getCitasPorSala(Sala sala) {

        List<Cita> resultado = new ArrayList<>();

        for (Cita cita : citas) {
            if (cita.getSala() == sala) {
                resultado.add(cita);
            }
        }

        return resultado;
    }
}