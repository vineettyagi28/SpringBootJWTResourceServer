/**
 * 
 */
package com.vineet.resource.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

/**
 * @author vineet
 *
 */
@Configuration
public class JwtConfiguration {

	@Value(value = "classpath:public.cert")
	private Resource cert;

	@Autowired
	JwtAccessTokenConverter jwtAccessTokenConverter;

	@Bean
	@Qualifier("tokenStore")
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter);
	}

	@Bean
	protected JwtAccessTokenConverter jwtTokenEnhancer() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		String publicKey = null;
		try {
			publicKey = new String(FileCopyUtils.copyToByteArray(cert.getInputStream()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		converter.setVerifierKey(publicKey);
		return converter;
	}
}