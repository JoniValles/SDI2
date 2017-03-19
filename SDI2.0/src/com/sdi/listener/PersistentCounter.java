package com.sdi.listener;

import java.io.FileOutputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import alb.util.log.Log;

public class PersistentCounter implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		// Guardar el contador total de sesiones de usuario iniciadas
		String counter=(String)arg0.getServletContext().getAttribute("contador");
		Properties properties = new Properties();
		properties.setProperty("contadorSesiones", counter);
		try {
	        properties.store(new FileOutputStream(arg0.getServletContext().getRealPath(arg0.getServletContext().getInitParameter("ubicacionDelContadorDeSesiones"))),"Lista de propiedades");
		} catch (Exception e) {}
		Log.debug("Persistiendo contador de sesiones de usuario con el valor: %s",counter);
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		// Leer el contador total de sesiones de usuario iniciadas
		String counter="0";
		Properties properties = new Properties();
		try {
			properties.load(arg0.getServletContext().getResourceAsStream(arg0.getServletContext().getInitParameter("ubicacionDelContadorDeSesiones")));
			counter=properties.getProperty("contadorSesiones");
		} catch (Exception e) {}
		arg0.getServletContext().setAttribute("contador",counter);
		Log.debug("Inicializando contador de sesiones de usuario a: %s",counter);
	}

}