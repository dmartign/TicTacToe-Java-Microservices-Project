package cse551.hw1.parsers;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class HW1DOMParser {
    String content;
    Order order;
    int managerID;
    Store store;
    static SimpleDateFormat sdf;
    
    public static void main(String[] args) {
            sdf  = new SimpleDateFormat("M/dd/yyyy");
            String input = "Sample.xml";
            HW1DOMParser myParser = new HW1DOMParser();
            System.out.println("Here's the output using the DOM Parser:");
            myParser.GoParseDOM(input);
    }


    
    public String getNodeAttribute(Node currNode,String namedItem)
    {
        String ret = currNode.getAttributes().getNamedItem(namedItem).getTextContent();
        return ret;
    }
    
    public void GoParseDOM(String input)
    {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = factory.newDocumentBuilder();
                InputStream inputStream = HW1DOMParser.class.getClassLoader()
        				.getResourceAsStream(input);
                Document doc = dBuilder.parse(inputStream);
                
                //get doc's root element
                NodeList root = doc.getDocumentElement().getChildNodes();
                store = new Store();
   
                store.orders = new ArrayList<>();
                for(int i = 0; i<root.getLength();i++)
                {
                    Node currNode = root.item(i);
                    if (currNode instanceof Element)
                    {
                        switch (currNode.getNodeName().toUpperCase())
                        {
                            case "ORDER":
                                order = new Order();
                                order.orderID = Integer.parseInt(getNodeAttribute(currNode,"OrderID"));
                                store.orders.add(order);
                                NodeList orderKids = currNode.getChildNodes();
                                for(int j = 0; j<orderKids.getLength();j++)
                                {
                                    currNode = orderKids.item(j);
                                    if(currNode instanceof Element)
                                    {
                                        switch(currNode.getNodeName().toUpperCase())
                                        {
                                            case "ITEMS":
                                                order.items = new Items();
                                                NodeList itemKids = currNode.getChildNodes();
                                                for (int g =0; g < itemKids.getLength();g++)
                                                {
                                                    currNode = itemKids.item(g);
                                                    //Items item = new Items();
                                                    if(currNode instanceof Element)
                                                    {
                                                        switch (currNode.getNodeName().toUpperCase())
                                                        {
                                                            case "NAME":
                                                                order.items.name = currNode.getTextContent();
                                                                break;
                                                            case "CATEGORY":
                                                                order.items.category = currNode.getTextContent();
                                                                break;
                                                            case "SOLDDATE":
                                                                try{order.items.soldDate = sdf.parse(currNode.getTextContent());}
                                                                catch(Exception e){System.out.println("Bad date format!");}
                                                                break;
                                                            case "DETAILS":
                                                                order.items.details = new Details();
                                                                NodeList detailKids = currNode.getChildNodes();
                                                                for (int m=0;m<detailKids.getLength();m++)
                                                                {
                                                                    switch (currNode.getNodeName().toUpperCase())
                                                                    {
                                                                        case "UNITPRICE":
                                                                            order.items.details.unitPrice = Float.parseFloat(currNode.getTextContent());
                                                                            break;
                                                                        case "SIZE":
                                                                            order.items.details.size = currNode.getTextContent();
                                                                            break;
                                                                        case "COLOR":
                                                                            order.items.details.color = currNode.getTextContent();
                                                                            break;
                                                                        case "PATTERN":
                                                                            order.items.details.pattern = currNode.getTextContent();
                                                                            break;
                                                                        case "BRAND":
                                                                            order.items.details.brand = currNode.getTextContent();
                                                                            break;
                                                                    }
                                                                }
                                                                break;
                                                        }
                                                    
                                                    }
                                                    
                                                }
                                                break;
                                                
                                            case "OFFICE":
                                                order.office = new Office();
                                                order.office.location = getNodeAttribute(currNode,"location");
                                                NodeList officeKids = currNode.getChildNodes();
                                                for(int k = 0;k<officeKids.getLength();k++)
                                                {
                                                    currNode = officeKids.item(k);
                                                    if(currNode instanceof Element)
                                                    {
                                                        switch (currNode.getNodeName().toUpperCase())
                                                        {
                                                            case "MANAGERS":
                                                                order.office.managers = new ArrayList<>();
                                                                NodeList managerKids = currNode.getChildNodes();
                                                                for(int h = 0;h<managerKids.getLength();h++)
                                                                {
                                                                    currNode = managerKids.item(h);
                                                                    if(currNode instanceof Element)
                                                                    {
                                                                        switch (currNode.getNodeName().toUpperCase())
                                                                        {
                                                                            case "MANAGER":
                                                                                Manager manager = new Manager();
                                                                                manager.id = Integer.parseInt(getNodeAttribute(currNode,"id"));
                                                                                manager.name = currNode.getTextContent();
                                                                                order.office.managers.add(manager);
                                                                                break;
                                                                        }
                                                                    }
                                                                }
                                                                break;
                                                        }
                                                    }     
                                                }
                                                break;
                                        }
                                        
                                    }
                                    
                                }
                                break;
                            
                        }
                    }
                    
                    
                }
                
                
              store.printSomeInfoAboutStore();
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
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
                                        