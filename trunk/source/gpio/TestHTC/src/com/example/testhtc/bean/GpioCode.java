package com.example.testhtc.bean;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;

public class GpioCode {

	private GpioPin pin1 = new GpioPin();
	private GpioPin pin2 = new GpioPin();
	private GpioPin pin3 = new GpioPin();
	private GpioPin pin4 = new GpioPin();

	private GpioPin pin5 = new GpioPin();
	private GpioPin pin6 = new GpioPin();
	private GpioPin pin7 = new GpioPin();
	private GpioPin pin8 = new GpioPin();

	private GpioPin pin9 = new GpioPin();
	private GpioPin pin10 = new GpioPin();
	private GpioPin pin11 = new GpioPin();
	private GpioPin pin12 = new GpioPin();

	private GpioPin pin13 = new GpioPin();
	private GpioPin pin14 = new GpioPin();
	private GpioPin pin15 = new GpioPin();
	private GpioPin pin16 = new GpioPin();

	private List<GpioPin> allPins = new ArrayList<GpioPin>();

	public GpioCode(String code) {
		addPinsToList();
		setAllPins(code);
	}

	public void setAllPins(String code) {
		Log.w("codelength", code.length() + "");
		if (code.length() < 17) {
			int i = 0;
			for (GpioPin pin : allPins) {
				pin.setValue(code.charAt(i));
				i++;
			}
		}

	}

	@Override
	public String toString() {
		return pin1.toString() + pin2.toString() + pin3.toString() + pin4.toString() + pin5.toString() + pin6.toString() + pin7.toString()
				+ pin8.toString() + pin9.toString() + pin10.toString() + pin11.toString() + pin12.toString() + pin13.toString()
				+ pin14.toString() + pin15.toString() + pin16.toString();
	}

	private void addPinsToList() {
		allPins.add(pin1);
		allPins.add(pin2);
		allPins.add(pin3);
		allPins.add(pin4);

		allPins.add(pin5);
		allPins.add(pin6);
		allPins.add(pin7);
		allPins.add(pin8);

		allPins.add(pin9);
		allPins.add(pin10);
		allPins.add(pin11);
		allPins.add(pin12);

		allPins.add(pin13);
		allPins.add(pin14);
		allPins.add(pin15);
		allPins.add(pin16);

	}

	public GpioPin getPin1() {
		return pin1;
	}

	public void setPin1(GpioPin pin1) {
		this.pin1 = pin1;
	}

	public GpioPin getPin2() {
		return pin2;
	}

	public void setPin2(GpioPin pin2) {
		this.pin2 = pin2;
	}

	public GpioPin getPin3() {
		return pin3;
	}

	public void setPin3(GpioPin pin3) {
		this.pin3 = pin3;
	}

	public GpioPin getPin4() {
		return pin4;
	}

	public void setPin4(GpioPin pin4) {
		this.pin4 = pin4;
	}

	public GpioPin getPin5() {
		return pin5;
	}

	public void setPin5(GpioPin pin5) {
		this.pin5 = pin5;
	}

	public GpioPin getPin6() {
		return pin6;
	}

	public void setPin6(GpioPin pin6) {
		this.pin6 = pin6;
	}

	public GpioPin getPin7() {
		return pin7;
	}

	public void setPin7(GpioPin pin7) {
		this.pin7 = pin7;
	}

	public GpioPin getPin8() {
		return pin8;
	}

	public void setPin8(GpioPin pin8) {
		this.pin8 = pin8;
	}

	public GpioPin getPin9() {
		return pin9;
	}

	public void setPin9(GpioPin pin9) {
		this.pin9 = pin9;
	}

	public GpioPin getPin10() {
		return pin10;
	}

	public void setPin10(GpioPin pin10) {
		this.pin10 = pin10;
	}

	public GpioPin getPin11() {
		return pin11;
	}

	public void setPin11(GpioPin pin11) {
		this.pin11 = pin11;
	}

	public GpioPin getPin12() {
		return pin12;
	}

	public void setPin12(GpioPin pin12) {
		this.pin12 = pin12;
	}

	public GpioPin getPin13() {
		return pin13;
	}

	public void setPin13(GpioPin pin13) {
		this.pin13 = pin13;
	}

	public GpioPin getPin14() {
		return pin14;
	}

	public void setPin14(GpioPin pin14) {
		this.pin14 = pin14;
	}

	public GpioPin getPin15() {
		return pin15;
	}

	public void setPin15(GpioPin pin15) {
		this.pin15 = pin15;
	}

	public GpioPin getPin16() {
		return pin16;
	}

	public void setPin16(GpioPin pin16) {
		this.pin16 = pin16;
	}

}
