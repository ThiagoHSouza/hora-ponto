package br.com.developer.horaponto.core.domain.enums;

public enum StatusPoint {
	ENTRY, EXIT;

	public static StatusPoint getToggle(StatusPoint state){
		return state.equals(StatusPoint.ENTRY)? StatusPoint.EXIT : StatusPoint.ENTRY;
	}
}
