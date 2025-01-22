package com.talentreef.interviewquestions.takehome.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.talentreef.interviewquestions.services.WidgetService;
import com.talentreef.interviewquestions.takehome.models.Widget;
import com.talentreef.interviewquestions.takehome.respositories.WidgetRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
//@ExtendWith(MockitoExtension.class)
public class WidgetServiceTests {

  @Mock
  private WidgetRepository widgetRepository;

  @InjectMocks
  private WidgetService widgetService;

  @Test
  public void testGetAllWidgetsExpectFindAllResult() throws Exception {
    Widget widget = Widget.builder().name("Widgette").build();
    Widget widget1 = Widget.builder().name("Samuel").description("This is Description").price(BigDecimal.valueOf(10.33)).build();
    List<Widget> response = List.of(widget);
    when(widgetRepository.findAll()).thenReturn(response);

    List<Widget> result = widgetService.getAllWidgets();

    assertThat(result).isEqualTo(response);
  }
  
  @Test
  public void testDeleteWidgetExpectDeleteResult() throws Exception {
    Widget widget = Widget.builder().name("Widgette").build(); //stubbing
    List<Widget> response = List.of(widget);
    when(widgetRepository.existsByName(any())).thenReturn(true);
    when(widgetRepository.deleteByName("Widgette")).thenReturn(response); //Method Stubbing
    List<Widget> result = widgetService.deleteWidget("Widgette");
    assertThat(result).isEqualTo(response);
  }

  @Test
  public void testUpdateWidgetExpectUpdatedResult() throws Exception {
    Widget widget = Widget.builder().name("Widgette").build();
    Widget updatedWidget = Widget.builder().name("Widgette").description("Updated Description").build();
    when(widgetRepository.save(widget)).thenReturn(updatedWidget); // Method Stubbing
    when(widgetRepository.findByName("Widgette")).thenReturn(Optional.of(widget));
    Widget result = widgetService.updateWidget(widget.getName(), widget);
    assertThat(result).isEqualTo(updatedWidget);
  }

  @Test
  public void testFindWidgetByNameExpectCorrectWidget() throws Exception {
    Widget widget = Widget.builder().name("Samuel").description("Test description").build();
    when(widgetRepository.findByName("Samuel")).thenReturn(Optional.ofNullable(widget)); // Method Stubbing
    Widget result = widgetService.getWidgetByName("Samuel");
    assertThat(result).isEqualTo(widget);
  }

  @Test
  public void testCreateWidgetExpectWidgetCreation() throws Exception {
    Widget widget = Widget.builder().name("Samuel").description("Test description").build();
    when(widgetRepository.save(widget)).thenReturn(widget);
    Widget result = widgetService.createWidget(widget);
    assertThat(result).isEqualTo(widget);
  }
  
  

}
