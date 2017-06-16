package compraventa.interceptor;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/**
 * Anotación que indica que una clase o métodos de clase deben ser procesados
 * por el {@link LoggedInterceptor}.
 * 
 * @author jorge
 */
@Retention(RUNTIME)
@Inherited
@InterceptorBinding
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface Logged {

}
