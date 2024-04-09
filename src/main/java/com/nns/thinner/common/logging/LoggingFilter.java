package com.nns.thinner.common.logging;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
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

	private static final List<MediaType> VISIBLE_TYPES = Arrays.asList(
		MediaType.valueOf("text/*"),
		MediaType.APPLICATION_FORM_URLENCODED,
		MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_XML,
		MediaType.valueOf("application/*+json"),
		MediaType.valueOf("application/*+xml"),
		MediaType.MULTIPART_FORM_DATA
	);
	private final LogEventPublisher logEventPublisher;

	public static String generateTraceId() {
		long currentTimeMillis = Instant.now().toEpochMilli();

		return String.format("%d", currentTimeMillis);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		String traceId = generateTraceId();

		doFilterWrapped(new ContentCachingRequestWrapper(request), new ContentCachingResponseWrapper(response),
			filterChain, traceId);

	}

	protected void doFilterWrapped(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response,
		FilterChain filterChain, String traceId) throws
		IOException, ServletException {
		try {
			logRequest(request, traceId);
			filterChain.doFilter(request, response);
		} finally {
			logResponse(request, response, traceId);
			response.copyBodyToResponse();

		}
	}

	private void logRequest(ContentCachingRequestWrapper request, String traceId) throws
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

		byte[] content = request.getContentAsByteArray();

		String contentType = request.getContentType();
		MediaType mediaType = MediaType.valueOf(contentType);

		boolean visible = VISIBLE_TYPES.stream().anyMatch(visibleType -> visibleType.includes(mediaType));

		String contentString;

		JsonNode bodyNode = mapper.createObjectNode();

		if (visible) {
			if (MediaType.APPLICATION_JSON_VALUE.equals(contentType)) {
				bodyNode = mapper.readTree(request.getInputStream());
			}
			contentString = new String(content, request.getCharacterEncoding());

		} else {
			contentString = content.length + " bytes Content";
		}

		rootNode.put("body", contentString);

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

	private void logResponse(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response,
		String traceId) throws
		IOException {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();

		String queryString = request.getQueryString();
		String queryStringUri =
			queryString == null ? request.getRequestURI() : request.getRequestURI() + "?" + queryString;

		rootNode.put("httpMessage", "response");
		rootNode.put("method", request.getMethod());
		rootNode.put("uri", queryStringUri);

		byte[] content = response.getContentAsByteArray();

		String contentType = response.getContentType();

		String contentString;

		JsonNode bodyNode = mapper.createObjectNode();

		if (MediaType.APPLICATION_JSON_VALUE.equals(contentType)) {
			bodyNode = mapper.readTree(response.getContentInputStream());
		}

		contentString = new String(content, StandardCharsets.UTF_8);

		rootNode.put("body", contentString);

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
