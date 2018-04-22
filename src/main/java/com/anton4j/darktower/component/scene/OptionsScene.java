package com.anton4j.darktower.component.scene;

import com.anton4j.darktower.component.option.Option;
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
            Object o = selectedOption
                  .get()
                  .processOption();

            return (T) o; // todo
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
