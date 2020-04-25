package com.bfis.common.response;

import java.util.List;
import java.util.function.Supplier;

public class ItemsResponse<T> extends Response {

	private List<T> items;

	public ItemsResponse(List<T> items) {
		super();
		this.items = items;
	}

	public ItemsResponse(Exception exception) {
		super(exception);
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public List<T> orElse(List<T> defaultItems) {
		return responseIsOk() ? getItems() : defaultItems;
	}
	public List<T> orElseGet(Supplier<List<T>> defaultItemsSupplier) {
		return responseIsOk() ? getItems() : defaultItemsSupplier.get();
	}
	
	@Override
	public String toString() {
		return "ItemsResponse [items=" + items + ", type=" + type + ", message=" + message + ", exceptionType="
				+ exceptionType + ", data=" + data + "]";
	}
}
