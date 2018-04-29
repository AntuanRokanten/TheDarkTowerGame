package com.anton4j.darktower.game.component.option;

import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;
import com.anton4j.darktower.game.GameContext;

/**
 * Option that can be chosen in console.
 *
 * @author ant
 */
public abstract class Option<T> {

    /**
     * Context of the game.
     */
    protected final GameContext gameContext;

    /**
     * Text that should be displayed on the scene for this option.
     */
    private final String displayText;

    protected Option(GameContext gameContext, String displayText) {
        this.gameContext = gameContext;
        this.displayText = displayText;
    }

    /**
     * @return scene display value.
     */
    public String displayText() {
        return displayText;
    }

    public final OptionResult<T> processOption() {
        try {
            return processOptionForResult();
        } catch (Exception e) {
            new ConsoleLine("Error occurred while processing option.", FontColor.RED).println();
            return OptionResult.error();
        }
    }

    /**
     * Processes this option.
     *
     * @return result of the option processing.
     */
    protected abstract OptionResult<T> processOptionForResult();

}
