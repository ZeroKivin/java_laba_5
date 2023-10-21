package org.example;

import org.example.injector.Injector;
import org.example.model.SomeBean;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException,
            IllegalAccessException, InstantiationException, ClassNotFoundException {
        (new Injector()).inject(new SomeBean()).foo();
    }
}