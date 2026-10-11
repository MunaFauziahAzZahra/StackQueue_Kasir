// Class Stack: riwayat transaksi (LIFO) berbasis linked list
public class Stack {
    private Node top;     // transaksi terbaru
    private int size;

    public Stack() {
        top = null;
        size = 0;
    }

    public boolean isEmpty() { return top == null; }
    public int getSize()     { return size; }

    // Push: simpan transaksi baru di atas stack
    public void push(String kode, String nama, double total) {
        Node baru = new Node(kode, nama, total);
        baru.next = top;
        top = baru;
        size++;
    }

    // Display: tampilkan dari transaksi terbaru ke terlama
    public void display() {
        if (isEmpty()) {
            System.out.println("Belum ada riwayat transaksi.");
            return;
        }
        System.out.println("Riwayat Transaksi (terbaru ke lama):");
        Node bantu = top;
        int no = 1;
        while (bantu != null) {
            System.out.println(no + ". " + bantu.kode + " | " + bantu.nama
                    + " | Rp" + (long) bantu.total);
            bantu = bantu.next;
            no++;
        }
        System.out.println("Total Transaksi: " + size);
    }
}
