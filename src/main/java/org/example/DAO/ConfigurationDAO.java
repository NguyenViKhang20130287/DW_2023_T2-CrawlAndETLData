package org.example.DAO;

import org.example.DBConnection;
import org.example.entity.DataFileConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationDAO {

    DBConnection db = new DBConnection();
    PreparedStatement ps;
    ResultSet rs;
    public List<DataFileConfigs> getDataConfigWithFlag(){
        List<DataFileConfigs> list = new ArrayList<>();
        try {
            Connection connection = db.getConnection("control");
            String query = "SELECT * FROM `data_file_configs` where flag = 1 ";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                DataFileConfigs data = new DataFileConfigs(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(7));
                list.add(data);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public void insertStatusConfig(int df_id, String status){
        try {
            Connection connection = db.getConnection("control");
            String query = "INSERT INTO data_files(data_files.df_config_id, " +
                    "data_files.`name`, data_files.`status`, " +
                    "data_files.file_timestamp, " +
                    "data_files.note, " +
                    "data_files.created_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, df_id);
            ps.setString(2, "KET QUA XO SO");
            ps.setString(3, status);
            ps.setString(4, String.valueOf(LocalDateTime.now()));
            ps.setString(5, "NOTE");
            ps.setString(6, String.valueOf(LocalDate.now()));
            ps.executeUpdate();
            System.out.println("Insert status: " + status +  " success");
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
