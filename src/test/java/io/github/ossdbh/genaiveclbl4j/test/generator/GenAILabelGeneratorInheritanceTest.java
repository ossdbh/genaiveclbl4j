package io.github.ossdbh.genaiveclbl4j.test.generator;

import io.github.ossdbh.genaiveclbl4j.generator.ClassFieldResolver;
import io.github.ossdbh.genaiveclbl4j.generator.GenAIVectorTrainAndSearchlabelGenerator;
import io.github.ossdbh.genaiveclbl4j.test.dto.Chld;
import io.github.ossdbh.genaiveclbl4j.test.dto.Teen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

public class GenAILabelGeneratorInheritanceTest {
    private Chld chld;
    private Teen t;

    @Before
    public void setup() {
        this.chld = Chld.chldBuilder()
                .parentName("Andrea")
                .parentAge(38)
                .childName("Emma")
                .skipThis("Well dont skip this")
                .childAge(6)
                .build();

        this.t = Teen.teenBuilder()
                .parentName("Andrea")
                .parentAge(38)
                .childName("Emma")
                .skipThis("well skip this")
                .childAge(6)
                .address("ashda skjdfg skjd skjdhfksdh kjgs")
                .fld3(1000000)
                .fld4("Hey There lets see what we got")
                .fld5(5000000)
                .build();
    }

    @Test
    public void test() {
        //System.out.println(this.chld.toString());
        String format = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(this.chld);
        System.out.println(format);
        Assert.assertTrue("This is a String in a parent class Andrea | This is an int in a parent class 38 | This is a String in a deep stacked inherited child class, level 1Emma | This is another String in a deep stacked inherited child class, level 1Well dont skip this | This is an int in a deep stacked inherited child class, level 16".equals(format));
    }

    @Test
    public void test2() {
        // Run with teen instance that has 3 levels of inheritance
        String format = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(this.t);
        System.out.println(format);
        Assert.assertTrue("This is a String in a parent class Andrea | This is an int in a parent class 38 | This is a String in a deep stacked inherited child class, level 1Emma | This is another String in a deep stacked inherited child class, level 1well skip this | This is an int in a deep stacked inherited child class, level 16 | This is a String in a deep stacked inherited child class, level 2ashda skjdfg skjd skjdhfksdh kjgs | This is an int in a deep stacked inherited child class, level 21000000 | This is another String in a deep stacked inherited child class, level 2Hey There lets see what we got | This is another int in a deep stacked inherited child class, level 25000000".equals(format));
    }

    @Test
    public void test3() {
        List<String> l = new ArrayList<>();
        l.add("Teen.Chld.skipThis");
        l.add("Teen.fld3");

        // In case of inheritance you get a flat attribute space
        // Always use the classname of the instance that you are trying to fixed width format
        String format = GenAIVectorTrainAndSearchlabelGenerator.generateTextLabel(this.t, l);
        System.out.println(format);
        // This should not print "well skip this and 1000000"
        Assert.assertTrue("This is a String in a parent class Andrea | This is an int in a parent class 38 | This is a String in a deep stacked inherited child class, level 1Emma | This is an int in a deep stacked inherited child class, level 16 | This is a String in a deep stacked inherited child class, level 2ashda skjdfg skjd skjdhfksdh kjgs | This is another String in a deep stacked inherited child class, level 2Hey There lets see what we got | This is another int in a deep stacked inherited child class, level 25000000".equals(format));
    }

    @Test
    public void test4() {
        Map<String, Integer> m = new HashMap<>();
        m.put("Teen.Chld.skipThis", 1);
        m.put("Teen.fld5", 1);
        List<Field> classFieldsList = new ArrayList<>();
        ClassFieldResolver classFieldResolver = new ClassFieldResolver();
        classFieldResolver.getClassFields(Teen.class, new Stack<Class>(), new Stack<String>(), classFieldsList, m);
    }

}
