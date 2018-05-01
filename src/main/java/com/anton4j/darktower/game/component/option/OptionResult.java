package com.anton4j.darktower.game.component.option;

/**
 * Result of the option execution.
 *
 * @author ant
 */
public class OptionResult<T> {

    /**
     * Result status.
     */
    private final Status status;
    /**
     * Object produced by the option.
     */
    private final T resultObj;

    public OptionResult(Status status, T resultObj) {
        this.status = status;
        this.resultObj = resultObj;
    }

    /**
     * Creates successful result object.
     *
     * @param resultObj object produced by the option.
     * @return success result containing specified value.
     */
    public static <E> OptionResult<E> success(E resultObj) {
        return new OptionResult<>(Status.SUCCESS, resultObj);
    }

    /**
     * Creates successful result object.
     *
     * @return empty success result.
     */
    public static <E> OptionResult<E> success() {
        return new OptionResult<>(Status.SUCCESS, null);
    }

    /**
     * Creates error result object.
     *
     * @return error result.
     */
    public static <E> OptionResult<E> error() {
        return new OptionResult<>(Status.ERROR, null);
    }

    /**
     * Checks if this result is successful.
     *
     * @return true if success; false otherwise.
     */
    public boolean isSuccess() {
        return this.status == Status.SUCCESS;
    }

    /**
     * @return object produced by the option.
     */
    public T getResultObj() {
        return resultObj;
    }

    /**
     * Option result status.
     */
    public enum Status {
        SUCCESS, ERROR
    }

}
