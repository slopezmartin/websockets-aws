package org.softwaresapiens.websockets.services;

import lombok.RequiredArgsConstructor;
import org.softwaresapiens.websockets.controllers.ReportController;
import org.softwaresapiens.websockets.domain.Report;
import org.softwaresapiens.websockets.repositories.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    public Report create(String reportType) {
        Report report = new Report();
        report.setType(reportType);
        return reportRepository.save(report);
    }
}
