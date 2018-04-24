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

import static com.anton4j.darktower.character.battle.EncounterOption.FIGHT;
import static com.anton4j.darktower.character.battle.EncounterOption.RUN_AWAY;
import static com.anton4j.darktower.util.RandomUtils.randomBoolean;
import static com.anton4j.darktower.util.Utils.optionsFromEnumValues;

/**
 * @author anton
 */
public class ExploreOption extends Option<RoundOutcome> {

    public ExploreOption() {
        super("Explore the location");
    }

    @Override
    public OptionResult<RoundOutcome> processOption() {
        new ConsoleLine("You are exploring a current location...", FontColor.GREEN).println();

        if (randomBoolean() && randomBoolean()) {
            new ConsoleLine("You have explored new territories in the current location", FontColor.YELLOW).println();
            // todo print result
        } else {
            ConsoleUtils.beep();

            new ConsoleLine("You encountered a beast", FontColor.PURPLE).println();

            Char mainCharacter = GameContext.getInstance().getMainCharacter();

            Mob enemy = CreatureFactory.createCharacterEnemy(mainCharacter);
            new ConsoleLine("Beast stats: " + enemy.toString(), FontColor.PURPLE).println();
            new ConsoleLine("Your stats: " + mainCharacter.toString(), FontColor.BLACK).println();

            EncounterOption encounterOption = new OptionsScene<>(optionsFromEnumValues(EncounterOption.values()))
                  .processScene();

            if (encounterOption == FIGHT) {
                FightOutcome fightOutcome = mainCharacter.fight(enemy);

                CharEvent fightVictory;
                if (fightOutcome == FightOutcome.VICTORY) {
                    new ConsoleLine("Congrats! Your character won the fight!", FontColor.CYAN).println();
                    fightVictory = CharEvent.FIGHT_VICTORY;
                } else if (fightOutcome == FightOutcome.DEFEAT) {
                    new ConsoleLine("Your character lost the battle. Take a rest in order to heal.", FontColor.RED).println();
                    fightVictory = CharEvent.FIGHT_DEFEAT;
                } else {
                    return new OptionResult<>(EventResult.Status.ERROR, null);
                }
                mainCharacter.increaseExperience(fightVictory);

            } else if (encounterOption == RUN_AWAY ) {
                mainCharacter.runAway(enemy);
            } else {
                return new OptionResult<>(EventResult.Status.ERROR, null);
            }
        }

        return new OptionResult<>(EventResult.Status.SUCCESS, new RoundOutcome());
    }

}