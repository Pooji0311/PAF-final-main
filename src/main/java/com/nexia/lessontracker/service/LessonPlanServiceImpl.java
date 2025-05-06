package com.nexia.lessontracker.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.nexia.lessontracker.model.LessonPlan;

@Service
public class LessonPlanServiceImpl implements LessonPlanService {
    
    @Override
    public LessonPlan createLessonPlan(LessonPlan lessonPlan) {
        // TODO: Implement repository logic
        return lessonPlan;
    }

    @Override
    public List<LessonPlan> getAllLessonPlans() {
        // TODO: Implement repository logic
        return List.of();
    }

    @Override
    public Optional<LessonPlan> updateLessonPlan(Long id, LessonPlan lessonPlan) {
        // TODO: Implement repository logic
        return Optional.of(lessonPlan);
    }

    @Override
    public void deleteLessonPlan(Long id) {
        // TODO: Implement repository logic
    }
} 