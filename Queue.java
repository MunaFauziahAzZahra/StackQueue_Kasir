// Class Queue: antrian pelanggan (FIFO) berbasis linked list
public class Queue {
    private Node front;   // pelanggan paling depan (dilayani lebih dulu)
    private Node rear;    // pelanggan paling belakang
    private int size;
    private final int kapasitas;

    public Queue(int kapasitas) {
        this.kapasitas = kapasitas;
        front = null;
        rear = null;
        size = 0;
    }

    public boolean isEmpty() { return front == null; }
    public boolean isFull()  { return size >= kapasitas; }
    public int getSize()     { return size; }

    // Enqueue: tambah pelanggan di belakang antrian
    public boolean enqueue(String kode, String nama, double total) {
        if (isFull()) {
            return false;               // antrian penuh (maks. 5)
        }
        Node baru = new Node(kode, nama, total);
        if (isEmpty()) {
            front = baru;
        } else {
            rear.next = baru;
        }
        rear = baru;
        size++;
        return true;
    }

    // Dequeue: ambil pelanggan paling depan
    public Node dequeue() {
        if (isEmpty()) {
            return null;
        }
        Node layani = front;
        front = front.next;
        if (front == null) {            // antrian menjadi kosong
            rear = null;
        }
        layani.next = null;
        size--;
        return layani;
    }

    // Cek apakah nomor antrian sudah dipakai
    public boolean adaKode(String kode) {
        Node bantu = front;
        while (bantu != null) {
            if (bantu.kode.equalsIgnoreCase(kode)) return true;
            bantu = bantu.next;
        }
        return false;
    }

    // Tampilkan antrian dari depan ke belakang
    public void display() {
        if (isEmpty()) {
            System.out.println("Antrian kosong.");
            return;
        }
        System.out.println("Antrian saat ini (" + size + " pelanggan):");
        Node bantu = front;
        int no = 1;
        while (bantu != null) {
            System.out.println(no + ". " + bantu.kode + " | " + bantu.nama
                    + " | Rp" + (long) bantu.total);
            bantu = bantu.next;
            no++;
        }
    }
}
