/*
 * Copyright (C) 2013 ENTERTAILION LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.entertailion.android.overlaynews;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

public class OverlayApplication extends Application {
	private static final String LOG_TAG = "OverlayApplication";

	private Typeface lightTypeface = null;
	private Typeface thinTypeface = null;
	private Typeface mediumTypeface = null;
	private Typeface italicTypeface = null;

	private long overlayTime = 0;
	private String overlayState = OutgoingReceiver.OVERLAY_INTENT_STATE_STOPPED;
	private boolean otherOverlayAppActive = false;
	private boolean isEarliestOverlay = true;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	public String getOverlayState() {
		return overlayState;
	}

	public void setOverlayState(String state) {
		this.overlayState = state;
		isEarliestOverlay = true;
		otherOverlayAppActive = false;

		// send broadcast to other overlay apps
		if (state.equals(OutgoingReceiver.OVERLAY_INTENT_STATE_QUERY)) {
			OutgoingReceiver.sendOverlayQueryIntent(this);
		} else {
			OutgoingReceiver.sendOverlayIntent(this);
		}
	}

	public boolean isOtherOverlayAppActive() {
		return otherOverlayAppActive;
	}

	public void setOtherOverlayAppActive(boolean otherOverlayAppActive) {
		this.otherOverlayAppActive = otherOverlayAppActive;
	}

	public long getOverlayTime() {
		return overlayTime;
	}

	public void setOverlayTime(long overlayTime) {
		this.overlayTime = overlayTime;
	}

	public boolean isEarliestOverlay() {
		return isEarliestOverlay;
	}

	public void setEarliestOverlay(boolean isEarliestOverlay) {
		this.isEarliestOverlay = isEarliestOverlay;
	}

	/**
	 * Get the light typeface
	 * 
	 * @param context
	 * @return
	 */
	public Typeface getLightTypeface(Context context) {
		if (lightTypeface == null) {
			lightTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
		}
		return lightTypeface;
	}

	/**
	 * Get the thin typeface
	 * 
	 * @param context
	 * @return
	 */
	public Typeface getThinTypeface(Context context) {
		if (thinTypeface == null) {
			thinTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Thin.ttf");
		}
		return thinTypeface;
	}

	/**
	 * Get the medium typeface
	 * 
	 * @param context
	 * @return
	 */
	public Typeface getMediumTypeface(Context context) {
		if (mediumTypeface == null) {
			mediumTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
		}
		return mediumTypeface;
	}

	/**
	 * Get the italic typeface
	 * 
	 * @param context
	 * @return
	 */
	public Typeface getItalicTypeface(Context context) {
		if (italicTypeface == null) {
			italicTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Italic.ttf");
		}
		return italicTypeface;
	}

}
