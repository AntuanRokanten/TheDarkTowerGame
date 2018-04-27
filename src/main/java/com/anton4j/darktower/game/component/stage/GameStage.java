package com.anton4j.darktower.game.component.stage;

import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.component.scene.Scene;
import com.anton4j.darktower.console.ConsoleLine;

/**
 * Stage of the game.
 *
 * @author ant
 */
public abstract class GameStage<T> {

    /**
     * Stage next to the current.
     */
    private final GameStage next;

    /**
     * Intro to be displayed before processing stage.
     */
    private final ConsoleLine stageIntro;

    /**
     * Flag denotes if this stage is completed.
     */
    private boolean stageCompleted;

    public GameStage(GameStage next, ConsoleLine stageIntro) {
        this.next = next;
        this.stageIntro = stageIntro;
    }

    public GameStage(GameStage next) {
        this(next, null);
    }

    /**
     * Processes the current stage.
     *
     * @param gameContext context of the game.
     */
    public void processScene(GameContext gameContext) {
        if (stageIntro != null) {
            stageIntro.println();
        }

        stageScene(gameContext).processScene();
        stageCompleted = getCompletionStatus(gameContext);
    }

    /**
     * @return stage next to the current.
     */
    public GameStage nextStage() {
        return next;
    }

    /**
     * @param gameContext context of the game.
     * @return scene of this stage.
     */
    public abstract Scene<T> stageScene(GameContext gameContext);

    /**
     * @return flag that denotes if the stage was completed.
     */
    public boolean stageCompleted() {
        return stageCompleted;
    }

    /**
     * Determines if the stage was completed.
     *
     * @param gameContext context of the game.
     * @return true if this stage was completed.
     */
    public abstract boolean getCompletionStatus(GameContext gameContext);

}