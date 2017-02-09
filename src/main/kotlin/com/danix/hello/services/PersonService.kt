package com.danix.hello.services

import com.danix.hello.domain.Person
import com.danix.hello.repositories.PersonRepo
import reactor.core.publisher.Flux


interface PersonService {

    fun all(): Flux<Person>
}

class PersonServiceImpl(val personRepo: PersonRepo) : PersonService {
    override fun all(): Flux<Person> {
        return Flux.fromStream(personRepo.all())
    }
}