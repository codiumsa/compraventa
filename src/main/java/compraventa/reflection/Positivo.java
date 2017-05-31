package compraventa.reflection;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Validador de números positivos.
 * 
 * @author jorge
 */
@Retention(RUNTIME)
@Target({ ElementType.FIELD })
public @interface Positivo {

}
