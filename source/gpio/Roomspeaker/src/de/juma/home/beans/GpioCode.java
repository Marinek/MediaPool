package de.juma.home.beans;

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

	private boolean allPinsChecked = false;
	private boolean egPinsChecked = false;
	private boolean ogPinsChecked = false;
	private boolean dgPinsChecked = false;

	private boolean pinsOK = false;

	private List<GpioPin> allPins = new ArrayList<GpioPin>();
	private List<GpioPin> egPins = new ArrayList<GpioPin>();
	private List<GpioPin> ogPins = new ArrayList<GpioPin>();
	private List<GpioPin> dgPins = new ArrayList<GpioPin>();

	private static final String DEFAULT = "0000000000000000";

	public GpioCode(String code) {
		addPinsToList();
		setAllPins(code);
	}

	public GpioCode() {
		this(DEFAULT);
	}

	public void setAllPins(String code) {
		pinsOK = false;
		Log.w("codelength", code.length() + "");
		Log.w("listlength", allPins.size() + "");
		if (code.length() == allPins.size()) {
			int i = 0;
			for (GpioPin pin : allPins) {
				pin.setValue(code.charAt(i));
				i++;
			}
			pinsOK = true;
		}
	}

	@Override
	public String toString() {
		return pin1.toString() + pin2.toString() + pin3.toString() + pin4.toString() + pin5.toString() + pin6.toString() + pin7.toString()
				+ pin8.toString() + pin9.toString() + pin10.toString() + pin11.toString() + pin12.toString() + pin13.toString()
				+ pin14.toString() + pin15.toString() + pin16.toString();
	}

	private void setPinGroup(boolean status, List<GpioPin> pinList) {
		for (GpioPin pin : pinList) {
			pin.switchTo(status);
		}
	}

	public void setEGGroup(boolean status) {
		setPinGroup(status, egPins);
		egPinsChecked = status;
	}

	public void setOgPinsChecked(boolean ogPinsChecked) {
		this.ogPinsChecked = ogPinsChecked;
	}

	public void setOGGroup(boolean status) {
		setPinGroup(status, ogPins);
		ogPinsChecked = status;
	}

	public void setDGGroup(boolean status) {
		setPinGroup(status, dgPins);
		dgPinsChecked = status;
	}

	public void setAllGroup(boolean status) {
		setPinGroup(status, allPins);
		allPinsChecked = status;
		egPinsChecked = status;
		ogPinsChecked = status;
		dgPinsChecked = status;
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

		egPins.add(pin2);
		egPins.add(pin3);
		egPins.add(pin4);
		egPins.add(pin5);

		ogPins.add(pin7);
		ogPins.add(pin8);
		ogPins.add(pin9);
		ogPins.add(pin10);

		dgPins.add(pin12);
		dgPins.add(pin13);
		dgPins.add(pin14);

	}

	public boolean isPinsOK() {
		return pinsOK;
	}

	// pr�ft ob die vollst�ndige Gruppe mit dem letzen Pin aktiviert oder
	// deaktiviert wurde
	private boolean checkGroupIsChecked(GpioPin pin, List<GpioPin> pinGroup, boolean groupCheck) {
		boolean goon = true;
		if (pinGroup.contains(pin)) {
			for (GpioPin other : pinGroup) {
				if (goon && other.isOn() == pin.isOn()) {
					goon = true;
				} else {
					goon = false;
				}
			}
			if (goon) {
				return pin.isOn();
			} else {
				return false;
			}
		}
		return groupCheck;
	}

	private void checkAllGroupAreChecked(GpioPin pin) {
		egPinsChecked = checkGroupIsChecked(pin, egPins, egPinsChecked);
		ogPinsChecked = checkGroupIsChecked(pin, ogPins, ogPinsChecked);
		dgPinsChecked = checkGroupIsChecked(pin, dgPins, dgPinsChecked);
		allPinsChecked = checkGroupIsChecked(pin, allPins, allPinsChecked);
	}

	public void setPinsOK(boolean pinsOK) {
		this.pinsOK = pinsOK;
	}

	public boolean isAllPinsChecked() {
		return allPinsChecked;
	}

	public boolean isOgPinsChecked() {
		return ogPinsChecked;
	}

	public boolean isEgPinsChecked() {
		return egPinsChecked;
	}

	public boolean isDgPinsChecked() {
		return dgPinsChecked;
	}

	public GpioPin getPin1() {
		return pin1;
	}

	public void setPin1(GpioPin pin1) {
		this.pin1 = pin1;
		checkAllGroupAreChecked(pin1);
	}

	public GpioPin getPin2() {
		return pin2;
	}

	public void setPin2(GpioPin pin2) {
		this.pin2 = pin2;
		checkAllGroupAreChecked(pin2);
	}

	public GpioPin getPin3() {
		return pin3;
	}

	public void setPin3(GpioPin pin3) {
		this.pin3 = pin3;
		checkAllGroupAreChecked(pin3);
	}

	public GpioPin getPin4() {
		return pin4;
	}

	public void setPin4(GpioPin pin4) {
		this.pin4 = pin4;
		checkAllGroupAreChecked(pin4);
	}

	public GpioPin getPin5() {
		return pin5;
	}

	public void setPin5(GpioPin pin5) {
		this.pin5 = pin5;
		checkAllGroupAreChecked(pin5);
	}

	public GpioPin getPin6() {
		return pin6;
	}

	public void setPin6(GpioPin pin6) {
		this.pin6 = pin6;
		checkAllGroupAreChecked(pin6);
	}

	public GpioPin getPin7() {
		return pin7;
	}

	public void setPin7(GpioPin pin7) {
		this.pin7 = pin7;
		checkAllGroupAreChecked(pin7);
	}

	public GpioPin getPin8() {
		return pin8;
	}

	public void setPin8(GpioPin pin8) {
		this.pin8 = pin8;
		checkAllGroupAreChecked(pin8);
	}

	public GpioPin getPin9() {
		return pin9;
	}

	public void setPin9(GpioPin pin9) {
		this.pin9 = pin9;
		checkAllGroupAreChecked(pin9);
	}

	public GpioPin getPin10() {
		return pin10;
	}

	public void setPin10(GpioPin pin10) {
		this.pin10 = pin10;
		checkAllGroupAreChecked(pin10);
	}

	public GpioPin getPin11() {
		return pin11;
	}

	public void setPin11(GpioPin pin11) {
		this.pin11 = pin11;
		checkAllGroupAreChecked(pin11);
	}

	public GpioPin getPin12() {
		return pin12;
	}

	public void setPin12(GpioPin pin12) {
		this.pin12 = pin12;
		checkAllGroupAreChecked(pin12);
	}

	public GpioPin getPin13() {
		return pin13;
	}

	public void setPin13(GpioPin pin13) {
		this.pin13 = pin13;
		checkAllGroupAreChecked(pin13);
	}

	public GpioPin getPin14() {
		return pin14;
	}

	public void setPin14(GpioPin pin14) {
		this.pin14 = pin14;
		checkAllGroupAreChecked(pin14);
	}

	public GpioPin getPin15() {
		return pin15;
	}

	public void setPin15(GpioPin pin15) {
		this.pin15 = pin15;
		checkAllGroupAreChecked(pin15);
	}

	public GpioPin getPin16() {
		return pin16;
	}

	public void setPin16(GpioPin pin16) {
		this.pin16 = pin16;
		checkAllGroupAreChecked(pin16);
	}

}
