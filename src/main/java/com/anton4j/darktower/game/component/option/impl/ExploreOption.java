package com.anton4j.darktower.game.component.option.impl;

import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.ConsoleUtils;
import com.anton4j.darktower.console.FontColor;
import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.character.*;
import com.anton4j.darktower.game.character.encounter.EncounterOption;
import com.anton4j.darktower.game.character.encounter.EncounterOutcome;
import com.anton4j.darktower.game.component.option.Option;
import com.anton4j.darktower.game.component.option.OptionResult;
import com.anton4j.darktower.game.component.scene.OptionsScene;
import com.anton4j.darktower.game.component.util.OptionsFromEnum;

import java.util.List;

import static com.anton4j.darktower.game.character.CharEvent.*;
import static com.anton4j.darktower.game.character.encounter.EncounterOption.FIGHT;
import static com.anton4j.darktower.game.character.encounter.EncounterOption.THESE_ARE_NOT_THE_DROIDS_YOU_ARE_LOOKING_FOR;
import static com.anton4j.darktower.game.character.encounter.EncounterOutcome.SUCCESS;
import static com.anton4j.darktower.util.RandomUtils.randomBoolean;

/**
 * Option for exploring location.
 *
 * @author ant
 */
public class ExploreOption extends Option<Void> {

    public ExploreOption(GameContext gameContext) {
        super(gameContext, "Explore the location");
    }

    @Override
    protected OptionResult<Void> processOptionForResult() {
        new ConsoleLine("You are exploring a current location...", FontColor.GREEN).println();

        Char mainCharacter = gameContext.getMainCharacter();
        if (gameContext.getGameMap().canMoveToNextLocation(mainCharacter)) {
            new ConsoleLine("The character is ready to move closer to the destination! Choose 'Move to the next location' option", FontColor.BLUE).println();
        }

        CharEvent charEvent;
        if (randomBoolean() && randomBoolean()) {
            new ConsoleLine("You have explored new territories in the current location", FontColor.YELLOW).println();
            charEvent = EXPLORATION;
        } else {
            ConsoleUtils.beep();

            new ConsoleLine("You encountered a beast", FontColor.PURPLE).println();

            Mob enemy = CreatureFactory.createCharacterEnemy(mainCharacter);
            new ConsoleLine("Beast stats: " + enemy.toString(), FontColor.PURPLE).println();
            new ConsoleLine("Your stats: " + mainCharacter.toString(), FontColor.WHITE).println();

            List<Option<EncounterOption>> options = new OptionsFromEnum<>(EncounterOption.values()).options(gameContext);
            EncounterOption encounterOption = new OptionsScene<>(options)
                  .processScene();

            if (encounterOption == FIGHT) {
                charEvent = processFight(mainCharacter, enemy);
            } else if (encounterOption == THESE_ARE_NOT_THE_DROIDS_YOU_ARE_LOOKING_FOR) {
                EncounterOutcome runOutcome = mainCharacter.deceit(enemy);
                if (runOutcome == SUCCESS) {
                    charEvent = RUN_SUCCESS;
                } else {
                    charEvent = processFight(mainCharacter, enemy);
                }
            } else {
                new ConsoleLine("Please try again", FontColor.RED).println();
                return processOption();
            }
        }
        charEvent.logEvent(gameContext);
        mainCharacter.increaseExperience(charEvent);

        return OptionResult.success();
    }

    private CharEvent processFight(Creature mainCharacter, Creature enemy) {
        EncounterOutcome fightOutcome = mainCharacter.fight(enemy);

        CharEvent charEvent;
        if (fightOutcome == SUCCESS) {
            new ConsoleLine("You won the fight!", FontColor.CYAN).println();
            charEvent = FIGHT_VICTORY;
        } else {
            new ConsoleLine("You lost the fight. Take a rest in order to heal.", FontColor.RED).println();
            charEvent = FIGHT_DEFEAT;
        }

        return charEvent;
    }

}