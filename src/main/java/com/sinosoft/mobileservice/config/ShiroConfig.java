package com.sinosoft.mobileservice.config;

import com.sinosoft.mobileservice.shiro.CustomRealm;
import com.sinosoft.mobileservice.shiro.CustomShiroSessionManager;
import com.sinosoft.mobileservice.shiro.MyExceptionHandler;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.apache.shiro.mgt.SecurityManager;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 注入需要被application 扫描 否者找不Subjectutils
 */
@Configuration
public class ShiroConfig  {



    @Value("${spring.redis.shiro.host}")
    private String host;
    @Value("${spring.redis.shiro.port}")
    private int port;
    @Value("${spring.redis.shiro.timeout}")
    private int timeout;
    @Value("${spring.redis.shiro.password}")
    private String password;

    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

//    public Map<String, String> filterChainDefinitionMap(){
//
//
//
//        return filterChainDefinitionMap();
//    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter( @Qualifier("securityManager")SecurityManager securityManager) {
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
//
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        //注意过滤器配置顺序 不能颠倒
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
        filterMap.put("/login", "logout");
        // 配置不会被拦截的链接 顺序判断
        filterMap.put("/static/**", "anon");
//        filterMap.put("/ajaxLogin", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/unauth", "anon");
        filterMap.put("/**", "authc");

        //
        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    //自定义实例化并注入reamml
    @Bean
    public CustomRealm myShiroRealm() {
        CustomRealm myShiroRealm = new CustomRealm();
//        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher()); //对密码加密
        return myShiroRealm;
    }

//    实例化并注入SecurityManager
    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        // 自定义session管理 使用redis
//        securityManager.setSessionManager(sessionManager());
        // 自定义缓存实现 使用redis
//        securityManager.setCacheManager(cacheManager());


//        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:com/txr/resource/shiro-permission.ini");
//        //创建SecurityManager
//        SecurityManager securityManager2=factory.getInstance();
        //将SecurityManager设置到系统运行环境
        SecurityUtils.setSecurityManager(securityManager);

        return securityManager;
    }

    //自定义sessionManager
    @Bean
    public SessionManager sessionManager() {
        CustomShiroSessionManager mySessionManager = new CustomShiroSessionManager();
//        mySessionManager.setSessionDAO(redisSessionDAO());
        return mySessionManager;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param Manager
     * @return
     */
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager Manager) {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(Manager);
//        return authorizationAttributeSourceAdvisor;
//    }
    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor( @Qualifier("securityManager")SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    /**
     * 注册全局异常处理
     * @return
     */
    @Bean(name = "exceptionHandler")
    public HandlerExceptionResolver handlerExceptionResolver() {
        return new MyExceptionHandler();
    }
    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     */
    @Bean
    //@ConditionalOnMissingBean(LifecycleBeanPostProcessor.class)
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 支持Shiro对Controller的方法级AOP安全控制
     */
    @Bean
    //@ConditionalOnMissingBean
    //@DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * ）
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }



    /**
     * 配置shiro redisManager
     * <p>
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
//    public RedisManager redisManager() {
//        RedisManager redisManager = new RedisManager();
//        redisManager.setHost(host);
//        redisManager.setPort(port);
//        redisManager.setExpire(1800);// 配置缓存过期时间
//        redisManager.setTimeout(timeout);
//        redisManager.setPassword(password);
//        return redisManager;
//    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * <p>
     * 使用的是shiro-redis开源插件
     */
//    @Bean
//    public RedisSessionDAO redisSessionDAO() {
//        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
//        redisSessionDAO.setRedisManager(redisManager());
//        return redisSessionDAO;
//    }

}
