package ua.goit.hw6.repository;

import ua.goit.hw6.config.DatabaseManagerConnector;
import ua.goit.hw6.model.dao.CompanyDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CompanyRepository implements Repository<CompanyDao> {
    private static final String INSERT = "insert into companies(name, country) values(?,?)";
    private static final String DELETE = "delete from companies where name = ? and country = ?";
    private static final String SELECT_BY_ID = "select id, name, country from companies where id = ?";
    private static final String UPDATE = "update companies set name = ?, country = ? where id = ? " +
            "returning id, name, country";
    private static final String SELECT_ALL = "select id, name, country from companies";
    private static final String SELECT_ALL_WITH_IDS = "select id, name, country from companies where id in (%s)";
    private final DatabaseManagerConnector manager;

    public CompanyRepository(DatabaseManagerConnector manager) {
        this.manager = manager;
    }

    @Override
    public CompanyDao save(CompanyDao entity) {
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getCountry());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating company failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Company not created");
        }
        return entity;
    }

    @Override
    public void delete(CompanyDao entity) {
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getCountry());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Delete company failed");
        }
    }

    @Override
    public Optional<CompanyDao> findById(Long id) {
        CompanyDao companyDao = null;
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    companyDao = new CompanyDao();
                    getEntity(resultSet, companyDao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select company by id failed");
        }
        return Optional.ofNullable(companyDao);
    }

    @Override
    public CompanyDao update(CompanyDao entity) {
        CompanyDao companyDao = new CompanyDao();
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getCountry());
            statement.setLong(3, entity.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    getEntity(resultSet, companyDao);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Company not updated");
        }
        return companyDao;
    }

    @Override
    public List<CompanyDao> findAll() {
        List<CompanyDao> companyDaoList = new ArrayList<>();
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                CompanyDao companyDao = new CompanyDao();
                getEntity(resultSet, companyDao);
                companyDaoList.add(companyDao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select all companies failed");
        }
        return companyDaoList;
    }
    @Override
    public List<CompanyDao> findByListOfID(List<Long> idList) {
        List<CompanyDao> companyDaoList = new ArrayList<>();
        String stmt = String.format(SELECT_ALL_WITH_IDS,
                idList.stream()
                        .map(v -> "?")
                        .collect(Collectors.joining(", ")));
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(stmt)) {
            int index = 1;
            for( Long id : idList ) {
                statement.setLong(  index++, id );}
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    CompanyDao companyDao = new CompanyDao();
                    getEntity(resultSet, companyDao);
                    companyDaoList.add(companyDao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select all companies failed");
        }
        return companyDaoList;
    }

    private void getEntity(ResultSet resultSet, CompanyDao companyDao) throws SQLException {
        companyDao.setId(resultSet.getLong("id"));
        companyDao.setName(resultSet.getString("name"));
        companyDao.setCountry(resultSet.getString("country"));
    }
}
