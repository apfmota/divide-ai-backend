package com.elc1009.projeto3.backend;
import io.javalin.Javalin;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        var app = Javalin.create(/*config*/)
            .get("/", ctx -> ctx.result("Hello World"));
            //adicionar aqui as demais rotas
        app.start(80);
    }
}