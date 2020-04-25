package com.bfis.common.response;



public class Response {
	
	
	
	public static final Response OK = createUnmodificableResponse(ResponseType.OK);
	public static final Response ERROR = createUnmodificableResponse(ResponseType.ERROR);
	protected ResponseType type;
	protected String message;
	protected String exceptionType;
	protected Object data;
	
	/* Constructors */
	
	public Response() {
		this(ResponseType.OK);
	}
	
	public Response(ResponseType type, Exception exception) {
		this(type);
		this.exceptionType = exception.getClass().getSimpleName();

	}
	
	public Response(ResponseType type, String message) {
		super();
		this.type = type;
		this.message = message;
	}
	
	public Response(ResponseType type, String message, Object data) {
		super();
		this.type = type;
		this.message = message;
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Response(ResponseType type) {
		this.type = type;
	}
	
	public Response(Exception exception) {
		this(ResponseType.ERROR, exception.getLocalizedMessage(), exception);
	}
	
	public Response(String message) {
		this(ResponseType.OK);
		this.message = message;
	}
	
	public Response(ResponseType type, String message, Exception Exception) {
		this(type, Exception);
		this.message = message;
	}
	
	public Response(String message, Exception exception) {
		this(ResponseType.ERROR, message, exception);
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	
	/**
	 * This method was named "isOk", but getter method without field with that name, cause json serialization error.
	 * @author atomaja
	 */
	public boolean responseIsOk() {
		return ResponseType.OK == getType();
	}
	
	public Response ifOk(Runnable action) {
		if(responseIsOk())
			action.run();
		return this;
	}
	public Response ifError(Runnable action) {
		if(!responseIsOk())
			action.run();
		return this;
	}
	public void throwIfError() {
		if(!responseIsOk())
			throw new RuntimeException(getMessage());
	}
	
	public ResponseType getType() {
		return type;
	}

	public void setType(ResponseType type) {
		this.type = type;
	}
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Response [type=" + type + ", message=" + message + "]";
	}	
	
	public static Response createUnmodificableResponse(ResponseType newType) {
		return new Response() {
			private final ResponseType type = newType;

			
			@Override
			public Object getData() {
				return null;
			}


			@Override
			public void setData(Object data) {
				
			}


			@Override
			public String getExceptionType() {
				return null;
			}


			@Override
			public void setExceptionType(String exceptionType) {

			}


			@Override
			public boolean responseIsOk() {
				return newType == ResponseType.OK;
			}


			@Override
			public void setType(ResponseType type) {
			}


			@Override
			public String getMessage() {
				return null;
			}


			@Override
			public void setMessage(String message) {

			}


			@Override
			public ResponseType getType() {
				return type;
			}
			
		};
	}
}