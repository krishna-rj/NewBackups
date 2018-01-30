package com.test.xmlcreator;

public class Orders {

	
	int itemId;
	String description;
	String extendedDescription;
	
	public Orders(){
		
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExtendedDescription() {
		return extendedDescription;
	}
	public void setExtendedDescription(String extendedDescription) {
		this.extendedDescription = extendedDescription;
	}
	@Override
	public String toString() {
		return "Orders [itemId=" + itemId + ", description=" + description + ", extendedDescription="
				+ extendedDescription + "]";
	}
	
}
