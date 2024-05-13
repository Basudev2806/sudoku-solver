package com.basudev.sudokusolver

import android.graphics.Color
import android.widget.TextView

object Helper {
    fun cleanArray(array: Array<IntArray>){
        for(i in 0..8){
            for(j in 0..8){
                array[i][j] = 0
            }
        }

    }
    fun cleanArray(array: Array<Array<TextView?>>){
        for(i in 0..8){
            for(j in 0..8){
                array[i][j]?.text = ""
                array[i][j]?.setBackgroundColor(Color.WHITE)
                array[i][j]?.setTextColor(Color.BLACK)
            }
        }
    }
    fun numCellsLeft(array: Array<IntArray>):Int{
        var result = 0
        for(i in 0..8){
            for(j in 0..8){
                if(array[i][j] == 0){
                    result++
                }
            }
        }
        return result
    }

}