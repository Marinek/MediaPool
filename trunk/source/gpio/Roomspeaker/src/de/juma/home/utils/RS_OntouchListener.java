package de.juma.home.utils;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class RS_OntouchListener implements View.OnTouchListener {

	private Activity activity;
	static final int MIN_DISTANCE = 100;
	private float downX, upX;
	boolean main;

	public RS_OntouchListener(final Activity activity, boolean main) {
		this.main = main;
		this.activity = activity;
	}

	private void toggleViews() {
		((RS_Interface) activity).switchView();
	}

	public final void onRightToLeftSwipe() {
		toggleViews();
	}

	public void onLeftToRightSwipe() {
		toggleViews();
	}

	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			downX = event.getX();

			// return true;
		}
		case MotionEvent.ACTION_UP: {
			upX = event.getX();

			float deltaX = downX - upX;

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

			return false;
		}
		}
		return false;
	}
}