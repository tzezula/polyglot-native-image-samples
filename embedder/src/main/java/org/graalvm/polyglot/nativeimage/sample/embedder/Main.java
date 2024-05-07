package org.graalvm.polyglot.nativeimage.sample.embedder;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.PolyglotAccess;
import org.graalvm.polyglot.Value;

import java.nio.file.Path;
import java.util.List;

public final class Main {

    public static void main(String... args) {
        Path libraryPath = Path.of(args[0]).resolve(System.mapLibraryName("templates"));
        Context.Builder builder = Context.newBuilder()
                .allowExperimentalOptions(true)
                .option("native.ImagePath", libraryPath.toString());
        HostAccess hostAccess = HostAccess.ALL;
        List<Person> data = List.of(new Person("Karel", "", "Prdel"));
        try (Context ctx = builder.allowHostAccess(hostAccess).build()) {
            Value templatesFunctions = ctx.getBindings("native").getMember("org.graalvm.polyglot.nativeimage.sample.library.Templates");
            Value result = templatesFunctions.invokeMember("asHtmlTable", data);
            System.out.println(result.asString());
        }
    }
}
