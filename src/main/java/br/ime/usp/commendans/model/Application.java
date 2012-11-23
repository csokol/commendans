package br.ime.usp.commendans.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Application {
    
    @Id @GeneratedValue
    private Long id;
    
    private String name;
    
    private String accessKey;

    @Deprecated
    protected Application() {
    }
    
    public Application(String name, String accessKey) {
        this.name = name;
        this.accessKey = accessKey;
    }
    
    public String getName() {
        return name;
    }

}
