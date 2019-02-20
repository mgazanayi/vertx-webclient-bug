package com.mgazanayi.bug;

import io.vertx.reactivex.core.AbstractVerticle;

public class Deployer extends AbstractVerticle {

   @Override
   public void start() {
      vertx.deployVerticle(new HttpServer());
   }
}
