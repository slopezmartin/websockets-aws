package org.softwaresapiens.websockets.controllers;


import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.softwaresapiens.websockets.domain.*;
import org.softwaresapiens.websockets.services.ReportService;
import org.softwaresapiens.websockets.services.WebSocketNotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportControllerTest {

    @InjectMocks
    private ReportController controllerUnderTest;
    @Mock
    private ReportService mockReportService;

    @Mock
    private WebSocketNotificationService mockWebSocketNotificationService;

    @Test
    void testCreateReport(){

        CreateReportRequest createReportRequest = new CreateReportRequest();
        createReportRequest.setType("reportType");

        Report reportCreated = new Report(UUID.randomUUID(),createReportRequest.getType(),null,ReportStatus.PENDING);

        when(mockReportService.create(createReportRequest.getType())).thenReturn(reportCreated);
        ResponseEntity<CreateReportResponse> response = controllerUnderTest.createReport(createReportRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCodeValue());
        assertEquals(reportCreated.getId(), response.getBody().getId());
        assertEquals(reportCreated.getType(), response.getBody().getType());

        verify(mockReportService).create(createReportRequest.getType());
        verifyNoMoreInteractions(mockReportService);
    }

    @Test
    void updateReport(){
            UpdateReportRequest updateReportRequest = new UpdateReportRequest("path", ReportStatus.COMPLETED);
            UUID reportId = UUID.randomUUID();

            Report reportUpdated = new Report(reportId, "updatedReportType", updateReportRequest.getPath(), updateReportRequest.getStatus());
            when(mockReportService.update(reportId,updateReportRequest.getPath(), updateReportRequest.getStatus())).thenReturn(reportUpdated);
            doNothing().when(mockWebSocketNotificationService).notifyClient(any(), anyMap());
            ResponseEntity<UpdateReportResponse> response = controllerUnderTest.updateReport(reportId, updateReportRequest);
            assertNotNull(response);
            assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
            assertEquals("path", response.getBody().getPath());
            assertEquals(ReportStatus.COMPLETED, response.getBody().getStatus());

            verify(mockReportService).update(reportId, updateReportRequest.getPath(), updateReportRequest.getStatus());
            verifyNoMoreInteractions(mockReportService);

    }
}