package com.anton4j.darktower.event;

import com.anton4j.darktower.event.option.Option;

import java.util.List;

/**
 * @author anton
 */
public interface Event {

    void handleSelection();
    List<Option> eventOptions();

}
