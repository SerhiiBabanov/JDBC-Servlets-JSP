package ua.goit.hw6.repository;

import ua.goit.hw6.config.DatabaseManagerConnector;
import ua.goit.hw6.model.dao.DeveloperDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeveloperRepository implements Repository<DeveloperDao> {
    private static final String INSERT = "insert into developers(name, username, salary) values(?,?,?)";
    private static final String DELETE = "delete from developers where name = ? and username = ? and salary = ?";
    private static final String SELECT_BY_ID = "select id, name, username, salary from developers where id = ?";
    private static final String UPDATE = "update developers set name = ?, username = ?, salary = ? where id = ? " +
            "returning id, name, username, salary";
    private static final String SELECT_ALL = "select id, name, username, salary from developers";
    private static final String SELECT_ALL_WITH_IDS = "select id, name, username, salary from developers " +
            "where id in (%s)";
    private static final String SELECT_ALL_WITH_SKILL_ID = "select id, developer_id, skill_id " +
            "from developer_skill_relation" +
            " where skill_id = ?";
    private static final String SELECT_ALL_WITH_SKILL_ID_LIST = "select distinct d.id, d.name, d.username, d.salary "+
    "from developers d " +
    "inner join developer_skill_relation dsr on d.id = dsr.developer_id " +
    "inner join skills s on dsr.skill_id = s.id " +
    "where s.id in (%s)";

    private static final String SELECT_ALL_WITH_PROJECT_ID = "select d.id, d.name, d.username, d.salary " +
    "from developers d " +
    "inner join project_developer_relation pdr on d.id = pdr.developer_id "+
    "inner join projects p on pdr.project_id = p.id " +
    "where p.id = ?";
    private final DatabaseManagerConnector manager;

    public DeveloperRepository(DatabaseManagerConnector manager) {
        this.manager = manager;
    }

    @Override
    public DeveloperDao save(DeveloperDao entity) {
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getUsername());
            statement.setInt(3, entity.getSalary());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating developer failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Developer not created");
        }
        return entity;
    }

    @Override
    public void delete(DeveloperDao entity) {
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getUsername());
            statement.setInt(3, entity.getSalary());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Delete developer failed");
        }
    }

    @Override
    public Optional<DeveloperDao> findById(Long id) {
        DeveloperDao developerDao = null;
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    developerDao = new DeveloperDao();
                    getEntity(resultSet, developerDao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select developer by id failed");
        }
        return Optional.ofNullable(developerDao);
    }

    @Override
    public DeveloperDao update(DeveloperDao entity) {
        DeveloperDao developerDao = new DeveloperDao();
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getUsername());
            statement.setInt(3, entity.getSalary());
            statement.setLong(4, entity.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    getEntity(resultSet, developerDao);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Developer not updated");
        }
        return developerDao;
    }

    @Override
    public List<DeveloperDao> findAll() {
        List<DeveloperDao> developerDaoList = new ArrayList<>();
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                DeveloperDao developerDao = new DeveloperDao();
                getEntity(resultSet, developerDao);
                developerDaoList.add(developerDao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select all developers failed");
        }
        return developerDaoList;
    }

    @Override
    public List<DeveloperDao> findByListOfID(List<Long> idList) {
        List<DeveloperDao> developerDaoList = new ArrayList<>();
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
                    DeveloperDao developerDao = new DeveloperDao();
                    getEntity(resultSet, developerDao);
                    developerDaoList.add(developerDao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select developers failed");
        }
        return developerDaoList;
    }
    public List<DeveloperDao> getBySkillId(Long skillId){
        List<DeveloperDao> developerDaoList = new ArrayList<>();
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_WITH_SKILL_ID)) {
            statement.setLong(1, skillId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    DeveloperDao developerDao = new DeveloperDao();
                    getEntity(resultSet, developerDao);
                    developerDaoList.add(developerDao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select developers with skill failed");
        }
        return developerDaoList;
    }
    public List<DeveloperDao> getBySkillIdList(List<Long> skillIdList){
        List<DeveloperDao> developerDaoList = new ArrayList<>();
        String stmt = String.format(SELECT_ALL_WITH_SKILL_ID_LIST,
                skillIdList.stream()
                        .map(v -> "?")
                        .collect(Collectors.joining(", ")));
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(stmt)) {
            int index = 1;
            for( Long id : skillIdList ) {
                statement.setLong(  index++, id );}
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    DeveloperDao developerDao = new DeveloperDao();
                    getEntity(resultSet, developerDao);
                    developerDaoList.add(developerDao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select developers with skills failed");
        }
        return developerDaoList;
    }
    public List<DeveloperDao> getByProjectId(Long projectId){
        List<DeveloperDao> developerDaoList = new ArrayList<>();
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_WITH_PROJECT_ID)) {
            statement.setLong(1, projectId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    DeveloperDao developerDao = new DeveloperDao();
                    getEntity(resultSet, developerDao);
                    developerDaoList.add(developerDao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select developers with project failed");
        }
        return developerDaoList;
    }

    protected static void getEntity(ResultSet resultSet, DeveloperDao developerDao) throws SQLException {
        developerDao.setId(resultSet.getLong("id"));
        developerDao.setName(resultSet.getString("name"));
        developerDao.setUsername(resultSet.getString("username"));
        developerDao.setSalary(resultSet.getInt("salary"));
    }

}
