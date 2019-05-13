package com.baord.boardpage;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
	Object data[][] = new Object[1][1];
	String[] columnName =  new String[1];
	NoticeBoard noticeBoard;

	public TableModel(NoticeBoard noticeBoard) {
		this.noticeBoard = noticeBoard;
	}

	public int getColumnCount() {

		return columnName.length;
	}

	@Override
	public int getRowCount() {

		return data.length;
	}

	@Override
	public String getColumnName(int col) {

		return columnName[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

}
