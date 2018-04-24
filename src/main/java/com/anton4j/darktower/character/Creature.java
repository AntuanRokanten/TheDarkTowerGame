package com.anton4j.darktower.character;

import com.anton4j.darktower.audio.Audio;
import com.anton4j.darktower.audio.AudioFactory;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;
import com.anton4j.darktower.util.RandomUtils;

import static com.anton4j.darktower.character.EncounterOutcome.FAILURE;
import static com.anton4j.darktower.character.EncounterOutcome.SUCCESS;
import static com.anton4j.darktower.console.ConsoleUtils.sleep;
import static com.anton4j.darktower.util.CalculateUtils.calculatePercentValue;
import static com.anton4j.darktower.util.CalculateUtils.calculatePercentage;
import static com.anton4j.darktower.util.RandomUtils.integerInRange;
import static com.anton4j.darktower.util.RandomUtils.randomBoolean;

/**
 * @author ant
 */
public abstract class Creature {

    private final Race race;

    int vitality;
    int strength;
    int defence;
    int speed;

    int health;

    Creature(Race race, int vitality, int strength, int defence, int speed) {
        this.defence = defence;
        this.vitality = vitality;
        this.strength = strength;
        this.race = race;
        this.speed = speed;

        this.health = vitality;
    }

    public int vitality() {
        return vitality;
    }

    public int speed() {
        return speed;
    }

    public int strength() {
        return strength;
    }

    public int defence() {
        return defence;
    }

    public EncounterOutcome runAway(Creature enemy) {
        new ConsoleLine("Character is running away", FontColor.PURPLE).println();

        EncounterOutcome encounterOutcome;
        if (this.speed >= enemy.speed) {
            float speedPercentage = (100 - calculatePercentValue(this.speed, enemy.speed));
            if (speedPercentage >= 30) {
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
            float speedPercentage = (100 - calculatePercentValue(enemy.speed, this.speed));
            if (speedPercentage >= 30) {
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
            new ConsoleLine("Character successfully run away", FontColor.PURPLE).println();
        } else {
            new ConsoleLine("Character was caught by the creature", FontColor.PURPLE).println();
        }

        return encounterOutcome;
    }

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
            new ConsoleLine(attacks.race + " attacks", FontColor.BLACK).println();

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

            sleep(1000);
        }

        EncounterOutcome fightOutcome;
        Audio audio;
        Creature winner;
        if (this.isDefeated()) {
            winner = enemy;
            audio = AudioFactory.looseAudio();
            fightOutcome = FAILURE;
        } else {
            winner = this;
            audio = AudioFactory.winAudio();
            fightOutcome = SUCCESS;
        }
        new Thread(audio::play).start();
//        new ConsoleLine(winner.race + " won the fight!", FontColor.BLACK).println();

        return fightOutcome;
    }

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
              ", speed = " + speed +
              ", health = " + health;
    }

}