package org.graalvm.polyglot.nativeimage.sample.espresso;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.PolyglotAccess;
import org.graalvm.polyglot.Value;

import java.nio.file.Path;

public final class Main {

    public static void main(String... args) throws Exception {
        PolyglotAccess pa = PolyglotAccess.newBuilder()
                .allowBindingsAccess("java")
                .build();
        Path libraryPath = Path.of(args[0]).resolve(System.mapLibraryName("templates"));
        String cp = System.getProperty("java.class.path");
        Context.Builder builder = Context.newBuilder()
                .allowNativeAccess(true)
                .allowExperimentalOptions(true)
                .allowCreateThread(true)
                .allowPolyglotAccess(pa)
                .allowHostAccess(HostAccess.ALL)
                .option("native.ImagePath", libraryPath.toString())
                .option("java.Properties.java.class.path", cp)
                .option("java.Polyglot", "true");
        try (Context ctx = builder.build()) {
            ctx.getPolyglotBindings().putMember("lib_templates", ctx.getBindings("native"));
            Value main = ctx.getBindings("java").getMember("org.graalvm.polyglot.nativeimage.sample.espresso.EspressoMain");
            Value result = main.invokeMember("execute");
            System.out.println(result.asString());
        }
    }
}
