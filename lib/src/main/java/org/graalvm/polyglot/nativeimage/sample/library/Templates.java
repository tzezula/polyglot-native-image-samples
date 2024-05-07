package org.graalvm.polyglot.nativeimage.sample.library;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

public class Templates {
    
    
    private static final Mustache htmlTable;
    private static final Mustache htmlList;
    static {
        MustacheFactory mf = new DefaultMustacheFactory();
        String template = Templates.class.getPackageName().replace('.', '/') + '/' + "table.mustache";
        htmlTable = mf.compile(template);
        template = Templates.class.getPackageName().replace('.', '/') + '/' + "list.mustache";
        htmlList = mf.compile(template);
    }

    public static String asHtmlTable(List<Person> persons) throws IOException {
        StringWriter out = new StringWriter();
        htmlTable.execute(out, new Persons(persons)).flush();
        return out.toString();
    }

    public static String asHtmlList(List<Person> persons) throws IOException {
        StringWriter out = new StringWriter();
        htmlList.execute(out, new Persons(persons)).flush();
        return out.toString();
    }
}
