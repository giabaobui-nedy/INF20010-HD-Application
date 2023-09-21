package com.example.demo;
import oracle.jdbc.OracleTypes;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {
    private static final String url = "jdbc:oracle:thin:@//feenix-oracle.swin.edu.au:1521/DMS";
    private static final String user = "s103533680";
    private static final String password = "Rinie3baebae62003.";

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(url, user, password);
    }

    public static String callAddCustomerToDb(int custId, String custName) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            connection.setAutoCommit(false);
            // Prepare a CallableStatement to call the PL/SQL procedure
            callableStatement = connection.prepareCall("{call ADD_CUST_TO_DB(?, ?)}");
            // Set the input parameters
            callableStatement.setInt(1, custId);
            callableStatement.setString(2, custName);
            // Execute the procedure
            callableStatement.execute();
            // Commit the transaction
            connection.commit();
            // If the procedure executed successfully, return a success message
            message = "Customer added OK";
        } catch (ClassNotFoundException e) {
            message = "Error: Class not found!";
        } catch (SQLException sqlErr) {
            message = sqlErr.getMessage().substring(0, sqlErr.getMessage().indexOf("\n"));
        } finally {
            // Close resources in the "finally" block
            try {
                if (callableStatement != null)
                {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ignored) {
            }
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
            connection.setAutoCommit(false);

            // Prepare a CallableStatement to call the PL/SQL function
            callableStatement = connection.prepareCall("{? = call DELETE_ALL_CUSTOMERS_FROM_DB}");

            // Register the return value as an OUT parameter
            callableStatement.registerOutParameter(1, Types.INTEGER);

            // Execute the function
            callableStatement.execute();

            // Commit the transaction
            connection.commit();

            message = callableStatement.getInt(1) + " rows deleted";
        } catch (ClassNotFoundException e) {
            message = "Error: Class not found!";
        } catch (SQLException sqlErr) {
            message = sqlErr.getMessage().substring(0, sqlErr.getMessage().indexOf("\n"));
        } finally {
            // Close resources in the "finally" block
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException e) {
                message = "Error: in the finally block. Possibly caused by: Errors when closing the callable statement and connection to the database!";
            }
        }
        return message;
    }

    public static String callAddProductToDB(int prodId, String prodName, Float price) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();

            //set auto-commit false to enable explicit commit
            connection.setAutoCommit(false);

            // Prepare a CallableStatement to call the PL/SQL procedure
            callableStatement = connection.prepareCall("{call ADD_PRODUCT_TO_DB(?, ?, ?)}");

            // Set the input parameters
            callableStatement.setInt(1, prodId);
            callableStatement.setString(2, prodName);
            callableStatement.setFloat(3, price);

            // Execute the procedure
            callableStatement.execute();

            // Commit the transaction
            connection.commit();

            // If the procedure executed successfully, return a success message
            message = "Product added OK";
        } catch (ClassNotFoundException e) {
            message = "Error: Class not found! The connection to database cannot be established!";
        } catch (SQLException sqlErr) {
            message = sqlErr.getMessage().substring(0, sqlErr.getMessage().indexOf("\n"));
        } finally {
            // Close resources in the "finally" block
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException e) {
                message = "Error: in the finally block. Possibly caused by: Errors when closing the callable statement and connection to the database!";
            }
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
            connection.setAutoCommit(false);
            // Prepare a CallableStatement to call the PL/SQL function
            callableStatement = connection.prepareCall("{? = call DELETE_ALL_PRODUCTS_FROM_DB}");
            // Register the return value as an OUT parameter
            callableStatement.registerOutParameter(1, Types.INTEGER);
            // Execute the function
            callableStatement.execute();
            // Commit the transaction
            connection.commit();
            message = callableStatement.getInt(1) + " rows deleted";
        } catch (ClassNotFoundException e) {
            message = "Error: Class not found!";
        } catch (SQLException sqlErr) {
            message = sqlErr.getMessage().substring(0, sqlErr.getMessage().indexOf("\n"));
        } finally {
            // Close resources in the "finally" block
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException e) {
                message = "Error: in the finally block. Possibly caused by: Errors when closing the callable statement and connection to the database!";
            }
        }
        return message;
    }

    public static String callGetCustomerStringFromDB(int custId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String result;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            // Prepare a CallableStatement to call the PL/SQL function
            callableStatement = connection.prepareCall("{? = call GET_CUST_STRING_FROM_DB(?)}");
            // Register the return value as an OUT parameter
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            // Set the input parameter
            callableStatement.setInt(2, custId);
            // Execute the function
            callableStatement.execute();
            // Get the result
            result = callableStatement.getString(1);
        } catch (ClassNotFoundException e) {
            result = "Error: The JDBC driver class not found!";
        } catch (SQLException sqlErr) {
            // Handle any SQL exceptions
            result = sqlErr.getMessage().substring(0, sqlErr.getMessage().indexOf("\n"));
        } finally {
            // Close resources in the "finally" block
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                result = "Error: An error occurred while closing the callable statement and connection to the database!";
            }
        }
        return result;
    }

    public static String callGetProductStringFromDB(int prodId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String result;
        try {
            // Establish a JDBC connection
            connection = getConnection();

            // Prepare a CallableStatement to call the PL/SQL function
            callableStatement = connection.prepareCall("{? = call GET_PROD_STRING_FROM_DB(?)}");

            // Register the return value as an OUT parameter
            callableStatement.registerOutParameter(1, Types.VARCHAR);

            // Set the input parameter
            callableStatement.setInt(2, prodId);

            // Execute the function
            callableStatement.execute();

            // Get the result
            result = callableStatement.getString(1);
        } catch (ClassNotFoundException e) {
            result = "Error: The JDBC driver class not found!";
        } catch (SQLException sqlErr) {
            // Handle any SQL exceptions
            result = sqlErr.getMessage().substring(0, sqlErr.getMessage().indexOf("\n"));
        } finally {
            // Close resources in the "finally" block
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                result = "Error: An error occurred while closing the callable statement and connection to the database!";
            }
        }
        return result;
    }

    public static String callUpdCustomerSalesYtdInDb(int custId, float amount) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
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

            // If the procedure executed successfully, return a success message
            message = "Update OK";
        } catch (ClassNotFoundException e) {
            message = "Error: The JDBC driver class not found!";
        } catch (SQLException sqlErr) {
            // Handle any SQL exceptions
            message = sqlErr.getMessage().substring(0, sqlErr.getMessage().indexOf("\n"));
        } finally {
            // Close resources in the "finally" block
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                message = "Error: An error occurred while closing the callable statement and connection to the database!";
            }
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

            // If the procedure executed successfully, return a success message
            message = "Update OK";
        } catch (ClassNotFoundException e) {
            message = "Error: The JDBC driver class not found!";
        } catch (SQLException sqlErr) {
            // Handle any SQL exceptions
            message = sqlErr.getMessage().substring(0, sqlErr.getMessage().indexOf("\n"));
        } finally {
            // Close resources in the "finally" block
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                message = "Error: An error occurred while closing the callable statement and connection to the database!";
            }
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

            // If the procedure executed successfully, return a success message
            message = "Update OK";
        } catch (ClassNotFoundException e) {
            message = "Error: The JDBC driver class not found!";
        } catch (SQLException sqlErr) {
            // Handle any SQL exceptions
            message = sqlErr.getMessage().substring(0, sqlErr.getMessage().indexOf("\n"));
        } finally {
            // Close resources in the "finally" block
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                message = "Error: An error occurred while closing the callable statement and connection to the database!";
            }
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
        } catch (ClassNotFoundException e) {
            message = "Error: The JDBC driver class not found!";
        } catch (SQLException sqlErr) {
            // Handle any SQL exceptions
            message = sqlErr.getMessage().substring(0, sqlErr.getMessage().indexOf("\n"));
        } finally {
            // Close resources in the "finally" block
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                message = "Error: An error occurred while closing the callable statement and connection to the database!";
            }
        }
        return message;
    }

    public static String callSumCustomerSalesYtd() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();

            // Prepare a CallableStatement to call the PL/SQL function
            callableStatement = connection.prepareCall("{? = call SUM_CUST_SALESYTD()}");

            // Register the return value as an OUT parameter
            callableStatement.registerOutParameter(1, Types.NUMERIC);

            // Execute the function
            callableStatement.execute();

            // If the function executed successfully, return a success message
            message = "All Customer Total: " + callableStatement.getFloat(1);
        } catch (ClassNotFoundException e) {
            message = "Error: The JDBC driver class not found!";
        } catch (SQLException sqlErr) {
            // Handle any SQL exceptions
            message = sqlErr.getMessage().substring(0, sqlErr.getMessage().indexOf("\n"));
        } finally {
            // Close resources in the "finally" block
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                message = "Error: An error occurred while closing the callable statement and connection to the database!";
            }
        }
        return message;
    }

    public static String callSumProductSalesYtd() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();

            // Prepare a CallableStatement to call the PL/SQL function
            callableStatement = connection.prepareCall("{? = call SUM_PROD_SALESYTD_FROM_DB}");

            // Register the return value as an OUT parameter
            callableStatement.registerOutParameter(1, Types.NUMERIC);

            // Execute the function
            callableStatement.execute();

            // If the function executed successfully, return a success message
            message = "All Product Total: " + callableStatement.getFloat(1);
        } catch (ClassNotFoundException e) {
            message = "Error: The JDBC driver class not found!";
        } catch (SQLException sqlErr) {
            // Handle any SQL exceptions
            message = sqlErr.getMessage().substring(0, sqlErr.getMessage().indexOf("\n"));
        } finally {
            // Close resources in the "finally" block
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                message = "Error: An error occurred while closing the callable statement and connection to the database!";
            }
        }
        return message;
    }

    public static String callGetAllCustomer() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String result;
        try {
            // Establish a JDBC connection
            connection = getConnection();

            // Prepare a CallableStatement to call the PL/SQL function
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

            result = strBuilder.toString();
        }
        catch (ClassNotFoundException e) {
            result = "Error: The JDBC driver class not found!";
        }
        catch (SQLException e) {
            result = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        }
        finally {
            // Close resources in the "finally" block
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ignored) {
            }
        }
        return result;
    }

    public static String callGetAllProduct() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String result;
        try {
            // Establish a JDBC connection
            connection = getConnection();

            // Prepare a CallableStatement to call the PL/SQL function
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

            result = strBuilder.toString();
        }
        catch (ClassNotFoundException e) {
            result = "Error: The JDBC driver class not found!";
        }
        catch (SQLException e) {
            result = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        }
        finally {
            // Close resources in the "finally" block
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ignored) {
            }
        }
        return result;
    }

    public static String callGetAllSale() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String result;
        try {
            // Establish a JDBC connection
            connection = getConnection();

            // Prepare a CallableStatement to call the PL/SQL function
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
            result = strBuilder.toString();
        }
        catch (ClassNotFoundException e) {
            result = "Error: The JDBC driver class not found!";
        }
        catch (SQLException e) {
            result = e.getMessage().substring(0, e.getMessage().indexOf("\n"));
        }
        finally {
            // Close resources in the "finally" block
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ignored) {
            }
        }
        return result;
    }

    public static String callAddComplexSale(int custId, int prodId, int quantity, String date) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
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

            // If the procedure executed successfully, return a success message
            message = "Added Complex Sale OK";
        } catch (ClassNotFoundException e) {
            message = "Error: The JDBC driver class not found!";
        } catch (SQLException sqlErr) {
            // Handle any SQL exceptions
            message = sqlErr.getMessage().substring(0, sqlErr.getMessage().indexOf("\n"));
        } finally {
            // Close resources in the "finally" block
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                message = "Error: An error occurred while closing the callable statement and connection to the database!";
            }
        }
        return message;
    }

    public static String callGetCountSales(int numberOfDays) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String result;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            // Prepare a CallableStatement to call the PL/SQL function
            callableStatement = connection.prepareCall("{? = call COUNT_PRODUCT_SALES_FROM_DB(?)}");
            // Register the return value as an OUT parameter
            callableStatement.registerOutParameter(1, Types.INTEGER);
            // Set the input parameter
            callableStatement.setInt(2, numberOfDays);
            // Execute the function
            callableStatement.execute();
            // Get the result
            result = "Total number of sales: " + callableStatement.getInt(1);
        } catch (ClassNotFoundException e) {
            result = "Error: The JDBC driver class not found!";
        } catch (SQLException sqlErr) {
            // Handle any SQL exceptions
            result = sqlErr.getMessage().substring(0, sqlErr.getMessage().indexOf("\n"));
        } finally {
            // Close resources in the "finally" block
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                result = "Error: An error occurred while closing the callable statement and connection to the database!";
            }
        }
        return result;
    }

    public static String callDeleteAllSalesFromDB() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String message;
        try {
            // Establish a JDBC connection
            connection = getConnection();
            connection.setAutoCommit(false);

            // Prepare a CallableStatement to call the PL/SQL procedure
            callableStatement = connection.prepareCall("{call DELETE_ALL_SALES_FROM_DB}");

            // Execute the procedure
            callableStatement.execute();

            // Commit the transaction
            connection.commit();

            message = "Deletion OK";
        } catch (ClassNotFoundException e) {
            message = "Error: Class not found!";
        } catch (SQLException sqlErr) {
            message = sqlErr.getMessage().substring(0, sqlErr.getMessage().indexOf("\n"));
        } finally {
            // Close resources in the "finally" block
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException e) {
                message = "Error: in the finally block. Possibly caused by: Errors when closing the callable statement and connection to the database!";
            }
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
            connection.setAutoCommit(false);

            // Prepare a CallableStatement to call the PL/SQL procedure
            callableStatement = connection.prepareCall("{ ? = call DELETE_SALE_FROM_DB}");

            // register the return type
            callableStatement.registerOutParameter(1, Types.INTEGER);

            // Execute the procedure
            callableStatement.execute();

            // Commit the transaction
            connection.commit();

            // If the procedure executed successfully, return a success message
            message = "Deleted Sale OK. Sale ID: " + callableStatement.getInt(1);
        } catch (ClassNotFoundException e) {
            message = "Error: The JDBC driver class not found!";
        } catch (SQLException sqlErr) {
            // Handle any SQL exceptions
            message = sqlErr.getMessage().substring(0, sqlErr.getMessage().indexOf("\n"));
        } finally {
            // Close resources in the "finally" block
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                message = "Error: An error occurred while closing the callable statement and connection to the database!";
            }
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
            connection.setAutoCommit(false);
            // Prepare a CallableStatement to call the PL/SQL function
            callableStatement = connection.prepareCall("{call DELETE_CUSTOMER(?)}");
            // Register the return value as an OUT parameter
            callableStatement.setInt(1, custId);
            // Execute the function
            callableStatement.execute();
            // Commit the transaction
            connection.commit();
            message = "Deleted Customer OK";
        } catch (ClassNotFoundException e) {
            message = "Error: Class not found!";
        } catch (SQLException sqlErr) {
            message = sqlErr.getMessage().substring(0, sqlErr.getMessage().indexOf("\n"));
        } finally {
            // Close resources in the "finally" block
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException e) {
                message = "Error: in the finally block. Possibly caused by: Errors when closing the callable statement and connection to the database!";
            }
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
            connection.setAutoCommit(false);
            // Prepare a CallableStatement to call the PL/SQL function
            callableStatement = connection.prepareCall("{call DELETE_PROD_FROM_DB(?)}");
            // Register the return value as an OUT parameter
            callableStatement.setInt(1, prodId);
            // Execute the function
            callableStatement.execute();
            // Commit the transaction
            connection.commit();
            message = "Deleted Product OK";
        } catch (ClassNotFoundException e) {
            message = "Error: Class not found!";
        } catch (SQLException sqlErr) {
            message = sqlErr.getMessage().substring(0, sqlErr.getMessage().indexOf("\n"));
        } finally {
            // Close resources in the "finally" block
            try {
                if (callableStatement != null) {callableStatement.close();}
                if (connection != null) {connection.close();}
            } catch (SQLException e) {
                message = "Error: in the finally block. Possibly caused by: Errors when closing the callable statement and connection to the database!";
            }
        }
        return message;
    }

    public static void main(String[] args) {
        System.out.println(callDeleteAProductFromDB(1231));
    }
}
