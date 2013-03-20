package de.mediapool.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

import de.mediapool.core.beans.business.authentication.UserBean;
import de.mediapool.core.exceptions.MPException;
import de.mediapool.core.services.MPLocalService;
import de.mediapool.ui.composits.authentification.AuthenticationPanel;
import de.mediapool.ui.composits.main.MainPanel;
import de.mediapool.ui.utils.MPExceptionUtil;

@Theme("runo")
public class Mediapool extends UI {

	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest request) {

		installDB();

		UserBean lAuth = VaadinSession.getCurrent().getAttribute(UserBean.class);

		if (lAuth != null) {
			// Check if we are Logged In
			try {
				boolean valid = MPLocalService.getInstance().getAuthService().isValid(lAuth);

				if (valid) {
					getUI().setContent(new MainPanel());
				} else {
					this.setContent(new AuthenticationPanel());
				}
			} catch (MPException e) {
				MPExceptionUtil.showMPExceptionDialog(e, getUI());
			}
		} else {
			this.setContent(new AuthenticationPanel());
		}

	}

	private void installDB() {
		try {
			MPLocalService.getInstance().getInstallationService().installDB();
		} catch (MPException e) {
			MPExceptionUtil.showMPExceptionDialog(e, getUI());
		}
	}

}