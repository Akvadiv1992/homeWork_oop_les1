package Family_tree;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Human {
    private int id;
    private String name;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private Gender gender;
    private Human mother;
    private Human father;
    private List <Human> children;

    Human(String name, LocalDate birthDate, LocalDate deathDate, Gender gender,
    Human mother, Human father) {
        id = -1;
        this.name = name;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.gender = gender;
        this.mother = mother;
        this.father = father;
        children = new ArrayList<>();
    }
    public Human(String name, LocalDate birthDate, Gender gender) {
        this(name, birthDate, null, gender, null, null);
    }
    public Human(String name, LocalDate birthDate, Gender gender, Human mother, Human father) {
        this(name, birthDate, null, gender, mother, father);
    }
    public boolean addChild(Human child) {
        if(!children.contains(child)){
            children.add(child);
            return true;
        }
        return false;
    }
    public boolean addParent(Human parent) {
        if (parent.getGender().equals(Gender.Male)){
            setFather(parent);
        }
        else if (parent.getGender().equals(Gender.Female)){
            setMother(parent);
        }
        return true;
    }
    public void setMother(Human mother) {
        this.mother = mother;
    }
    public void setFather(Human father) {
        this.father = father;
    }
    public Human getMother(){
        return mother;
    } 
    public Human getFather(){
        return father;
    }
    public List<Human> getParents() {
        List<Human> list = new ArrayList<>(2);
        if (father != null){
            list.add(father);
        }
        if (mother != null){
            list.add(mother);
        }
        return list;
    }
    public int getAge(){
        if (deathDate == null){
            return getPeriod(birthDate, LocalDate.now());
        } else {
            return getPeriod(birthDate, deathDate);
        }
    }
    private int getPeriod(LocalDate birthDate, LocalDate deathDate){
        Period diff = Period.between(birthDate, deathDate);
        return diff.getYears();
    }
    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public LocalDate getBirthDate(){
        return birthDate;
    }
    public LocalDate getDeathDate(){
        return deathDate;
    }
    public List<Human> getChildren(){
        return children;
    }
    public void setBirthDate(LocalDate birthDate){
        this.birthDate = birthDate;
    }
    public void setDeathDate(LocalDate deathDate){
        this.deathDate = deathDate;
    }
    public Gender getGender(){
        return gender;
    }
    @Override
    public String toString(){
        return getInfo();
    }
    public String getInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("id: ");
        sb.append(id);
        sb.append(", имя: ");
        sb.append(name);
        sb.append(", пол: ");
        sb.append(gender);
        sb.append(", возраст: ");
        sb.append(getAge());
        sb.append(", ");
        sb.append(getMotherInfo());
        sb.append(", ");
        sb.append(getFatherInfo());
        sb.append(", ");
        sb.append(getChildrenInfo());
        sb.append(".");
        return sb.toString();
    }
    public String getMotherInfo(){
        String res = "Мать: ";
        if (mother != null){
            res += mother.getName();
        } else {
            res += "неизвестна";
        }  
        return res;
    }
    public String getFatherInfo(){
        String res = "Отец: ";
        if (father != null){
            res += father.getName();
        } else {
            res += "неизвестен";
        }  
        return res;
    }
    public String getChildrenInfo(){
        StringBuilder res = new StringBuilder();
        res.append("дети: ");
        if (children.size() != 0){
            res.append(children.get(0).getName());
            for (int i = 1; i < children.size(); i++) {
                res.append(", ");
                res.append(children.get(i).getName());
            }
        } else {
            res.append("Отсутствуют");
        }
        return res.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Human)){
            return false;
        }
        Human human = (Human)obj;
        return human.getId() == getId();
    }
}