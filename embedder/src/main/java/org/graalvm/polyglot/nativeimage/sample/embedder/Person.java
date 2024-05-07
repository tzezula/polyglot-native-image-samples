package org.graalvm.polyglot.nativeimage.sample.embedder;

final class Person {

    public String first;
    public String mid;
    public String last;

    Person(String firstName, String midName, String lastName) {
        this.first = firstName;
        this.mid = midName;
        this.last = lastName;
    }

    public String  firstName() {
        return first;
    }

    public String  midName() {
        return mid;
    }

    public String  lastName() {
        return last;
    }
}
