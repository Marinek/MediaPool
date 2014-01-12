package de.juma.home.utils;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import de.juma.home.ReceiverControl;
import de.juma.home.Roomspeaker;

public class ActivitySwipeDetector implements View.OnTouchListener {

	private Activity activity;
	static final int MIN_DISTANCE = 100;
	private float downX, downY, upX, upY;
	boolean main;

	public ActivitySwipeDetector(final Activity activity, boolean main) {
		this.main = main;
		this.activity = activity;
	}

	private void toggleViews() {
		if (main) {
			((Roomspeaker) activity).switchView();
		} else {
			((ReceiverControl) activity).switchView();
		}
	}

	public final void onRightToLeftSwipe() {
		toggleViews();
	}

	public void onLeftToRightSwipe() {
		toggleViews();
	}

	public void onTopToBottomSwipe() {
		// Log.w("onTopToBottomSwipe!", "onTopToBottomSwipe!");
	}

	public void onBottomToTopSwipe() {
		// Log.w("onBottomToTopSwipe!", "onBottomToTopSwipe!");
	}

	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			downX = event.getX();
			downY = event.getY();
			// return true;
		}
		case MotionEvent.ACTION_UP: {
			upX = event.getX();
			upY = event.getY();

			float deltaX = downX - upX;
			float deltaY = downY - upY;

			// swipe horizontal?
			if (Math.abs(deltaX) > MIN_DISTANCE) {
				// left or right
				if (deltaX < 0) {
					this.onLeftToRightSwipe();
					return true;
				}
				if (deltaX > 0) {
					this.onRightToLeftSwipe();
					return true;
				}
			} else {
				Log.w("Swipe was only ", Math.abs(deltaX) + " long, need at least " + MIN_DISTANCE);
			}

			// swipe vertical?
			if (Math.abs(deltaY) > MIN_DISTANCE) {
				// top or down
				if (deltaY < 0) {
					this.onTopToBottomSwipe();
					return true;
				}
				if (deltaY > 0) {
					this.onBottomToTopSwipe();
					return true;
				}
			} else {
				Log.w("Swipe was only ", Math.abs(deltaX) + " long, need at least " + MIN_DISTANCE);
			}

			return true;
		}
		}
		return false;
	}
}