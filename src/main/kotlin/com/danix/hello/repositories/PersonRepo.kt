package com.danix.hello.repositories

import com.danix.hello.domain.Person
import java.util.stream.Stream


interface PersonRepo {
    fun all(): Stream<Person>
}

class MockPersonRepo : PersonRepo {
    override fun all(): Stream<Person> {
        return Stream.of(Person("Dan", "dan@yahoo.com"),
                Person("Alex", "alex@yahoo.com"),
                Person("Ade", "ade@yahoo.com"))
    }
}
