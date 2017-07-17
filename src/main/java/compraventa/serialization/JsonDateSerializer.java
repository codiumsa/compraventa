package compraventa.serialization;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

/**
 * Serializador para los campos de tipo {@link Date}. El atributo debe estar
 * anotado con {@link DateFormat} si se desea especificar el formato de fecha a
 * utilizar. Formato por defecto: "yyyy/MM/dd"
 *
 * @author Jorge Ramírez <jorge@codium.com.py>
 * @since 0.1.0
 **/
public class JsonDateSerializer extends JsonSerializer<Date> implements ContextualSerializer {

	private static final String DEFAULT_FORMAT = "yyyy/MM/dd";
	private String format;

	public JsonDateSerializer() {
		this(DEFAULT_FORMAT);
	}

	public JsonDateSerializer(String format) {
		this.format = format;
	}

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		jgen.writeString(sdf.format(value));
	}

	@Override
	public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
			throws JsonMappingException {
		DateFormat format = property.getAnnotation(DateFormat.class);

		if (format == null) {
			format = property.getContextAnnotation(DateFormat.class);
		}

		if (format == null) {
			return new JsonDateSerializer();
		}
		return new JsonDateSerializer(format.value());
	}
}