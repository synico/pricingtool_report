package com.avnet.pricingtool.reporting.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.avnet.pricingtool.reporting.model.PriceReport;

public class PriceReportRowMapper implements RowMapper<PriceReport> {

	@Override
	public PriceReport mapRow(ResultSet rs, int rowNum) throws SQLException {
		PriceReport pr = new PriceReport();
		
		pr.setAppl_date(rs.getDate("APPL_DATE"));
		pr.setScenario_1(rs.getLong("SCENARIO_1"));
		pr.setScenario_2(rs.getLong("SCENARIO_2"));
		pr.setScenario_3(rs.getLong("SCENARIO_3"));
		pr.setScenario_4(rs.getLong("SCENARIO_4"));
		pr.setScenario_5(rs.getLong("SCENARIO_5"));
		pr.setScenario_6(rs.getLong("SCENARIO_6"));
		pr.setScenario_7(rs.getLong("SCENARIO_7"));
		pr.setScenario_8(rs.getLong("SCENARIO_8"));
		pr.setComments(rs.getString("COMMENTS"));
		
		return pr;
	}
	
	

}
