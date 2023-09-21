package com.example.demo;
import oracle.jdbc.OracleTypes;
import java.sql.*;

public class DatabaseConnection {
    private static final String url = "jdbc:oracle:thin:@//feenix-oracle.swin.edu.au:1521/DMS";
    private static final String user = "s103533680";
    private static final String password = "Rinie3baebae62003.";

    private static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("The JDBC class not found");
        } catch (SQLException e) {
            System.out.println("Cannot establish a connection to the database");
        }
        return null;
    }

    public static String callAddCustomerToDb(int custId, String custName) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            connection = getConnection();
            assert connection != null;
            connection.setAutoCommit(false);
            callableStatement = connection.prepareCall("{call ADD_CUST_TO_DB(?, ?)}");
            callableStatement.setInt(1, custId);
            callableStatement.setString(2, custName);
            callableStatement.execute();
            connection.commit();
            System.out.println("The transaction of adding a customer has been committed!");
            message = "Customer added OK";
        } catch (SQLException e) {
            try {
                System.err.println("The transaction of adding a customer is being rolled back!");
                connection.rollback();
            } catch (SQLException ignored) {}
            message = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        } finally {
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException ignored) {}
        }
        return message;
    }

    public static String callDeleteAllCustomerFromDB() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            assert connection != null;
            connection.setAutoCommit(false);
            // Prepare a CallableStatement to call the PL/SQL function
            callableStatement = connection.prepareCall("{? = call DELETE_ALL_CUSTOMERS_FROM_DB}");
            // Register the return value as an OUT parameter
            callableStatement.registerOutParameter(1, Types.INTEGER);
            // Execute the function
            callableStatement.execute();
            // Commit the transaction
            connection.commit();
            System.out.println("The transaction of deleting all customers has been committed!");
            message = callableStatement.getInt(1) + " rows deleted";
        } catch (SQLException e) {
            try {
                System.err.println("The transaction of deleting all customers is being rolled back!");
                connection.rollback();
            } catch (SQLException ignored) {}
            message = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        } finally {
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException ignored) {}
        }
        return message;
    }


    public static String callAddProductToDB(int prodId, String prodName, Float price) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            connection = getConnection();
            assert connection != null;
            connection.setAutoCommit(false);
            callableStatement = connection.prepareCall("{call ADD_PRODUCT_TO_DB(?, ?, ?)}");
            // Set the input parameters
            callableStatement.setInt(1, prodId);
            callableStatement.setString(2, prodName);
            callableStatement.setFloat(3, price);
            // Execute the procedure
            callableStatement.execute();
            // Commit the transaction
            connection.commit();
            System.out.println("The transaction of adding a product has been committed!");
            // If the procedure executed successfully, return a success message
            message = "Product added OK";
        } catch (SQLException e) {
            try {
                System.err.println("The transaction of adding a product is being rolled back!");
                connection.rollback();
            } catch (SQLException ignored) {}
            message = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        } finally {
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException ignored) {}
        }
        return message;
    }

    public static String callDeleteAllProductFromDB() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            assert connection != null;
            connection.setAutoCommit(false);
            // Prepare a CallableStatement to call the PL/SQL function
            callableStatement = connection.prepareCall("{? = call DELETE_ALL_PRODUCTS_FROM_DB}");
            // Register the return value as an OUT parameter
            callableStatement.registerOutParameter(1, Types.INTEGER);
            // Execute the function
            callableStatement.execute();
            // Commit the transaction
            connection.commit();
            System.out.println("The transaction of deleting all products has been committed!");
            message = callableStatement.getInt(1) + " rows deleted";
        } catch (SQLException e) {
            try {
                System.err.println("The transaction of deleting all products is being rolled back!");
                connection.rollback();
            } catch (SQLException ignored) {}
            message = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        } finally {
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException ignored) {}
        }
        return message;
    }

    //no commit
    public static String callGetCustomerStringFromDB(int custId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            // Prepare a CallableStatement to call the PL/SQL function
            assert connection != null;
            callableStatement = connection.prepareCall("{? = call GET_CUST_STRING_FROM_DB(?)}");
            // Register the return value as an OUT parameter
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            // Set the input parameter
            callableStatement.setInt(2, custId);
            // Execute the function
            callableStatement.execute();
            // Get the result
            message = callableStatement.getString(1);
        } catch (SQLException e) {
            message = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        } finally {
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException ignored) {}
        }
        return message;
    }

    //no commit
    public static String callGetProductStringFromDB(int prodId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            // Prepare a CallableStatement to call the PL/SQL function
            assert connection != null;
            callableStatement = connection.prepareCall("{? = call GET_PROD_STRING_FROM_DB(?)}");
            // Register the return value as an OUT parameter
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            // Set the input parameter
            callableStatement.setInt(2, prodId);
            // Execute the function
            callableStatement.execute();
            // Get the result
            message = callableStatement.getString(1);
        } catch (SQLException e) {
            message = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        } finally {
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException ignored) {}
        }
        return message;
    }

    public static String callUpdCustomerSalesYtdInDb(int custId, float amount) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            assert connection != null;
            connection.setAutoCommit(false);
            // Prepare a CallableStatement to call the PL/SQL procedure
            callableStatement = connection.prepareCall("{call UPD_CUST_SALESYTD_IN_DB(?, ?)}");
            // Set the input parameters
            callableStatement.setInt(1, custId);
            callableStatement.setFloat(2, amount);
            // Execute the procedure
            callableStatement.execute();
            // Commit the transaction
            connection.commit();
            System.out.println("The transaction of updating sales amount of a customer has been committed!");
            // If the procedure executed successfully, return a success message
            message = "Update OK";
        } catch (SQLException e) {
            try {
                System.err.println("The transaction of updating sales amount of a customer is being rolled back!");
                connection.rollback();
            } catch (SQLException ignored) {}
            message = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        } finally {
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException ignored) {}
        }
        return message;
    }

    public static String callUpdProdSalesYtdInDb(int prodId, float amount) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            assert connection != null;
            connection.setAutoCommit(false);
            // Prepare a CallableStatement to call the PL/SQL procedure
            callableStatement = connection.prepareCall("{call UPD_PROD_SALESYTD_IN_DB(?, ?)}");
            // Set the input parameters
            callableStatement.setInt(1, prodId);
            callableStatement.setFloat(2, amount);
            // Execute the procedure
            callableStatement.execute();
            // Commit the transaction
            connection.commit();
            System.out.println("The transaction of updating sales amount of a product has been committed!");
            // If the procedure executed successfully, return a success message
            message = "Update OK";
        } catch (SQLException e) {
            try {
                System.err.println("The transaction of updating sales amount of a product is being rolled back!");
                connection.rollback();
            } catch (SQLException ignored) {}
            message = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        } finally {
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException ignored) {}
        }
        return message;
    }

    public static String callUpdCustomerStatusInDb(int custId, String status) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            assert connection != null;
            connection.setAutoCommit(false);
            // Prepare a CallableStatement to call the PL/SQL procedure
            callableStatement = connection.prepareCall("{call UPD_CUST_STATUS_IN_DB(?, ?)}");
            // Set the input parameters
            callableStatement.setInt(1, custId);
            callableStatement.setString(2, status);
            // Execute the procedure
            callableStatement.execute();
            // Commit the transaction
            connection.commit();
            System.out.println("The transaction of updating a customer status has been committed!");
            // If the procedure executed successfully, return a success message
            message = "Update OK";
        } catch (SQLException e) {
            try {
                System.err.println("The transaction of updating a customer status is being rolled back!");
                connection.rollback();
            } catch (SQLException ignored) {}
            message = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        } finally {
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException ignored) {}
        }
        return message;
    }

    public static String callAddSimpleSaleToDb(int custId, int prodId, int quantity) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            assert connection != null;
            connection.setAutoCommit(false);
            // Prepare a CallableStatement to call the PL/SQL procedure
            callableStatement = connection.prepareCall("{call ADD_SIMPLE_SALE_TO_DB(?, ?, ?)}");
            // Set the input parameters
            callableStatement.setInt(1, custId);
            callableStatement.setInt(2, prodId);
            callableStatement.setInt(3, quantity);
            // Execute the procedure
            callableStatement.execute();
            // Commit the transaction
            connection.commit();
            // If the procedure executed successfully, return a success message
            message = "Added simple sale OK";
        } catch (SQLException e) {
            try {
                System.err.println("The transaction is being rolled back!");
                connection.rollback();
            } catch (SQLException ignored) {}
            message = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        } finally {
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException ignored) {}
        }
        return message;
    }

    //no commit
    public static String callSumCustomerSalesYtd() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            // Prepare a CallableStatement to call the PL/SQL function
            assert connection != null;
            callableStatement = connection.prepareCall("{? = call SUM_CUST_SALESYTD()}");
            // Register the return value as an OUT parameter
            callableStatement.registerOutParameter(1, Types.NUMERIC);
            // Execute the function
            callableStatement.execute();
            // If the function executed successfully, return a success message
            message = "All Customer Total: " + callableStatement.getFloat(1);
        } catch (SQLException e) {
            message = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        } finally {
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException ignored) {}
        }
        return message;
    }

