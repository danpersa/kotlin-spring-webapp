package com.danix.hello.services

import com.danix.hello.domain.Person
import com.danix.hello.repositories.PersonRepo
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import java.util.stream.Stream

internal class PersonServiceImplTest {

    val person = Person("Dan", "dan@yahoo.com")
    val persons = Stream.of(person)!!

    val personRepo: PersonRepo = mock {
        on { all() } doReturn persons
    }

    val personService = PersonServiceImpl(personRepo)

    @Test
    fun all() {
        assertThat(personService.all().blockFirst(), equalTo(person))
    }
}
