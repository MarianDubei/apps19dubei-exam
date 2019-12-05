package domain;

import json.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    protected List<Tuple> exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = Arrays.asList(exams);
    }

    public JsonObject toJsonObject() {
        JsonObject jsonObjectWithExams = super.toJsonObject();
        JsonObject[] examsObject = new JsonObject[exams.size()];
        int i = 0;
        boolean passed;
        for (Tuple tuple: exams) {
            if ((Integer) tuple.value > 2) {
                passed = true;
            } else {
                passed = false;
            }
            examsObject[i] = new JsonObject(new JsonPair("course", new JsonString((String) tuple.key)), new JsonPair("mark", new JsonNumber((Integer) tuple.value)), new JsonPair("passed", new JsonBoolean(passed)));
            i++;
        }
        JsonArray jExams = new JsonArray(examsObject);
        JsonPair exams = new JsonPair("exams", jExams);
        jsonObjectWithExams.add(exams);
        return jsonObjectWithExams;
    }
}
