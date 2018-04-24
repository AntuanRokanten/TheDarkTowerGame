package com.anton4j.darktower.character;

import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;
import com.anton4j.darktower.util.RandomUtils;

import java.util.concurrent.TimeUnit;

import static com.anton4j.darktower.character.FightOutcome.DEFEAT;
import static com.anton4j.darktower.character.FightOutcome.VICTORY;
import static com.anton4j.darktower.util.CalculateUtils.calculatePercentValue;
import static com.anton4j.darktower.util.CalculateUtils.calculatePercentage;
import static com.anton4j.darktower.util.RandomUtils.integerInRange;
import static com.anton4j.darktower.util.RandomUtils.randomBoolean;

/**
 * @author ant
 */
public abstract class Creature {

   private final Race race;

   private int vitality;

   private int strength;
   private int defence;

   private int health;

   protected Creature(Race race, int vitality, int strength, int defence) {
      this.defence = defence;
      this.vitality = vitality;
      this.strength = strength;
      this.race = race;

      this.health = vitality;
   }

   public int vitality() {
      return vitality;
   }

   public int strength() {
      return strength;
   }

   public int defence() {
      return defence;
   }

   public void runAway(Mob enemy) {
      new ConsoleLine("You are running away ...", FontColor.PURPLE).println();
      // todo print result
   }

   public FightOutcome fight(Creature enemy) {
      new ConsoleLine("Starting a battle", FontColor.PURPLE).println();

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

         try {
            TimeUnit.SECONDS.sleep(1);
         } catch (InterruptedException ignored) {
         }
      }

      return this.isDefeated() ? DEFEAT : VICTORY;
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
              ", health = " + health;
   }

}