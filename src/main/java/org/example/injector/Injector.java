package org.example.injector;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Injector {
    public  <T> T inject(T component) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        Properties properties = new Properties();

        FileInputStream fileInputStream = new FileInputStream("src/main/resources/injector.properties");
        properties.load(fileInputStream);

        for (Field field : component.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                Object type = properties.get(field.getType().getName());
                Constructor<?> constructor = Class.forName((String) type).getDeclaredConstructor();
                Object object = constructor.newInstance();
                boolean isAccessible = field.canAccess(component);
                field.setAccessible(true);
                field.set(component, object);
                field.setAccessible(isAccessible);
            }
        }

        return component;
    }
}
