package com.anton4j.darktower.component.scene;

import com.anton4j.darktower.component.option.EventOption;
import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.ValueOption;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.ConsoleUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author anton
 */
public class Scene {

    private final List<Option> options;
    private final ConsoleLine sceneTitle;

    public Scene(List<Option> options, ConsoleLine sceneTitle) {
        this.options = options;
        this.sceneTitle = sceneTitle;
    }

    public void processScene() {
        sceneTitle.println();

        for (Option option : options) {
            option.consoleLine().println();
        }

        String selectedIndex = ConsoleUtils.readLine();

        Integer integer = Integer.valueOf(selectedIndex); // todo reame

        Optional<Option> optionalOption = options
              .stream()
              .filter(option -> option.index() == integer)
              .findFirst();

        if (optionalOption.isPresent()) {
            Option option = optionalOption.get();
            if (option instanceof EventOption) {
                ((EventOption) option).optionEvent().process();
            } else if (option instanceof ValueOption) {
                ((ValueOption) option).getValue();
            }
        } else {
            // todo
        }
    }

}
