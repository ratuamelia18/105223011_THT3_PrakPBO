import java.util.Scanner;

class Karyawan {
    String id, nama, shift;
    int jamKerja, absen;

    public Karyawan(String idInput, String namaInput, String shiftInput, int jamKerjaInput, int absenInput) {
        id = idInput;
        nama = namaInput;
        shift = shiftInput;
        jamKerja = jamKerjaInput;
        absen = absenInput;
    }

    private double hitungGajiPerJam() {
        return switch (shift.toLowerCase()) {
            case "pagi" -> 350000;
            case "siang" -> 400000;
            case "malam" -> 450000;
            default -> 0;
        };
    }

    public void tampilkanGaji() {
        double gajiPerJam = hitungGajiPerJam();
        double gajiNormal = jamKerja * gajiPerJam;
        double lembur = (jamKerja > 40) ? (jamKerja - 40) * gajiPerJam * 1.5 : 0;
        double potongan = (jamKerja < 30) ? gajiNormal * 0.1 : 0;
        double potonganAbsen = absen * 100000;
        double totalGaji = gajiNormal + lembur - potongan - potonganAbsen;

        System.out.printf("ID: %s | Nama: %s | Shift: %s | Gaji: Rp%.2f\n", id, nama, shift, totalGaji);
    }
}

public class GajiKaryawan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan jumlah karyawan: ");
        int jumlahKaryawan = scanner.nextInt();
        scanner.nextLine();

        Karyawan[] karyawanList = new Karyawan[jumlahKaryawan];

        for (int i = 0; i < jumlahKaryawan; i++) {
            System.out.printf("\nData Karyawan ke-%d:\n", i + 1);
            System.out.print("ID: ");
            String id = scanner.nextLine();
            System.out.print("Nama: ");
            String nama = scanner.nextLine();
            System.out.print("Shift (pagi/siang/malam): ");
            String shift = scanner.nextLine();
            System.out.print("Jam kerja dalam seminggu: ");
            int jamKerja = scanner.nextInt();
            System.out.print("Hari absen: ");
            int absen = scanner.nextInt();
            scanner.nextLine();

            if (jamKerja < 0 || jamKerja > 168 || absen < 0 || absen > 7) {
                System.out.println("Data tidak valid. Harap masukkan dataulang.");
                i--;
                continue;
            }
            karyawanList[i] = new Karyawan(id, nama, shift, jamKerja, absen);
        }

        System.out.println("\n -- Laporan Gaji Karyawan --");
        for (Karyawan karyawan : karyawanList) {
            karyawan.tampilkanGaji();
        }
        scanner.close();
    }
}
