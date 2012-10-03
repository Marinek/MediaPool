package de.mediapool.core.utils;

import java.util.List;

import de.mediapool.core.beans.validation.ValidationErrorType;
import de.mediapool.core.beans.validation.ValidationResultBean;

public class ValidationUtil {

	public static ValidationResultBean info(String fieldname, String message) {
		return create(ValidationErrorType.INFO, fieldname, message);
	}

	public static ValidationResultBean warn(String fieldname, String message) {
		return create(ValidationErrorType.WARN, fieldname, message);
	}

	public static ValidationResultBean error(String fieldname, String message) {
		return create(ValidationErrorType.ERROR, fieldname, message);
	}

	private static ValidationResultBean create(ValidationErrorType errorType, String fieldName, String message) {
		return new ValidationResultBean(errorType, fieldName, message);
	}

	public static boolean canProceed(List<ValidationResultBean> validationResultList) {
		for (ValidationResultBean errorType : validationResultList) {
			if (!errorType.getValidationErrorType().canProceed()) {
				return false;
			}
		}

		return true;
	}

	public static String toString(List<ValidationResultBean> pValidationResultList) {
		StringBuffer lBuffer = new StringBuffer();
		if (pValidationResultList != null) {
			for (ValidationResultBean lBean : pValidationResultList) {
				lBuffer.append(lBean);
				lBuffer.append("\n");
			}
		} else {
			lBuffer.append("<Leere Liste>");
		}
		return lBuffer.toString();
	}
}
