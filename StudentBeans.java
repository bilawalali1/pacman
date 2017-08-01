package beans;

/**
 * Created by Khan on 5/3/2017.
 */
import com.sun.rowset.internal.Row;

import javax.faces.bean.ManagedBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "studentbean")
public class StudentBeans {
    String name,password,gender;

    public StudentBeans()
    {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    public void printData()throws Exception
    {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/studentinfo","postgres", "khan");
        System.out.println("Opened database successfully");
        String sql = "insert into student(name, password, gender)Values(?,?,?)";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, gender);

        preparedStatement.executeUpdate();
    }

    public List<StudentBeans> getData()throws Exception {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/studentinfo", "postgres", "khan");
        System.out.println("Opened database successfully");
        List<StudentBeans> list = new ArrayList<StudentBeans>();
        preparedStatement = connection.prepareStatement("SELECT * FROM student");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            StudentBeans beans = new StudentBeans();
            beans.setName(resultSet.getString(2));
            beans.setPassword(resultSet.getString(3));
            beans.setGender(resultSet.getString(4));

            list.add(beans);
        }
        return list;
    }
    public void deleteAction(StudentBeans std)throws Exception
    {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/studentinfo","postgres", "khan");
        System.out.println("Opened database successfully");
        String sql = "DELETE * FROM student WHERE name=std.name";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.executeQuery();
    }
    }
