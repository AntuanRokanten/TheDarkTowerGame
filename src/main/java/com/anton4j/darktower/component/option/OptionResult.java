package com.anton4j.darktower.component.option;

import com.anton4j.darktower.component.event.EventResult;

/**
 * @author ant
 */
public class OptionResult<T> {

//    public enum Status {
//        SUCCESS, ERROR
//    }

    private final EventResult.Status status;
    private final T resultObj;

    public OptionResult(EventResult.Status status, T resultObj) {
        this.status = status;
        this.resultObj = resultObj;
    }

    public EventResult.Status status() {
        return status;
    }

    public T getResultObj() {
        return resultObj;
    }
}
