package rs.raf.demo.repositories.category;

import rs.raf.demo.entities.Category;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCategoryRepository extends MySqlAbstractRepository implements CategoryRepository {

    @Override
    public List<Category> allCategory() {
        List<Category> categoryList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from category");

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");

                Category category = new Category(name, description);
                categoryList.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return categoryList;
    }

    @Override
    public Category findCategory(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Category category = null;

        try {
            connection = this.newConnection();
            //get user by username
            preparedStatement = connection.prepareStatement("SELECT * FROM category where name like ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            //user
            if (resultSet.next()) {
                String name1 = resultSet.getString("name");
                String description = resultSet.getString("description");

                category = new Category(name1, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return category;
    }

    @Override
    public Category addCategory(Category category) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            String[] generatedColumns = {"name"};

            preparedStatement = connection.prepareStatement("select * from category where name = ? ");
            preparedStatement.setString(1, category.getName());
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                preparedStatement = connection.prepareStatement("INSERT INTO category (name, description) VALUES (?,?)", generatedColumns);
                preparedStatement.setString(1, category.getName());
                preparedStatement.setString(2, category.getDescription());

                preparedStatement.executeUpdate();
                resultSet = preparedStatement.getGeneratedKeys();

            } else category = null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return category;
    }

    @Override
    public String deleteCategory(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM category where name = ?");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return name;
    }

    @Override
    public Category updateCategory(Category category, String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            if (!(name.equals(category.getName()))) {
                preparedStatement = connection.prepareStatement("select * from category where name = ? ");
                preparedStatement.setString(1, category.getName());
                resultSet = preparedStatement.executeQuery();
            }

            if (resultSet == null || !resultSet.next() || name.equals(category.getName())) {
                preparedStatement = connection.prepareStatement("update category set name = ?, description = ? where name = ?");
                preparedStatement.setString(1, category.getName());
                preparedStatement.setString(2, category.getDescription());
                preparedStatement.setString(3, name);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                this.closeResultSet(resultSet);
            }
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return category;
    }

}
