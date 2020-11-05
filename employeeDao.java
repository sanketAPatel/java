package com.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.model.employee;

public class employeeDao {

	public employeeDao() {
		super();
	}

	private String jdbcURL = "jdbc:mysql://localhost:3306/emp?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";


	private static final String INSERT_EMP_SQL = "INSERT INTO emp" + "(name, position, department) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_EMP_BY_ID = "select id,name, position, department from emp where id =?";
	private static final String SELECT_ALL_EMP = "select * from emp";
	private static final String DELETE_EMP_SQL = "delete from emp where id = ?;";
	private static final String UPDATE_EMP_SQL = "update emp set name = ?,position= ?, department=? where id = ?;";


	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}


	public void insertEmp(employee emp) throws SQLException {
		System.out.println(INSERT_EMP_SQL);

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMP_SQL)) {
			preparedStatement.setString(1, emp.getName());
			preparedStatement.setString(2, emp.getPosition());
			preparedStatement.setString(3, emp.getDepartment());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
			//e.printStackTrace();
		}
	}

	public employee selectEmp(int id) {
		employee emp = null;

		try (Connection connection = getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMP_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();


			while (rs.next()) {
				String name = rs.getString("name");
				String position = rs.getString("position");
				String department = rs.getString("department");
				emp = new employee(id, name, position, department);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return emp;
	}

	public List<employee> selectAllEmp() {


		List<employee> emp = new ArrayList<>();

		try (Connection connection = getConnection();


				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMP);) {
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();


			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String position = rs.getString("position");
				String department = rs.getString("department");
				emp.add(new employee(id, name, position, department));
			}
		} catch (SQLException e) {
			printSQLException(e);

		}
		return emp;
	}


	public boolean deleteEmp(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_EMP_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateEmp(employee emp) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_EMP_SQL);) {
			statement.setString(1, emp.getName());
			statement.setString(2, emp.getPosition());
			statement.setString(3, emp.getDepartment());
			statement.setInt(4, emp.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
