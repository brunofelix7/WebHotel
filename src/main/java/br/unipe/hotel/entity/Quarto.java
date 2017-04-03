package br.unipe.hotel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Quarto extends AbstractEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotEmpty(message = "Campo Número é obrigatório")
    @Column(nullable = true)
    private String numero;
    
    @NotEmpty(message = "Campo Andar é obrigatório")
    @Column(nullable = true)
    private String andar;
    
    @NotNull(message = "Por favor, selecione um Categoria")
    @ManyToOne
    private Categoria categoria;

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getAndar() {
        return andar;
    }
    public void setAndar(String andar) {
        this.andar = andar;
    }
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
