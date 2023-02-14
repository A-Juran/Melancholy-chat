package la.iit.annotation;

import java.lang.annotation.*;

/**
 * @author JuRan
 * @date 2/12/2023
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)   //注解会在class字节码文件中存在,在运行时通过反射进行获取。
@Target({ElementType.METHOD,ElementType.PARAMETER}) //注解作用目标，方法和参数
@Inherited  //子类可以继承父类中的该注解。
public @interface SysLogin {
    String value() default "";
}
