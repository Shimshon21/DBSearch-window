package model;

//Employee class
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

    public Integer getEmpno() {
        return empno;
    }
    public void setEmpno(Integer empno) {
        this.empno = empno;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getSalary() {
        return salary;
    }
    public void setSalary(Double salary) {
        this.salary = salary;
    }

}
