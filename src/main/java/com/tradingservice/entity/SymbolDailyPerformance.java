package com.tradingservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "SymbolDailyPerformance")
@AllArgsConstructor
@NoArgsConstructor
public class SymbolDailyPerformance {
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sym_seq")
	@SequenceGenerator(name="sym_seq", sequenceName = "sym_daily_sequence")
	private Long id;
	
	private String day;
	private String open;
	private String high;
	private String low;
	private String close;
	private String volume;
	private String symbol;

}
