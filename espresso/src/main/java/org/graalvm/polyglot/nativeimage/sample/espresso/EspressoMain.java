package org.graalvm.polyglot.nativeimage.sample.espresso;

import java.util.ArrayList;
import java.util.List;

import com.oracle.truffle.espresso.polyglot.Polyglot;
import com.oracle.truffle.espresso.polyglot.Interop;
import com.oracle.truffle.espresso.polyglot.InteropException;

public final class EspressoMain {

    public static String execute() {
        Object libTemplates = Polyglot.importObject("lib_templates");
        try {
            Object templatesFunctions = Interop.readMember(libTemplates, "org.graalvm.polyglot.nativeimage.sample.library.Templates");
            String result = Interop.invokeMemberWithCast(String.class, templatesFunctions, "asHtmlTable", createData());
            return result;
        } catch (InteropException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Person> createData() {
        List<Person> result = new ArrayList<>();
        String[] names = new String[]{"Eliska", "Viktorie", "Anna", "Sofie"};
        String[] surnames = new String[]{"Novakova", "Svobodova"};
        for (String name : names) {
            for (String surname : surnames) {
                result.add(new Person(name, "", surname));
            }
        }
        return result;
    }
}
