package com.anton4j.darktower.game.component.option;

/**
 * @author ant
 */
public class OptionResult<T> {

    public enum Status {
        SUCCESS, ERROR
    }

    private final Status status;
    private final T resultObj;

    public OptionResult(Status status, T resultObj) {
        this.status = status;
        this.resultObj = resultObj;
    }

    public boolean isSuccess() {
        return this.status == Status.SUCCESS;
    }

    public T getResultObj() {
        return resultObj;
    }

    public static <E> OptionResult<E> success(E resultObj) {
        return new OptionResult<>(Status.SUCCESS, resultObj);
    }

    public static <E> OptionResult<E> success() {
        return new OptionResult<>(Status.SUCCESS, null);
    }

    public static <E> OptionResult<E> error() {
        return new OptionResult<>(Status.ERROR, null);
    }

}
