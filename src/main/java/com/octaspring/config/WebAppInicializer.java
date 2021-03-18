package com.octaspring.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInicializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		
		return new Class[] {SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {WebAppConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}
	// desactivar pagina del servidor y tome en cuenta la configuracion
	@Override
	protected FrameworkServlet createDispatcherServlet(WebApplicationContext context) {
		DispatcherServlet dispatcher = (DispatcherServlet) super.createDispatcherServlet(context);
		dispatcher.setThrowExceptionIfNoHandlerFound(true);
		return dispatcher;
	}
	
	
}
