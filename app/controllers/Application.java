package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static String szDbPassword = "";
    public static Result index() {
        System.out.println("\n[INFO] Server: new Session\n");
        return redirect("assets/index.html");
    }

}
