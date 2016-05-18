package employee.app;

import com.google.common.collect.ImmutableMap;
import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.org.apache.regexp.internal.recompile;

import jdk.internal.dynalink.linker.GuardingTypeConverterFactory;
import ratpack.server.RatpackServer;
import sun.net.www.content.audio.x_aiff;
import ratpack.dropwizard.metrics.DropwizardMetricsConfig;
import ratpack.dropwizard.metrics.DropwizardMetricsModule;
import ratpack.dropwizard.metrics.MetricsWebsocketBroadcastHandler;
import ratpack.guice.*;

public class Main {
 public static void main(String... args) throws Exception {
   RatpackServer.start(server -> server 
	 .registryOf(r -> r
			 .add(MyHandler.class,new MyHandler())
			 .add(MetricsWebsocketBroadcastHandler.class,new MetricsWebsocketBroadcastHandler())
	 )
     .serverConfig(c -> c
     .props(ImmutableMap.of("metrics.jmxenabled","true"))
     .sysProps()
     .require("/metrics", DropwizardMetricsConfig.class)
     )
     .handlers(chain -> chain
       .get("a",MyHandler.class)
       .get("metrics",MetricsWebsocketBroadcastHandler.class)
       .get(ctx -> ctx.render("Hello World!"))
       .get("x",ctx -> ctx.render("X!"))
       .get(ctx -> ctx.byContent(c -> c.json(() -> ctx.render("{a: 'B'"))))
       .get(":name", ctx -> ctx.render("Hello " + ctx.getPathTokens().get("name") + "!"))
     )
   );
 }
}
