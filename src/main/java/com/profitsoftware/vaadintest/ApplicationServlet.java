package com.profitsoftware.vaadintest;

import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/*"},initParams={
  @WebInitParam(name="UI", value="com.profitsoftware.vaadintest.AppUI")
}, asyncSupported=true)
@ServletSecurity(value=@HttpConstraint(ServletSecurity.EmptyRoleSemantic.DENY),
  httpMethodConstraints = {
    @HttpMethodConstraint(value="GET",emptyRoleSemantic=ServletSecurity.EmptyRoleSemantic.PERMIT),
    @HttpMethodConstraint(value="POST",emptyRoleSemantic=ServletSecurity.EmptyRoleSemantic.PERMIT)
})
public class ApplicationServlet extends VaadinServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public String getServletInfo() {
    return "app servlet";
  }

}
