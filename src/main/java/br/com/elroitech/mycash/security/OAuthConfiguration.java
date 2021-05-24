package br.com.elroitech.mycash.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {

	@Value("${mycash.clientId}")
	private String clientId;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${mycash.clientSecret}")
	private String clientSecret;

	@Autowired
	private UserDetailsService UserDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.inMemory()
			.withClient(clientId)
			.secret(passwordEncoder.encode(clientSecret))
			.accessTokenValiditySeconds(43200) //12h
			.refreshTokenValiditySeconds(2592000) //30d
			.authorizedGrantTypes("password", "refresh_token")
			.scopes("read", "write")
			.resourceIds("api");

	}
	
	@Override
	public void configure(
			AuthorizationServerEndpointsConfigurer endpoints) 
					throws Exception {

		endpoints
		.accessTokenConverter(accessTokenConverter())
		.userDetailsService(UserDetailsService)
		.authenticationManager(authenticationManager);
	}
	
	@Bean
	public AccessTokenConverter accessTokenConverter() {
		return new JwtAccessTokenConverter();
	}
}
