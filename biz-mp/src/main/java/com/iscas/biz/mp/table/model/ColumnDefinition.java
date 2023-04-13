package com.iscas.biz.mp.table.model;

import lombok.Data;

@Data
public class ColumnDefinition {
	private Integer id;
	private String	tableDefinition;
	private String field;
	private String	header;
	private boolean editable;
	private boolean sortable;
	private String type;
	private boolean search;
	private String searchType;
	private boolean link;
	private boolean addable;
	private boolean hidden;
	private String refTable;
	private String refColumn;
	private String refValue;
	private boolean required;
	private String reg;
	private Integer minLength = -1;
	private Integer maxLength = -1;
	private boolean distinct;
	private String selectUrl;
}
