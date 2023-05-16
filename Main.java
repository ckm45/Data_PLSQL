import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
//      실행 전 현재 시작 측정
      long startTime = System.currentTimeMillis();

        Customer customer = new Customer();
        SqlFunction sqlfunction = new SqlFunction();
        Connection conn = ConnectJDBC.getConnection();
        //JDBC 연결 
        //customer.makeAccountMgrCounts();
       // customer.printAccountMgrCounts();
      
        sqlfunction.makeEmp();
        //emp 테이블 정보를 객체 및 emplist에 저장
        sqlfunction.makeBonus();
        //bonus를 계산한 후 넣어주는 쿼리가 들어있는 메소드
       
//      실행 후 현재 시작 측정
      long endTime = System.currentTimeMillis();

//      실행 전과 실행 후 시간 측정
      double time = (endTime - startTime) / 1000.0;
      System.out.println("소요 시간 : " + time + "초");

        
        
    }
}