package com.bage.dao;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bage.domain.module.Customer;
import com.bage.utils.LogUtils;

@Repository
public class CustomerDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void createTable() {

		LogUtils.info("Creating table ... ");

		//jdbcTemplate.execute(" DROP TABLE customers IF EXISTS ");
		jdbcTemplate.execute(" CREATE TABLE customers(" + "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

		LogUtils.info("Creating table finish ");
	}

	public void insert() {
		LogUtils.info("start insert");
		// Split up the array of whole names into an array of first/last names
		Object[] splitUpNames = new Object[] { "John", "Woo" };

		// Uses JdbcTemplate's batchUpdate operation to bulk load data
		jdbcTemplate.update("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

		LogUtils.info("insert finish ");
	}

	public void batchInsert() {
		LogUtils.info("start batchInsert ... ");
		// Split up the array of whole names into an array of first/last names
		List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
				.map(name -> name.split(" ")).collect(Collectors.toList());
		// Use a Java 8 stream to print out each tuple of the list
		splitUpNames
				.forEach(name -> LogUtils.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

		// Uses JdbcTemplate's batchUpdate operation to bulk load data
		jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

		LogUtils.info("batchInsert finish ");
	}
	//@Transactional
	public void batchInsertTransactional() {
		// TODO 可以使用事务
		LogUtils.info("start batchInsert ... ");
		// Split up the array of whole names into an array of first/last names
		List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
				.map(name -> name.split(" ")).collect(Collectors.toList());
		// Use a Java 8 stream to print out each tuple of the list
		splitUpNames
		.forEach(name -> LogUtils.info(String.format("Inserting customer record for %s %s", name[0], name[1])));
		
		// Uses JdbcTemplate's batchUpdate operation to bulk load data
		jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);
		
		LogUtils.info("batchInsert finish ");
	}

	public List<Object> query() {
		LogUtils.info("Querying for customer records where first_name = 'Josh':");
		List<Object> list = jdbcTemplate
				.query("SELECT id, first_name, last_name FROM customers WHERE first_name = ?",
						new Object[] { "Josh" }, (rs, rowNum) -> new Customer(rs.getLong("id"),
								rs.getString("first_name"), rs.getString("last_name")));
		list.forEach(customer -> LogUtils.info(customer.toString()));

		LogUtils.info("query finish ");
		
		return list;
		
	}

	public List<Object> queryAll() {
		List<Object> list = jdbcTemplate
				.query("SELECT id, first_name, last_name FROM customers ", (rs, rowNum) -> new Customer(rs.getLong("id"),
								rs.getString("first_name"), rs.getString("last_name")));
		list.forEach(customer -> System.out.println(customer.toString()));
		
		
		return list;
		
	}
}
