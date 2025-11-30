package model;

public interface InputCheck {
    default boolean check(int value) {
        return true;
    }
}