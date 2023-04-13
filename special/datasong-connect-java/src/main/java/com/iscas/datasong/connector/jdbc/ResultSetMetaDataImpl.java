package com.iscas.datasong.connector.jdbc;

import com.iscas.datasong.client.jdbc.Field;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/8 9:45
 * @since jdk11
 */
@SuppressWarnings({"rawtypes", "unused"})
public class ResultSetMetaDataImpl implements ResultSetMetaData {
    private final List<ColumnMetaData> columns = new ArrayList<>();

    public ResultSetMetaDataImpl(List<String> headers) {
        for (String column : headers) {
            ColumnMetaData columnMetaData = new ColumnMetaData();
            columnMetaData.setColumnLabel(column);
            columnMetaData.setColumnName(column);
            if (Objects.equals("_id", column)) {
                columnMetaData.setColumnType(Types.VARCHAR);
            }
            this.columns.add(columnMetaData);
        }
    }

    @SuppressWarnings("unused")
    public ResultSetMetaDataImpl(List<String> headers, List<Field> fields) {
        if (null != fields) {
            for (Field field : fields) {
                ColumnMetaData columnMetaData = new ColumnMetaData();
                columnMetaData.setColumnLabel(field.getName());
                columnMetaData.setColumnName(field.getName());
                columnMetaData.setColumnType(field.getType());
                columnMetaData.setColumnTypeName(field.getTypeName());
                columnMetaData.setNullable(field.getIsNullable());
                columnMetaData.setPrecision(field.getPrecision());
                columnMetaData.setScale(field.getScale());
                columnMetaData.setSigned(field.isSigned());
                this.columns.add(columnMetaData);
            }
        }
    }

    public List<ColumnMetaData> getColumns() {
        return this.columns;
    }

    public int findColumn(String columnName) throws SQLException {
        for(int i = 0; i < this.columns.size(); ++i) {
            ColumnMetaData column = this.columns.get(i);
            if (column.getColumnName().equals(columnName)) {
                return i + 1;
            }
        }

        throw new SQLException("column '" + columnName + "' not found.");
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return this.isWrapperFor(iface) ? (T) this : null;
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return iface != null && iface.isAssignableFrom(this.getClass());
    }

    public int getColumnCount() throws SQLException {
        return this.columns.size();
    }

    public boolean isAutoIncrement(int column) throws SQLException {
        return this.getColumn(column).isAutoIncrement();
    }

    public boolean isCaseSensitive(int column) throws SQLException {
        return this.getColumn(column).isCaseSensitive();
    }

    public boolean isSearchable(int column) throws SQLException {
        return this.getColumn(column).isSearchable();
    }

    public boolean isCurrency(int column) throws SQLException {
        return this.getColumn(column).isCurrency();
    }

    public int isNullable(int column) throws SQLException {
        return this.getColumn(column).getNullable();
    }

    public boolean isSigned(int column) throws SQLException {
        return this.getColumn(column).isSigned();
    }

    public int getColumnDisplaySize(int column) throws SQLException {
        return this.getColumn(column).getColumnDisplaySize();
    }

    public String getColumnLabel(int column) throws SQLException {
        return this.getColumn(column).getColumnLabel();
    }

    public ColumnMetaData getColumn(int column) {
        return this.columns.get(column - 1);
    }

    public String getColumnName(int column) throws SQLException {
        return this.getColumn(column).getColumnName();
    }

    public String getSchemaName(int column) throws SQLException {
        return this.getColumn(column).getSchemaName();
    }

    public int getPrecision(int column) throws SQLException {
        return this.getColumn(column).getPrecision();
    }

    public int getScale(int column) throws SQLException {
        return this.getColumn(column).getScale();
    }

    public String getTableName(int column) throws SQLException {
        return this.getColumn(column).getTableName();
    }

    public String getCatalogName(int column) throws SQLException {
        return this.getColumn(column).getCatalogName();
    }

    public int getColumnType(int column) throws SQLException {
        return this.getColumn(column).getColumnType();
    }

    public String getColumnTypeName(int column) throws SQLException {
        return this.getColumn(column).getColumnTypeName();
    }

