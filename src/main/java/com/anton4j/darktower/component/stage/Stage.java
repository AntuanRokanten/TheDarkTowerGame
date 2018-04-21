package com.anton4j.darktower.component.stage;

import com.anton4j.darktower.component.option.Option;

import java.util.List;
import java.util.Optional;

/**
 * @author anton
 */
public abstract class Stage {

    private final List<Option> options;

    public Stage(List<Option> options) {
        this.options = options;
    }

    public final List<Option> options() {
        return options;
    }

    public void handleSelection(int index) {
        Optional<Option> optionalOption = options
              .stream()
              .filter(option -> option.index() == index)
              .findFirst();

        if (optionalOption.isPresent()) {
            optionalOption.get().processOption();
        } else {
            // todo
        }
    }

    public abstract boolean stageCompleted();

}
