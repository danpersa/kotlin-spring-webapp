import io.undertow.Undertow
import org.springframework.http.MediaType
import org.springframework.http.server.reactive.UndertowHttpHandlerAdapter
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RequestPredicates.accept
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok

fun helloWorld() = HandlerFunction<ServerResponse> {
    ok().body(fromObject("Hello World from function"))
}

fun routingFunction(): RouterFunction<*> {
    return route(GET("/hello-world").and(accept(MediaType.TEXT_PLAIN)), helloWorld())
}

fun main(args: Array<String>) {
    val httpHandler = toHttpHandler(routingFunction())
    val adapter = UndertowHttpHandlerAdapter(httpHandler)
    val server = Undertow.builder()
            .addHttpListener(8080, "localhost")
            .setHandler(adapter)
            .build()
    server.start()
}
