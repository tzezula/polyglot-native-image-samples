package org.graalvm.polyglot.nativeimage.sample.js;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotAccess;
import org.graalvm.polyglot.Value;

import java.nio.file.Path;

public final class Main {

    private static final String SCRIPTS = """
            let names = ['Jakub', 'Jan', 'Matias', 'Adam']
            let surname = ['Novak', 'Svoboda']
            let data = []
            for (n of names) {
              for (s of surname) {
                data.push({
                  'firstName': n,
                  'midName': '',
                  'lastName': s})
              }
            }
            let libTemplates = Polyglot.import('lib_templates')
            let templatesFunctions = libTemplates['org.graalvm.polyglot.nativeimage.sample.library.Templates'] 
            templatesFunctions.asHtmlTable(data)
            """;

    public static void main(String... args) {
        PolyglotAccess pa = PolyglotAccess.newBuilder()
                .allowBindingsAccess("js")
                .build();
        Path libraryPath = Path.of(args[0]).resolve(System.mapLibraryName("templates"));
        Context.Builder builder = Context.newBuilder()
                .allowExperimentalOptions(true)
                .allowPolyglotAccess(pa)
                .option("native.ImagePath", libraryPath.toString());
        try (Context ctx = builder.build()) {
            ctx.getPolyglotBindings().putMember("lib_templates", ctx.getBindings("native"));
            Value result = ctx.eval("js", SCRIPTS);
            System.out.println(result.asString());
        }
    }
}
