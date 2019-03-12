/*
 * 2. Создать класс с несколькими методами. Вызвать метод по имени заданном как строка используя рефлексию.
 */

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {

        SomeClass someClass = new SomeClass();
        System.out.println("\tLegal access to fields >>>");
        System.out.printf("Restricted data: %d\n", someClass.getData());
        System.out.printf("Modified restricted data: %d\n\n", someClass.setData(97643182, 345));

        System.out.println("\tField access using reflection >>>");
        // Access to private "restData" field
        try {
            Field field = someClass.getClass().getDeclaredField("restData");
            field.setAccessible(true);
            System.out.printf("Restricted data (direct access): %d\n", (int) field.get(someClass));
            field.set(someClass, (int) 987);
            System.out.printf("Restricted data is modified (direct access): %d\n", (int) field.get(someClass));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // Access to private "AUTENTIFICATION_KEY" field
        try {
            Field field = someClass.getClass().getDeclaredField("AUTENTIFICATION_KEY");
            field.setAccessible(true);
            int restrictedData = (int) field.get(someClass);
            System.out.printf("AUTENTIFICATION_KEY (direct access): %d\n", restrictedData);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // Invoking private method emptyMethod()
        try {
            Method method = someClass.getClass().getDeclaredMethod("emptyMethod");
            method.setAccessible(true);
            method.invoke(someClass);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // Invoking private method modData()
        try {
            Method method = someClass.getClass().getDeclaredMethod("modData", int.class);
            method.setAccessible(true);
            System.out.printf("Access to restricted method modData(): %d\n", method.invoke(someClass, 5678));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // Access to information about fields and methods
        Class<SomeClass> refClass = SomeClass.class;
        System.out.println("\n\tDeclared methods in the SomeClass >>>");
        Method[] declaredMethods = refClass.getDeclaredMethods();
        for (Method method : declaredMethods){
            System.out.println(method);
        }
        System.out.println("\tDeclared fields in the SomeClass >>>");
        Field[] declaredFields = refClass.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field);
        }


    }
}
