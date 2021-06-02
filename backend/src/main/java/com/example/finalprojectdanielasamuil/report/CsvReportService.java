package com.example.finalprojectdanielasamuil.report;

import com.example.finalprojectdanielasamuil.mapper.ClassSubscriptionMapper;
import com.example.finalprojectdanielasamuil.model.dtos.ClassSubscriptionDto;
import com.example.finalprojectdanielasamuil.repository.ClassSubscriptionRepository;
import com.example.finalprojectdanielasamuil.service.ClassSubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.example.finalprojectdanielasamuil.report.ReportType.CSV;

@Service
@AllArgsConstructor
public class CsvReportService implements ReportService {

    private final ClassSubscriptionService classSubscriptionService;

    @Override
    public String export() {
        try {

            List<ClassSubscriptionDto> classSubscriptionDtoList = classSubscriptionService.findAll();

            FileWriter fileWriter;
            fileWriter = new FileWriter("AllClassSubscriptions.csv");
            fileWriter.append("ID customer, ID fitness class");
            fileWriter.append("\n");

            for (ClassSubscriptionDto classSubscriptionDto : classSubscriptionDtoList) {
                fileWriter.append(classSubscriptionDto.getCustomerId() + "");
                fileWriter.append(",");
                fileWriter.append(classSubscriptionDto.getFitnessClassId() + "");
                fileWriter.append("\n");
            }

            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "CSV!";
    }

    @Override
    public ReportType getType() {
        return CSV;
    }
}
