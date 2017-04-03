package br.unipe.hotel.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class Endereco {
    
	@NotEmpty(message = "Campo Rua é obrigatório")
    private String logradouro;
    
	@NotEmpty(message = "Campo Cidade é obrigatório")
    private String cidade;
    
	@NotEmpty(message = "Campo Bairro é obrigatório")
    private String bairro;
    
	@NotEmpty(message = "Campo CEP é obrigatório")
    private String cep;
    
	@Size(max = 2, message = "Estado não pode conter mais de 2 caracteres")
	@NotEmpty(message = "Campo Estado é obrigatório")
    private String estado;
    
    @Column(nullable = true)
    @NotNull(message = "Campo Número é obrigatório")
    private Integer numero;

    
    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
    
}
