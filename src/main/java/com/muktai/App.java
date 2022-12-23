package com.muktai;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.muktai.entity.Customer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	Transaction tx =null;
    	Session session=null;
    	FileInputStream fis=null;
    	FileReader fr=null;
    	BufferedReader br =null;
        try {
			System.out.println( "Hello World!" );
			Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
			SessionFactory factory = cfg.buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			
				//1. Transient- newly created, not ass with session, no id, not ass with database
				Customer c=new Customer(101,"Rajesh","Jalgaon",new Date(),new Date(), false);   
				
				//2. Persistent state: asso with session, have representation in database, id assco, update operations with immidiately reflected
				session.save(c);            //or persist(),saveOrUpadte(),update()
			
				fis=new FileInputStream("e://flower.png");
				byte[]barr=new byte[fis.available()];
				fis.read(barr);
				
				c.setPhoto(barr);
				
				fr=new FileReader("e://INSTALL.txt");
				br=new BufferedReader(fr);
				
				String str=null;
				String s=null;
				while((s=br.readLine())!=null)
				{
					str=str+s;
				}
				
				char[] charArr=str.toCharArray();
				c.setMyFile(charArr);
				tx.commit();
				
				//3. DETACHED STATE : not associated with session, but have associated id, and have representation in database
				//session.close();  //or session.clear(); 
				
				
				
				
				try 
				{
					Session session2=factory.openSession();
					Transaction tx1=session2.beginTransaction();
					
					//REMOVED STATE: no session and not in database
					session2.delete(c);
					System.out.println("\n\nObject deleted\n\n ");
					((Transaction) tx1).commit();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} 
        catch (HibernateException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tx.rollback();
		}
        finally {
        	
        	session.close();
		}
        
    }
}
