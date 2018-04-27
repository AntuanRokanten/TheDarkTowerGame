package com.anton4j.darktower.game.character.encounter;

/**
 * Creatures encounter outcome.
 *
 * @author ant
 */
public enum EncounterOutcome {

    /**
     * If encounter was successful for the character (depends on what {@link EncounterOption} was chosen)
     */
    SUCCESS,

    /**
     * If encounter was NOT successful for the character (depends on what {@link EncounterOption} was chosen)
     */
    FAILURE

}
