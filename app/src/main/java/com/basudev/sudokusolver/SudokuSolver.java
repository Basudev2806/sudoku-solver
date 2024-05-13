package com.basudev.sudokusolver;

import java.util.Random;

class SudokuSolver{
    public static boolean isValid(int[][] gameboard)
    {

        //checking for rows
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                for(int k = j+1; k < 9; k++)
                {
                    if(gameboard[i][j] == gameboard[i][k] && gameboard[i][j] != 0)
                    {
                        System.out.println("Rows");
                        return false;
                    }
                }
            }
        }
        //checking for columns
        for(int j = 0; j < 9; j++)
        {
            for(int i = 0; i < 9; i++)
            {
                for(int k = i+1; k < 9; k++)
                {
                    if(gameboard[i][j] == gameboard[k][j] && gameboard[i][j]!= 0)
                    {
                        System.out.println("Columns");
                        return false;
                    }
                }
            }
        }
        //checking for 3 by 3 squares
        for(int m = 0; m < 3; m++)
        {
            for(int n = 0; n < 3; n++)
            {
                for(int i = 0; i < 3;i++)
                {
                    for(int j = 0; j < 3; j++)
                    {
                        for(int a = 0; a < 3; a++)
                        {
                            for(int b = 0; b < 3; b++)
                            {
                                if(gameboard[3*m + i][3*n + j] == gameboard[3*m +a][3*n + b] && gameboard[3*m+i][3*n+j] != 0 && (a!=i && b!=j))
                                {
                                    System.out.println("Squares");
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    public static boolean isSafe(int[][] gameboard, int row, int column, int entry)
    {
        // we assume that gameboard is solvable
        // then we only need to check whether new entry makes it unsolvable in respective row, column, 3 x 3 grid

        //checking row
        for(int i = 0; i < 9; i++)
        {
            if(gameboard[row][i] == entry)
            {
                return false;
            }
        }

        //checking column
        for(int i = 0; i < 9; i++)
        {
            if(gameboard[i][column] == entry)
            {
                return false;
            }
        }

        //checking 3 x 3 squares
        for(int i = (row -(row % 3)); i < (3 + (row -(row % 3))); i++)
        {
            for(int j = (column - (column % 3)); j < (3 + (column - (column % 3))); j++)
            {
                if(gameboard[i][j] == entry)
                {
                    return false;
                }
            }
        }
        return true;
    }



    //this method is a helper method that helps find the empty cell and returns whether the the gameboard has empty cells
    // true - there are emmpty cells left; false - no empty cell are left
    //location[0] represents a row, location[1] represents a column
    public static boolean hasEmpty(int[][] gameboard, int[] location)
    {

        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                if(gameboard[i][j] == 0)
                {
                    location[0] = i;
                    location[1] = j;
                    return true;
                }
            }
        }
        return false;
    }

    //assume that initially board doesn't have any mistakes
    public static boolean solveSudoku(int[][] gameboard)
    {
        int[] location = new int[2];
        if(hasEmpty(gameboard, location))
        {
            for(int i = 1; i < 10; i++)
            {
                if(isSafe(gameboard, location[0], location[1], i))
                {
                    gameboard[location[0]][location[1]] = i;
                    if(solveSudoku(gameboard))
                    {
                        return true;
                    }
                    else
                    {
                        gameboard[location[0]][location[1]] = 0;
                    }
                }
            }
        }
        else
        {
            return true;
        }
        return false;
    }

    //same as solveSudoku, but returns 2 if number of unique sollutions is more than 1, returns 1 and 0 if the number of solutions is 1 and 0 accordingly
    public static int uniqueSolutions(int[][] gameboard)
    {
        //to keep track of number of solutions
        int count = 0;
        int[] location = new int[2];
        if(hasEmpty(gameboard, location))
        {
            for(int i = 1; i < 10;i++)
            {
                if(isSafe(gameboard, location[0], location[1], i))
                {
                    gameboard[location[0]][location[1]] = i;
                    count += uniqueSolutions(gameboard);
                    if(count >= 2)
                    {
                        return 2;
                    }
                    else {
                        gameboard[location[0]][location[1]] = 0;
                    }
                }
            }
        }
        else{
            return 1;
        }
        return count;
    }

    public static void printBoard(int[][]gameboard)
    {
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                System.out.print(gameboard[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");
    }

    //helper function
    public static boolean hasEmptyRandom(int[][] gameboard, int[] location, int[]coordinates)
    {

        for(int i = 0; i < 81; i++){
            int coordinate = coordinates[i];
            if(gameboard[coordinate / 9][coordinate % 9] == 0){
                location[0] = coordinate / 9;
                location[1] = coordinate % 9;
                return true;
            }
        }
        return false;
    }
    public static void shuffleArray(int[] array){
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            int index = rand.nextInt(i + 1);
            // Simple swap
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }


    public static void GenerateRandomCompleteSudoku(int[][] gameboard){
        int[] coordinates = new int[81];
        for( int k = 0; k < 81; k++){
            coordinates[k] = k;
        }
        int[] tobeShuffled = new int[30];
        for(int i = 0; i < 30; i++){
            tobeShuffled[i] = i;
        }
        shuffleArray(tobeShuffled);
        System.arraycopy(tobeShuffled, 0, coordinates, 0, 30);


        //shuffle coordinates
        //for optimization purposes, i will only shuffle the first 30 coordinates


        RandomCompleteSudoku(gameboard, coordinates);

    }

    //helper-function
    public static boolean RandomCompleteSudoku(int[][] gameboard, int[] coordinates)
    {
        //using the alternative of SolveSudoku, where picking cells is randomized
        int[] location = new int[2];
        if(hasEmptyRandom(gameboard, location, coordinates))
        {
            for(int i = 1; i < 10; i++){
                if(isSafe(gameboard, location[0], location[1], i))
                {
                    gameboard[location[0]][location[1]] = i;
                    if(RandomCompleteSudoku(gameboard, coordinates))
                    {
                        return true;
                    }
                    else
                    {
                        gameboard[location[0]][location[1]] = 0;
                    }
                }
            }
        }
        else
        {
            return true;
        }
        return false;
    }

    //N indicated the number of empty cells, it has to be less than 64
    public static void GenerateSudoku(int[][] gameboard, int N)
    {
        boolean valid = false;
        //there is currently a bug that I am still working on for higher values of N. putzeros function generates the incorrect gameboard
        //temporary solution is to regenerate the gameboard since the problem only occurs around 15% times for high values of N(like N = 50).
        //the error wasn't observed for smaller values N
        while(!valid){
            //empty array
            int[][] array = new int[9][9];
            for(int i = 0; i < 9;i++){
                for(int j = 0; j < 9; j++){
                    array[i][j] = 0;
                }
            }

            GenerateRandomCompleteSudoku(array);
            if(isValid(array)){
                putZeros(array, N);
            }
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    gameboard[i][j] = array[i][j];
                }
            }
            solveSudoku(array);
            if(isValid(gameboard)){
                valid = true;
            }
        }
    }
    //helper function
    public static boolean putZeros(int[][] gameboard, int N)
    {   int[] location = new int[2];
        int value;
        if(numofZeros(gameboard) == N){
            System.out.println("KJ");
            return true;
        }
        else{
            for(int i = 0; i < 3 ; i++)
            {
                if(!chooseCell(gameboard, location)){
                    return false;
                }
                else
                {
                    value = gameboard[location[0]][location[1]];
                    gameboard[location[0]][location[1]] = 0;
                    if(putZeros(gameboard, N))
                    {
                        return true;
                    }
                    else
                    {
                        //backtracking
                        gameboard[location[0]][location[1]] = value;
                    }
                }
            }


        }
        return false;

    }
    //helper function
    public static boolean chooseCell(int[][] gameboard, int[] location)
    {
        //choose the random cell
        int random = (int)Math.floor(Math.random() * 81);
        int row = random / 9, column = random % 9;
        int value = gameboard[row][column];
        gameboard[row][column] = 0;
        int unique = uniqueSolutions(gameboard);
        gameboard[row][column] = value;
        if(value == 0 || unique != 1 )
        {
            //choose the next non-empty cell which leads to unique solution
            for(int i = 0; i < 9; i++)
            {
                for(int j = 0; j < 9; j++)
                {
                    value = gameboard[i][j];
                    gameboard[i][j] = 0;
                    unique = uniqueSolutions(gameboard);
                    if(value != 0 &&  unique == 1)
                    {
                        location[0] = i;
                        location[1] = j;
                        return true;
                    }
                    gameboard[i][j] = value;
                }
            }
        }
        else{
            location[0] = row;
            location[1] = column;
            return true;
        }
        return false;
    }

    //returns the number of zeros
    public static int numofZeros(int[][] gameboard){
        int count = 0;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(gameboard[i][j] == 0){
                    count++;
                }
            }
        }
        return count;
    }


    public static void main(String[] args)
    {
        //was used mostly for debugging

    }


}