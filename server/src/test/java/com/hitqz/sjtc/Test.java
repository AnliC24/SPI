package com.hitqz.sjtc;


import java.util.HashSet;
import java.util.Set;

public class Test {

    public int findRepeatNumber(int[] nums) {

        int size = nums.length;
        if(size < 2 || size > 100000){
            return 0;
        }

        Set<Integer> set = new HashSet();
        int setSize = 0;
        for(int i = 0; i<size; i++){
            setSize = set.size();
            set.add(nums[i]);
            if(set.size()>setSize){
                return nums[i];
            }
        }

        // for(int i = 0; i < size; i++){
        //     int a = nums[i];
        //     for(int j = i+1; j < size; j++){
        //         if(a == nums[j]){
        //             return a;
        //         }
        //     }
        // }

        return 0;
    }
}
