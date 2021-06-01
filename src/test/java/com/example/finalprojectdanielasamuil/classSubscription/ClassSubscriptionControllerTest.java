package com.example.finalprojectdanielasamuil.classSubscription;

import com.example.finalprojectdanielasamuil.BaseControllerTest;
import com.example.finalprojectdanielasamuil.TestCreationFactory;
import com.example.finalprojectdanielasamuil.controller.ClassSubscriptionController;
import com.example.finalprojectdanielasamuil.model.dtos.ClassSubscriptionDto;
import com.example.finalprojectdanielasamuil.report.CsvReportService;
import com.example.finalprojectdanielasamuil.report.PdfReportService;
import com.example.finalprojectdanielasamuil.report.ReportServiceFactory;
import com.example.finalprojectdanielasamuil.service.ClassSubscriptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.List;

import static com.example.finalprojectdanielasamuil.TestCreationFactory.randomInt;
import static com.example.finalprojectdanielasamuil.UrlMapping.*;
import static com.example.finalprojectdanielasamuil.report.ReportType.CSV;
import static com.example.finalprojectdanielasamuil.report.ReportType.PDF;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClassSubscriptionControllerTest extends BaseControllerTest {

    @InjectMocks
    private ClassSubscriptionController controller;

    @Mock
    private ClassSubscriptionService classSubscriptionService;

    @Mock
    private ReportServiceFactory reportServiceFactory;

    @Mock
    private CsvReportService csvReportService;

    @Mock
    private PdfReportService pdfReportService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        MockitoAnnotations.openMocks(this);
        controller = new ClassSubscriptionController(classSubscriptionService, reportServiceFactory);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void allFitnessClasses() throws Exception {
        List<ClassSubscriptionDto> classSubscriptionDtos = TestCreationFactory.listOf(ClassSubscriptionDto.class);
        when(classSubscriptionService.findAll()).thenReturn(classSubscriptionDtos);

        ResultActions result = mockMvc.perform(get(SUBSCRIPTIONS));
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(classSubscriptionDtos));
    }

    @Test
    void create() throws Exception {
        ClassSubscriptionDto classSubscriptionDto = ClassSubscriptionDto.builder()
                .id(randomInt())
                .customerId(randomInt())
                .fitnessClassId(randomInt())
                .build();

        when(classSubscriptionService.create(classSubscriptionDto)).thenReturn(false);

        ResultActions result = performPostWithRequestBody(SUBSCRIPTIONS, classSubscriptionDto);
        result.andExpect(status().isOk());
    }

    @Test
    void deleteAll() throws Exception {
        doNothing().when(classSubscriptionService).deleteAll();

        ResultActions result = performDelete(SUBSCRIPTIONS);
        result.andExpect(status().isOk());
        verify(classSubscriptionService, times(1)).deleteAll();
    }

    @Test
    void delete() throws Exception {
        int id = randomInt();
        doNothing().when(classSubscriptionService).delete(id);

        ResultActions result = performDeleteWithPathVariable(SUBSCRIPTIONS + ENTITY, id);
        result.andExpect(status().isOk());
        verify(classSubscriptionService, times(1)).delete(id);
    }

    @Test
    void exportReport() throws Exception {

        when(reportServiceFactory.getReportService(PDF)).thenReturn(pdfReportService);
        when(reportServiceFactory.getReportService(CSV)).thenReturn(csvReportService);
        String pdfResponse = "";
        when(pdfReportService.export()).thenReturn(pdfResponse);
        String csvResponse = "";
        when(csvReportService.export()).thenReturn(csvResponse);

        ResultActions pdfExport = mockMvc.perform(get(SUBSCRIPTIONS + EXPORT_REPORT, PDF.name()));
        ResultActions csvExport = mockMvc.perform(get(SUBSCRIPTIONS + EXPORT_REPORT, CSV.name()));

        pdfExport.andExpect(status().isOk())
                .andExpect(content().string(pdfResponse));
        csvExport.andExpect(status().isOk())
                .andExpect(content().string(csvResponse));

    }
}
