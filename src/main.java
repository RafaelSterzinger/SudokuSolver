public class main {
    public static int solutionCount = 0;

    public static void main(String[] args) {
        Sudoku test = new Sudoku("3xxx4xx1xx12x83xxxxx5xx1324x49xxxx8xxx78x54xxxx1x947x3xx845x9xx763xxxx4xxxx3x82x1",9);
        //Sudoku test = new Sudoku("xx76xx85x68xx5xx3x4xxx2xx6xxxxxxx3x4x6x472x8x5x2xxxxxxx2xx3xxx5x7xx9xx18x59xx16xx",9);
        //Sudoku test = new Sudoku("x6x4x21xx9xxxxxx8xxxxxxx34x37x5x9xxxxxx3x4xxxxxx1x6x39x42xxxxxxx1xxxxxx3xx39x8x6x",9);
        //Sudoku test = new Sudoku("x2637xxx93x75xx2xxxxxxxx3xxx4x76xxxx7x9xxx5xxx8xxxxx9xxx813xxxx2xxx45x7x97x6xx1x4",9);
        //Sudoku test = new Sudoku("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx4",9);
        System.out.println("Problem");
        System.out.println(test);
        System.out.println("Solutions");
        System.out.println(test.solver());
        System.out.println("Found " + solutionCount + " solutions");
    }
}
