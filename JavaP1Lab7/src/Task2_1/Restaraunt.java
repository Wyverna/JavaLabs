package Task2_1;

public class Restaraunt {
    private int amountOfTables;
    private Client[][] operators;

    public Restaraunt(int size) {
        this.amountOfTables = size;
        this.operators = new Client[size][2];
    }

    public void showLines() {
        System.out.println("Line:{");
        for (int i = 0; i < amountOfTables; i++) {
            for(int j=0;j<2;j++) {
                if (operators[i][j] != null) {
                    System.out.println(i + 1 + ") " + operators[i][j].getClientName());
                } else {
                    System.out.println(i + 1 + ") empty");
                }
            }
        }
        System.out.println("}");
    }

    public void connect(Client client) {
        operators[this.FreeOperator()][this.FreeOperator1()] = client;
    }

    public void disconnect(Client client) {
        for (int i = 0; i < this.amountOfTables; i++) {
            for(int j=0;j<2;j++) {
                if (operators[i][j] == client) {
                    operators[i][j] = null;
                }
            }
        }
    }

    public int FreeOperator() {
        for (int i = 0; i < this.amountOfTables; i++) {
            for(int j=0;j<2;j++) {
                if (operators[i][j] == null) {
                    return i;
                }
            }
        }
        return -1;
    }
    public int FreeOperator1() {
        for (int i = 0; i < this.amountOfTables; i++) {
            for(int j=0;j<2;j++) {
                if (operators[i][j] == null) {
                    return j;
                }
            }
        }
        return -1;
    }
}

