package com.cx.blog.server.dto.response.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

@ApiModel
public class PageListViewData<T> {

	@ApiModelProperty("数据列表")
	private List<T> data;

	@ApiModelProperty("总记录数")
	private Integer total;

	@ApiModelProperty("总金额")
	private Long totalAmount;

	@ApiModelProperty
	private Map<String, String> params;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
}
