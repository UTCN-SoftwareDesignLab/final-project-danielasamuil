package com.example.finalprojectdanielasamuil.controller;

import com.example.finalprojectdanielasamuil.model.dtos.ClassSubscriptionDto;
import com.example.finalprojectdanielasamuil.report.ReportServiceFactory;
import com.example.finalprojectdanielasamuil.report.ReportType;
import com.example.finalprojectdanielasamuil.service.ClassSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.finalprojectdanielasamuil.UrlMapping.*;

@RestController
@RequestMapping(SUBSCRIPTIONS)
@RequiredArgsConstructor
public class ClassSubscriptionController {

    private final ClassSubscriptionService classSubscriptionService;
    private final ReportServiceFactory reportServiceFactory;

    @GetMapping()
    public List<ClassSubscriptionDto> findAll() {
        return classSubscriptionService.findAll();
    }

    @PostMapping()
    public Boolean create(@RequestBody ClassSubscriptionDto classSubscriptionDto) {
        return classSubscriptionService.create(classSubscriptionDto);
    }

    @DeleteMapping()
    public void deleteAll() {
        classSubscriptionService.deleteAll();
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Integer id) {
        classSubscriptionService.delete(id);
    }

    @GetMapping(EXPORT_REPORT)
    public void exportReport(@PathVariable ReportType type) {
        reportServiceFactory.getReportService(type).export();
    }
}
