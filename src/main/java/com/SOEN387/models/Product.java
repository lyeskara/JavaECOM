package com.SOEN387.models;

public class Product {
    private String name;
    private String description;
    private String vendor; 
    private String urlSlug;
    private String sku;
    private double price;

    public Product(String name, String description, String vendor, String urlSlug, String sku, double price) {
        this.name = name;
        this.description = description;
        this.vendor = vendor;
        this.urlSlug = urlSlug;
        this.sku = sku;
        this.price = price;
    }
    
    // deep copy constructor
   
    public Product(Product updateProduct) {
    	  this.name = updateProduct.name;
          this.description = updateProduct.description;
          this.vendor = updateProduct.vendor;
          this.urlSlug = updateProduct.urlSlug;
          this.sku = updateProduct.sku;
          this.price = updateProduct.price;
	}
    
    // Getters and setters for all attributes


	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getUrlSlug() {
        return urlSlug;
    }

    public void setUrlSlug(String urlSlug) {
        this.urlSlug = urlSlug;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
