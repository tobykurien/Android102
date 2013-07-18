package com.myfamily.db;

public class FamilyMember {
	private long id;
	private long contactId;
	private String name;

	public FamilyMember() {
	}

	public FamilyMember(long id, long contactId, String name) {
		super();
		this.id = id;
		this.contactId = contactId;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getContactId() {
		return contactId;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
