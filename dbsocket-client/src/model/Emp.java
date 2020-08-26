package model;
// Employee data class;
public class Emp {

    private Integer empno;
    private String name;
    private Double salary;

    public Emp(Integer empno, String name, Double salary) {
        super();
        this.empno = empno;
        this.name = name;
        this.salary = salary;
    }
    //Get employee id;
    public Integer getEmpno() {
        return empno;
    }
    //Set employee id;
    public void setEmpno(Integer empno) {
        this.empno = empno;
    }
    //Get employee name.
    public String getName() {
        return name;
    }
    //Set employee name.
    public void setName(String name) {
        this.name = name;
    }
    //Get employee salary.
    public Double getSalary() {
        return salary;
    }
    //Set employee salary.
    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
