package com.disfluency.disfluencyapi.data;

import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.domain.forms.AnswerScale;
import com.disfluency.disfluencyapi.domain.forms.Form;
import com.disfluency.disfluencyapi.domain.forms.FormAssignment;
import com.disfluency.disfluencyapi.domain.forms.FormQuestion;

import java.util.List;

public class MockedForm {
    static final Form situacionesEnElColegio;
    static final Form situacionesSociales;

    static final List<Form> all;

    private MockedForm(){}

    static {
        situacionesEnElColegio = Form.builder()
            .title("Situaciones en el colegio")
            .questions(
                List.of(
                    FormQuestion.builder()
                        .scaleQuestion("Tengo dificultades para comunicarme con mis compañeros")
                        .followUpQuestion("¿En que situaciones?")
                        .minorScale(new AnswerScale("Siempre", 1))
                        .minorScale(new AnswerScale("Nunca", 5))
                        .build(),
                    FormQuestion.builder()
                        .scaleQuestion("Trato de no participar en clase para no tener episodios de tartamudez")
                        .followUpQuestion("¿Alguna vez tuviste uno de estos episodios al participar?")
                        .minorScale(new AnswerScale("Siempre", 1))
                        .minorScale(new AnswerScale("Nunca", 5))
                        .build(),
                    FormQuestion.builder()
                        .scaleQuestion("Puedo dar examen oral sin ningun problema")
                        .followUpQuestion("¿Esto afecta tus notas?")
                        .minorScale(new AnswerScale("Nunca", 1))
                        .minorScale(new AnswerScale("Siempre", 5))
                        .build(),
                    FormQuestion.builder()
                        .scaleQuestion("Cuando tengo una duda, levanto la mano y le pregunto al profesor")
                        .followUpQuestion("¿De que otra manera tratas de resolver tu duda?")
                        .minorScale(new AnswerScale("Nunca", 1))
                        .minorScale(new AnswerScale("Siempre", 5))
                        .build()
                )
            )
            .build();

        situacionesSociales = Form.builder()
                .title("Situaciones sociales")
                .questions(
                    List.of(
                        FormQuestion.builder()
                            .scaleQuestion("Cuando subo al colectivo, me cuesta pedir el boleto al colectivero")
                            .followUpQuestion("¿Le logras comunicar a donde queres ir?")
                            .minorScale(new AnswerScale("Me cuesta", 1))
                            .minorScale(new AnswerScale("No me cuesta", 5))
                            .build(),
                        FormQuestion.builder()
                            .scaleQuestion("Si me piden el DNI, no tengo problemas para decirlo")
                            .followUpQuestion("¿Como soles resolver la situacion?")
                            .minorScale(new AnswerScale("Me cuesta", 1))
                            .minorScale(new AnswerScale("No me cuesta", 5))
                            .build(),
                        FormQuestion.builder()
                            .scaleQuestion("Tengo problemas al pedir una pizza por telefono")
                            .followUpQuestion("¿Evitas hacerlo?")
                            .minorScale(new AnswerScale("Siempre", 1))
                            .minorScale(new AnswerScale("Nunca", 5))
                            .build()
                    )
                )
                .build();

        all = List.of(situacionesEnElColegio, situacionesSociales);
    }


    public static List<FormAssignment> allAssignments(){
        return all.stream().map(FormAssignment::newFormAssignment).toList();
    }
}
