package com.zhunusov;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        if(args.length!=1){
            System.exit(-1);
        }
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(new Account(0, 1000));
        accounts.add(new Account(1, 1000));
        accounts.add(new Account(2, 1000));

        try {
            int id, from, to;
            float amount;
            File xmlFile = new File(args[0]);
            if(!(xmlFile.isFile() && (xmlFile.getName().endsWith(".xml") || xmlFile.getName().endsWith(".txt")))){
                throw new IOException("It is not xml/txt file!");
            }
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("transaction");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println();
                System.out.println("Текущий элемент: " + node.getNodeName());
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    id =  Integer.parseInt(element.getAttribute("id"));
                    from = Integer.parseInt(element.getAttribute("from"));
                    to = Integer.parseInt(element.getAttribute("to"));
                    amount = Float.parseFloat(element.getAttribute("amount"));

                    System.out.println("ID trans: " + id);
                    System.out.println("From: " + from);
                    System.out.println("To: " + to);
                    System.out.println("Amount: " + amount);

                    int finalFrom = from;
                    int finalTo = to;
                    if(accounts.stream().anyMatch(Account -> Account.getID()== finalFrom) &&
                            accounts.stream().anyMatch(Account -> Account.getID()==finalTo)){

                        float finalAmount = amount;
                        int finalId = id;
                        Thread thread = new Thread(() -> {
                            boolean success = Transaction.operation(
                                    accounts.stream().filter(Account -> Account.getID() == finalFrom).findFirst().get(),
                                    accounts.stream().filter(Account -> Account.getID() == finalTo).findFirst().get(),
                                    finalAmount);
                            if(success) {
                                System.out.println("Транзакция id=" + finalId + " выполнена!");
                            } else {
                                System.out.println("ГАЛЯ, ОТМЕНА id=" + finalId + "!");
                            }
                        });
                        thread.start();
                    } else {
                        System.out.println("Счет From или To не существует! id="+id);
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}