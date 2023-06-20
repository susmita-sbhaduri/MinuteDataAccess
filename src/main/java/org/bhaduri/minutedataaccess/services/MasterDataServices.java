/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.minutedataaccess.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.bhaduri.minutedataaccess.DA.MinuteDataAccess;
import org.bhaduri.minutedataaccess.entities.Minutedata;
import org.bhaduri.minutedataaccess.entities.MinutedataPK;
import org.bhaduri.datatransfer.DTO.CsvTickData;

/**
 *
 * @author sb
 */
public class MasterDataServices {

    private EntityManagerFactory emf;

    public MasterDataServices() {
        emf = Persistence.createEntityManagerFactory("org.bhaduri_MinuteDataAccess_jar_1.0-SNAPSHOTPU");
    }

    public CsvTickData getLastpricerPerScripID(String scripid) {
        MinuteDataAccess minuteDataAccess = new MinuteDataAccess(emf);
        CsvTickData retCsvTickData = new CsvTickData();
        List<List<Double>> recordData = new ArrayList<>();
        List<Double> row;
        try {
            List<Minutedata> minutedatas = minuteDataAccess.listByScripid(scripid);
            for (int i = 0; i < minutedatas.size(); i++) {
                row = new ArrayList<>();
                row.add(Double.valueOf(i));
                row.add(minutedatas.get(i).getDaylastprice());
                recordData.add(row);
            }
            retCsvTickData.setTickData(recordData);
            DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String strDate = targetFormat.format(minutedatas.get(minutedatas.size()-1).getMinutedataPK().getLastupdateminute());
//            retCsvTickData.setDateTime(minutedatas.get(minutedatas.size()-1).getMinutedataPK().getLastupdateminute().toString());
            retCsvTickData.setDateTime(strDate);
            return retCsvTickData;
        } catch (Exception exception) {
            System.out.println(exception + " has occurred in getLastpricerPerScripID.");
            return null;
        }
    }
     public CsvTickData getLatestDataScripID(String scripid, Date lastupdateDate) {
        MinuteDataAccess minuteDataAccess = new MinuteDataAccess(emf);
        CsvTickData retCsvTickData = new CsvTickData();
        List<List<Double>> recordData = new ArrayList<>();
        List<Double> row;
        try {
            List<Minutedata> minutedatas = minuteDataAccess.listByScripidLastupdate(scripid, lastupdateDate);
            for (int i = 0; i < minutedatas.size(); i++) {
                row = new ArrayList<>();
                row.add(Double.valueOf(i));
                row.add(minutedatas.get(i).getDaylastprice());
                recordData.add(row);
            }
            retCsvTickData.setTickData(recordData);
            return retCsvTickData;
        } catch (Exception exception) {
            System.out.println(exception + " has occurred in getLastpricerPerScripID.");
            return null;
        }
    }
}
