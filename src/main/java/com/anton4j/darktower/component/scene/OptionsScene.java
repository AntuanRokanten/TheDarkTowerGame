package com.anton4j.darktower.component.scene;

import com.anton4j.darktower.component.event.EventResult;
import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionResult;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.ConsoleUtils;
import com.anton4j.darktower.console.FontColor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author anton
 */
public class OptionsScene<T> extends Scene<T> {

    private final List<Option> options;

    public OptionsScene(List<Option> options, ConsoleLine sceneTitle) {
        super(sceneTitle);

        this.options = options;
    }

    @Override
    public T processScene() {
        sceneTitle.println();

        options.forEach(Option::printConsoleLine);
        String line = ConsoleUtils.readLine();

        Optional<Option> selectedOption = getSelectedOption(line, options);
        if (selectedOption.isPresent()) {
            OptionResult o = selectedOption
                  .get()
                  .processOption();

            // todo return from event raw result then option checks it and returns result
            // todo here we check if it was success if not repeat the scene
            // todo e.g. load game may fail
            if (o.status() == EventResult.Status.SUCCESS) {
                return (T) o.getResultObj(); // todo
            } else {
                new ConsoleLine("Repeating a scene").println();
                return processScene();
            }
        } else {
            String indexes = options.stream().map(Option::index).map(String::valueOf).collect(Collectors.joining(", "));
            new ConsoleLine("Please enter a valid value. Possible values: " + indexes, FontColor.YELLOW).println();

            return processScene();
        }
    }

    private static Optional<Option> getSelectedOption(String line, List<Option> options) {
        if (!isNumeric(line)) {
            return Optional.empty();
        } else {
            int selectedIndex = Integer.parseInt(line);

            return options
                  .stream()
                  .filter(option -> option.index() == selectedIndex)
                  .findFirst();
        }
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
