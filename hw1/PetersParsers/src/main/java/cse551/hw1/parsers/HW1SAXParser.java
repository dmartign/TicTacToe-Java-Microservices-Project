package cse551.hw1.parsers;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class HW1SAXParser extends DefaultHandler {
    String content;
    Order order;
    int managerID;
    Store store;
    static SimpleDateFormat sdf;
    
    public static void main(String[] args) {
          
            sdf  = new SimpleDateFormat("M/dd/yyyy");
            String input = "Sample.xml";
            HW1SAXParser myParser = new HW1SAXParser();
            System.out.println("Here's the output using the SAX Parser:");
            myParser.GoParseSAX(input);
        
    }

    public void GoParseSAX(String input)
    {
            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser parser = factory.newSAXParser();
                HW1SAXParser handler = new HW1SAXParser();
                InputStream inputStream = HW1SAXParser.class.getClassLoader()
        				.getResourceAsStream(input);
                parser.parse(inputStream,handler);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
    }
      
        @Override
        public void startDocument() throws SAXException
        {
            System.out.println("{Start Document}");
        }
        
        @Override
        public void endDocument() throws SAXException
        {
            System.out.println("{End Document}");
        }
        
        @Override
        public void characters(char[] ch,int start,int length) throws SAXException
        {
            content = String.copyValueOf(ch,start,length).trim();
        }
    
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
        {
            String tagName = qName.toUpperCase();
            switch (tagName){
                case "STORE":
                    store = new Store();
                    store.orders = new ArrayList<Order>();
                    break;
                case "ORDER":
                    order = new Order();
                   order.orderID = Integer.parseInt(attributes.getValue("OrderID").toString()); 
                   break;
                case "ITEMS":
                    order.items = new Items();
                    break;
                case "DETAILS":
                    order.items.details = new Details();
                    break;
                case "OFFICE":
                    order.office =new Office();
                    order.office.location = attributes.getValue("location");
                    break;
                case "MANAGERS":
                    order.office.managers = new ArrayList<Manager>();
                    break;
                case "MANAGER":
                    managerID = Integer.parseInt(attributes.getValue("id"));
                    break;
            }
                
        }
        
          @Override
        public void endElement(String uri,String localName,String qName)throws SAXException
        {
            String tagName = qName.toUpperCase();
            switch(tagName){
                case "STORE":
                    store.printSomeInfoAboutStore();
                    break;
                case "ORDER":
                    store.orders.add(order);
                   break;
                case "NAME":
                    order.items.name = content;
                    break;
                case "CATEGORY":
                    order.items.category = content;
                    break;
                case "UNITPRICE":
                    order.items.details.unitPrice = Float.parseFloat(content);
                    break;
                case "MANAGER":
                    Manager newManager = new Manager();
                    newManager.id = managerID;
                    newManager.name = content;
                    order.office.managers.add(newManager);
                    break;
                case "SOLDDATE":
                    
                    try{order.items.soldDate = sdf.parse(content);}
                    catch (Exception e)
                    { System.out.print("You have a bad date format!");}
                    break;
            }

        }
        
    class Details
    {
        public float unitPrice;
        public String size;
        public String color;
        public String pattern;
        public String brand;
    }
    
    class Items
    {
        Details details;
        String name;
        String category;
        Date soldDate;
        
    }
    
    class Manager
    {
        int id;
        String name;
    }
    
    class Office
    {
        String location;
        List<Manager> managers;
        
      
    }
    class Order
    {
        Items items;
        int orderID;
        Office office;
    }
    
    class Store
    {
        List<Order> orders;
        
        void printSomeInfoAboutStore()
        {
            for (int i = 0; i< orders.size();i++)
            {
                System.out.println("Order:");
                System.out.println("\t item name: " + orders.get(i).items.name);
                System.out.println("\t Office location: " + orders.get(i).office.location);
                System.out.println("\t SoldDate: " + sdf.format(orders.get(i).items.soldDate));
            }
          
        }
                
        
    }
}
