package com.bfis.common;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;


public class Utils {
	
	
	
	public static List<Object> iterableAsList(Iterable<Object> i){
		return StreamSupport.stream(i.spliterator(), false)
			    .collect(Collectors.toList());
	}
	
	public static void doAndConvertToRuntime(ThrowingAction action) {
		doAndConvertToRuntime(() -> {
			action.execute();
			return null;
		});
	}

	public static <T> T doAndConvertToRuntime(Callable<T> action) {
		try {
			return action.call();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static boolean nullOrEmpty(Object object) {
		if (object == null)
			return true;
		if (object instanceof Collection)
			return ((Collection<?>) object).isEmpty();
		if (object instanceof String)
			return ((String) object).length() == 0;
		if (object instanceof Map)
			return ((Map<?, ?>) object).isEmpty();
		if(object.getClass().isArray()) {
			return Array.getLength(object) == 0;
		}
		return false;
	}
	
	public static void throwIfCondition(boolean condition, Supplier<RuntimeException> thrower) {
		if (condition)
			throw thrower.get();
	}
	
	public static boolean notNullAndNotEmpty(Object object) {
		return !nullOrEmpty(object);
	}
	
	public static <T> List<T> asList(Iterable<T> items){
		 return StreamSupport.stream(items.spliterator(), false)
		    .collect(Collectors.toList());
	}
	
	public static void requireNonNullOrEmpty(Object object, String errorMessage) {
		if (nullOrEmpty(object)) {
			throw new InvalidParameterException(errorMessage);
		}
	}
	
	public static boolean objectIn(Object value, Collection<? extends Object> values) {
		if (nullOrEmpty(values))
			return false;
		for (Object v : values) {
			if (v == value || (v != null && value != null && v.equals(value)))
				return true;
		}
		return false;
	}
	
	public static void serveFile(InputStream stream,String fileName,String mimeType,HttpServletResponse response) throws Exception {
		serveFile(stream, fileName, mimeType, response, Optional.empty());
	}
	
	public static  void serveFile(InputStream stream,
			String fileName,
			String mimeType,
			HttpServletResponse response,
			Optional<Integer> contentLength) throws Exception {
		serveFile(stream, fileName, mimeType, response,contentLength,true);
	}


	
	public static  void serveFile(InputStream stream,
			String fileName,
			String mimeType,
			HttpServletResponse response,
			Optional<Integer> contentLength,
			boolean asAttachment) throws Exception {
		ServletOutputStream outputStream =null;
		try {
			if(asAttachment) {
				response.setHeader("Content-Disposition", "attachment; filename= " +fileName);
			}
			response.setContentType(mimeType);
			contentLength.ifPresent(response::setContentLength);
			outputStream=response.getOutputStream();
			IOUtils.copy(stream, outputStream);
		} finally {
			outputStream.flush();
			outputStream.close();
		}
	
	
	}








}

