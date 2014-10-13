package co.a123.mediacion.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.a123.mediacion.gcm.GoogleConnectionManager;
import co.a123.mediacion.util.Constant;

@WebServlet("/Autenticacion")
public class Interruptor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public Interruptor() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleInterruptorEvents(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleInterruptorEvents(request, response);
	}

	private void handleInterruptorEvents(HttpServletRequest request, HttpServletResponse response){
		
		String action  = request.getParameter("action");
		GoogleConnectionManager connectionManager = GoogleConnectionManager.getInstance();
		
		//connectionManager.isConected();
		
		if(Constant.ACTION_ENCENDER.equals(action)){
			connectionManager.connect();
		}else if(Constant.ACTION_APAGAR.equals(action)){
			connectionManager.disconnect();
		}
	}
}
