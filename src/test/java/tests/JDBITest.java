package tests;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.Employee;

import java.util.List;

public class JDBITest {


    String db_url;
    String user;
    String password;

    @BeforeClass
    public void setUp(){
        db_url = "jdbc:mysql://localhost:3306/emp_db";
        user = "root";
        password = "admin123";
    }
    @Test
    public void verifyAllColumnDataJDBITest(){
        Jdbi jdbi = Jdbi.create(db_url,user,password);
        try(Handle handle = jdbi.open()){
            List<Employee> emp = handle.createQuery("select * from emp")
                    .mapToBean(Employee.class)
                    .list();
            emp.forEach(System.out::println);
        }
    }
    @Test
    public void verifySingleColumnDataJDBITest(){
        Jdbi jdbi = Jdbi.create(db_url,user,password);
        try(Handle handle = jdbi.open()){
            List<String> empNames = handle.createQuery("select name from emp")
                    .mapTo(String.class)
                    .list();
            empNames.forEach(System.out::println);
        }
    }
    @Test
    public void verifySingleColumnDataUsingConditionJDBITest(){
        Jdbi jdbi = Jdbi.create(db_url,user,password);
        String id = "1";
        try(Handle handle = jdbi.open()){
            List<String> empNames = handle.createQuery("select name,email,age from emp where id = :id")
                    .bind("id",id)
                    .mapTo(String.class)
                    .list();
            empNames.forEach(System.out::println);
        }
    }
}
