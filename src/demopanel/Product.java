/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demopanel;

/**
 *
 * @author Ender
 */
public class Product {
    private int id;
    private String name;
    private float price;
    private String addDate;
    private byte[] picture;
    private int stock;

    public Product(int pid, String pname, float pprice, String paddDate, int pstock, byte[] ppicture) {
        this.id = pid;
        this.name = pname;
        this.price = pprice;
        this.addDate = paddDate;
        this.stock = pstock;
        this.picture = ppicture;
        
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getAddDate() {
        return addDate;
    }

    public byte[] getPicture() {
        return picture;
    }

    public int getStock() {
        return stock;
    }
    
    
    
}
