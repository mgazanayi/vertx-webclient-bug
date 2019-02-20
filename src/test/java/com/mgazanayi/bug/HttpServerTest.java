package com.mgazanayi.bug;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.WebClient;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class HttpServerTest {

   private Vertx vertx;
   private WebClient webClient;
   private HttpServer httpServer;

   @Before
   public final void init(TestContext context) {
      vertx = Vertx.vertx();
      webClient = WebClient.wrap(vertx.createHttpClient());
      httpServer = new HttpServer();
      vertx.deployVerticle(httpServer, new DeploymentOptions(), context.asyncAssertSuccess());
   }

   @After
   public void close(TestContext context) {
      vertx.close(context.asyncAssertSuccess());
   }

   @Test
   public void should_call_vertx_http_server(TestContext testContext) {
      Async async = testContext.async();

      webClient.get(9000, "localhost", "/api/dummy")
            .send(feed -> {
               Assertions.assertThat(feed.result().bodyAsString()).isEqualTo("{\"response\" : \"Working if test launched alone\"}");
               async.complete();
            });
   }
}