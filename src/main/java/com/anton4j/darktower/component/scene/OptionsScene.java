package com.anton4j.darktower.component.scene;

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

    private final List<Option<T>> options;

    public OptionsScene(List<Option<T>> options, ConsoleLine sceneTitle) {
        super(sceneTitle);
        this.options = options;
    }

    public OptionsScene(List<Option<T>> options) {
        this.options = options;
    }

    @Override
    public T processScene() {
        if (sceneTitle != null) {
            sceneTitle.println();
        }

        for (int i = 0; i < options.size(); i++) {
            new ConsoleLine((i + 1) + " - " + options.get(i).displayText()).println();
        }

        String line = ConsoleUtils.readLine();

        Optional<Option<T>> selectedOption = getSelectedOption(line, options);
        if (selectedOption.isPresent()) {
            Option<T> option = selectedOption.get();

            OptionResult<T> o = option.processOption();

            if (o.isSuccess()) {
                return o.getResultObj();
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

    private static <T> Optional<Option<T>> getSelectedOption(String line, List<Option<T>> options) {
        if (!isNumeric(line)) {
            return Optional.empty();
        } else {
            int selectedIndex = Integer.parseInt(line) - 1;

            if (selectedIndex >= 0 && selectedIndex < options.size()) {
                return Optional.of(options.get(selectedIndex));
            } else {
                return Optional.empty();
            }
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