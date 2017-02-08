package com.danix.hello.controllers

import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.RequestPredicates.accept
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.route
import reactor.core.publisher.Mono

@Controller
class HelloController : RouterFunction<ServerResponse> {


    override fun route(request: ServerRequest): Mono<HandlerFunction<ServerResponse>> = route(request) {
        accept(MediaType.TEXT_PLAIN).apply {
            GET("/hello") { ok().render("hello world") }
        }
    }
}