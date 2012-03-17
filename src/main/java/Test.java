import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


public class Test {

	private List<String> list;
	
	public static void main(String[] args) throws SecurityException, NoSuchFieldException {
		Class<?> clazz = Test.class;
		
		Field field = clazz.getDeclaredField("list");
		
		ParameterizedType type = (ParameterizedType)field.getGenericType();
		Type type1 = type.getActualTypeArguments()[0];
		
	}
}
