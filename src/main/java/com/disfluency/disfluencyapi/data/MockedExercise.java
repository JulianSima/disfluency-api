package com.disfluency.disfluencyapi.data;

import com.disfluency.disfluencyapi.domain.exercises.Exercise;
import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.dto.exercises.NewExerciseDTO;

import java.util.List;

public class MockedExercise {
    static final Exercise fonacionContinuada;
    static final Exercise inicioSuave;
    static final Exercise toquesLigeros;
    static final Exercise velocidadComoda;
    static final List<Exercise> all;

    private MockedExercise(){}

    static {
        fonacionContinuada = Exercise.newExercise(new NewExerciseDTO(
                "Fonación continuada",
                "Mantener la fonacion a lo largo de la palabra y entre palabra y palabra. Mantener la vibracion de las cuerdas vocales a lo largo de la palabra y entre palabras sin frenar, y sostener durante toda una frase",
                "La portabilidad es la capacidad del producto o componente de ser transferido de forma efectiva y eficiente de un entorno hardware, software, operacional o de utilización a otro",
                "https://pf5302.s3.us-east-2.amazonaws.com/audios/fonacion.mp3")
        );

        inicioSuave = Exercise.newExercise(new NewExerciseDTO(
                "Inicio suave",
                "Es un suave comienzo en la vibración de las cuerdas vocales en el inicio de cualquier palabra que comience con vocal. Dejo salir un poco de aire a través de las cuerdas vocales antes de comenzar la fonación; quizás un poco aireado al principio pero mejorará con la práctica",
                "La fiabilidad es la capacidad de un sistema o componente para desempeñar las funciones especificadas, cuando se usa bajo unas condiciones y periodo de tiempo determinados",
                "https://pf5302.s3.us-east-2.amazonaws.com/audios/iniciosuave.mp3")
        );

        toquesLigeros = Exercise.newExercise(new NewExerciseDTO(
                "Toques ligeros",
                "Producir las consonantes con movimientos y toques suaves entre las zonas de contacto. Empiezo todas las palabras que comienzan con consonante con una suave producción; toco y me voy; evito la presión en el resto de las consonantes de la palabra",
                "La mantenibilidad es la característica que representa la capacidad del producto software para ser modificado efectiva y eficientemente, debido a necesidades evolutivas, correctivas o perfectivas",
                "https://pf5302.s3.us-east-2.amazonaws.com/audios/toquesligeros.mp3")
        );

        velocidadComoda = Exercise.newExercise(new NewExerciseDTO(
                "Velocidad cómoda y fluida",
                "Controlar la velocidad de manera que me sea cómodo, tratar de mantenerla ajustándola a mi comodidad. Hablá a una velocidad cómoda y constante a lo largo de las palabras; y de las frases; bajá un poco la velocidad cuando notás un poco de tensión en tu máquina de hablar",
                "La usabilidad es la capacidad del producto software para ser entendido, aprendido, usado y resultar atractivo para el usuario, cuando se usa bajo determinadas condiciones",
                "https://pf5302.s3.us-east-2.amazonaws.com/audios/velocidad.mp3")
        );

        all = List.of(fonacionContinuada, inicioSuave, toquesLigeros, velocidadComoda);
    }

    public static List<ExerciseAssignment> allAssignments(){
        return all.stream().map(ExerciseAssignment::newExerciseAssignment).toList();
    }
}
