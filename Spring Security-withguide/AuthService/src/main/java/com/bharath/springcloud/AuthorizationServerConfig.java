package com.bharath.springcloud;

import java.security.KeyPair;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private static final String COUPONSERVICE = "couponservice";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailServiceImpl userDetailService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private DataSource dataSource;

	@Value("${keyFile}")
	private String keyFile;
	@Value("${password}")
	private char[] password;
	@Value("${alias}")
	private String alias;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("couponclientapp").secret(encoder.encode("9999")) // registering client id and
																						// screet in inmemory token
																						// store
				.authorizedGrantTypes("password", "refresh_token").scopes("read", "write").resourceIds(COUPONSERVICE);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		endpoints.tokenStore(new JdbcTokenStore(dataSource)).authenticationManager(authenticationManager)		//we configuring our tokenstore,authentication amnager ans userdetail service
//				.userDetailsService(userDetailService);
		endpoints.tokenStore(tokenStore()).accessTokenConverter(accessTokenConverter())
				.authenticationManager(authenticationManager) // we configuring our tokenstore,authentication amnager
																// ans userdetail service
				.userDetailsService(userDetailService);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()");
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter(); // use keys to generates
																							// signature
//		KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(keyFile), password); // read
//																													// file
//																													// from
//																													// resources
//		KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias); // get key using alias name
//		jwtAccessTokenConverter.setKeyPair(keyPair); // set keypair
		jwtAccessTokenConverter.setSigningKey("testkey");
		return jwtAccessTokenConverter;
	}

}
