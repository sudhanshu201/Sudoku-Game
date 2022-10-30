import java.util.*;

public class Board {

    // An AL of the three ALs below
    ArrayList<ArrayList<int[]>> allLinesAL = new ArrayList<ArrayList<int[]>>();

    // An AL of Arrays set to accept 3 arrays.
    ArrayList<int[]> firstLineOfThree = new ArrayList<int[]>(3);
    ArrayList<int[]> secondLineOfThree = new ArrayList<int[]>(3);
    ArrayList<int[]> thirdLineOfThree = new ArrayList<int[]>(3);

    // Integer arrays which represent each row of the Sudoku Game.
    private int[] lineOne = {2, 9, 6, 3, 1, 8, 5, 7, 4};
    private int[] lineTwo = {5, 8, 4, 9, 7, 2, 6, 1, 3};
    private int[] lineThree = {7, 1, 3, 6, 4, 5, 2, 8, 9};
    private int[] lineFour = {6, 2, 5, 8, 9, 7, 3, 4, 1};
    private int[] lineFive = {9, 3, 1, 4, 2, 6, 8, 5, 7};
    private int[] lineSix = {4, 7, 8, 5, 3, 1, 9, 2, 6};
    private int[] lineSeven = {1, 6, 7, 2, 5, 3, 4, 9, 8};
    private int[] lineEight = {8, 5, 9, 7, 6, 4, 1, 3, 2};
    private int[] lineNine = {3, 4, 2, 1, 8, 9, 7, 6, 5};

    private int[][] finalBoard = new int[9][9];

    /**
     * {@code getFinalBoard} produces the a two-dimensional integer array, {@code finalBoard}, to produce
     * @return
     */
    public int[][] getFinalBoard(){
        return finalBoard;
    }


    // Constructs Sudoku Board
    public Board(){

        initializeLinesOfThrees(lineOne, lineTwo, lineThree, lineFour, lineFive, lineSix, lineSeven, lineEight,
                lineNine);

//        randomizeRows();

        initializeLinesOfThrees(allLinesAL);

//        randomizeColumns();

        createBoard();

        hideBoardValues();
    }

    /**
     * {@code constructLinesFromAL} consumes an ArrayList of arrays of ints, {@code al}, and an integer,
     * {@code rowCounter}, and adds it to the final 2d board.
     *
     * @param al The ArrayList with the arrays within it
     * @param rowCounter Additional information needed to determine which row should receive data
     */
    private void constructLinesFromAL(ArrayList<int[]> al, int rowCounter){
        for (int alIndex = 0; alIndex < 3; alIndex++){
            for (int i = 0; i < 9; i++){
                // Ensures that the elements are placed in the proper coordinates of the 2D Array
                finalBoard[rowCounter + alIndex][i] = al.get(alIndex)[i];
            }
        }
    }

    /**
     * {@code createBoard} creates a 2D array of integers after all randomization has been done to the pre-existing
     * board.
     *
     */
    private void createBoard() {
        constructLinesFromAL(firstLineOfThree, 0);
        constructLinesFromAL(secondLineOfThree, 3);
        constructLinesFromAL(thirdLineOfThree, 6);
    }

    /**
     * {@code randomizeRows} uses the three ArrayList of integer arrays, {@code firstLineOfThree},
     * {@code secondLineOfThree} and {@code thirdLineOfThree} and shuffles the integer arrays around while also not
     * breaking the sudoku game laws
     *
     */
    /*private void randomizeRows(){
        // Lines within the lineOfThree will be randomized.
        Collections.shuffle(firstLineOfThree);
        Collections.shuffle(secondLineOfThree);
        Collections.shuffle(thirdLineOfThree);

        // Need to rearrange the "cluster of 3 lines"
        allLinesAL.clear();

        allLinesAL.add(firstLineOfThree);
        allLinesAL.add(secondLineOfThree);
        allLinesAL.add(thirdLineOfThree);

        allLinesAL = collectionShuffle(allLinesAL);
    }*/