/*    public static String callSumProductSalesYtd() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            // Prepare a CallableStatement to call the PL/SQL function
            assert connection != null;
            callableStatement = connection.prepareCall("{? = call SUM_PROD_SALESYTD_FROM_DB}");
            // Register the return value as an OUT parameter
            callableStatement.registerOutParameter(1, Types.NUMERIC);
            // Execute the function
            callableStatement.execute();
            // If the function executed successfully, return a success message
            message = "All Product Total: " + callableStatement.getFloat(1);
        }catch (SQLException e) {
            message = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        } finally {
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException ignored) {}
        }
        return message;
    }
 */

    //no commit
    public static String callGetAllCustomer() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            // Prepare a CallableStatement to call the PL/SQL function
            assert connection != null;
            callableStatement = connection.prepareCall("{? = call getEntity.getAllCust}");
            // register the return type
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            //execute the statement
            callableStatement.execute();
            ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
            StringBuilder strBuilder = new StringBuilder();
            while (resultSet.next()) {
                strBuilder.append("Custid: ");
                strBuilder.append(resultSet.getInt("CUSTID")).append(" ");
                strBuilder.append("Name: ");
                strBuilder.append(resultSet.getString("CUSTNAME")).append(" ");
                strBuilder.append("Status: ");
                strBuilder.append(resultSet.getString("STATUS")).append(" ");
                strBuilder.append("SalesYTD: ");
                strBuilder.append(resultSet.getFloat("SALES_YTD")).append("\n");
            }
            message = strBuilder.toString();
        }
        catch (SQLException e) {
            message = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        } finally {
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException ignored) {}
        }
        return message;
    }

    //no commit
    public static String callGetAllProduct() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            // Prepare a CallableStatement to call the PL/SQL function
            assert connection != null;
            callableStatement = connection.prepareCall("{? = call getEntity.getAllProd}");
            // register the return type
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            //execute the statement
            callableStatement.execute();
            ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
            StringBuilder strBuilder = new StringBuilder();
            while (resultSet.next()) {
                strBuilder.append("Prodid: ");
                strBuilder.append(resultSet.getInt("PRODID")).append(" ");
                strBuilder.append("Name: ");
                strBuilder.append(resultSet.getString("PRODNAME")).append(" ");
                strBuilder.append("Price: ");
                strBuilder.append(resultSet.getFloat("SELLING_PRICE")).append(" ");
                strBuilder.append("SalesYTD: ");
                strBuilder.append(resultSet.getFloat("SALES_YTD")).append("\n");
            }
            message = strBuilder.toString();
        }
        catch (SQLException e) {
            message = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        } finally {
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException ignored) {}
        }
        return message;
    }

    //no commit
    public static String callGetAllSale() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            // Prepare a CallableStatement to call the PL/SQL function
            assert connection != null;
            callableStatement = connection.prepareCall("{? = call getEntity.getAllSale}");
            // register the return type
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            //execute the statement
            callableStatement.execute();
            ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
            StringBuilder strBuilder = new StringBuilder();
            while (resultSet.next()) {
                strBuilder.append("Saleid: ");
                strBuilder.append(resultSet.getInt("SALEID")).append(" ");
                strBuilder.append("Custid: ");
                strBuilder.append(resultSet.getInt("CUSTID")).append(" ");
                strBuilder.append("Prodid: ");
                strBuilder.append(resultSet.getInt("PRODID")).append(" ");
                strBuilder.append("Date: ");
                strBuilder.append(resultSet.getDate("SALEDATE")).append(" ");
                strBuilder.append("Amount: ");
                strBuilder.append(resultSet.getInt("QTY") * resultSet.getFloat("PRICE")).append("\n");
            }
            message = strBuilder.toString();
        }catch (SQLException e) {
            message = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        } finally {
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException ignored) {}
        }
        return message;
    }

    public static String callAddComplexSale(int custId, int prodId, int quantity, String date) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            assert connection != null;
            connection.setAutoCommit(false);
            // Prepare a CallableStatement to call the PL/SQL procedure
            callableStatement = connection.prepareCall("{call ADD_COMPLEX_SALE_TO_DB(?, ?, ?, ?)}");
            // Set the input parameters
            callableStatement.setInt(1, custId);
            callableStatement.setInt(2, prodId);
            callableStatement.setInt(3, quantity);
            callableStatement.setString(4, date);
            // Execute the procedure
            callableStatement.execute();
            // Commit the transaction
            connection.commit();
            System.out.println("The transaction of adding a complex sale has been committed!");
            // If the procedure executed successfully, return a success message
            message = "Added Complex Sale OK";
        }catch (SQLException e) {
            try {
                System.err.println("The transaction of adding a complex sale is being rolled back!");
                connection.rollback();
            } catch (SQLException ignored) {}
            message = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        } finally {
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException ignored) {}
        }
        return message;
    }

    //no commit
    public static String callGetCountSales(int numberOfDays) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            // Prepare a CallableStatement to call the PL/SQL function
            assert connection != null;
            callableStatement = connection.prepareCall("{? = call COUNT_PRODUCT_SALES_FROM_DB(?)}");
            // Register the return value as an OUT parameter
            callableStatement.registerOutParameter(1, Types.INTEGER);
            // Set the input parameter
            callableStatement.setInt(2, numberOfDays);
            // Execute the function
            callableStatement.execute();
            // Get the result
            message = "Total number of sales: " + callableStatement.getInt(1);
        } catch (SQLException e) {
            message = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        } finally {
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException ignored) {}
        }
        return message;
    }

    public static String callDeleteAllSalesFromDB() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            assert connection != null;
            connection.setAutoCommit(false);
            // Prepare a CallableStatement to call the PL/SQL procedure
            callableStatement = connection.prepareCall("{call DELETE_ALL_SALES_FROM_DB}");
            // Execute the procedure
            callableStatement.execute();
            // Commit the transaction
            connection.commit();
            System.out.println("The transaction of deleting all sales has been committed!");
            message = "Deletion OK";
        } catch (SQLException e) {
            try {
                System.err.println("The transaction of deleting all sales is being rolled back!");
                connection.rollback();
            } catch (SQLException ignored) {}
            message = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        } finally {
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException ignored) {}
        }
        return message;
    }

    public static String callDeleteMinSale() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            assert connection != null;
            connection.setAutoCommit(false);
            // Prepare a CallableStatement to call the PL/SQL function
            callableStatement = connection.prepareCall("{ ? = call DELETE_SALE_FROM_DB}");
            // register the return type
            callableStatement.registerOutParameter(1, Types.INTEGER);
            // Execute the procedure
            callableStatement.execute();
            // Commit the transaction
            connection.commit();
            System.out.println("The transaction of deleting the minimum sale has been committed!");
            // If the function executed successfully, return a success message
            message = "Deleted Sale OK. Sale ID: " + callableStatement.getInt(1);
        } catch (SQLException e) {
            try {
                System.err.println("The transaction of deleting the minimum sale is being rolled back!");
                connection.rollback();
            } catch (SQLException ignored) {}
            message = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        } finally {
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException ignored) {}
        }
        return message;
    }

    public static String callDeleteACustomerFromDB(int custId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            assert connection != null;
            connection.setAutoCommit(false);
            // Prepare a CallableStatement to call the PL/SQL function
            callableStatement = connection.prepareCall("{call DELETE_CUSTOMER(?)}");
            // Register the return value as an OUT parameter
            callableStatement.setInt(1, custId);
            // Execute the function
            callableStatement.execute();
            // Commit the transaction
            connection.commit();
            System.out.println("The transaction of deleting a customer has been committed!");
            message = "Deleted Customer OK";
        } catch (SQLException e) {
            try {
                System.err.println("The transaction of deleting a customer is being rolled back!");
                connection.rollback();
            } catch (SQLException ignored) {}
            message = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        } finally {
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException ignored) {}
        }
        return message;
    }

    public static String callDeleteAProductFromDB(int prodId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            assert connection != null;
            connection.setAutoCommit(false);
            // Prepare a CallableStatement to call the PL/SQL function
            callableStatement = connection.prepareCall("{call DELETE_PROD_FROM_DB(?)}");
            // Register the return value as an OUT parameter
            callableStatement.setInt(1, prodId);
            // Execute the function
            callableStatement.execute();
            // Commit the transaction
            connection.commit();
            System.out.println("The transaction of deleting a product has been committed!");
            message = "Deleted Product OK";
        } catch (SQLException e) {
            try {
                System.err.println("The transaction of deleting a product is being rolled back!");
                connection.rollback();
            } catch (SQLException ignored) {}
            message = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        } finally {
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException ignored) {}
        }
        return message;
    }

    public static void main(String[] args) {
        System.out.println(callDeleteAProductFromDB(1231));
    }
}
