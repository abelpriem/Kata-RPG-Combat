package com.factoriaf5.kata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CharacterTest {

    @Test
    void testGetCharacterHealth() {
        Character firstPlayer = new Character(true);

        int firstPlayerHealth = firstPlayer.getHealth();

        assertEquals(1000, firstPlayerHealth);
    }

    @Test
    void testSetCharacterHealth() {
        int health = 875;
        Character firstPlayer = new Character(true);

        firstPlayer.setHealth(health);
        int playerHealth = firstPlayer.getHealth();

        assertEquals(health, playerHealth);
    }

    @Test
    void testGetCharacterLevel() {
        Character firstPlayer = new Character(true);

        int firstPlayerLevel = firstPlayer.getLevel();

        assertEquals(1, firstPlayerLevel);
    }

    @Test
    void testSetCharacterLevel() {
        int level = 20;
        Character firstPlayer = new Character(true);

        firstPlayer.setLevel(level);
        int playerLevel = firstPlayer.getLevel();

        assertEquals(20, playerLevel);
    }

    @Test
    void testCharacterIsAlive() {
        Character firstPlayer = new Character(true);

        boolean firstPlayerLevel = firstPlayer.isAlive();

        assertTrue(firstPlayerLevel);
    }

    @Test
    void testGetMeleeRange() {
        Character firstPlayer = new Character(true);

        int range = firstPlayer.getMaxRange();

        assertEquals(2, range);
    }

    @Test
    void testGetRangeRange() {
        Character firstPlayer = new Character(false);

        int range = firstPlayer.getMaxRange();

        assertEquals(20, range);
    }

    @Test
    void testAttackToYourself() {
        Character firstPlayer = new Character(false);

        String range = firstPlayer.attack(firstPlayer, 200, 2);

        assertEquals("No te puedes atacar a tí mismo!", range);
    }

    @Test
    void testAttackOutOfRange() {
        Character firstPlayer = new Character(true);
        Character secondPlayer = new Character(false);

        String result = firstPlayer.attack(secondPlayer, 200, 5);

        assertEquals("No se puede atacar porque está fuera de rango", result);
    }

    @Test
    void testAttackWhenIsDead() {
        Character firstPlayer = new Character(true);
        Character secondPlayer = new Character(true);

        firstPlayer.attack(secondPlayer, 1000, 2);
        String result = secondPlayer.attack(firstPlayer, 300, 2);

        int healtAfterAttack = secondPlayer.getHealth();

        assertEquals("No puedes atacar porque estas muerto", result);
        assertEquals(0, healtAfterAttack);
    }

    @Test
    void testAttackSuccessInRange() {
        Character firstPlayer = new Character(true);
        Character secondPlayer = new Character(true);

        String result = firstPlayer.attack(secondPlayer, 200, 2);
        int healthAfterAttack = secondPlayer.getHealth();

        assertEquals(800, healthAfterAttack);
        assertEquals("Atacado correctamente!", result);
    }

    @Test
    void testWithNoDamage() {
        Character firstPlayer = new Character(true);
        Character secondPlayer = new Character(true);

        String result = firstPlayer.attack(secondPlayer, 0, 2);
        int healthAfterAttack = secondPlayer.getHealth();

        assertEquals("El jugador ha salido ileso del combate...", result);
        assertEquals(1000, healthAfterAttack);
    }

    @Test
    void testAttackWithLessLevel() {
        int level = 10;
        Character firstPlayer = new Character(true);
        Character secondPlayer = new Character(true);

        secondPlayer.setLevel(level);

        firstPlayer.attack(secondPlayer, 300, 2);
        int healtAfterAttack = secondPlayer.getHealth();

        assertEquals(850, healtAfterAttack);
    }

    @Test
    void testAttackWithMoreLevel() {
        int level = 10;
        Character firstPlayer = new Character(true);
        Character secondPlayer = new Character(true);

        firstPlayer.setLevel(level);

        firstPlayer.attack(secondPlayer, 300, 2);
        int healtAfterAttack = secondPlayer.getHealth();

        assertEquals(400, healtAfterAttack);
    }

    @Test
    void testAttackToDeadInRange() {
        Character firstPlayer = new Character(true);
        Character secondPlayer = new Character(true);

        String result = firstPlayer.attack(secondPlayer, 1000, 2);
        boolean alive = secondPlayer.isAlive();

        assertFalse(alive);
        assertEquals("Ganador! Has matado al jugador", result);
    }

    @Test
    void testAttackDeadCharacterInRange() {
        Character firstPlayer = new Character(true);
        Character secondPlayer = new Character(true);

        firstPlayer.attack(secondPlayer, 1000, 2);

        String result = firstPlayer.attack(secondPlayer, 200, 2);
        boolean alive = secondPlayer.isAlive();

        assertFalse(alive);
        assertEquals("El objetivo ya esta MUERTO...", result);
    }

    @Test
    void testHealPlayer() {
        Character firstPlayer = new Character(true);
        Character secondPlayer = new Character(true);

        int healthBeforeHealing = secondPlayer.getHealth();

        firstPlayer.attack(secondPlayer, 200, 2);

        String result = firstPlayer.heal(secondPlayer, 50);
        int healthAfterHealing = secondPlayer.getHealth();

        assertEquals(1000, healthBeforeHealing);
        assertEquals("Curado correctamente", result);
        assertEquals(850, healthAfterHealing);
    }

    @Test
    void testHealWithMaxHealth() {
        Character firstPlayer = new Character(true);
        Character secondPlayer = new Character(true);

        String result = firstPlayer.heal(secondPlayer, 50);

        assertEquals("La vida ya está al máximo", result);
    }

    @Test
    void testHealingDeadCharacter() {
        Character firstPlayer = new Character(true);
        Character secondPlayer = new Character(true);

        int healthBeforeHealing = secondPlayer.getHealth();
        firstPlayer.attack(secondPlayer, 1000, 2);

        String result = firstPlayer.heal(secondPlayer, 50);
        int healthAfterHealing = secondPlayer.getHealth();

        assertEquals(1000, healthBeforeHealing);
        assertEquals("No se puede curar a un personaje que está muerto", result);
        assertEquals(0, healthAfterHealing);
    }

}
