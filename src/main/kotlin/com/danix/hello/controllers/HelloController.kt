package com.danix.hello.controllers

import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.RequestPredicates.accept
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.route

class HelloController : RouterFunction<ServerResponse> {

    override fun route(request: ServerRequest) =
            route(request) {
                accept(MediaType.TEXT_PLAIN).apply {
                    GET("/hello-controller") {
                        ok().body(fromObject("Hello World from controller"))
                    }
                }
            }
}
