package com.talentreef.interviewquestions.takehome.controllers;

import com.talentreef.interviewquestions.takehome.models.Widget;
import com.talentreef.interviewquestions.services.WidgetService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

@RunWith(SpringRunner.class)
public class WidgetControllerTests {

  @Mock
  private WidgetService widgetService;

  @InjectMocks
  private WidgetController widgetController;

  public WidgetControllerTests() {
    MockitoAnnotations.openMocks(this);
//    widgetController = new Mock(WidgetService.class);
  }

  @Test
  void testGetAllWidgets_ReturnsWidgets_WhenFound() {
    // Arrange
    List<Widget> mockWidgets = List.of(
            new Widget(1L, "Widget1", "Description1", new BigDecimal("12.34"))
    );
    when(widgetService.getAllWidget()).thenReturn(mockWidgets);

    // Act
    ResponseEntity<List<Widget>> response = widgetController.getAllWidgets();

    // Assert
    assertNotNull(response);
    assertEquals(OK, response.getStatusCode());
    assertEquals(1, response.getBody().size());
    verify(widgetService, times(1)).getAllWidget();
  }

  @Test
  void testGetAllWidgets_ReturnsNotFound_WhenNoWidgets() {
    // Arrange
    when(widgetService.getAllWidget()).thenThrow(new RuntimeException("No widgets found"));

    // Act
    Exception exception = assertThrows(RuntimeException.class, () -> {
      widgetController.getAllWidgets();
    });

    // Assert
    assertEquals("No widgets found", exception.getMessage());
    verify(widgetService, times(1)).getAllWidget();
  }
}