package com.bfis.common.response;


import java.util.function.Function;
import java.util.function.Supplier;

public class ItemResponse<T> extends Response {

	@SuppressWarnings("rawtypes")
	private static final ItemResponse<?> ERROR = new ItemResponse() {
		{
			setType(ResponseType.ERROR);
		}
	
		@Override
		public boolean responseIsOk() {
			return false;
		}
		
	};
	
	
	private T item;
	
	public ItemResponse() {
		super();
	}
	
	public ItemResponse(ResponseType type, String message) {
		super(type, message);
	}

	public ItemResponse(Exception exception) {
		super(exception);
	}

	public ItemResponse(T item) {
		this.item = item;
	}

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}
	public T orElse(T defaultItem) {
		return responseIsOk() ? getItem() :defaultItem;
	}
	public T orElseGet(Supplier<T> defaultItemSupplier) {
		return responseIsOk() ? getItem() :  defaultItemSupplier.get();
	}
	public T orElseThrow(Supplier<RuntimeException> exception) {
		if(responseIsOk())
			return getItem();
		throw exception.get();
	}
	public <X> ItemResponse<X> map(Function<T,X> mapper) {
		if(responseIsOk()) {
			return new ItemResponse<>(mapper.apply(this.getItem()));
		} 
		return new ItemResponse<>(type,message);
	}

	@SuppressWarnings("unchecked")
	public static <T> ItemResponse<T> error() {
		return (ItemResponse<T>) ERROR;
	}


}
