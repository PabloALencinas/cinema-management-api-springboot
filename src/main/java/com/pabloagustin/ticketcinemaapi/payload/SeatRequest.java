package com.pabloagustin.ticketcinemaapi.payload;

import com.pabloagustin.ticketcinemaapi.models.ESeatStatus;

public class SeatRequest {

	private Long id;

	private int rows;

	private int columns;

	private ESeatStatus status;

	public SeatRequest(Long id, ESeatStatus status){}

	public SeatRequest(Long id, int rows, int columns, ESeatStatus status) {
		this.id = id;
		this.rows = rows;
		this.columns = columns;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public ESeatStatus getStatus() {
		return status;
	}

	public void setStatus(ESeatStatus status) {
		this.status = status;
	}
}
