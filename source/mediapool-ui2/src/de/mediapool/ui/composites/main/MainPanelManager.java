package de.mediapool.ui.composites.main;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import de.mediapool.ui.composites.main.sub.MainPanelSub;
import de.mediapool.ui.composites.main.sub.media.MediaTableSub;
import de.mediapool.ui.composites.main.sub.media.ProductTableSub;

public class MainPanelManager {

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Statische Deklarationen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private static final String KEY_VALUE_DELIMITER = "=";

	private static final String PARAMETER_DELMITER = ";";

	private static final char SUBPANEL_NAME_DELIMITER = '?';

	private static MainPanelManager myInstance = null;

	private Map<String, Class<? extends MainPanelSub>> mainPanelMap = new HashMap<String, Class<? extends MainPanelSub>>();

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Member Variablen
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Konstruktoren
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private MainPanelManager() {

	}

	public static final MainPanelManager getInstance() {
		if (myInstance == null) {
			myInstance = new MainPanelManager();
			myInstance.init();
		}

		return myInstance;
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// public Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	public MainPanelSub getSubPanel(String pParameter) {

		String lPanelName = this.extractPanel(pParameter);

		MainPanelSub lReturnPanel = this.createSubPanel(lPanelName);

		lReturnPanel.setParameter(this.extractParameter(pParameter));

		return lReturnPanel;

	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// protected Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// private Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

	private Map<String, String> extractParameter(String pParameter) {
		Map<String, String> lParamMap = new HashMap<String, String>();

		if (pParameter.indexOf(SUBPANEL_NAME_DELIMITER) == -1) {
			return lParamMap;
		}

		String lParameterString = pParameter.substring(pParameter.indexOf(SUBPANEL_NAME_DELIMITER));
		StringTokenizer lTokenizer = new StringTokenizer(lParameterString, PARAMETER_DELMITER);

		while (lTokenizer.hasMoreTokens()) {
			String lToken = lTokenizer.nextToken();

			String lElements[] = lToken.split(KEY_VALUE_DELIMITER);
			if (lElements.length == 1) {
				lParamMap.put(lElements[0], lElements[0]);
			} else if (lElements.length == 2) {
				lParamMap.put(lElements[0], lElements[1]);
			}
		}

		return lParamMap;
	}

	private MainPanelSub createSubPanel(String panelName) {
		if (this.mainPanelMap.containsKey(panelName)) {
			try {
				Class<? extends MainPanelSub> lPanelClass = this.mainPanelMap.get(panelName);

				return lPanelClass.newInstance();
			} catch (Throwable e) {
				// TODO: Exception
			}
		}
		return null;
	}

	private String extractPanel(String pParameter) {
		if (pParameter.indexOf(SUBPANEL_NAME_DELIMITER) == -1) {
			return pParameter;
		}
		return pParameter.substring(0, pParameter.indexOf(SUBPANEL_NAME_DELIMITER));
	}

	private void init() {
		this.mainPanelMap.put("showmedia/product", ProductTableSub.class);
		this.mainPanelMap.put("showmedia/movie", MediaTableSub.class);
	}

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// abstrakte Methoden
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
}
