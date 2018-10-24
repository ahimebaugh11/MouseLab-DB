import java.util.Date;

public class Mouse {

    private String ID;
    private char sex;
    private Date birthday;
    private int status = -1;
    /*
    0: Nursing
    1: Being genotyped
    2: Storage
    3: Mating
    4: Dead
     */
    private Mouse mother;
    private Mouse father;
    private Genotype genotype;

    /**
     * Constructs an 'empty' mouse.
     */
    Mouse(){
        this.ID = "";
        this.sex = 'N';
        this.birthday = null;
        this.mother = null;
        this.father = null;
        this.genotype = null;
    }

    /**
     * Constructs a mouse with the information typically available at weaning.
     * <code>Status</code> is automatically set to "Being genotyped".
     *
     * @param ID the identifier for the new mouse
     * @param sex the sex of the mouse: M for male, F for female
     * @param birthday the date the mouse was born on
     * @param mother the mother of the mouse or <code>null</code> if unknown
     * @param father the father of the mouse or <code>null</code> if unknown
     * @see Date
     */
    Mouse(String ID, char sex, Date birthday, Mouse mother, Mouse father){
        this.ID = ID;
        this.sex = sex;
        this.birthday = birthday;
        this.status = 1;
        this.mother = mother;
        this.father = father;
    }
    
    /**
     * Constructs a copy of a given mouse. New copies are created of every field.
     * @param other a fully initialized mouse to copy
     */
    Mouse(Mouse other){
        this.ID = other.getID();
        this.sex = other.getSex();
        this.birthday = (Date)other.getBirthday().clone();
        this.status = other.getStatusInt();
        this.mother = new Mouse(other.getMother());
        this.father = new Mouse(other.getFather());
        this.father = new Genotype(other.getGenotype());
    }

    //Accessor methods
    /**
     * Gets this mouse's ID
     * @return the mouse's identifier
     */
    public String getID(){
        return this.ID;
    }
    /**
     * Gets this mouse's sex
     * @return the mouse's sex: M for male, F for female
     */
    public char getSex(){
        return this.sex;
    }
    /**
     * Gets this mouse's birthday
     * @return the mouse's birthday
     */
    public Date getBirthday(){
        return this.birthday;
    }
    /**
     * Gets this mouse's status
     * @return the mouse's status
     * @throws RuntimeException if the mouse's status was never initialized
     */
    public String getStatus() throws RuntimeException{
        switch(this.status){
            case 0: return "Nursing";
            case 1: return "Being genotyped";
            case 2: return "Storage";
            case 3: return "Mating";
            case 4: return "Dead";
            default: throw new RuntimeException("This mouse's status has not been initialized");
        }
    }
    /**
     * Gets this mouse's status integer
     * @return the mouse's status integer
     * @throws RuntimeException if the mouse's status was never initialized
     */
    public int getStatusInt() throws RuntimeException{
        if(this.status<0)
            throw new RuntimeException("This mouse's status has not been initialized");
        return status;
    }
    /**
     * Gets this mouse's mother. If the birth mother is unknown, returns <code>null</code>.
     * @return this mouse's mother or <code>null</code> if unknown
     */
    public Mouse getMother(){
        return this.mother;
    }
    /**
     * Gets this mouse's father. If the birth father is unknown, returns <code>null</code>.
     * @return this mouse's father or <code>null</code> if unknown
     */
    public Mouse getFather(){
        return this.father;
    }
    /**
     * Gets this mouse's genotype
     * @return this mouse's genotype
     */
    public Genotype getGenotype(){
        return this.genotype;
    }

    // Modifier methods
    /**
     * Defines the identifier of the mouse. This is for ease of searching. There is no need for this to be unique
     * @param ID a way to identify the mouse
     */
    public void setID(String ID){
        this.ID = ID;
    }
    /**
     * Defines the sex of the mouse, either M or F representing male or female, respectively
     * @param sex a character representation for the sex of the mouse
     * @return true if setting the sex was successful, false otherwise
     */
    public boolean setSex(char sex){
        if(sex == 'M' || sex == 'F') {
            this.sex = sex;
            return true;
        }
        return false;
    }
    /**
     * Defines the birthday of the mouse or approximate birthday if the true date is unknown
     * @param birthday the mouse's date of birth
     */
    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }
    /**
     * Defines the status of the mouse using the status integer: 1-Nursing, 2-Being genotyped, 3-Storage, 4-Mating,
     * 5-Dead
     * @param status the mouse's status integer
     * @return true if the status integer is recognized and set, false otherwise
     */
    public boolean setStatus(int status){
        if(status>=0 && status<=4) {
            this.status = status;
            return true;
        }
        return false;
    }
    /**
     * Defines the status of the mouse using the status <code>String</code>
     * @param status the mouse's status <code>String</code>
     * @return true if the status <code>String</code> is recognized and set, false otherwise
     */
    public boolean setStatus(String status){
        switch(status) {
            case "Nursing":
                this.status = 0;
                return true;
            case "Being genotyped":
                this.status = 1;
                return true;
            case "Storage":
                this.status = 2;
                return true;
            case "Mating":
                this.status = 3;
                return true;
            case "Dead":
                this.status = 4;
                return true;
            default:
                return false;
        }
    }
    /**
     * Defines the mouse's mother. The mother must have a valid status to be used or must be <code>null</code>. A
     * <code>null</code> mother indicates the birth mother is unknown.
     * @param mother the mouse's mother
     * @return true if the mother has a valid status or is null, false otherwise
     */
    public boolean setMother(Mouse mother){
        if(mother==null){
            this.mother = null;
            return true;
        }
        try{
            mother.getGenotype();
        } catch(RuntimeException ex){
            return false;
        }
        this.mother = mother;
        return true;
    }
    /**
     * Defines the mouse's father. The father must have a valid status to be used or must be <code>null</code>. A
     * <code>null</code> father indicates the birth father is unknown.
     * @param father the mouse's father
     * @return true if the father has a valid status or is null, false otherwise
     */
    public boolean setFather(Mouse father){
        if(father==null){
            this.father = null;
            return true;
        }
        try{
            father.getGenotype();
        } catch(RuntimeException ex){
            return false;
        }
        this.father = father;
        return true;
    }
    /**
     * Defines the mouse's genotype. There are no checks to see if this is a valid genotype.
     * @param genotype the mouse's genotype
     */
    public void setGenotype(Genotype genotype){
        this.genotype = genotype;
    }

    /**
     * Calculates the mouse's age in weeks
     * @return the mouse's age in weeks
     */
    public int getAge(){
        Date today = new Date();
        long age = today.getTime() - this.birthday.getTime();
        long milliSecInWeek = 604800000;
        return (int)(age/milliSecInWeek);
    }
    
    /**
     * Tests to see if the two mice have the same genotype
     * @param otherMouse the mouse to test against
     * @return true if the two mice's genotypes are the same, false otherwise
     */
    public boolean equals(Mouse otherMouse) {
        return this.genotype.equals(otherMouse.getGenotype());
    }

    /**
     * Translates all fields into a useful form for display
     * @return all information contained in <code>Mouse</code> in <code>String</code> format
     */
    @Override
    public String toString() {
        return "ID: "+this.ID
            +"\nSex: "+this.sex
            +"\nDOB: "+this.birthday
            +"\nStatus:"+this.getStatus()
            +"\nMother:"+this.mother.getID()
            +"\nFather:"+this.father.getID()
            +"\nGenotype: "+this.genotype;
    }
}
