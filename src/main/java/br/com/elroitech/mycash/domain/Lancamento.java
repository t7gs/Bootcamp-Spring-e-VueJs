package br.com.elroitech.mycash.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;


@Entity
public class Lancamento {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "Campo descrição é obrigatório")
	@Size(max = 50)
	private String descricao;
	
	
	@Column(name = "vl_lancamento", precision = 8, scale = 2)
	@Min(value = 0, message = "O Campo deve ser maior que zero")
	private Double valor;
	
//	@FutureOrPresent
	@NotNull(message = "campo data é obrigatório") //para todos os campos e @Notblank para string
	private LocalDate data;
	
	@Enumerated(EnumType.STRING)
	//@Column(nullable = false)
	@NotNull(message = "campo tipo é obrigatório")
	private LancamentoTipo tipo;

	
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LancamentoTipo getTipo() {
		return tipo;
	}

	public void setTipo(LancamentoTipo tipo) {
		this.tipo = tipo;
	}

}
