package com.revature.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="spells")
@Data @AllArgsConstructor @NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Spell {

	@Id
	@Column(name="spell_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="school_id", nullable=false)
	private School school;
	
	@Column(name="level")
	@Min(value=0)
	@Max(value=9)
	private int level;
	
	@Column(name="casting_time")
	private String castingTime;
	
	@Column(name="range")
	private String range;
	
	@Column(name="components")
	private String components;
	
	@Column(name="material")
	private String material;
	
	@Column(name="duration")
	private String duration;
	
	@Column(name="ritual")
	private boolean ritual;
	
	@Column(name="concentration")
	private boolean concentration;
	
	@Column(name="description")
	private String description;
	
	@Column(name="higher_level")
	private String higherLevel;
	
	@ManyToMany
	@JoinTable(name="spells_classes",
	joinColumns=@JoinColumn(name="spell_id"),
	inverseJoinColumns=@JoinColumn(name="class_id"))
	private List<Class> classes;
	
}
