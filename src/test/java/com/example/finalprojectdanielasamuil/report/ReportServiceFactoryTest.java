package com.example.finalprojectdanielasamuil.report;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.finalprojectdanielasamuil.report.ReportType.CSV;
import static com.example.finalprojectdanielasamuil.report.ReportType.PDF;

@SpringBootTest
class ReportServiceFactoryTest {

    @Autowired
    private ReportServiceFactory reportServiceFactory;

    @Test
    void getReportService() {
        ReportService csvReportService = reportServiceFactory.getReportService(CSV);
        Assertions.assertEquals("CSV!", csvReportService.export());

        ReportService pdfReportService = reportServiceFactory.getReportService(PDF);
        Assertions.assertEquals("PDF!", pdfReportService.export());
    }
}
