package com.example.security;

//@Configuration
//@EnableAuthorizationServer
public class AuthorizationServerConfiguration /*extends AuthorizationServerConfigurerAdapter*/ {

//    @Autowired
//    private DataSource dataSource;
//
//    @Bean // ����TokenStoreʵ��
//    public TokenStore tokenStore() {
//        return new JdbcTokenStore(dataSource);
//    }
//
//    @Bean // ���� ClientDetailsʵ��
//    public ClientDetailsService clientDetails() {
//        return new JdbcClientDetailsService(dataSource);
//    }
//
//    @Autowired
//    private TokenStore tokenStore;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients
//            .jdbc(dataSource);
//    }
//
//    @Override // ���ÿ��Ӧ������ʵ��
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(authenticationManager);
//        endpoints.tokenStore(tokenStore());
//        endpoints.userDetailsService(userService);
////         ����TokenServices����
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(endpoints.getTokenStore());
//        tokenServices.setSupportRefreshToken(true);
//        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
//        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
//        tokenServices.setAccessTokenValiditySeconds( (int) TimeUnit.DAYS.toSeconds(1)); // 1��
//        endpoints.tokenServices(tokenServices);
//    }
//
//    @Bean
//    @Primary
//    public DefaultTokenServices tokenServices() {
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setSupportRefreshToken(true); // support refresh token
//        tokenServices.setTokenStore(tokenStore); // use jdbc token store
//        return tokenServices;
//    }
}