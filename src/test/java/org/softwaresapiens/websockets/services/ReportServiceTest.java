package org.softwaresapiens.websockets.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.softwaresapiens.websockets.domain.Report;
import org.softwaresapiens.websockets.domain.ReportStatus;
import org.softwaresapiens.websockets.exceptions.EntityNotFoundException;
import org.softwaresapiens.websockets.repositories.ReportRepository;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
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

        Report reportSaved = new Report(UUID.randomUUID(), "reportType",null, ReportStatus.PENDING);

        when(reportRepository.save(any(Report.class))).thenReturn(reportSaved);

        Report result = reportService.create("reportType");

        assertEquals("reportType", result.getType());
        assertEquals(reportSaved.getId(), result.getId());
        assertEquals(ReportStatus.PENDING, result.getStatus());

        verify(reportRepository).save(any(Report.class));
        verifyNoMoreInteractions(reportRepository);

    }

    @Test
    void update_OK(){
        UUID reportId = UUID.randomUUID();
        String path = "path/to/report";
        ReportStatus status = ReportStatus.COMPLETED;

        Report reportInDatabase = new Report(reportId, "reportType", null, ReportStatus.PENDING);
        Report reportUpdated = new Report(reportId, "reportType", path, ReportStatus.COMPLETED);

        when(reportRepository.findById(reportId)).thenReturn(reportInDatabase);
        when(reportRepository.save(any(Report.class))).thenReturn(reportUpdated);

        Report result = reportService.update(reportId, path, status);

        assertNotNull(result);
        assertEquals(reportId, result.getId());
        assertEquals("path/to/report", result.getPath());
        assertEquals(ReportStatus.COMPLETED, result.getStatus());


        verify(reportRepository).findById(reportId);
        verify(reportRepository).save(any(Report.class));
        verifyNoMoreInteractions(reportRepository);

    }

    @Test
    void update_UUID_NotFound_Exception(){
        UUID reportId = UUID.randomUUID();
        String path = "path/to/report";
        ReportStatus status = ReportStatus.COMPLETED;

        when(reportRepository.findById(reportId)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, ()-> reportService.update(reportId, path, status), "Entity not found with id: " + reportId);

        verify(reportRepository).findById(reportId);
        verifyNoMoreInteractions(reportRepository);

    }
}