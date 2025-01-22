package com.talentreef.interviewquestions.takehome.respositories;

import com.talentreef.interviewquestions.takehome.models.Widget;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class WidgetRepository {

  private List<Widget> table = new ArrayList<>();

  public List<Widget> deleteByName(String name) {
    this.table = table.stream()
                      .filter((Widget widget) -> !name.equals(widget.getName()))
                      .collect(Collectors.toCollection(ArrayList::new));
    return table;
  }

  public List<Widget> findAll() {
    return table;
  }

  public Optional<Widget> findByName(String name) {
    Optional<Widget> result = table.stream()
                                   .filter((Widget widget) -> name.equals(widget.getName()))
                                   .findAny();
    return result;
  }

  public Widget save(Widget widget) {
    if (widget.getId() == null || widget.getId() == 0L) {
      Random random = new Random();
      widget.setId(Math.abs(random.nextLong())); // Generate a random UUID and set it as the ID
    }

    deleteByName(widget.getName());
    table.add(widget);
    return widget;
  }

  public boolean existsByName(String name) {
    if (findByName(name).isEmpty()) {
      return false;
    }
    return true;
  }
}
