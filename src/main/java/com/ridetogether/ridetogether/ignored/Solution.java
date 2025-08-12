package com.ridetogether.ridetogether.ignored;

import java.util.*;

class Solution {
    public List<Integer> luckyNumbers(int[][] matrix) {
        //A lucky number is an element of the matrix such
        //that it is the minimum element in its row and maximum in its column.

        List<Integer> ls = new ArrayList<>();
        //idx of max, column idx
        HashMap<Integer, Integer> columnMap = new HashMap<>();

        //row idx, idx of min
        HashMap<Integer, Integer> rowMap = new HashMap<>();


        for (int column = 0; column < matrix.length; column++) {
            columnMap.put(findMaxIdx(matrix[column]), column);
        }
        //O(m)


        for (int row = 0; row < matrix.length; row++) {
            rowMap.put(row, findMinIdx(matrix[row]));
        }
        //O(n)

        //idxMax is the max num in a column
        //accordingly is an idx of a row
        for(int idxMax:columnMap.keySet()){
            if(rowMap.containsKey(idxMax)){
                ls.add(rowMap.get(idxMax));
            }
        }
        //O(m)


        return ls;
    }

    public int findMinIdx(int[] arr){
        int minIdx = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length ; i++) {
            if(arr[i] < arr[minIdx]){
                minIdx = i;
            }
        }
        return minIdx;
    }

    public int findMaxIdx(int[] arr){
        int maxidx = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length ; i++) {
            if(arr[i] > arr[maxidx]){
                maxidx = i;
            }
        }
        return maxidx;
    }




}