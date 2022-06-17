package myset;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public final class MySet {

    private MySet() {
        // TODO Auto-generated constructor stub
    }
    /**
     * Hàm sinh tập hợp với kích thước và khoảng giá trị.
     * @param size
     * @param range
     * @return ArrayListr
     */
    public static Set<Integer> generateSet(final int size, final int range) {
        Set<Integer> newSet = new HashSet<Integer>(size, 1);
        Random rand = new Random();
        while (newSet.size() < size) {
            newSet.add(rand.nextInt(range) + 1);
        }
        return newSet;
    }
    /**
     * Hàm hợp hai tập hợp.
     * @param setA
     * @param setB
     * @return trả về Tập hợp giữa hai tập đầu vào
     */
    public static Set<Integer> unionTwoSet(final Set<Integer> setA,
                                            final Set<Integer> setB) {
        Set<Integer> unionSet = new HashSet<Integer>(setA.size()
                + setB.size(), 1);
        unionSet.addAll(setA);
        unionSet.addAll(setB);
        return unionSet;
    }
    /**
     * Hàm giao giữa hai tập hợp.
     * @param setA
     * @param setB
     * @return set
     */
    public static Set<Integer> intersectionTwoSets(final Set<Integer> setA,
            final Set<Integer> setB) {
        Set<Integer> intersectionSet = new HashSet<Integer>(setA.size() + 1,
                1);
        Iterator<Integer> itr = setB.iterator();
        Integer tmp;
        while (itr.hasNext())  {
            tmp = itr.next();
            if (setA.contains(tmp)) {
                intersectionSet.add(tmp);
            }
        }
        return intersectionSet;
    }
}
