/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.bhaduri.minutedataaccess.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.bhaduri.minutedataaccess.DA.MinuteDataAccess;
import org.bhaduri.minutedataaccess.DA.CallDataAccess;
import org.bhaduri.minutedataaccess.entities.Minutedata;
import org.bhaduri.minutedataaccess.entities.MinutedataPK;
import org.bhaduri.minutedataaccess.entities.Calltable;
import org.bhaduri.minutedataaccess.entities.CalltablePK;
import org.bhaduri.datatransfer.DTO.CsvTickData;
import org.bhaduri.datatransfer.DTO.RecordCallPrice;
import org.bhaduri.minutedataaccess.DA.ScripIdAccess;
import org.bhaduri.minutedataaccess.JPA.exceptions.PreexistingEntityException;
import org.bhaduri.minutedataaccess.entities.Scrips;

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
//            DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//            String strDate = targetFormat.format(minutedatas.get(minutedatas.size()-1).getMinutedataPK().getLastupdateminute());
//            retCsvTickData.setDateTime(minutedatas.get(minutedatas.size()-1).getMinutedataPK().getLastupdateminute().toString());
//            retCsvTickData.setDateTime(strDate);
            retCsvTickData.setDateTime(minutedatas.get(minutedatas.size() - 1).getMinutedataPK().getLastupdateminute());
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

    public void insertIntoCalltable(RecordCallPrice lastCallData) {
        CallDataAccess caltableDA = new CallDataAccess(emf);
        try {
            CalltablePK calltablePK = new CalltablePK();
            Calltable callDataRecord = new Calltable();

            calltablePK.setScripid(lastCallData.getScripID());
//            DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//            Date lastupdateDate = targetFormat.parse(lastCallData.getLastUpdateTime());
            calltablePK.setLastupdateminute(lastCallData.getLastUpdateTime());

            callDataRecord.setCalltablePK(calltablePK);
            callDataRecord.setPrice(lastCallData.getPrice());
            callDataRecord.setCallone(lastCallData.getLastCallVersionOne());
            callDataRecord.setCalltwo(lastCallData.getLastCallVersionTwo());
            callDataRecord.setRetraceone(lastCallData.getRetraceVersionOne());
            callDataRecord.setRetracetwo(lastCallData.getRetraceVersionTwo());

            caltableDA.create(callDataRecord);
//            return HedwigResponseCode.SUCCESS;
        } catch (PreexistingEntityException preexistingEntityException) {
            System.out.println("data exists" + lastCallData.getScripID() + lastCallData.getLastUpdateTime());
        } //        catch (ParseException ex) {
        //            System.out.println("data parsing problem" + lastCallData.getLastUpdateTime());
        //        }
        catch (Exception exception) {
            System.out.println(exception + " has occurred in saveSripData.");
        }
    }

    public List<RecordCallPrice> readSortCallList() {
        CallDataAccess caltableDA = new CallDataAccess(emf);
        try {
            List<Calltable> calldata = caltableDA.calllistSorted();
            List<RecordCallPrice> recordList = new ArrayList<>();
            RecordCallPrice record = new RecordCallPrice();
            
            for (int i = 0; i < calldata.size(); i++) {
                record.setScripID(calldata.get(i).getCalltablePK().getScripid());
                record.setLastUpdateTime(calldata.get(i).getCalltablePK().getLastupdateminute());
                record.setPrice(calldata.get(i).getPrice());
                record.setLastCallVersionOne(calldata.get(i).getCallone());
                record.setLastCallVersionTwo(calldata.get(i).getCalltwo());
                record.setRetraceVersionOne(calldata.get(i).getRetraceone());
                record.setRetraceVersionTwo(calldata.get(i).getRetracetwo());
                
                recordList.add(record);
                record = new RecordCallPrice();
            }
            return recordList;
        } 
        catch (Exception exception) {
            System.out.println(exception + " has occurred in readSortCallList.");
            return null;
        }
    }
    
    public List<String> readScripData() {
        ScripIdAccess scripDA = new ScripIdAccess(emf);
        try {
            List<Scrips> scripIDs = scripDA.listAllScripid();
            List<String> scripIDStr = new ArrayList<>();
            for (int i = 0; i < scripIDs.size(); i++) {
                scripIDStr.add(scripIDs.get(i).getScripid());
            }            
            return scripIDStr;
        } 
        catch (Exception exception) {
            System.out.println(exception + " has occurred in readScripData.");
            return null;
        }
    }
}
