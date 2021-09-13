package com.leo.http.framework.resolver.argument;

import com.leo.http.framework.Request;
import com.leo.http.framework.annotation.RequestHeader;
import com.leo.http.framework.exception.UnsupportedParameterException;

import java.io.OutputStream;
import java.lang.reflect.Parameter;
import java.util.Optional;

public class RequestHeaderHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
  private final Class<RequestHeader> annotation = RequestHeader.class;

  @Override
  public boolean supportsParameter(Parameter parameter) {
    return parameter.getType().isAssignableFrom(String.class) && parameter.isAnnotationPresent(annotation);
  }

  @Override
  public Object resolveArgument(Parameter parameter, Request request, OutputStream response) {
    if (!supportsParameter(parameter)) {
      // this should never happen
      throw new UnsupportedParameterException(parameter.getType().getName());
    }

    final RequestHeader annotation = parameter.getAnnotation(this.annotation);
    return Optional
        .ofNullable(
            request.getHeaders().get(annotation.value())
        ).orElseThrow(
            () -> new UnsupportedParameterException(annotation.value())
        );
  }
}
