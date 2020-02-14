public class Sudoku implements Cloneable {
    char[][] problem;
    int length;

    public Sudoku(String problem, int length) {
        this.length = length;
        this.problem = new char[length][length];
        int rowCount = 0;
        try {
            for (int i = 0; i < problem.length(); i++) {
                this.problem[rowCount][i % length] = problem.charAt(i);
                if ((i + 1) % length == 0) {
                    rowCount++;
                }
            }
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("problem mismatch");
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder verticial = new StringBuilder();
        for (int i = 0; i < length + length / 3 + 1; i++) {
            verticial.append('-');
        }
        verticial.append('\n');
        stringBuilder.append(verticial);
        for (int i = 0; i < problem.length; i++) {
            stringBuilder.append('|');
            for (int j = 0; j < problem[i].length; j++) {
                stringBuilder.append(problem[i][j]);
                if (j % 3 == 2) {
                    stringBuilder.append('|');
                }
            }
            stringBuilder.append('\n');
            if (i % 3 == 2) {
                stringBuilder.append(verticial);
            }
        }
        return stringBuilder.toString();
    }

    private boolean check(int y, int x) {

        if (x > length || y > length) {
            System.out.println("index mismatch");
        }
        char proof = this.problem[y][x];

        for (int i = 0; i < this.problem[y].length; i++) {
            if (i != x) {
                if (this.problem[y][i] == proof) {
                    return false;
                }
            }
        }

        for (int i = 0; i < this.problem.length; i++) {
            if (i != y) {
                if (this.problem[i][x] == proof) {
                    return false;
                }
            }
        }

        switch (x % 3) {
            case 0:
                switch (y % 3) {
                    case 0:
                        return boxChecker(x,x+3,y,y+3,x,y,proof);
                    case 1:
                        return boxChecker(x,x+3,y-1,y+2,x,y,proof);
                    case 2:
                        return boxChecker(x,x+3,y-2,y+1,x,y,proof);
                }
                break;
            case 1:
                switch (y % 3) {
                    case 0:
                        return boxChecker(x-1,x+2,y,y+3,x,y,proof);
                    case 1:
                        return boxChecker(x-1,x+2,y-1,y+2,x,y,proof);
                    case 2:
                        return boxChecker(x-1,x+2,y-2,y+1,x,y,proof);
                }
                break;
            case 2:
                switch (y % 3) {
                    case 0:
                        return boxChecker(x-2,x+1,y,y+3,x,y,proof);
                    case 1:
                        return boxChecker(x-2,x+1,y-1,y+2,x,y,proof);
                    case 2:
                        return boxChecker(x-2,x+1,y-2,y+1,x,y,proof);
                }
        }
        return false;
    }

    private boolean boxChecker(int xFrom, int xTo, int yFrom, int yTo, int x, int y, int proof){
        for (int i = xFrom; i < xTo; i++) {
            for (int j = yFrom; j < yTo; j++) {
                if (i!=x || j!=y){
                    if(this.problem[j][i]==proof)
                        return false;
                }
            }
        }
        return true;
    }

    public Sudoku solver(){
        int i = -1;
        while (this.problem[0][++i]!='x');

        for (int j = 1; j <= 9; j++) {
            try {
                Sudoku temp = (Sudoku)this.clone();
                temp.problem[0][i] = Character.forDigit(j,10);
                if (temp.check(0,i)) {
                    Sudoku solution = temp.rekursiv(0);
                    if (solution!=null){
                        return solution;
                    }
                }
            }
            catch (CloneNotSupportedException e){
                System.out.println("Error while cloning");
            }
        }

        return null;
    }

    private Sudoku rekursiv(int rowCount){
        int i = 0;
        while (this.problem[rowCount][i]!='x'){
            if (++i%length==0 && rowCount<length){
                i=0;
                rowCount++;
                if (rowCount==length){
                    System.out.println(this);
                    main.solutionCount++;
                    //return this;
                    return null;
                }
            }
        }

        for (int j = 1; j <= 9; j++) {
            try {
                Sudoku temp = (Sudoku)this.clone();
                temp.problem[rowCount][i] = Character.forDigit(j,10);
                if (temp.check(rowCount,i)) {
                    Sudoku solution = temp.rekursiv(rowCount);
                    if (solution!=null){
                        return solution;
                    }
                }
            }
            catch (CloneNotSupportedException e){
                System.out.println("Error while cloning");
            }
        }

        return null;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.problem.length; i++) {
            for (int j = 0; j < this.problem[i].length; j++) {
                stringBuilder.append(this.problem[i][j]);
            }
        }
        return new Sudoku(stringBuilder.toString(),this.length);
    }
}
