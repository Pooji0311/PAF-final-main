package com.nexia.lessontracker.service;

import java.util.List;
import java.util.Optional;
import com.nexia.lessontracker.model.LessonPlan;

public interface LessonPlanService {
    LessonPlan createLessonPlan(LessonPlan lessonPlan);
    List<LessonPlan> getAllLessonPlans();
    Optional<LessonPlan> updateLessonPlan(Long id, LessonPlan lessonPlan);
    void deleteLessonPlan(Long id);
} 