    // 0 Values will be placed on board which mimic and empty cell on traditional sudoku boards
    private void hideBoardValues(){
        // any algorithm for hiding the values

        for (int row = 0; row < 9; row++){
            int zeroesToMake = (int) (Math.random() * 3) + 3; // Produces a random int from 3-5.
            double rngTemp;
            double removeZeroChance;

            for (int col = 0; col < 9; col++){
                rngTemp = Math.random();
                removeZeroChance = (double) zeroesToMake / (double) (9 - col);
                // Percent chance determining if we should remove 0

                // Makes it so it's a probability if statement. Only occurs a percentage of the time.
                if (removeZeroChance <= rngTemp) {
                    zeroesToMake--;
                    finalBoard[row][col] = 0;
                }
            }
        }
    }

    /**
     * {@code randomizeColumns} does the same thing as {@code randomizeRows}, but first makes the columns into rows.
     *
     */
    /*private void randomizeColumns(){
        // First step is to make the columns into rows to utilize previous methods implemented.

        int[] lineOne = new int[9];
        int[] lineTwo = new int[9];
        int[] lineThree = new int[9];
        int[] lineFour = new int[9];
        int[] lineFive = new int[9];
        int[] lineSix = new int[9];
        int[] lineSeven = new int[9];
        int[] lineEight = new int[9];
        int[] lineNine = new int[9];

        for (int j = 0; j < 9; j++){
            // Sucessfully populates the integer array with the proper column values.
            System.out.println("");
            for (int i = 8; i >= 0; i--) {

                int innerALCounter;
                if ((i + 1) % 3 == 0) {
                    innerALCounter = 2;
                } else if ((i + 1) % 3 == 2) {
                    innerALCounter = 1;
                } else {
                    innerALCounter = 0;
                }


                int cellValue = allLinesAL.get(i/3).get(innerALCounter)[j];
                System.out.println("C VALUE: " + cellValue);
                // Obtains the jth int (from reg array) from the ith ArrayList

                // Add this to the normal array.
                switch (j) {
                    case 0:
                        lineOne [8 - i] = cellValue;
                        break;
                    case 1:
                        lineTwo [8 - i] = cellValue;
                        break;
                    case 2:
                        lineThree [8 - i] = cellValue;
                        break;
                    case 3:
                        lineFour [8 - i] = cellValue;
                        break;
                    case 4:
                        lineFive [8 - i] = cellValue;
                        break;
                    case 5:
                        lineSix [8 - i] = cellValue;
                        break;
                    case 6:
                        lineSeven [8 - i] = cellValue;
                        break;
                    case 7:
                        lineEight [8 - i] = cellValue;
                        break;
                    case 8:
                        lineNine [8 - i] = cellValue;
                        break;
                }
            }


        }
        initializeLinesOfThrees(lineOne, lineTwo, lineThree, lineFour, lineFive, lineSix, lineSeven, lineEight,
                lineNine);


//        randomizeRows();
    }*/
    /*private ArrayList collectionShuffle(ArrayList<ArrayList<int[]>> al){
        // Implementing Fisher-Yates shuffle
        for (int i = 0; i < al.size(); i++) {
            int index = (int) (Math.random() * al.size());

            ArrayList<int[]> temp = new ArrayList<int[]>();
            temp = al.get(i);

            al.set(i, al.get(index));
            al.set(index, temp);
        }
        return al;
    }*/

    private void initializeLinesOfThrees(int[] lineOne, int[] lineTwo, int[] lineThree, int[] lineFour, int[] lineFive,
                                         int[] lineSix, int[] lineSeven, int[] lineEight, int[] lineNine){

        firstLineOfThree.clear();
        secondLineOfThree.clear();
        thirdLineOfThree.clear();

        // Initialize all linesOfThrees
        firstLineOfThree.add(lineOne);
        firstLineOfThree.add(lineTwo);
        firstLineOfThree.add(lineThree);

        secondLineOfThree.add(lineFour);
        secondLineOfThree.add(lineFive);
        secondLineOfThree.add(lineSix);

        thirdLineOfThree.add(lineSeven);
        thirdLineOfThree.add(lineEight);
        thirdLineOfThree.add(lineNine);
    }


    private void initializeLinesOfThrees(ArrayList<ArrayList<int[]>> al){

        for (int i = 0; i < al.size(); i++ ){

            if (i == 0){
                firstLineOfThree = al.get(i);
            } else if (i == 1) {
                secondLineOfThree = al.get(i);
            } else {
                thirdLineOfThree = al.get(i);
            }
        }
    }
}