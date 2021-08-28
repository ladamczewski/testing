package pl.testing.account;
public class Account {

    private boolean active;
    private Address defaultDeliveryAddress;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email.matches("^(.+)@(.+)$")){
        this.email = email;
        }else {
            throw new IllegalArgumentException("Wrong email format");
        }
    }

    public Account(){
        this.active = false;
    }
    public Account(Address defaultDeliveryAddress){
        this.defaultDeliveryAddress = defaultDeliveryAddress;
        if(defaultDeliveryAddress != null){
            activate();
        }else {
            this.active = false;
        }
    }

    public void activate(){
        this.active = true;
    }

    public boolean isActive(){
        return this.active;
    }

    public Address getDefaultDeliveryAddress() {
        return defaultDeliveryAddress;
    }

    public void setDefaultDeliveryAddress(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
    }
}
