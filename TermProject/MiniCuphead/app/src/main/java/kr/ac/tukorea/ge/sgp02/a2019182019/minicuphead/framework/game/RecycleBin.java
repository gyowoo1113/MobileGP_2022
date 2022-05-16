package kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.game;

import java.util.ArrayList;
import java.util.HashMap;

import kr.ac.tukorea.ge.sgp02.a2019182019.minicuphead.framework.interfaces.Recyclable;

public class RecycleBin {
    private static HashMap<Class, ArrayList<Recyclable>> recycleBin = new HashMap<>();
    public static void init() {
        recycleBin.clear();
    }
    public static Recyclable get(Class clazz) {
        ArrayList<Recyclable> bin = recycleBin.get(clazz);
        if (bin == null) return null;
        if (bin.size() == 0) return null;
        return bin.remove(0);
    }

    public static void add(Recyclable object) {
        Class clazz = object.getClass();
        ArrayList<Recyclable> bin = recycleBin.get(clazz);
        if (bin == null) {
            bin = new ArrayList<>();
            recycleBin.put(clazz, bin);
        }
        if (bin.indexOf(object) >= 0) {
            // already exists in the recycle bin
            return;
        }
        bin.add(object);
    }
}
