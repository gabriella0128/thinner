package com.nns.thinner.common.logging;

import java.io.IOException;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RequiredArgsConstructor
public class LoggingFilter extends OncePerRequestFilter {

	private static final AtomicLong counter = new AtomicLong(0);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		String traceId = generateTraceId();

		doFilterWrapped(new RequestWrapper(request), new ContentCachingResponseWrapper(response), filterChain, traceId);

	}

	protected void doFilterWrapped(HttpServletRequestWrapper request, ContentCachingResponseWrapper response, FilterChain filterChain, String traceId) throws
		IOException, ServletException {
		try {
			logRequest(request, response);
			filterChain.doFilter(request, response);

		} finally {
			if(!isAsyncDispatch(request)) {
				logResponse(request, response);
				response.copyBodyToResponse();
			}
		}
	}

	private void logRequest(HttpServletRequestWrapper request, ContentCachingResponseWrapper response) {


	}

	private void logResponse(HttpServletRequestWrapper request, ContentCachingResponseWrapper response) {

	}

	private void log(){
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();


	}


	public static String generateTraceId() {
		long currentTimeMillis = Instant.now().toEpochMilli();
		long sequence = counter.getAndIncrement();
		return String.format("%d-%d", currentTimeMillis, sequence);
	}



}
