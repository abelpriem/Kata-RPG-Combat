package com.factoriaf5.kata;

public class Character {
    public Integer health = 1000;
    public Integer level = 1;
    public Boolean alive = true;
    public Integer range;
    public Boolean meleeCharacter;
    public Boolean rangeCharacter;

    public Character() {

    }

    public Character(Boolean isMelee) {
        if (isMelee) {
            this.meleeCharacter = true;
            this.rangeCharacter = false;
            this.range = 2;
        } else {
            this.meleeCharacter = false;
            this.rangeCharacter = true;
            this.range = 20;
        }
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean isAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public Integer getMaxRange() {
        return range;
    }

    public String attack(Character target, Integer damage, Integer maxRange) {
        if (this.equals(target)) {
            return "No te puedes atacar a tí mismo!";
        }

        if (maxRange <= this.range) {
            if (this.alive == true) {
                if (target.isAlive()) {
                    if (damage == 0) {
                        return "El jugador ha salido ileso del combate...";
                    }

                    if (target.getLevel() >= this.level + 5) {
                        damage /= 2;
                    } else if (target.getLevel() < this.level - 5) {
                        damage *= 2;
                    }

                    target.health = Math.max(0, target.health - damage);

                    if (target.health == 0) {
                        target.alive = false;

                        return "Ganador! Has matado al jugador";
                    }

                    return "Atacado correctamente!";
                } else {
                    return "El objetivo ya esta MUERTO...";
                }
            } else {
                return "No puedes atacar porque estas muerto";
            }
        } else {
            return "No se puede atacar porque está fuera de rango";
        }
    }

    public String heal(Character target, Integer pointsToHeal) {
        if (target.isAlive()) {
            if (target.health >= 1000) {
                return "La vida ya está al máximo";
            }

            target.health = Math.min(1000, target.health + pointsToHeal);

            return "Curado correctamente";
        } else {
            return "No se puede curar a un personaje que está muerto";
        }
    }
}
