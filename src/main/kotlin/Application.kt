import com.danix.hello.controllers.HelloController
import io.undertow.Undertow
import org.springframework.beans.factory.getBeansOfType
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.registerBean
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
    return route(GET("/hello-world").and(accept(MediaType.TEXT_PLAIN)),
            helloWorld())
}

fun routingFunction1(): RouterFunction<*> {
    return route(GET("/hello-world-1").and(accept(MediaType.TEXT_PLAIN)),
            helloWorld())
}

val context = GenericApplicationContext {
    registerBean<HelloController>()
}

fun main(args: Array<String>) {

    val definedRoutes = routingFunction().and(routingFunction1())
    context.refresh()

    val allRoutes =
            context.getBeansOfType(RouterFunction::class)
                    .values
                    .reduce(RouterFunction<*>::and)
                    .and(definedRoutes)


    val httpHandler = toHttpHandler(allRoutes)
    val adapter = UndertowHttpHandlerAdapter(httpHandler)
    val server = Undertow.builder()
            .addHttpListener(8080, "localhost")
            .setHandler(adapter)
            .build()
    server.start()
}
