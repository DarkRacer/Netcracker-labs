package com.nc.labs.di;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The class performs dependency injection
 * @author Maksim Shcherbakov
 * @version 1.0
 */
@Configuration(packages = {"com.nc.labs.sort.impl", "com.nc.labs.validation.client", "com.nc.labs.validation.contract"})
public class Injector {
    /**
     * List of classes in the specified packages
     */
    private static List<Object> classList;

    /**
     * The constructor fills in the list of classes in the specified packages
     */
    public Injector() {
        classList = new ArrayList<>();
        if (this.getClass().isAnnotationPresent(Configuration.class)) {
            Configuration configuration = this.getClass().getAnnotation(Configuration.class);

            for (String pack : configuration.packages()) {
                pack = pack.replace(".", "/");

                try (DataInputStream dataInputStream = new DataInputStream((InputStream) Objects.requireNonNull(
                        this.getClass().getClassLoader().getResource(pack)).getContent())) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        if (line.endsWith(".class")) {
                            String className = pack.replace("/", ".") + "."
                                    + line.substring(0, line.length() - 6);
                            classList.add(Class.forName(className).getConstructor().newInstance());
                        }
                    }
                } catch (IOException | InstantiationException | InvocationTargetException | NoSuchMethodException
                        | ClassNotFoundException | IllegalAccessException e) {
                    System.out.println("Class not found");
                }
            }
        }
    }

    /**
     * The method performs dependency injection
     * @param o object for dependency injection
     * @param <T> generic for dependency injection
     * @return object with dependency injection
     * @throws Exception to handle an injection error
     */
    public static <T> T inject(final T o) throws IllegalAccessException {
        List<Object> classForInjection = new ArrayList<>();

        for (Field field : o.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                if (field.getType().getName().contains("java.util.List")) {
                    ParameterizedType fieldListType = (ParameterizedType) field.getGenericType();
                    Class<?> fieldGenericType = (Class<?>) fieldListType.getActualTypeArguments()[0];

                    for (Object object : classList) {
                        if (object != null && fieldGenericType.isAssignableFrom(object.getClass())) {
                            classForInjection.add(object);
                        }

                        field.set(o, classForInjection);
                    }
                } else {
                    for (Object object : classList) {
                        if (object != null && field.getType().isAssignableFrom(object.getClass())) {
                            classForInjection.add(object);
                        }

                        if (classForInjection.size() == 1) {
                            field.set(o, classForInjection.get(0));
                        } else {
                            throw new RuntimeException("The number of classes for injection is not equal to 1");
                        }
                    }
                }
            }
        }

        return o;
    }
}
