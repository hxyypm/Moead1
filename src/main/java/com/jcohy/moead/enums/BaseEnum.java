package com.jcohy.moead.enums;

public interface BaseEnum<E extends Enum<E>> {
    String getEnumValue();
}