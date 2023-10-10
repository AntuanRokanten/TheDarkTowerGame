package com.anton4j.darktower.game.character;

import com.anton4j.darktower.audio.Audio;
import com.anton4j.darktower.audio.AudioFactory;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;
import com.anton4j.darktower.game.character.encounter.EncounterOutcome;
import com.anton4j.darktower.util.RandomUtils;

import java.io.Serializable;

import static com.anton4j.darktower.console.ConsoleUtils.sleep;
import static com.anton4j.darktower.game.character.encounter.EncounterOutcome.FAILURE;
import static com.anton4j.darktower.game.character.encounter.EncounterOutcome.SUCCESS;
import static com.anton4j.darktower.util.CalculateUtils.calculatePercentValue;
import static com.anton4j.darktower.util.CalculateUtils.calculatePercentage;
import static com.anton4j.darktower.util.RandomUtils.integerInRange;
import static com.anton4j.darktower.util.RandomUtils.randomBoolean;

/**
 * Creature that can be created in the game.
 *
 * @author ant
 */
public abstract class Creature implements Serializable {

    /**
     * Creature's race.
     */
    final Race race;

    /**
     * Current health value.
     */
    int health;

    /* stats */
    int vitality;
    int strength;
    int defence;
    int deceit;

    Creature(Race race, int vitality, int strength, int defence, int deceit) {
        this.defence = defence;
        this.vitality = vitality;
        this.strength = strength;
        this.race = race;
        this.deceit = deceit;

        this.health = vitality;
    }

    /**
     * Maximum health value.
     */
    public int vitality() {
        return vitality;
    }

    /**
     * deceit value.
     */
    public int deceit() {
        return deceit;
    }

    /**
     * Strength value.
     */
    public int strength() {
        return strength;
    }

    /**
     * Defence value.
     */
    public int defence() {
        return defence;
    }

    /**
     * Tries to deceit the specified creature.
     */
    public EncounterOutcome deceit(Creature enemy) {
        new ConsoleLine("You are applying Jedi Mind Trick: THESE AREN'T THE DROIDS YOU'RE LOOKING FOR", FontColor.PURPLE).println();

        EncounterOutcome encounterOutcome;
        if (this.deceit >= enemy.deceit) {
            float deceitPercentage = (100 - calculatePercentValue(this.deceit, enemy.deceit));
            if (deceitPercentage >= 30) {
                encounterOutcome = SUCCESS;
            } else if (this.health >= enemy.health) {
                encounterOutcome = SUCCESS;
            } else {
                float healthPercentage = (100 - calculatePercentValue(enemy.health, this.health));
                if (healthPercentage < 20) {
                    encounterOutcome = SUCCESS;
                } else {
                    encounterOutcome = FAILURE;
                }
            }
        } else {
            float deceitPercentage = (100 - calculatePercentValue(enemy.deceit, this.deceit));
            if (deceitPercentage >= 30) {
                encounterOutcome = FAILURE;
            } else if (enemy.health >= this.health) {
                encounterOutcome = FAILURE;
            } else {
                float healthPercentage = (100 - calculatePercentValue(this.health, enemy.health));
                if (healthPercentage < 20) {
                    encounterOutcome = FAILURE;
                } else {
                    encounterOutcome = SUCCESS;
                }
            }
        }

        if (encounterOutcome == SUCCESS) {
            new ConsoleLine("You can move on", FontColor.PURPLE).println();
        } else {
            new ConsoleLine("You was caught by the creature", FontColor.PURPLE).println();
        }

        return encounterOutcome;
    }

    /**
     * Fights specified creature.
     *
     * @param enemy creature to fight with.
     * @return fight outcome.
     */
    public EncounterOutcome fight(Creature enemy) {
        new ConsoleLine("Starting a fight", FontColor.PURPLE).println();

        Creature attacks;
        Creature defends;

        boolean thisAttacksFirst = RandomUtils.randomBoolean();
        if (thisAttacksFirst) {
            attacks = this;
            defends = enemy;
        } else {
            attacks = enemy;
            defends = this;
        }

        while (!attacks.isDefeated() && !defends.isDefeated()) {
            new ConsoleLine(attacks.race + " attacks", FontColor.WHITE).println();

            boolean criticalHit = randomBoolean() && randomBoolean() && randomBoolean() && randomBoolean();

            float attackPercent;
            if (criticalHit) {
                new ConsoleLine(attacks.race + " performs a critical hit!", FontColor.RED).println();
                attackPercent = 60f;
            } else {
                attackPercent = integerInRange(30, 40);
            }
            int hitStrength = calculatePercentage(attacks.strength, attackPercent);

            int defence = calculatePercentage(defends.defence, integerInRange(5, 15));

            int damage = hitStrength - defence;
            if (damage < 0) {
                damage = 0;
            }

            int healthAfterHit = defends.health - damage;
            if (healthAfterHit < 0) {
                healthAfterHit = 0;
            }

            defends.health = healthAfterHit;

            new ConsoleLine(defends.race + " health after attack: " + defends.health, FontColor.GREEN).println();

            Creature temp = defends;
            defends = attacks;
            attacks = temp;

            sleep(600);
        }

        EncounterOutcome fightOutcome;
        Audio audio;
        if (this.isDefeated()) {
            audio = AudioFactory.looseAudio();
            fightOutcome = FAILURE;
        } else {
            audio = AudioFactory.winAudio();
            fightOutcome = SUCCESS;
        }
        new Thread(audio::play).start();
        return fightOutcome;
    }

    /**
     * @return true if current creature health level is lower then 5% so it is considered defeated.
     */
    private boolean isDefeated() {
        float percent = calculatePercentValue(vitality, health);
        return percent < 5f;
    }

    @Override
    public String toString() {
        return "race = " + race +
               ", vitality = " + vitality +
               ", strength = " + strength +
               ", defence = " + defence +
               ", deceit = " + deceit +
               ", health = " + health;
    }

}