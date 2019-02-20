package com.mgazanayi.bug;

import io.vertx.core.Launcher;

public class Application extends Launcher {
   public static void main(String[] args) {
      new Application().dispatch(args);
   }
}
