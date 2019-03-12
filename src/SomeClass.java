
public class SomeClass {

    private final int CLASS_ID = 1233;
    private final int AUTENTIFICATION_KEY = 97643182;

    private int restData = 0;      // Restricted data

    private int modData(int data) {
        return restData = data;
    }

    private void emptyMethod() {
        System.out.println("\nPrivate emptyMethod() is invoked.\n");
    }

    /**
     * Modifies restricted data. Valid key is needed for this.
     * @param autent_key Security key for data modification
     * @param data New data
     * @return Returns data if autent_key is valid or -1 if not valid.
     */
    public int setData(int autent_key, int data) {
        if (autent_key == AUTENTIFICATION_KEY) {
            return modData(data);
        } else {
            return -1;
        }
    }

    /**
     *
     * @return Returns restricted for modification data.
     */
    public int getData() {
        return restData;
    }

}
