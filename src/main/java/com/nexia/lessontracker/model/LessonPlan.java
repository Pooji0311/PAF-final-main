package com.nexia.lessontracker.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class LessonPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String type; // video or podcast
    private boolean completed = false;
    public Object createLessonPlan(LessonPlan lessonPlan) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createLessonPlan'");
    }
}
