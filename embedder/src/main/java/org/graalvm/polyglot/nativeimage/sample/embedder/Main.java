package org.graalvm.polyglot.nativeimage.sample.embedder;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Value;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class Main {

    public static void main(String... args) {
        Path libraryPath = Path.of(args[0]).resolve(System.mapLibraryName("templates"));
        Context.Builder builder = Context.newBuilder()
                .allowExperimentalOptions(true)
                .allowHostAccess(HostAccess.ALL)
                .option("native.ImagePath", libraryPath.toString());
        List<Person> data = createData();
        try (Context ctx = builder.build()) {
            Value templatesFunctions = ctx.getBindings("native").getMember("org.graalvm.polyglot.nativeimage.sample.library.Templates");
            Value result = templatesFunctions.invokeMember("asHtmlTable", data);
            System.out.println(result.asString());
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
