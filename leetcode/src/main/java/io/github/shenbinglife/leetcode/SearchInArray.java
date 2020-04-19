package io.github.shenbinglife.leetcode;

import java.util.ArrayList;
import java.util.Collections;

public class SearchInArray {

    public boolean Find(int target, int [][] array) {
        for(int i = 0; i < array.length; i++) {
            boolean res = find(target, array, i);
            if(res) {
                return true;
            }
        }
        return false;
    }

    boolean find(int target, int[][] array, int line) {
        int[] arrLine = array[line];
        if (arrLine.length == 0) {
            return false;
        }
        if(target > arrLine[arrLine.length-1]){
            return false;
        } else {
            for(int i = 0; i < arrLine.length; i++) {
                if(arrLine[i] == target){
                    return true;
                }
            }
            return false;
        }
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list= new ArrayList();
        while(listNode != null) {
            list.add(listNode.val);
            listNode=listNode.next;
        }
        Collections.reverse(list);
        return list;
    }

    public static void main(String[] args) {
        int[][] array = {{}};

        System.out.println(new SearchInArray().Find(7, array));
    }
}
