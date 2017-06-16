package compraventa.interceptor;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Interceptor utilizado para logging.
 * 
 * @author jorge
 */
@Logged
@Interceptor
public class LoggedInterceptor implements Serializable {

	private static final long serialVersionUID = 4480620538742614433L;

	/**
	 * Método que implementa la lógica de logging.
	 * 
	 * @return
	 */
	@AroundInvoke
	public Object log(InvocationContext ic) throws Exception {
		String msg = "Método a ejecutar: " + ic.getMethod().getName();
		msg += ", de la clase: " + ic.getMethod().getDeclaringClass().getName();
		System.out.println(msg);
		return ic.proceed();
	}
}
