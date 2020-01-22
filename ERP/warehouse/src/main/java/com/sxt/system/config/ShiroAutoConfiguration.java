package com.sxt.system.config;

import com.sxt.system.realm.UserRealm;
import com.sxt.system.shiro.ShiroLoginFilter;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.IRedisManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
@EnableConfigurationProperties(ShiroProperties.class)
public class ShiroAutoConfiguration {

    @Autowired
    private ShiroProperties shiroProperties;

    @Autowired
    private RedisProperties redisProperties;
    /**
     * 创建凭证匹配器
     */
    @Bean
    public HashedCredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName(shiroProperties.getHashAlgorithmName());
        credentialsMatcher.setHashIterations(shiroProperties.getHashIterations());
        return  credentialsMatcher;
    }

    /**
     * 创建realm
     */
    @Bean
    public UserRealm userRealm(CredentialsMatcher credentialsMatcher){
        UserRealm userRealm=new UserRealm();
        //注入凭证匹配器
        userRealm.setCredentialsMatcher(credentialsMatcher);
        return userRealm;
    }

    /**
     * 声明安全管理器
     */
    @Bean("securityManager")
    public SecurityManager securityManager(DefaultWebSessionManager defaultWebSessionManager, SessionDAO redisSession, UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        defaultWebSessionManager.setSessionDAO(redisSession);
        securityManager.setSessionManager(defaultWebSessionManager);
        return  securityManager;
    }


    /**
     * 配置过滤器 Shiro 的Web过滤器 id必须和web.xml里面的shiroFilter的 targetBeanName的值一样
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        //注入安全管理器
        bean.setSecurityManager(securityManager);
        //注入登陆页面
        bean.setLoginUrl(shiroProperties.getLoginUrl());
        //注入未授权的页面地址
        bean.setUnauthorizedUrl(shiroProperties.getUnauthorizedUrl());

        //创建自定义filter
        Map<String, Filter> map=new HashMap<>();
        map.put("authc",new ShiroLoginFilter());
        bean.setFilters(map);
        //注入过滤器
        Map<String, String> filterChainDefinition=new HashMap<>();

        //注入放行地址
        if(shiroProperties.getAnonUrls()!=null&&shiroProperties.getAnonUrls().length>0){
            String[] anonUrls = shiroProperties.getAnonUrls();
            for (String anonUrl : anonUrls) {
                filterChainDefinition.put(anonUrl,"anon");
            }
        }
        //注入登出的地址
        if(shiroProperties.getLogoutUrl()!=null){
            filterChainDefinition.put(shiroProperties.getLogoutUrl(),"logout");
        }
        //注拦截的地址
        String[] authcUrls = shiroProperties.getAuthcUrls();
        if(authcUrls!=null&&authcUrls.length>0){
            for (String authcUrl : authcUrls) {
                filterChainDefinition.put(authcUrl,"authc");
            }
        }
        bean.setFilterChainDefinitionMap(filterChainDefinition);


        return bean;
    }


    /**
     * 注册过滤器
     */
    @Bean
    public FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBeanDelegatingFilterProxy(){
        FilterRegistrationBean<DelegatingFilterProxy> bean=new FilterRegistrationBean<>();
        //创建过滤器
        DelegatingFilterProxy proxy=new DelegatingFilterProxy();
        proxy.setTargetBeanName("shiroFilter");
        proxy.setTargetFilterLifecycle(true);
        bean.setFilter(proxy);
        List<String> servletNames=new ArrayList<>();
        servletNames.add(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME);
        bean.setServletNames(servletNames);
        return bean;
    }

    /*加入注解的使用，不加入这个注解不生效--开始*/
    /**
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    /*加入注解的使用，不加入这个注解不生效--结束*/


    /**
     * 使用Redis  来存储登录的信息
     *sessionDao 还需要设置给sessionManager
     */
    @Bean
    public SessionDAO redisSessionDAO(IRedisManager redisManager) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager); //操作那个redis
        redisSessionDAO.setExpire(7*24*3600); // 用户的登录信息保存多久？ 7 天
        //       redisSessionDAO.setKeySerializer(keySerializer); jdk
        //       redisSessionDAO.setValueSerializer(valueSerializer);jdk
        return redisSessionDAO ;
    }



    @Bean
    public IRedisManager  redisManager() {
        RedisManager redisManager = new RedisManager();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisProperties.getJedis().getPool().getMaxActive()); // 链接池的最量 20 ，并发特别大时，连接池的数据可以最大增加20个
        jedisPoolConfig.setMaxIdle(redisProperties.getJedis().getPool().getMaxIdle());// 连接池的最大剩余量15个 ：并发不大，池里面的对象用不上，里面对象太多了。浪费空间
        jedisPoolConfig.setMinIdle(redisProperties.getJedis().getPool().getMinIdle()); // 连接池初始就有10 个
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisProperties.getHost(), redisProperties.getPort(),2000,redisProperties.getPassword());
        redisManager.setJedisPool(jedisPool );
        return redisManager ;
    }
}
