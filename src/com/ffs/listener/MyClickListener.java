package com.ffs.listener;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * 点击监听
 * @author fengfasong
 * @date 2020/9/3
 */
public class MyClickListener extends ClickListener {

    int n = 3;

    int N = n * n;

    boolean sudokuSolved = false;
    /**
     * 记录每行，每列，每格是否出现某个数字
     */
    int [][] rows = new int[N][N + 1];
    int [][] columns = new int[N][N + 1];
    int [][] boxes = new int[N][N + 1];


    private int i;

    private int j;

    private TextField[][] textField;


    public MyClickListener(int i,int j,TextField[][] textField){
        this.i = i;
        this.j = j;
        this.textField = textField;
        for(int ii = 0;ii<N;ii++){
            for(int jj = 0;jj<N;jj++){
                //String num = textField[ii][jj].getText();
                if(!"".equals(textField[ii][jj].getText())){
                    int d = Integer.parseInt(textField[ii][jj].getText());
                    placeNumber(d,ii,jj);
                }
            }
        }
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        TextField textField = (TextField) event.getListenerActor();
        System.out.println(textField.getText());
        if("answer".equals(textField.getText())){
            backtrack(0,0);
            System.out.println(this.textField);
        }
        textField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                System.out.println("======="+c);
                if(Character.isDigit(c)){
                    boolean flag = couldPlace(Integer.parseInt(String.valueOf(c)),i,j);
                    placeNumber(Integer.parseInt(String.valueOf(c)),i,j);
                    System.out.println(i+"+++"+j);
                    if(!flag){
                        textField.setText("error");
                    }
                    System.out.println(flag);
                }

            }
        });

    }


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
        textField[row][col].setText(String.valueOf(d));
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

    public void backtrack(int row, int col){

        if("".equals(textField[row][col].getText())){
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
    public void removeNumber(int d, int row, int col){
        int idx = (row / n) * n + col / n;
        /**
         * 将数字记录移除
         */
        rows[row][d]--;
        columns[col][d]--;
        boxes[idx][d]--;
        textField[row][col].setText("");
    }

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

}
