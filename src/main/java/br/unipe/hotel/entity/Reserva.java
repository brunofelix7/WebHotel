package br.unipe.hotel.entity;

import br.unipe.hotel.repository.EnumStatus;
import br.unipe.hotel.repository.EnumPagamento;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Reserva extends AbstractEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "data_inicial")
    @Temporal(TemporalType.DATE)
    //@NotNull(message = "Campo Data Inicial é obrigatório")
    @DateTimeFormat(pattern = "dd/MM/yyyy")	//	Formata a data
    private Date dataInicial;
    
    @Column(name = "data_final")
    @Temporal(TemporalType.DATE)
    //@NotNull(message = "Campo Data Final é obrigatório")
    @DateTimeFormat(pattern = "dd/MM/yyyy")	//	Formata a data
    private Date dataFinal;
    
    @Column(name = "data_checkin")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")	//	Formata a data
    private Date dataCheckIn;
    
    @Column(name = "data_checkout")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")	//	Formata a data
    private Date dataCheckOut;

    @Column(name = "forma_pagamento")
    @Enumerated(EnumType.STRING)
    private EnumPagamento formaDePagamento;
    
    @Enumerated(EnumType.STRING)
    private EnumStatus status;
    
    @Column(name = "pago")
    private String pago;
    
    @Column(name = "valor_pago")
    //@NotNull(message = "Campo Valor Pago é obrigatório")
    @NumberFormat(pattern = "#,##0.00")
    private Double valorPago;
    
    @ManyToOne
    @NotNull(message = "Por favor, selecione um Quarto")
    private Quarto quarto;
    
    @ManyToOne
    @NotNull(message = "Por favor, selecione um Hospede")
    private Hospede hospede;
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getDataInicial() {
        return dataInicial;
    }
    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }
    public Date getDataFinal() {
        return dataFinal;
    }
    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
    public Date getDataCheckIn() {
        return dataCheckIn;
    }
    public void setDataCheckIn(Date dataCheckIn) {
        this.dataCheckIn = dataCheckIn;
    }
    public Date getDataCheckOut() {
        return dataCheckOut;
    }
    public void setDataCheckOut(Date dataCheckOut) {
        this.dataCheckOut = dataCheckOut;
    }
    public String getPago() {
		return pago;
	}
	public void setPago(String pago) {
		this.pago = pago;
	}
	public EnumPagamento getFormaDePagamento() {
        return formaDePagamento;
    }
    public void setFormaDePagamento(EnumPagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }
    public EnumStatus getStatus() {
        return status;
    }
    public void setStatus(EnumStatus status) {
        this.status = status;
    }
	public Double getValorPago() {
		return valorPago;
	}
	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}
	public Quarto getQuarto() {
		return quarto;
	}
	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}
	public Hospede getHospede() {
		return hospede;
	}
	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}
    
}
