package com.ffs.solution;


import com.badlogic.gdx.scenes.scene2d.ui.TextField;

/**
 * 解数独
 * @author fengfasong
 * @date 2020/8/29
 * 一个数独的解法需遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 */
public class Shudu {
    int n = 3;

    int N = n * n;
    /**
     * 记录每行，每列，每格是否出现某个数字
     */
    int [][] rows = new int[N][N + 1];
    int [][] columns = new int[N][N + 1];
    int [][] boxes = new int[N][N + 1];

    /**
     * 数独变化
     */
    char[][] board;

    TextField[][] textFields;


    public Shudu(TextField[][] textFields) {
        this.textFields = textFields;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public TextField[][] getTextFields() {
        return textFields;
    }

    public void setTextFields(TextField[][] textFields) {
        this.textFields = textFields;
    }

    /**
     * 该数独是否完成解读
     */
    boolean sudokuSolved = false;
    /**
     * 放数字d在row,col位置
     * @param d
     * @param row
     * @param col
     */
    public void placeNumber(int d, int row, int col){
        /**
         * 计算该点的索引位置，即9个大格子的索引位置
         */
        int idx = (row / n) * n + col / n;
        /**
         * row行出现了d，
         * col列出现了d，
         * idx格出现了d
         */
        rows[row][d]++;
        columns[col][d]++;
        boxes[idx][d]++;
        /**
         * 将数字d赋值给“棋盘”
         */
        board[row][col] = (char)(d+'0');
    }

    /**
     * 判断坐标 row,col能否放置数字d
     * @param d
     * @param row
     * @param col
     * @return
     */
    public boolean couldPlace(int d, int row, int col){
        int idx = (row / n) * n + col / n;
        /**
         * 如果行、列或者单元格出现数字d，则该行不能放值数字d
         * row,d:表示行row出现了数字d，若没有出现数字d，则[row][d]的值为0
         */
        return rows[row][d] + columns[col][d] + boxes[idx][d] == 0;
    }

    /**
     * 下一个放数字的位置的位置
     * @param row
     * @param col
     */
    public void placeNextNumbers(int row, int col){
        /**
         * 若当前位置是[8][8]，即最后一个位置，则不需要进行回溯，数独已经解决
         */
        if((row == N-1) && (col == N-1)){
            sudokuSolved = true;
        }else {
            /**
             * 到达最后一列，则行进行加一
             */
            if(col == N-1){
                backtrack(row + 1,0);
            }
            /**
             * 否则就是列进行加一
             */
            else {
                backtrack(row,col + 1);
            }
        }
    }

    /**
     * 数字移除
     * @param d
     * @param row
     * @param col
     */
    public void removeNumber(int d, int row, int col){
        int idx = (row / n) * n + col / n;
        /**
         * 将数字记录移除
         */
        rows[row][d]--;
        columns[col][d]--;
        boxes[idx][d]--;
        board[row][col] = '.';
    }

    /**
     * 回溯
     * @param row
     * @param col
     */
    public void backtrack(int row, int col){

        if(board[row][col] == '.'){
            /**
             * 开始对1-9的数字进行放置
             */
            for(int d = 1;d<10;d++){
                if(couldPlace(d,row,col)){
                    placeNumber(d,row,col);
                    placeNextNumbers(row,col);
                    /**
                     * 回溯点
                     */
                    if(!sudokuSolved){
                        removeNumber(d,row,col);
                    }
                }
            }
        }else {
            placeNextNumbers(row,col);
        }

    }

    public void solveSudoku(TextField[][] textFields){
       // this.board = board;
        this.textFields = textFields;

        /**
         * 首先记录原有数字
         */
        for(int i = 0;i<N;i++){
            for(int j = 0;j<N;j++){
                char num = textFields[i][j].getText().charAt(0);
                if(num != '.'){
                    int d = Character.getNumericValue(num);
                    placeNumber(d,i,j);
                }
            }
        }
        /**
         * 回溯
         */
        backtrack(0,0);
    }

   /* public static void main(String[] args) {
            char[][] ysd = {{'5','3','.','.','7','.','.','.','.'},
                    {'6','.','.','1','9','5','.','.','.'},
                    {'.','9','8','.','.','.','.','6','.'},
                    {'8','.','.','.','6','.','.','.','3'},
                    {'4','.','.','8','.','3','.','.','1'},
                    {'7','.','.','.','2','.','.','.','6'},
                    {'.','6','.','.','.','.','2','8','.'},
                    {'.','.','.','4','1','9','.','.','5'},
                    {'.','.','.','.','8','.','.','7','9'}};

            Shudu shudu = new Shudu(ysd);
            shudu.solveSudoku(ysd);
            char[][] board = shudu.getBoard();
        System.out.println(board.length);
        for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board[i].length;j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }*/

}
