package com.example.testhtc.bean;

public class GpioPin {
	char value;

	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

}
