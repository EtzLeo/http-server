package com.leo.http.framework.resolver.argument;

import com.leo.http.framework.Request;
import com.leo.http.framework.exception.UnsupportedParameterException;

import java.io.OutputStream;
import java.lang.reflect.Parameter;

public class RequestHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
  @Override
  public boolean supportsParameter(Parameter parameter) {
    return parameter.getType().isAssignableFrom(Request.class);
  }

  @Override
  public Object resolveArgument(Parameter parameter, Request request, OutputStream response) {
    if (!supportsParameter(parameter)) {
      // this should never happen
      throw new UnsupportedParameterException(parameter.getType().getName());
    }

    return request;
  }
}
