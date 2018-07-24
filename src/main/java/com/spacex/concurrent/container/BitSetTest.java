package com.spacex.concurrent.container;

import java.util.BitSet;

public class BitSetTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        BitSet bitSet = new BitSet();
        int position = 10;
        int position2 = 100;

        bitSet.set(position);
        bitSet.set(position2);
        bitSet.set(1000);
        System.out.println(String.format("bitSet#get:%s", bitSet.get(position)));
        System.out.println(String.format("bitSet#get(position2):%s", bitSet.get(position2)));
        System.out.println(String.format("bitSet#get(9000):%s", bitSet.get(9000)));
        bitSet.flip(position);
        System.out.println(String.format("bitSet#flit:%s", bitSet.get(position)));
        bitSet.flip(position);
        System.out.println(String.format("bitSet#flit:%s", bitSet.get(position)));
        System.out.println(String.format("bitSet#size:%s", bitSet.size()));
    }
}
