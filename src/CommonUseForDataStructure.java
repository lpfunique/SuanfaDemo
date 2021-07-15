import java.util.*;

public class CommonUseForDataStructure {

    public static void main(String[] args) {

        // TreeMap操作
        Map<String, Integer> treeMap = new TreeMap<>(Comparator.naturalOrder());
        treeMap.put("One", 1);
        treeMap.put("Two", 2);
        treeMap.put("Three", 3);
        treeMap.put("Four", 4);
        treeMap.put("Five", 5);

        System.out.println("treeMap内容:");
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            System.out.println(entry);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(treeMap.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        System.out.println("排序后treeMap内容:");
        for (Map.Entry<String, Integer> e : list) {
            System.out.println(e.getKey() + ":" + e.getValue());
        }
    }

}
