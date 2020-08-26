package repository;

import datasource.DBSocketDataSource;
import model.Emp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//Employee Repository class.
public class EmpRepository {
    private DBSocketDataSource dbSockDS = new DBSocketDataSource();

    //Find data in table 'emp' by the field 'name'.
    public List<Emp> findByNamePattern(String name,String field) throws Exception {
        Connection con = dbSockDS.getConnection();
        try {
            PreparedStatement ps =
                    con.prepareStatement(" select * from emp where " + field + " like ? ");
            ps.setString(1, "%" + name + "%");
            System.out.println(ps.toString());
            ResultSet rs = ps.executeQuery();
            List<Emp> empList = new ArrayList<Emp>();
            while (rs.next()) {
                Emp obj = new Emp(rs.getInt(1), rs.getString(2), rs.getDouble(3));
                empList.add(obj);
            }
            return empList;
        } finally {
            con.close();
        }
    }
}
