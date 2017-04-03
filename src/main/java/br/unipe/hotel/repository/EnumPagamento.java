package br.unipe.hotel.repository;

public enum EnumPagamento {
    
    DINHEIRO("Dinheiro"),
    DEBITO("Debito"),
    CREDITO("Credito");
    
    private String descricao;
    
    EnumPagamento(String descricao){
        this.descricao = descricao;
    }
    
    public String getDescricao(){
        return descricao;
    }
    
}
