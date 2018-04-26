package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.CreatureFactory;
import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.character.*;
import com.anton4j.darktower.character.battle.EncounterOption;
import com.anton4j.darktower.component.event.EventResult;
import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionResult;
import com.anton4j.darktower.component.scene.OptionsScene;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.ConsoleUtils;
import com.anton4j.darktower.console.FontColor;

import static com.anton4j.darktower.character.CharEvent.*;
import static com.anton4j.darktower.character.EncounterOutcome.SUCCESS;
import static com.anton4j.darktower.character.battle.EncounterOption.FIGHT;
import static com.anton4j.darktower.character.battle.EncounterOption.RUN_AWAY;
import static com.anton4j.darktower.util.RandomUtils.randomBoolean;
import static com.anton4j.darktower.util.Utils.optionsFromEnumValues;

/**
 * @author anton
 */
public class ExploreOption extends Option<RoundOutcome> {

    public ExploreOption(GameContext gameContext) {
        super(gameContext, "Explore the location");
    }

    @Override
    public OptionResult<RoundOutcome> processOption() {
        new ConsoleLine("You are exploring a current location...", FontColor.GREEN).println();

        Char mainCharacter = gameContext.getMainCharacter();
        if (gameContext.getGameMap().canMoveToNextLocation(mainCharacter)) {
            new ConsoleLine("The character is ready to move closer to the destination!", FontColor.BLUE).println();
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

            EncounterOption encounterOption = new OptionsScene<>(optionsFromEnumValues(EncounterOption.values(), gameContext))
                  .processScene();

            if (encounterOption == FIGHT) {
                charEvent = processFight(mainCharacter, enemy);
            } else if (encounterOption == RUN_AWAY) {
                EncounterOutcome runOutcome = mainCharacter.runAway(enemy);
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

        return new OptionResult<>(EventResult.Status.SUCCESS, new RoundOutcome());
    }

    private CharEvent processFight(Creature mainCharacter, Creature enemy) {
        EncounterOutcome fightOutcome = mainCharacter.fight(enemy);

        CharEvent charEvent;
        if (fightOutcome == SUCCESS) {
            new ConsoleLine("Character won the fight!", FontColor.CYAN).println();
            charEvent = FIGHT_VICTORY;
        } else {
            new ConsoleLine("Character lost the battle. Take a rest in order to heal.", FontColor.RED).println();
            charEvent = FIGHT_DEFEAT;
        }

        return charEvent;
    }

}