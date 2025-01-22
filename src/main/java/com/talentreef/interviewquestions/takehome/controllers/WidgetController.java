package com.talentreef.interviewquestions.takehome.controllers;

import com.talentreef.interviewquestions.takehome.Exceptions.WidgetNotFoundException;
import com.talentreef.interviewquestions.takehome.models.Widget;
import com.talentreef.interviewquestions.services.WidgetService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000",
        allowedHeaders = {"Authorization", "Content-Type"},
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/v1/widget")
public class WidgetController {

  private static final Logger logger =
          LoggerFactory.getLogger(WidgetController.class);

  @Autowired
  private final WidgetService widgetService;

  public WidgetController(WidgetService widgetService) {
    Assert.notNull(widgetService, "widgetService must not be null");
    this.widgetService = widgetService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Widget createWidget(@RequestBody Widget  widget) {
    logger.info("Creating new widget: {}", widget);
    return widgetService.createWidget(widget);
  }


  @GetMapping("/getWidget")
  public Widget getWidgetByName(@RequestParam String name) {
    logger.info("Fetching widget by name: {}", name);
    return widgetService.getWidgetByName(name);
  }

  @GetMapping("/getAllWidget")
  public List<Widget> getAllWidget() {
    logger.info("Request received to fetch all widgets");

//    return widgetService.getAllWidget();

    try {
      List<Widget> widgets = widgetService.getAllWidget();
      logger.debug("Successfully fetched {} widgets", widgets.size());
      return widgets;
    } catch (WidgetNotFoundException ex) {
      logger.warn("No widgets found: {}", ex.getMessage());
      throw ex; // This will be handled by your Global Exception Handler
    } catch (Exception ex) {
      logger.error("Unexpected error occurred while fetching widgets", ex);
      throw ex;
    }

  }

  @PutMapping()
  public Widget updateWidget(@Valid @RequestBody Widget widget) {
    logger.info("Updating widget: {}", widget);
    return widgetService.updateWidget(widget.getName(), widget);
  }

  @DeleteMapping()
  @ResponseStatus(HttpStatus.ACCEPTED)
  public List<Widget> deleteWidget(@RequestParam String name) {
    logger.info("Deleting widget: {}", name);
    return widgetService.deleteWidget(name);
  }
}
