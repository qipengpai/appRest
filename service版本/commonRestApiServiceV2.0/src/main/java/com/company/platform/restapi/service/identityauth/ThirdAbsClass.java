package com.company.platform.restapi.service.identityauth;

import java.util.Map;

public abstract class ThirdAbsClass<T> {

	public T callService(String loanId, String customerId) throws Exception {
		return this.callService(loanId, customerId, null);
	}

	abstract public T callService(final String loanId, final String customerId, final Map<String, Object> parameter)
			throws Exception;

}
