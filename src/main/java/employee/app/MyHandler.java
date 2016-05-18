package employee.app;

import ratpack.handling.Context;
import ratpack.handling.Handler;

public class MyHandler implements Handler{

	@Override
	public void handle(Context ctx) throws Exception {
		ctx.render("z");
		
	}
	
}
