package com.example.finalprojectdanielasamuil.report;

import com.example.finalprojectdanielasamuil.model.dtos.ClassSubscriptionDto;
import com.example.finalprojectdanielasamuil.service.ClassSubscriptionService;
import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.example.finalprojectdanielasamuil.report.ReportType.PDF;

@Service
@AllArgsConstructor
public class PdfReportService implements ReportService {

    private final ClassSubscriptionService classSubscriptionService;

    @Override
    public String export() {
        try {

            List<ClassSubscriptionDto> classSubscriptionDtoList = classSubscriptionService.findAll();

            PDDocument document = new PDDocument();
            PDPage page = new PDPage();

            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.COURIER_BOLD_OBLIQUE, 20);
            contentStream.newLineAtOffset(-200, -100);
            contentStream.newLineAtOffset(300, 800);
            contentStream.setLeading(20);
            contentStream.showText("LIST OF CLASS SUBSCRIPTIONS");
            contentStream.newLine();
            contentStream.newLine();

            for (ClassSubscriptionDto classSubscriptionDto : classSubscriptionDtoList) {

                contentStream.setFont(PDType1Font.COURIER, 10);

                contentStream.showText("ID customer: " + classSubscriptionDto.getCustomerId());
                contentStream.newLine();
                contentStream.showText("ID fitness class: " + classSubscriptionDto.getFitnessClassId());
                contentStream.newLine();
                contentStream.newLine();
            }

            contentStream.endText();
            contentStream.close();

            document.save("AllClassSubscriptions.pdf");
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "PDF!";
    }

    @Override
    public ReportType getType() {
        return PDF;
    }
}
