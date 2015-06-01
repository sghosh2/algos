/**
 * 
 */
package com.dsgn.prac.tictoe;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author sam
 *
 */
public class Team {
	
	public enum Symbol {
		X('X'), O('O'), E('-');
		
		char ch;
		Symbol(char ch) {
			this.ch = ch;
		}
		};
	
	private Set<String> ids;
	private String Name;
	private Symbol symbol;

	public Team(String Name, Symbol symbol) {
		this.Name = Name;
		ids = new HashSet<String>();
		this.symbol = symbol;
	}

	public void addPlayers(String id) {
		ids.add(id);
	}

	public String[] getPlayers() {
		String[] idsStr = new String[ids.size()];
		ids.toArray(idsStr);
		return idsStr;
	}

	public boolean isMember(String id) {
		return ids.contains(id);
	}

	public Set<String> getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids.addAll(Arrays.asList(ids));
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Symbol getSymbol() {
		return symbol;
	}

}
