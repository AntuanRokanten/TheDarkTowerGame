package com.anton4j.darktower.character;

import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;
import com.anton4j.darktower.util.RandomUtils;

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

   public void fight(Creature enemy) {
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
         boolean criticalHit = randomBoolean() && randomBoolean() && randomBoolean() && randomBoolean();

         float attackPercent;
         if (criticalHit) {
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

         Creature temp = defends;
         defends = attacks;
         attacks = temp;
      }

      // todo show who was defeated
      new ConsoleLine(toString() + " was defeated").println();
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