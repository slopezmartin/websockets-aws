package org.softwaresapiens.websockets.repositories;

import lombok.extern.slf4j.Slf4j;
import org.softwaresapiens.websockets.domain.Report;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Slf4j
public class ReportRepository {

    public Report save(Report report) {
        report.setId(UUID.randomUUID());
        log.info("Saving report with ID: {}", report.getId());
        return report;
    }
}
