package com.disfluency.disfluencyapi.domain.patients;

import com.disfluency.disfluencyapi.domain.exercises.ExerciseAssignment;
import com.disfluency.disfluencyapi.domain.exercises.ExercisePractice;
import com.disfluency.disfluencyapi.domain.forms.FormAssignment;
import com.disfluency.disfluencyapi.domain.forms.FormCompletionEntry;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class PatientProgressInfo {

    //TODO: recortar los ejercicios y cuestionarios para que manden solo el titulo y fecha y id
    private final Integer exercisesCount;
    private final Integer formsCount;
    private final Integer exercisesCompletedTodayCount;
    private final Integer formsCompletedTodayCount;
    private final Boolean allAssignmentsCompletedToday;
    private final Integer exercisesCompletedInLastTwoWeeks;
    private final ExerciseAssignment lastAssignedExercise;
    private final FormAssignment lastAssignedForm;
    private final List<ExerciseAssignment> pendingExercises;
    private final List<FormAssignment> pendingForms;
    private final Map<LocalDate, Integer> weeklyProgressMap;

    public PatientProgressInfo(Patient patient) {
        exercisesCount = patient.getExerciseAssignments().size();
        formsCount = patient.getFormAssignments().size();
        exercisesCompletedTodayCount = getExercisesCompletedTodayCount(patient);
        formsCompletedTodayCount = getFormsCompletedTodayCount(patient);
        allAssignmentsCompletedToday = getAllAssignmentsCompletedToday(patient);
        exercisesCompletedInLastTwoWeeks = getExercisesCompletedInLastTwoWeeks(patient);
        lastAssignedExercise = getLastAssignedExercise(patient);
        lastAssignedForm = getLastAssignedForm(patient);
        pendingExercises = getPendingExercises(patient);
        pendingForms = getPendingForms(patient);
        weeklyProgressMap = generateWeeklyProgressMap(patient);
    }

    private Integer getExercisesCompletedTodayCount(Patient patient){
        return Math.toIntExact(
            patient.getExerciseAssignments().stream()
                .filter(assignment -> assignment.getPracticeAttempts().stream().anyMatch(p -> dateIsToday(p.getDate())))
                .count()
        );
    }

    private Integer getFormsCompletedTodayCount(Patient patient){
        return Math.toIntExact(
            patient.getFormAssignments().stream()
                    .filter(assignment -> assignment.getCompletionEntries().stream().anyMatch(p -> dateIsToday(p.getDate())))
                    .count()
        );
    }

    private Boolean getAllAssignmentsCompletedToday(Patient patient){
        var exercisesCompletedToday = patient.getExerciseAssignments().stream()
                .allMatch(a -> a.getPracticeAttempts().stream().anyMatch(p -> dateIsToday(p.getDate())));

        var formsCompletedToday = patient.getFormAssignments().stream()
                .allMatch(a -> a.getCompletionEntries().stream().anyMatch(p -> dateIsToday(p.getDate())));

        return exercisesCompletedToday && formsCompletedToday;
    }

    private Integer getExercisesCompletedInLastTwoWeeks(Patient patient){
        return Math.toIntExact(
            patient.getExerciseAssignments().stream()
                .map(ExerciseAssignment::getPracticeAttempts)
                .flatMap(List::stream)
                .filter(p -> p.getDate().isAfter(LocalDateTime.now().minusWeeks(2)))
                .count()
        );
    }

    private ExerciseAssignment getLastAssignedExercise(Patient patient){
        return patient.getExerciseAssignments().stream()
                .max(Comparator.comparing(ExerciseAssignment::getDateOfAssignment))
                .orElse(null);
    }

    private FormAssignment getLastAssignedForm(Patient patient){
        return patient.getFormAssignments().stream()
                .max(Comparator.comparing(FormAssignment::getDate))
                .orElse(null);
    }

    private List<ExerciseAssignment> getPendingExercises(Patient patient) {
        return patient.getExerciseAssignments().stream()
                .filter(e -> e.getPracticeAttempts().isEmpty())
                .toList();
    }

    private List<FormAssignment> getPendingForms(Patient patient) {
        return patient.getFormAssignments().stream()
                .filter(f -> f.getCompletionEntries().isEmpty())
                .toList();
    }

    /**
     * Returns a map of the sum of exercise practices and form responses
     * for every week from the patients joined since date to today
     */
    private Map<LocalDate, Integer> generateWeeklyProgressMap(Patient patient) {
        var weeksRange = generateWeekRange(patient.getJoinedSince(), LocalDate.now());

        var exerciseDates = patient.getExerciseAssignments().stream()
                .map(ExerciseAssignment::getPracticeAttempts)
                .flatMap(List::stream)
                .map(ExercisePractice::getDate)
                .map(LocalDateTime::toLocalDate)
                .toList();

        var formDates = patient.getFormAssignments().stream()
                .map(FormAssignment::getCompletionEntries)
                .flatMap(List::stream)
                .map(FormCompletionEntry::getDate)
                .toList();

        var practicesDates = new ArrayList<LocalDate>();
        practicesDates.addAll(exerciseDates);
        practicesDates.addAll(formDates);

        return weeksRange.stream().collect(
            Collectors.toMap(
                Function.identity(),
                date -> practicesDates.stream()
                    .filter(d -> getCorrespondingWeekInRange(weeksRange.stream().sorted().toList(), d).equals(date))
                    .toList()
                    .size()
            )
        );
    }

    /**
     * Generates a list of LocalDates spaced by a week, from start date to end date
     */
    private List<LocalDate> generateWeekRange(LocalDate start, LocalDate end) {
        List<LocalDate> weeksRange = new ArrayList<>();

        var i = 0;
        while (start.plusWeeks(i).isBefore(end)){
            weeksRange.add(start.plusWeeks(i));
            i++;
        }

        return weeksRange;
    }

    /**
     * Given a list of weeks, returns the week element closest to the given date
     */
    private LocalDate getCorrespondingWeekInRange(List<LocalDate> range, LocalDate date) {
        int lastIndex = range.size() - 1;

        for( int i = 0 ; i < lastIndex ; i++ ){
            var start = range.get(i);
            var end = range.get(i + 1);

            if (date.isEqual(start) || (date.isAfter(start) && date.isBefore(end))){
                return start;
            }
        }

        return range.get(lastIndex);
    }


    private static boolean dateIsToday(LocalDateTime date) {
        return dateEqualsDay(date.toLocalDate(), LocalDate.now());
    }

    private static boolean dateIsToday(LocalDate date) {
        return dateEqualsDay(date, LocalDate.now());
    }

    private static boolean dateEqualsDay(LocalDate a, LocalDate b) {
        return a.getYear() == b.getYear()
                && a.getMonthValue() == b.getMonthValue()
                && a.getDayOfMonth() == a.getDayOfMonth();
    }
}
