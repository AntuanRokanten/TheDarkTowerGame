package com.anton4j.darktower.game.component.scene;

import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.ConsoleUtils;
import com.anton4j.darktower.console.FontColor;
import com.anton4j.darktower.game.component.option.Option;
import com.anton4j.darktower.game.component.option.OptionResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Scene for displaying a list of options on the console.
 *
 * @author ant
 */
public class OptionsScene<T> extends Scene<T> {

    /**
     * Options of the scene.
     */
    private final List<Option<T>> options;

    public OptionsScene(List<Option<T>> options, ConsoleLine sceneTitle) {
        super(sceneTitle);
        this.options = options;
    }

    public OptionsScene(List<Option<T>> options) {
        this.options = options;
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
            //noinspection ResultOfMethodCallIgnored
            Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
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
                return processScene();
            }
        } else {
            String indexes = IntStream.range(1, options.size() + 1)
                  .mapToObj(String::valueOf)
                  .collect(Collectors.joining(", "));

            new ConsoleLine("Please enter a valid value. Possible values: " + indexes, FontColor.YELLOW).println();

            return processScene();
        }
    }

}