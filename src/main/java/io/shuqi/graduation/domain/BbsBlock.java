package io.shuqi.graduation.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 论坛版块
 * @author shuqi
 * @date   2015年5月4日
 * @version since 1.0
 */
public class BbsBlock {

	private Long id;
	private String name;
	private String blockRules;
	private String description;
	
	/**
	 * 自关联
	 */ 
	private Set<BbsBlock> children = new HashSet<BbsBlock>();
	private BbsBlock parent;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBlockRules() {
		return blockRules;
	}
	public void setBlockRules(String blockRules) {
		this.blockRules = blockRules;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<BbsBlock> getChildren() {
		return children;
	}
	public void setChildren(Set<BbsBlock> children) {
		this.children = children;
	}
	public BbsBlock getParent() {
		return parent;
	}
	public void setParent(BbsBlock parent) {
		this.parent = parent;
	}
	public BbsBlock(String name, String blockRules, String description, BbsBlock parent) {
		super();
		this.name = name;
		this.blockRules = blockRules;
		this.description = description;
		this.parent = parent;
	}
	public BbsBlock() {
		super();
	}
	
	
	
}
