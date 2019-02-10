package testFamsa.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.apache.log4j.Logger;

import testFamsa.pojo.Activity;

public class ActivityDAO {
	
	public final static Logger logger = Logger.getLogger(ActivityDAO.class);
	
	// Method Used To Create The Hibernate's SessionFactory Object
    public static SessionFactory getSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");
 
        // Since Hibernate Version 4.x, Service Registry Is Being Used
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 
 
        // Creating Hibernate Session Factory Instance
        SessionFactory factoryObj = configObj.buildSessionFactory(serviceRegistryObj);      
        return factoryObj;
    }
 
    // Method 1: This Method Used To Create A New Activity Record In The Database Table
    public static Integer createRecord(Activity activityObj) {
        Session sessionObj = getSessionFactory().openSession();
 
        //Creating Transaction Object  
        Transaction transObj = sessionObj.beginTransaction();
        System.out.println(activityObj.toString());
        sessionObj.save(activityObj);
 
        // Transaction Is Committed To Database
        transObj.commit();
 
        // Closing The Session Object
        sessionObj.close();
        logger.info("Successfully Created " + activityObj.toString());
        return activityObj.getActivityId();
    }
 
    // Method 2: This Method Is Used To Display The Records From The Database Table
    @SuppressWarnings("unchecked")
    public static List displayRecords() {
        Session sessionObj = getSessionFactory().openSession();
        List activitiesList = sessionObj.createQuery("FROM Activity").list();
 
        // Closing The Session Object
        sessionObj.close();
        logger.info("Activity Records Available In Database Are?= " + activitiesList.size());
        return activitiesList;
    }
 
    // Method 3: This Method Is Used To Update A Record In The Database Table
    public static void updateRecord(Activity activityObj) {
        Session sessionObj = getSessionFactory().openSession();
 
        //Creating Transaction Object  
        Transaction transObj = sessionObj.beginTransaction();
        Activity actObj = (Activity) sessionObj.load(Activity.class, activityObj.getActivityId());
        actObj.setActivityDescription(activityObj.getActivityDescription());
 
        // Transaction Is Committed To Database
        transObj.commit();
 
        // Closing The Session Object
        sessionObj.close();
        logger.info("Activity Record Is Successfully Updated!= " + actObj.toString());
    }
 
    // Method 4(a): This Method Is Used To Delete A Particular Record From The Database Table
    public static void deleteRecord(Integer activityId) {
        Session sessionObj = getSessionFactory().openSession();
 
        //Creating Transaction Object  
        Transaction transObj = sessionObj.beginTransaction();
        Activity actObj = findRecordById(activityId);
        sessionObj.delete(actObj);
 
        // Transaction Is Committed To Database
        transObj.commit();
 
        // Closing The Session Object
        sessionObj.close();
        logger.info("Successfully Record Is Successfully Deleted!=  " + actObj.toString());
 
    }
 
    // Method 4(b): This Method To Find Particular Record In The Database Table
    public static Activity findRecordById(Integer activityId) {
        Session sessionObj = getSessionFactory().openSession();
        Activity act = (Activity) sessionObj.load(Activity.class, activityId);
 
        // Closing The Session Object
        sessionObj.close();
        return act;
    }
 
    // Method 5: This Method Is Used To Delete All Records From The Database Table
    public static void deleteAllRecords() {
        Session sessionObj = getSessionFactory().openSession();
 
        //Creating Transaction Object  
        Transaction transObj = sessionObj.beginTransaction();
        Query queryObj = sessionObj.createQuery("DELETE FROM Activity");
        queryObj.executeUpdate();
 
        // Transaction Is Committed To Database
        transObj.commit();
 
        // Closing The Session Object
        sessionObj.close();
        logger.info("Successfully Deleted All Records From The Database Table!");
    }
}
