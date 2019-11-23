package com.example.demo.common.dto;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class AbstractValueObject implements IValueObject {
	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
