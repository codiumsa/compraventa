package compraventa.service;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Permite configurar el ObjectMapper utilizado por Jackson para la
 * serialización/deserialización.
 * 
 * @author jorge
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ObjectMapperResolver implements ContextResolver<ObjectMapper> {

	private final ObjectMapper mapper;

	public ObjectMapperResolver() {
		mapper = new ObjectMapper();
		// indica que los atributos que no estén anotados con JsonViews no deben
		// incluirse en el JSON de respuesta.
		mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

		// indica que solo atributos con valores distintos a null deben formar
		// parte del JSON de respuesta
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		// Al desactivar la configuración, se le indica a jackson que cuando no
		// se encuentra un
		// getter/setter para un tipo (no primitivo) no debe lanzar una
		// excepción, sino simplemente retornar un objeto vació {}
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		// Al desactivar la configuración, se le indica a jackson que no debe
		// lanzar una excepción cuando encuentra un atributo que no forma parte
		// de la clase que se está deserializando.
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return mapper;
	}

}