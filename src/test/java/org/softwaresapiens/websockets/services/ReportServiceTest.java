package org.softwaresapiens.websockets.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.softwaresapiens.websockets.domain.Report;
import org.softwaresapiens.websockets.repositories.ReportRepository;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportService reportService;


    @Test
    void create() {

        Report reportSaved = new Report(UUID.randomUUID(), "reportType");

        when(reportRepository.save(any(Report.class))).thenReturn(reportSaved);

        Report result = reportService.create("reportType");

        assertEquals("reportType", result.getType());
        assertEquals(reportSaved.getId(), result.getId());

        verify(reportRepository).save(any(Report.class));
        verifyNoMoreInteractions(reportRepository);

    }
}