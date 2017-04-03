package br.unipe.hotel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Categoria extends AbstractEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotEmpty(message = "Campo Nome é obrigatório")
    @Column(nullable = true)
    private String nome;
    
    @NotEmpty(message = "Campo Descrição é obrigatório")
    @Column(nullable = true)
    private String descricao;
    
    @NotNull(message = "Campo Capacidade é obrigatório")
    @Column(nullable = true)
    @Min(value = 1, message = "Campo Capacidade deve possuir no mínimo 1")
    @Max(value = 3, message = "Campo Capacidade deve possuir no máximo 3")
    private Integer capacidade;
    
    @NotNull(message = "Campo Valor é obrigatório")
    @NumberFormat(pattern = "#,##0.00")
    @Column(nullable = true)
    private Double valor;

   
    public Categoria(){
    	super();
    }
    
    public Categoria(String nome, String descricao, int capacidade, double valor){
    	this.nome = nome;
    	this.descricao = descricao;
    	this.capacidade = capacidade;
    	this.valor = valor;
    }
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getCapacidade() {
        return capacidade;
    }
    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }

}
