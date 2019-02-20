package com.mgazanayi.bug;

import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.BodyHandler;

public class HttpServer extends AbstractVerticle {

   @Override
   public void start() {
      var router = Router.router(vertx);
      router.route().handler(BodyHandler.create());

      router.get("/api/dummy").handler(this::getHandler);

      vertx.createHttpServer()
            .requestHandler(router)
            .listen(config().getInteger("bug.server.port", 9000));
   }

   private void getHandler(RoutingContext rc) {
      rc.response()
            .setStatusCode(200)
            .end("{\"response\" : \"Working if test launched alone\"}");
   }
}
