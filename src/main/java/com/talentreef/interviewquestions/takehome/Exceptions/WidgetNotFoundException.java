package com.talentreef.interviewquestions.takehome.Exceptions;

public class WidgetNotFoundException extends RuntimeException {
    public WidgetNotFoundException(String message) {
        super(message);
    }
}
