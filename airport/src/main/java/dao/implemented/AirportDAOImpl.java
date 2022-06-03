package dao.implemented;

import dao.IDAOAirport;
import exceptions.CustomSQLException;
import model.Airport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirportDAOImpl implements IDAOAirport {

    final String GETONE = "SELECT * FROM airport WHERE idAirport = ?";
    final String GETALL = "SELECT * FROM airport";
    final String INSERT = "INSERT INTO airport (name, shortName) VALUES (?,?) ";

    private Connection conn;

    public AirportDAOImpl(Connection conn){
        this.conn = conn;
    }

    public AirportDAOImpl() {
    }

    public void closeRsPs(ResultSet rs, PreparedStatement ps){
        if (rs != null){
            try{
                rs.close();
            } catch (SQLException e) {
                new CustomSQLException("Unable to close Result Set", e);
            }
        }
        if (ps != null){
            try{
                ps.close();
            } catch (SQLException e) {
                new CustomSQLException("Unable to close Prepared Statement", e);
            }
        }
    }

    public Airport mapper(ResultSet rs) throws SQLException {

        Airport temp = new Airport();

        temp.setIdAirport(rs.getInt("idAirport"));
        temp.setName(rs.getString("name"));
        temp.setShortName(rs.getString("shortName"));

        return temp;
    }
    @Override
    public Airport getById(Integer id) throws CustomSQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Airport a = null;
        try{
            ps = conn.prepareStatement(GETONE);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                a = mapper(rs);
            }else{
                throw new CustomSQLException("Return value is null");
            }
        } catch (SQLException e) {
            throw new CustomSQLException("Error while executing SQL", e);
        }finally{
            closeRsPs(rs, ps);
        }
        System.out.println(a);
        return a;
    }

    @Override
    public List<Airport> getAll() throws CustomSQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Airport> airportList = new ArrayList<>();
        try{
            ps = conn.prepareStatement(GETALL);
            rs = ps.executeQuery();

            while(rs.next()){
                airportList.add(mapper(rs));
            }

        } catch (SQLException e) {
            throw new CustomSQLException("Error while executing SQL", e);
        }finally{
            closeRsPs(rs, ps);
        }

        airportList.forEach((final Airport a) -> System.out.println(a));
        System.out.println();
        return airportList;
    }

    @Override
    public void save(Airport o) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println(o.getName());
        System.out.println(o.getShortName());
        try{
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, o.getName());
            ps.setString(2, o.getShortName());
            if (ps.executeUpdate() == 0){
                throw new CustomSQLException("Error processing SQL Statement");
            }
        } catch (SQLException e) {
            throw new CustomSQLException("SQL Error", e);
        } finally {
            closeRsPs(rs, ps);
        }
    }

    @Override
    public void update(int id, Airport o) {

    }

    @Override
    public void delete(Integer id) {

    }
}
