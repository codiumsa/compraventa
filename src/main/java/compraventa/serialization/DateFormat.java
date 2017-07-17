package compraventa.serialization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación que indica el formato a utilizar al serializar un tipo
 * {@link java.util.Date}. Los opciones de formatos soportados se corresponden
 * con {@link java.text.SimpleDateFormat}.
 *
 * @author Jorge Ramirez
 */
@Target({ ElementType.FIELD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface DateFormat {

	String value() default "yyyy/MM/dd";
}