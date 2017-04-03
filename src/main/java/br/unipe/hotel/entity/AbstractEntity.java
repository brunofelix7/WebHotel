package br.unipe.hotel.entity;

public abstract class AbstractEntity {
    
    public abstract void setId(Long id);
    public abstract Long getId();
    
    public boolean hasValidId(){
		return getId() != null  && getId() > 0;
	}
    
}
