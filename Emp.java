import java.util.Objects;

public class Emp {
    private String ename;
    private String job;
    private int empno;
    private int sal;
    private int comm;
    
    public Emp(String ename, String job, int empno, int sal, int comm) {
        
        this.ename = ename;
        this.job = job;
        this.empno = empno;
        this.sal = sal;
        this.comm = comm;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public int getComm() {
        return comm;
    }

    public void setComm(int comm) {
        this.comm = comm;
    }

    @Override
    public String toString() {
        return "Emp [ename=" + ename + ", job=" + job + ", empno=" + empno + ", sal=" + sal
                + ", comm=" + comm + "]";
    }
  
    
    

}
