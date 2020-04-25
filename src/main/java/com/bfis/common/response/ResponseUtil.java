package com.bfis.common.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseUtil {

	public static final Logger LOGGER = LoggerFactory.getLogger(ResponseUtil.class);
	
	public static Response runInVoidTemplate(ThrowingAction action) {
		return runInVoidTemplate(() -> {action.execute();return null;},Response.OK);
	}

	public static <T> ItemResponse<T> runInItemTemplate(Callable<T> action) {
		return runInItemTemplate(action,Response.OK);
	}
	
	public static Response runInVoidTemplate(Callable<Void> action,Response checkResult) {
		try {
			if (checkResult.getType() == ResponseType.OK) {
				action.call();
			}
			return checkResult;
		} catch (Exception e) {
			LOGGER.error("Run in void template ",e);
			return new Response(e);
		}
	}

	
	
	public static <T> ItemResponse<T> runInItemTemplate(Callable<T> action,Response checkResult) {
		try {
			if (checkResult.getType() == ResponseType.OK) {
				return new ItemResponse<>(action.call());
			}
			return new ItemResponse<>(ResponseType.ERROR,"");
		} catch(Exception e) {
			LOGGER.error("Run in item template",e);
			return new ItemResponse<>(e);
		}
	}
	public static <T> ItemsResponse<T> runInMultiTemplateWithCollection(Callable<Collection<T>> action) {
		return runInMultiTemplate(() -> new ArrayList<>(action.call()));
	}
	
	public static <T> ItemsResponse<T> runInMultiTemplate(Callable<List<T>> action) {
		try {
			return new ItemsResponse<>(action.call());
		} catch(Exception e) {
			LOGGER.error("Run in multi template ",e);
			return new ItemsResponse<>(e);
		}
	}
	
	public static <T> ItemResponse<T> createRuntimeExceptionResponse(String message) {
		return new ItemResponse<T>(new RuntimeException(message));
	}
	public static <T> ItemsResponse<T> createRuntimeExceptionResponseForItems(String message) {
		return new ItemsResponse<T>(new RuntimeException(message));
	}
	public static Response runIfOkTemplate(Response response,Runnable action) {
		if(response.responseIsOk()) {
			action.run();
		}
		return response;
	}

	public static <T> ItemResponse<T> runInItemResponseTryCatch(Callable<T> action) {
		try {
			return new ItemResponse<T>(action.call());
		} catch (Exception e) {
			return new ItemResponse<T>(e);
		}
	}

	
}
