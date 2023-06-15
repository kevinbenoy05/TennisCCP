import java.util.ArrayList;
public class Queue{
    private ArrayList<String> list;
    public Queue(){
        list = new ArrayList<>();
    }
    public boolean isEmpty() {
        return list.isEmpty();
    }
    public void add(String x) {
        list.add(x);
    }
    public Object remove() {
        return list.remove(0);
    }
    public Object peek() {
        return list.get(0);
    }
    public Object get(int i){return list.get(i);}
    public int size() {
        return list.size();
    }
}
