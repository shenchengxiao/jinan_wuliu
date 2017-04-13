/**
 * 
 */
package com.manager.utils;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 
 * @author Fermi(fermi@youleyu.com)
 * @date Dec 8, 2013
 * @desc
 */
public class TrustAnyTrustManager implements X509TrustManager {

	public void checkClientTrusted(
			X509Certificate[] paramArrayOfX509Certificate, String paramString)
			throws CertificateException {

	}

	public void checkServerTrusted(
			X509Certificate[] paramArrayOfX509Certificate, String paramString)
			throws CertificateException {

	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

}
