import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class javaSource5 {

    public static void main(String[] args) {

        // 1. Oracle JDBC 드라이버를 로드합니다.
        Connection conn = null;

        // 2. 데이터베이스에 연결합니다.
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@192.168.119.119:1521/dink19.dbsvr";
            String user = "scott";
            String passwd = "tiger";
            conn = DriverManager.getConnection(url, user, passwd);
            long startTime = System.currentTimeMillis();
            // 3. SQL 쿼리를 실행합니다.
            Statement stmt = conn.createStatement();
            //equi join 을 통해 emp와 customer를 join 하여 비교하여 보너스 테이블에 insert 해주었습니다. 
            int rowsAffected = stmt.executeUpdate("INSERT INTO BONUS(ENAME,JOB,SAL,COMM) \r\n"
                    + "SELECT E.ENAME,E.JOB,E.SAL, CASE \r\n"
                    + "WHEN COUNT(C.ACCOUNT_MGR) >= 100000 THEN 2000\r\n"
                    + "WHEN COUNT(C.ACCOUNT_MGR) < 100000 THEN 1000\r\n"
                    + "WHEN E.JOB = 'PRESIDENT' OR E.JOB = 'ANALYST' THEN 0\r\n"
                    + "ELSE 0\r\n"
                    + "END AS COMM\r\n"
                    + "FROM EMP E,CUSTOMER C\r\n"
                    + "WHERE E.EMPNO = C.ACCOUNT_MGR\r\n"
                    + "GROUP BY E.ENAME, E.JOB,E.SAL");
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            System.out.println(rowsAffected + " rows affected.");
            System.out.println("실행 시간 : " + elapsedTime + "ms");

        } catch (ClassNotFoundException e) {
            // 드라이버 로드 중 예외가 발생한 경우 처리합니다.
            e.printStackTrace();
        } catch (SQLException e) {
            // 데이터베이스 연결 및 쿼리 실행 중 예외가 발생한 경우 처리합니다.
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                // 연결 닫기 중 예외가 발생한 경우 처리합니다.
                e.printStackTrace();
            }
        }
    }
}