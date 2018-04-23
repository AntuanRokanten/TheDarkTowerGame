package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.CreatureFactory;
import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.character.Creature;
import com.anton4j.darktower.character.RoundOutcome;
import com.anton4j.darktower.character.battle.EncounterOption;
import com.anton4j.darktower.component.event.EventResult;
import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionResult;
import com.anton4j.darktower.component.scene.OptionsScene;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.ConsoleUtils;
import com.anton4j.darktower.console.FontColor;

import static com.anton4j.darktower.character.battle.EncounterOption.FIGHT;
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

        if (randomBoolean()) {
            ConsoleUtils.beep();

            new ConsoleLine("You encountered a beast", FontColor.PURPLE).println();

            Creature enemy = CreatureFactory.createCharacterEnemy(GameContext.getInstance().getMainCharacter());
            new ConsoleLine("Beast stats: " + enemy.toString(), FontColor.PURPLE).println();
            new ConsoleLine("Your stats: " + GameContext.getInstance().getMainCharacter().toString(), FontColor.BLACK).println();

            EncounterOption encounterOption = new OptionsScene<>(optionsFromEnumValues(EncounterOption.values()))
                  .processScene();

            if (encounterOption == FIGHT) {
                new ConsoleLine("Starting a battle", FontColor.PURPLE).println();

                // todo battle
            } else {
                new ConsoleLine("You are running away ...", FontColor.PURPLE).println();
                // todo print result
            }
        } else {
            new ConsoleLine("You have explored new territories in the current location", FontColor.YELLOW).println();
            // todo print result
        }

        return new OptionResult<>(EventResult.Status.SUCCESS, new RoundOutcome());
    }

}