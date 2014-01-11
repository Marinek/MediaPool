package de.juma.home.beans;

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

	public GpioPin switchOutput() {
		this.value = this.value == '0' ? '1' : '0';
		return this;
	}

	public GpioPin switchTo(boolean status) {
		this.value = status ? '1' : '0';
		return this;
	}

	public boolean isOn() {
		return this.value == '1';
	}

}