    public boolean isReadOnly(int column) throws SQLException {
        return this.getColumn(column).isReadOnly();
    }

    public boolean isWritable(int column) throws SQLException {
        return this.getColumn(column).isWritable();
    }

    public boolean isDefinitelyWritable(int column) throws SQLException {
        return this.getColumn(column).isDefinitelyWritable();
    }

    public String getColumnClassName(int column) throws SQLException {
        return this.getColumn(column).getColumnClassName();
    }

    @SuppressWarnings("unused")
    public static class ColumnMetaData {
        private boolean autoIncrement = false;
        private boolean caseSensitive;
        private boolean searchable;
        private boolean currency;
        private int nullable = 0;
        private boolean signed;
        private int columnDisplaySize;
        private String columnLabel;
        private String columnName;
        private String schemaName;
        private int precision;
        private int scale;
        private String tableName;
        private String catalogName;
        private int columnType;
        private String columnTypeName;
        private boolean readOnly;
        private boolean writable;
        private boolean definitelyWritable;
        private String columnClassName;

        public ColumnMetaData() {
        }

        public boolean isAutoIncrement() {
            return this.autoIncrement;
        }

        public void setAutoIncrement(boolean autoIncrement) {
            this.autoIncrement = autoIncrement;
        }

        public boolean isCaseSensitive() {
            return this.caseSensitive;
        }

        public void setCaseSensitive(boolean caseSensitive) {
            this.caseSensitive = caseSensitive;
        }

        public boolean isSearchable() {
            return this.searchable;
        }

        public void setSearchable(boolean searchable) {
            this.searchable = searchable;
        }

        public boolean isCurrency() {
            return this.currency;
        }

        public void setCurrency(boolean currency) {
            this.currency = currency;
        }

        public int getNullable() {
            return this.nullable;
        }

        public void setNullable(int nullable) {
            this.nullable = nullable;
        }

        public boolean isSigned() {
            return this.signed;
        }

        public void setSigned(boolean signed) {
            this.signed = signed;
        }

        public int getColumnDisplaySize() {
            return this.columnDisplaySize;
        }

        public void setColumnDisplaySize(int columnDisplaySize) {
            this.columnDisplaySize = columnDisplaySize;
        }

        public String getColumnLabel() {
            return this.columnLabel;
        }

        public void setColumnLabel(String columnLabel) {
            this.columnLabel = columnLabel;
        }

        public String getColumnName() {
            return this.columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public String getSchemaName() {
            return this.schemaName;
        }

        public void setSchemaName(String schemaName) {
            this.schemaName = schemaName;
        }

        public int getPrecision() {
            return this.precision;
        }

        public void setPrecision(int precision) {
            this.precision = precision;
        }

        public int getScale() {
            return this.scale;
        }

        public void setScale(int scale) {
            this.scale = scale;
        }

        public String getTableName() {
            return this.tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getCatalogName() {
            return this.catalogName;
        }

        public void setCatalogName(String catalogName) {
            this.catalogName = catalogName;
        }

        public int getColumnType() {
            return this.columnType;
        }

        public void setColumnType(int columnType) {
            this.columnType = columnType;
        }

        public String getColumnTypeName() {
            return this.columnTypeName;
        }

        public void setColumnTypeName(String columnTypeName) {
            this.columnTypeName = columnTypeName;
        }

        public boolean isReadOnly() {
            return this.readOnly;
        }

        public void setReadOnly(boolean readOnly) {
            this.readOnly = readOnly;
        }

        public boolean isWritable() {
            return this.writable;
        }

        public void setWritable(boolean writable) {
            this.writable = writable;
        }

        public boolean isDefinitelyWritable() {
            return this.definitelyWritable;
        }

        public void setDefinitelyWritable(boolean definitelyWritable) {
            this.definitelyWritable = definitelyWritable;
        }

        public String getColumnClassName() {
            return this.columnClassName;
        }

        public void setColumnClassName(String columnClassName) {
            this.columnClassName = columnClassName;
        }
    }
}
