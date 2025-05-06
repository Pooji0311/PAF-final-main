package com.nexia.lessontracker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexia.lessontracker.model.LessonPlan;
import com.nexia.lessontracker.service.LessonPlanService;

@RestController
@RequestMapping("/api/lessonplans")
@CrossOrigin(origins = "*") // Allow testing from Postman
public class LessonPlanController {

    private final LessonPlanService lessonPlanService;

    public LessonPlanController(LessonPlanService lessonPlanService) {
        this.lessonPlanService = lessonPlanService;
    }

    // 1. POST - Create
    @PostMapping
    public ResponseEntity<LessonPlan> createLessonPlan(@RequestBody LessonPlan lessonPlan) {
        return ResponseEntity.ok(lessonPlanService.createLessonPlan(lessonPlan));
    }

    // 2. GET - Read
    @GetMapping
    public ResponseEntity<List<LessonPlan>> getAllLessonPlans() {
        return ResponseEntity.ok(lessonPlanService.getAllLessonPlans());
    }

    // 3. PUT - Update
    @PutMapping("/{id}")
    public ResponseEntity<LessonPlan> updateLessonPlan(@PathVariable Long id, @RequestBody LessonPlan updatedLessonPlan) {
        return lessonPlanService.updateLessonPlan(id, updatedLessonPlan)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. DELETE - Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLessonPlan(@PathVariable Long id) {
        lessonPlanService.deleteLessonPlan(id);
        return ResponseEntity.noContent().build();
    }
}
