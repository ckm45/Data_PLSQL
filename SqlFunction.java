import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlFunction {
    List<Emp> empList = new ArrayList<Emp>();
    Connection conn = ConnectJDBC.getConnection();
    Customer customer = new Customer();


    public void makeEmp() {
        String query = "SELECT ENAME, JOB, EMPNO, SAL, COMM FROM EMP";
        try (

                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery();) {
            while (rs.next()) {
                empList.add(new Emp(rs.getString("ENAME"), rs.getString("JOB"), rs.getInt("EMPNO"),
                        rs.getInt("SAL"), rs.getInt("COMM")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printEmp() {
        for (Emp emp : empList) {
            System.out.println(emp.toString());
        }
    }

    public void makeBonus() {
        customer.makeAccountMgrCounts();
        
        for (Integer key : customer.accountMgrCounts.keySet()) {
            //해시맵의 key값을 각각 가져온다.

            for (Emp emp : empList) {
                //emplist에서 emp객체 하나를 뽑아낸다. 
                if (customer.accountMgrCounts.get(key) <= 100000
                        //key값의 value즉 count의 값이 10만보다 아래 그리고 직업이 PRESIDENT, ANALYST가 아니라면 if 문 실행
                        && !emp.getJob().equals("PRESIDENT") && !emp.getJob().equals("ANALYST")) {
                    if (emp.getEmpno() == key) {
                        //그 중 key값 account_Mgr과 같은 empno을 가진 emp 객체의 정보를 
                        Bonus bonus = new Bonus(emp.getEname(), emp.getJob(), emp.getSal(), 1000);
                        //Bonus에 넣어준다.
                        try {
                            Statement stmt = conn.createStatement();
                            

                            int rowsAffected = stmt.executeUpdate("INSERT INTO BONUS(ENAME,JOB,SAL,COMM) VALUES('" + bonus.getEname() + "','"
                                    + bonus.getJob() + "','" + bonus.getSal() + "','" + bonus.getComm() + "')");
                            //보너스 객체를 보너스 테이블 INSERT 하는 쿼리를  STATEMENT 방식으로 날린다.


                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                }

                else if (customer.accountMgrCounts.get(key) >= 100000
                        && !emp.getJob().equals("PRESIDENT") && !emp.getJob().equals("ANALYST")) {
                    //key값의 value즉 count의 값이 10만보다 위 그리고 직업이 PRESIDENT, ANALYST가 아니라면 if 문 실행
                    if (emp.getEmpno() == key) {
                        //그 중 key값 account_Mgr과 같은 empno을 가진 emp 객체의 정보를                     
                        try {
                            Statement stmt = conn.createStatement();
                            Bonus bonus = new Bonus(emp.getEname(), emp.getJob(), emp.getSal(), 2000);
                            //Bonus에 넣어준다.
                            System.out.println(bonus);
                            int rowsAffected = stmt.executeUpdate("INSERT INTO BONUS(ENAME,JOB,SAL,COMM) VALUES('" + bonus.getEname() + "','"
                                    + bonus.getJob() + "','" + bonus.getSal() + "','" + bonus.getComm() + "')");


                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }


        }
    }


}


