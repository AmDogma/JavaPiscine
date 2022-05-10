package edu.school21.repositiries;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        try (Connection cn = dataSource.getConnection();
             Statement st = cn.createStatement())
        {
            ResultSet resultSet = st.executeQuery("SELECT * FROM products");
            while (resultSet.next()) {
                list.add(new Product(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Optional<Product> findById(Long id) {
        String command = "SELECT * FROM products WHERE id = " + id;
        try (Connection cn = dataSource.getConnection();
             Statement st = cn.createStatement())
        {
            ResultSet resultSet = st.executeQuery(command);
            if (resultSet.next())
                return Optional.of(new Product(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getInt(3)));
            else
                return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(Product product) {
        try (Connection cn = dataSource.getConnection();
             PreparedStatement preparedStatement = cn.prepareStatement("UPDATE" +
                     " products SET name = ?, price = ? WHERE id = ?"))
        {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Product product) {
        try (Connection cn = dataSource.getConnection();
             PreparedStatement preparedStatement = cn.prepareStatement("INSERT INTO" +
                     " products VALUES(?, ?, ?);"))
        {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection cn = dataSource.getConnection();
             PreparedStatement preparedStatement = cn.prepareStatement("DELETE FROM" +
                     " products WHERE id = ?"))
        {
            preparedStatement.setInt(1, Math.toIntExact(id));
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
