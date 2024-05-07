package org.graalvm.polyglot.nativeimage.sample.library;

import java.util.List;
import java.util.stream.Collectors;

final class Persons {

    private final List<Person> persons;

    Persons(List<Person> persons) {
        this.persons = persons.stream().map(PersonDecorator::new).collect(Collectors.toUnmodifiableList());
    }

    public List<Person> persons() {
        return persons;
    }

    private static final class PersonDecorator implements Person {

        final Person delegate;

        PersonDecorator(Person person) {
            this.delegate = person;
        }

        @Override
        public String firstName() {
            return delegate.firstName();
        }

        @Override
        public String midName() {
            return delegate.midName();
        }

        @Override
        public String lastName() {
            return delegate.lastName();
        }
    }
}
