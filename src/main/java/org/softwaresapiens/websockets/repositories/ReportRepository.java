package org.softwaresapiens.websockets.repositories;

import lombok.extern.slf4j.Slf4j;
import org.softwaresapiens.websockets.domain.Report;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Slf4j
public class ReportRepository {

    public Report save(Report report) {
        log.info("Saving report with ID: {}", report.getId());
        return report;
    }

    public Report findById(UUID reportId) {
        Report report = new Report();
        report.setId(reportId);
        return report;
    }
}
