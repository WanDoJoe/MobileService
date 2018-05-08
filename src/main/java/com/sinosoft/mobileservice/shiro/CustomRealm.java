package com.sinosoft.mobileservice.shiro;

import com.sinosoft.mobileservice.bean.shiro.SysPermission;
import com.sinosoft.mobileservice.bean.shiro.SysRole;
import com.sinosoft.mobileservice.bean.shiro.UserInfo;
import com.sinosoft.mobileservice.services.UserInfoService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class CustomRealm extends AuthorizingRealm {
    @Resource
    private UserInfoService userInfoService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
        for (SysRole role : userInfo.getRoleList()) {
            System.out.println("getRole="+role.getRole());
            authorizationInfo.addRole(role.getRole());
            for (SysPermission p : role.getPermissions()) {
                System.out.println("getPermission="+p.getPermission());
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;

    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
//        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        System.out.println("----->>userInfo="+username);
        UserInfo userInfo = userInfoService.findByUsername(username);
        System.out.println("----->>userInfo="+userInfo);
        if (userInfo == null) {
            return null;
        }
        if (userInfo.getState() == 1) { //账户冻结
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;

    }
}
