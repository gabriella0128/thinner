package com.nns.thinner.common.logging;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nns.thinner.event.LogEventDto;
import com.nns.thinner.event.LogEventPublisher;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RequiredArgsConstructor
public class LoggingFilter extends OncePerRequestFilter {

	private final LogEventPublisher logEventPublisher;

	public static String generateTraceId() {
		long currentTimeMillis = Instant.now().toEpochMilli();

		return String.format("%d", currentTimeMillis);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		String traceId = generateTraceId();

		doFilterWrapped(new RequestWrapper(request), new ContentCachingResponseWrapper(response), filterChain, traceId);

	}

	protected void doFilterWrapped(RequestWrapper request, ContentCachingResponseWrapper response,
		FilterChain filterChain, String traceId) throws
		IOException, ServletException {
		try {
			logRequest(request, traceId);
			filterChain.doFilter(request, response);

		} finally {
			if (!isAsyncDispatch(request)) {
				logResponse(request, response, traceId);
			}
			response.copyBodyToResponse();
		}
	}

	private void logRequest(RequestWrapper request, String traceId) throws
		IOException {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();

		String queryString = request.getQueryString();
		String queryStringUri =
			queryString == null ? request.getRequestURI() : request.getRequestURI() + "?" + queryString;

		rootNode.put("httpMessage", "request");
		rootNode.put("method", request.getMethod());
		rootNode.put("uri", queryStringUri);

		ObjectNode headersNode = rootNode.putObject("headers");
		Collections.list(request.getHeaderNames())
			.forEach(headerName -> headersNode.put(headerName, request.getHeader(headerName)));
		InputStream inputStream = request.getInputStream();
		String contentType = request.getContentType();

		JsonNode bodyNode = mapper.createObjectNode();

		if (MediaType.APPLICATION_JSON_VALUE.equals(contentType)) {
			bodyNode = mapper.readTree(inputStream);
			rootNode.set("body", bodyNode);

		} else {
			rootNode.put("body", "Empty Content");
		}

		String logMessage = mapper.writeValueAsString(rootNode);
		String logBodyMessage = mapper.writeValueAsString(bodyNode);

		logEventPublisher.createLog(LogEventDto.builder()
			.traceId(traceId)
			.httpMessage("request")
			.httpMethod(request.getMethod())
			.userKey(request.getHeader("UserKey"))
			.log(logBodyMessage)
			.uri(queryStringUri)
			.createDt(LocalDateTime.now())
			.build());

		log.info(logMessage);

	}

	private void logResponse(HttpServletRequest request, ContentCachingResponseWrapper response, String traceId) throws
		IOException {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();

		String queryString = request.getQueryString();
		String queryStringUri =
			queryString == null ? request.getRequestURI() : request.getRequestURI() + "?" + queryString;

		rootNode.put("httpMessage", "response");
		rootNode.put("method", request.getMethod());
		rootNode.put("uri", queryStringUri);

		String contentType = response.getContentType();
		InputStream inputStream = response.getContentInputStream();

		JsonNode bodyNode = mapper.createObjectNode();

		if (MediaType.APPLICATION_JSON_VALUE.equals(contentType)) {
			byte[] content = StreamUtils.copyToByteArray(inputStream);
			if (content.length > 0) {
				bodyNode = mapper.readTree(content);
				rootNode.set("body", bodyNode);
			}
		} else {
			rootNode.put("body", "Empty Content");
		}

		String logMessage = mapper.writeValueAsString(rootNode);
		String logBodyMessage = mapper.writeValueAsString(bodyNode);

		logEventPublisher.createLog(LogEventDto.builder()
			.traceId(traceId)
			.httpMessage("response")
			.httpMethod(request.getMethod())
			.userKey(request.getHeader("UserKey"))
			.log(logBodyMessage)
			.uri(queryStringUri)
			.createDt(LocalDateTime.now())
			.build());

		log.info(logMessage);

	}

}
