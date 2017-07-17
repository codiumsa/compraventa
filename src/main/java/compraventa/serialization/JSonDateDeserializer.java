package compraventa.serialization;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

/**
 * Deserializador para los campos de tipo {@link Date}. El atributo debe estar
 * anotado con {@link DateFormat} si se desea especificar el formato de fecha a
 * utilizar. Formato por defecto: "yyyy/MM/dd"
 *
 * @author Jorge Ramirez
 **/
public class JSonDateDeserializer extends JsonDeserializer<Date> implements ContextualDeserializer {

	private static final String DEFAULT_FORMAT = "yyyy/MM/dd";
	private String format;

	public JSonDateDeserializer() {
		this(DEFAULT_FORMAT);
	}

	public JSonDateDeserializer(String format) {
		this.format = format;
	}

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(jp.getValueAsString());
		} catch (ParseException e) {
			throw new IOException(e);
		}
	}

	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property)
			throws JsonMappingException {
		DateFormat format = property.getAnnotation(DateFormat.class);

		if (format == null) {
			format = property.getContextAnnotation(DateFormat.class);
		}

		if (format == null) {
			return new JSonDateDeserializer();
		}
		return new JSonDateDeserializer(format.value());
	}
}