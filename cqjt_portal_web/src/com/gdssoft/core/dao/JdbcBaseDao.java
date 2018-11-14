package com.gdssoft.core.dao;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcBaseDao {
	public Log log = LogFactory.getLog(this.getClass());
	@Autowired
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	/**
	 * @param jdbcTemplate the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
