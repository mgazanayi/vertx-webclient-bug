package com.mgazanayi.bug;

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
public class HttpExternalCallTest {

   private Vertx vertx;
   private WebClient webClient;


   @Before
   public final void init(TestContext context) {
      vertx = Vertx.vertx();
      webClient = WebClient.create(vertx);
   }

   @After
   public void close(TestContext context) {
      vertx.close(context.asyncAssertSuccess());
   }

   @Test
   public void should_call_ipstack_api_without_access_key(TestContext testContext) {
      Async async = testContext.async();

      webClient.get(80, "api.ipstack.com", "/122.201.250.155")
            .send(feed -> {
               Assertions.assertThat(feed.result().bodyAsString())
                     .isEqualTo("{\"success\":false,\"error\":{\"code\":101,\"type\":\"missing_access_key\",\"info\":\"You have not supplied an API Access Key. [Required format: access_key=YOUR_ACCESS_KEY]\"}}");
               async.complete();
            });
   }
}