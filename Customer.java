import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Customer {
    Map<Integer, Integer> accountMgrCounts = new HashMap<>();
    Connection conn = ConnectJDBC.getConnection();

    public void makeAccountMgrCounts() {
        String query = "SELECT ACCOUNT_MGR FROM CUSTOMER";
        //customer 테이블 account_mgr 데이터를 가져온다.
        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                ) {
            //rs.setFetchSize(10);
            while (rs.next()) {
                int accountMgr = rs.getInt("ACCOUNT_MGR");
                Integer count = accountMgrCounts.get(accountMgr);
                
                //COUNT를 측정해주는 쿼리
                if (count == null) {
                    accountMgrCounts.put(accountMgr, 1);
                } else {
                    
                    accountMgrCounts.put(accountMgr, count+1);
                }
                //각각의 account_mgr 과 그 mgr에 따른 갯수를 해시맵에 넣는다. 

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAccountMgrCounts() {
        for (Map.Entry<Integer, Integer> entry : accountMgrCounts.entrySet()) {
            System.out
                    .println("Account Manager: " + entry.getKey() + ", Count: " + entry.getValue());
        }
    }
}
