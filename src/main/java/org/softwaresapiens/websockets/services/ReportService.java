package org.softwaresapiens.websockets.services;

import lombok.RequiredArgsConstructor;
import org.softwaresapiens.websockets.controllers.ReportController;
import org.softwaresapiens.websockets.domain.Report;
import org.softwaresapiens.websockets.domain.ReportStatus;
import org.softwaresapiens.websockets.exceptions.EntityNotFoundException;
import org.softwaresapiens.websockets.repositories.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    public Report create(String reportType) {
        Report report = new Report();
        report.setType(reportType);
        report.setId(UUID.randomUUID());
        return reportRepository.save(report);
    }


    public Report update(UUID uuid, String path, ReportStatus status) {
        Report reportInDataBase = reportRepository.findById(uuid);

        if(Objects.isNull(reportInDataBase)){
            throw new EntityNotFoundException("Report not found with ID: " + uuid);
        }

        Report reportToUpdate = new Report();
        reportToUpdate.setId(reportInDataBase.getId());
        reportToUpdate.setType(reportInDataBase.getType());
        reportToUpdate.setPath(path);
        reportToUpdate.setStatus(status);

        return reportRepository.save(reportToUpdate);
    }
}
