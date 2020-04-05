/**
 * Copyright (c) 2015-2017, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stylefeng.guns.core.aop;

import com.stylefeng.guns.core.common.annotion.Permission;
import com.stylefeng.guns.core.shiro.check.PermissionCheckManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.naming.NoPermissionException;
import java.lang.reflect.Method;

/**
 * AOP 权限自定义检查
 */
@Aspect
@Component
@Order(200)
public class PermissionAop {

    @Pointcut(value = "@annotation(com.stylefeng.guns.core.common.annotion.Permission)")
    private void cutPermission() {

    }

    @Around("cutPermission()")
    public Object doPermission(ProceedingJoinPoint point) throws Throwable {
        MethodSignature ms = (MethodSignature) point.getSignature();
        //先获取被拦截的方法
        Method method = ms.getMethod();

        //获取到被拦截方法的注解
        Permission permission = method.getAnnotation(Permission.class);

        //再获取到注解的value
        Object[] permissions = permission.value();
        //@Permission("")执行这个
        if (permissions == null || permissions.length == 0) {
            //检查全体角色 检查当前用户有没有访问当前资源的权限
            boolean result = PermissionCheckManager.checkAll();
            if (result) {
                return point.proceed();
            } else {
                throw new NoPermissionException();
            }
        } else {
            //@Permission("admin")value不为空时,执行这个
            //检查指定角色
            boolean result = PermissionCheckManager.check(permissions);

            if (result) {
                //如果有这个角色,result=true,则继续执行后面的程序
                return point.proceed();
            } else {
                throw new NoPermissionException();
            }
        }

    }

}
