package com.anton4j.darktower.component.event;

/**
 * @author anton
 */
public class EventResult<T> {

    public enum Status {
        SUCCESS, ERROR
    }

    private final Status status;
    private final T resultObj;

    private EventResult(Status status, T resultObj) {
        this.status = status;
        this.resultObj = resultObj;
    }

    public Status status() {
        return status;
    }

    public T resultObj() {
        return resultObj;
    }

    public static <E> EventResult<E> success(E resultObj) {
        return new EventResult<>(Status.SUCCESS, resultObj);
    }

    public static <E> EventResult<E> error() {
        return new EventResult<>(Status.ERROR, null);
    }

}
