package com.talentreef.interviewquestions.services;

import com.talentreef.interviewquestions.takehome.Exceptions.WidgetNotFoundException;
import com.talentreef.interviewquestions.takehome.models.Widget;
import com.talentreef.interviewquestions.takehome.respositories.WidgetRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Service
public class WidgetService {

  private static final Logger logger =
          LoggerFactory.getLogger(WidgetService.class);
  private final WidgetRepository widgetRepository;

  @Autowired
  private WidgetService(WidgetRepository widgetRepository) {
    Assert.notNull(widgetRepository, "widgetRepository must not be null");
    this.widgetRepository = widgetRepository;
  }
  public Widget createWidget(@Valid Widget widget) {
    // Check if widget with the same name already exists
    if (widgetRepository.findByName(widget.getName()).isPresent()) {
      System.out.println("name must be unique");
      throw new IllegalArgumentException("Widget name must be unique.");
    }
    return widgetRepository.save(widget);
  }


  public Widget getWidgetByName(String name) {
    return widgetRepository.findByName(name)
            .orElseThrow(() -> new WidgetNotFoundException("Widget not found with name: " + name));
  }
  public List<Widget> getAllWidget() {
    try {
      logger.info("Fetching all widgets from the database...");
      List<Widget> widgets = widgetRepository.findAll();

      if (widgets.isEmpty()) {
        logger.warn("No widgets found in the database!");
        throw new WidgetNotFoundException("No widgets found in the database.");
      }

      logger.debug("Found {} widgets", widgets.size());
      return widgets;

    } catch (Exception ex) {
      logger.error("An exception occurred while fetching widgets", ex);
      throw new RuntimeException("An error occurred while fetching widgets.", ex);
    }
//            .orElseThrow(() -> new WidgetNotFoundException("Widget not found with name: " + name));
  }


  public Widget updateWidget(String name, @NotNull Widget updatedWidget) {
    Widget existingWidget = widgetRepository.findByName(name)
            .orElseThrow(() -> new WidgetNotFoundException("Widget not found with Name: " + name));
    existingWidget.setDescription(updatedWidget.getDescription());
    existingWidget.setPrice(updatedWidget.getPrice());
    return widgetRepository.save(existingWidget);
  }

  public List<Widget> deleteWidget(String name) {
    if (!widgetRepository.existsByName(name)) {
      throw new WidgetNotFoundException("Widget not found with name: " + name);
    }
    return widgetRepository.deleteByName(name);
  }


  public List<Widget> getAllWidgets() {
    return widgetRepository.findAll();
  }

}
