package co.edu.javeriana.ws.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Article {
    String author;
    String name;

    public Article(){

    }

    public String getAuthor(){
        return this.author;
    }

    public String getName(){
        return this.name;
    }

    public void setAuthor(String author){
        this.author = author;
    }
    public void setName(String name){
        this.name = name;
    }
    
}
