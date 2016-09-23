package com.avnet.pricingtool.reporting;

import javax.sql.DataSource;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;

public class LoadData4PriceReport implements Tasklet {
	
	private DataSource dataSource;
	private String procedureName;
	

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}


	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		JdbcTemplate jdbc = new JdbcTemplate(getDataSource());
		jdbc.execute(getProcedureName());
		return RepeatStatus.FINISHED;
	}

}
