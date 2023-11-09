package com.SOEN387.DAOs;

import com.SOEN387.configs.DatabaseConnection;
import com.SOEN387.models.Order;
import com.SOEN387.models.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
	private Connection connection;

	public OrderDAO() {
		connection = DatabaseConnection.getConnection();
	}

	public List<Order> getAllOrders() {
		List<Order> orders = new ArrayList<>();
		String query = "SELECT id, user_id, order_date, total_price, status FROM orders";

		try (PreparedStatement statement = connection.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				Order order = new Order(resultSet.getInt("id"), resultSet.getInt("user_id"),
						resultSet.getTimestamp("order_date"), resultSet.getDouble("total_price"),
						resultSet.getString("status"));

				order.setOrderItems(getOrderItemsByOrderId(order.getId()));
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	public List<OrderItem> getOrderItemsByOrderId(int orderId) {
		List<OrderItem> orderItems = new ArrayList<>();
		String query = "SELECT id, order_id, product_sku, quantity, price FROM order_items WHERE order_id = ?";

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, orderId);

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					OrderItem item = new OrderItem(resultSet.getInt("id"), orderId, resultSet.getString("product_sku"),
							resultSet.getInt("quantity"), resultSet.getDouble("price"));
					orderItems.add(item);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderItems;
	}

	public void createOrder(Order order) {
		String query = "INSERT INTO orders (user_id, total_price, status) VALUES (?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(query,
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			statement.setInt(1, order.getUserId());
			statement.setDouble(2, order.getTotalPrice());
			statement.setString(3, order.getStatus());

			int affectedRows = statement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating order failed, no rows affected.");
			}

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					order.setId(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Creating order failed, no ID obtained.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateOrder(Order order) {
		String query = "UPDATE orders SET user_id = ?, total_price = ?, status = ? WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, order.getUserId());
			statement.setDouble(2, order.getTotalPrice());
			statement.setString(3, order.getStatus());
			statement.setInt(4, order.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteOrder(int orderId) {
		String query = "DELETE FROM orders WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, orderId);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Order getOrderById(int orderId) {
		String query = "SELECT id, user_id, order_date, total_price, status FROM orders WHERE id = ?";
		Order order = null;
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, orderId);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					order = new Order(resultSet.getInt("id"), resultSet.getInt("user_id"),
							resultSet.getTimestamp("order_date"), resultSet.getDouble("total_price"),
							resultSet.getString("status"));

					order.setOrderItems(getOrderItemsByOrderId(order.getId()));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT id, user_id, order_date, total_price, status FROM orders WHERE user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = new Order(
                        resultSet.getInt("id"),
                        userId,
                        resultSet.getTimestamp("order_date"),
                        resultSet.getDouble("total_price"),
                        resultSet.getString("status")
                    );
                    order.setOrderItems(getOrderItemsByOrderId(order.getId()));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public void shipOrder(int orderId, String trackingNumber) {
        String query = "UPDATE orders SET status = 'Shipped', tracking_number = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, trackingNumber);
            statement.setInt(2, orderId);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Shipping order failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
