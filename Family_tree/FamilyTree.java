package Family_tree;

import java.util.ArrayList;
import java.util.List;

public class FamilyTree {
    private int humansId;
    private List<Human> humanList;

    public FamilyTree() {
        this(new ArrayList<>());
    }
    public FamilyTree(List<Human> humanList){
        this.humanList = humanList;
    }
    public boolean add(Human human) {
        if (human == null){
            return false;
        }
        if (!humanList.contains(human)){
            humanList.add(human);
            human.setId(humansId++);

            addToParents(human);
            addToChildren(human);

            return true;
        }
        return false;
    }
    public Human getById(int id) {
        if (!checkId(id)){
            return null;
        }
        for (Human e : humanList) {
            if (e.getId() == id){
                return e;
            }
        }
        return null;
    }
    public List<Human> getSibLings(int id) {
        Human human = getById(id);
        if (human == null) {
            return null;
        }
        List<Human> res = new ArrayList<>();
        for (Human parent : human.getParents()) {
            for (Human child : parent.getChildren()) {
                if (!child.equals(human) && !res.contains(child)){
                    res.add(child);
                }
            }
        }
        return res;
    }
    public List<Human> getByName(String name) {
        List<Human> res = new ArrayList<>();
        for (Human human : humanList) {
            if (human.getName().equals(name)) {
                res.add(human);
            }
        }
        return res;
    }
    private void addToParents(Human human) {
        for (Human parent : human.getParents()){
            parent.addChild(human);
        }
    }
    private void addToChildren(Human human) {
        for (Human child : human.getChildren()) {
            child.addParent(human); 
        }
    }
    private boolean checkId(long id) {
        return id < humansId && id >= 0;
    }
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("В дереве ");
        sb.append(humanList.size());
        sb.append(" Объектов: \n");
        for (Human human : humanList) {
            sb.append(human);
            sb.append("\n");
        }
        return sb.toString();
    }
    @Override
    public String toString(){
        return getInfo();
    }
}
