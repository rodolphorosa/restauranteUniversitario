package entidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class MapeadorAtributos {
	
	Map<String, Object> atributos;
	
	public MapeadorAtributos() {
		atributos = new TreeMap<String, Object>();
	}
	
	public void add(Atributo<?> field) {
		atributos.put(field.getNome(), field.getValor());
	}
	
	public Object get(String key) {
		return atributos.get(key);
	}
	
	public void replace(String key, Object value) {
		atributos.replace(key, value);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection<Atributo<?>> getAll() {
		Collection<Atributo<?>> atributosObj = new ArrayList<Atributo<?>>();
		for(String key : atributos.keySet()) {
			atributosObj.add(new Atributo(get(key).getClass(), key, get(key)));
		}
		return atributosObj;
	}
}