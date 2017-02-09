package com.danix.hello.controllers

import com.danix.hello.domain.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.route
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


class PersonController(@Autowired val personHandler: PersonHandler) : RouterFunction<ServerResponse> {

    override fun route(request: ServerRequest) =
            route(request) {
                RequestPredicates.accept(MediaType.APPLICATION_JSON).apply {
                    GET("/persons", personHandler::all)
                }
            }
}

class PersonHandler {

    fun all(request: ServerRequest): Mono<ServerResponse> {
        val persons: Flux<Person> = Flux.fromIterable(
                listOf(Person("Dan", "dan@yahoo.com"),
                        Person("Alex", "alex@yahoo.com")))
        return ServerResponse.ok().body(BodyInserters.fromPublisher(persons, Person::class.java))
    }
}
