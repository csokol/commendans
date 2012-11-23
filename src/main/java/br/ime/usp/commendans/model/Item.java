package br.ime.usp.commendans.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Item {
    @Id @GeneratedValue
    private Long id;
    
    private Long appItemId;
    
    @ManyToOne
    private Application app;

    @Deprecated
    protected Item() {
    }
    
    public Item(Long appItemId, Application app) {
        this.appItemId = appItemId;
        this.app = app;
    }
    
    public Long getAppItemId() {
        return appItemId;
    }
    
    public Application getApp() {
        return app;
    }
    
    @Override
    public String toString() {
        return "Item " + id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((app == null) ? 0 : app.hashCode());
        result = prime * result
                + ((appItemId == null) ? 0 : appItemId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (app == null) {
            if (other.app != null)
                return false;
        } else if (!app.equals(other.app))
            return false;
        if (appItemId == null) {
            if (other.appItemId != null)
                return false;
        } else if (!appItemId.equals(other.appItemId))
            return false;
        return true;
    }
}
