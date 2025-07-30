package org.softwaresapiens.websockets.repositories;

import org.junit.jupiter.api.Test;
import org.softwaresapiens.websockets.domain.Report;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReportRepositoryTest {

    private ReportRepository reportRepository = new ReportRepository();

    @Test
    void save() {

        Report report = new Report();
        report.setType("reportType");
        report.setId(UUID.randomUUID());

        Report result = reportRepository.save(report);
        assertEquals(report.getType(), result.getType());
        assertEquals(report.getId(), result.getId());
    }
}